package com.telcel.csv.services;

import java.net.MalformedURLException;
import javax.xml.ws.Service;
import java.net.URL;
import javax.xml.namespace.QName;
import com.telcel.rcontrol.services.remedy.generic.UpdateStatus;
import com.telcel.rcontrol.services.remedy.generic.InsertStatus;
import java.util.Iterator;
import com.telcel.rcontrol.services.remedy.generic.RemedyPort;
import com.telcel.rcontrol.services.remedy.generic.Field;
import com.telcel.rcontrol.services.remedy.generic.ListOfFields;
import com.telcel.sir.pojos.WorkOrder;
import java.util.List;
import com.infomedia.utils.PropertyLoader;
import java.util.Properties;
import org.apache.log4j.Logger;

public class RemedyControl
{
    private static final Logger log;
    Properties prop;
    private final String vsUsuarioRC;
    private final String vsFormularioRM;
    
    public RemedyControl() {
        this.prop = PropertyLoader.load("rutas.properties");
        this.vsUsuarioRC = this.prop.getProperty("USER_RM");
        this.vsFormularioRM = this.prop.getProperty("FORM_RM");
    }
    
    public boolean fncInsertRutinas(final List<WorkOrder> poListWO) {
        boolean vbStatus = false;
        try {
            final RemedyPort voRP = this.getPort(this.prop.getProperty("wsdlURL"));
            for (final WorkOrder ordenVO : poListWO) {
                final ListOfFields voFields = new ListOfFields();
                voFields.addField(new Field(1000000181, "10000"));
                voFields.addField(new Field(1000000063, "REQUERIMIENTO"));
                voFields.addField(new Field(1000000064, "ACCESO"));
                voFields.addField(new Field(1000000065, "BAJA CUENTA"));
                voFields.addField(new Field(1000001270, "SOFTWARE"));
                voFields.addField(new Field(1000001271, "APLICACION"));
                voFields.addField(new Field(1000001272, "ISE TACACS"));
                voFields.addField(new Field(1000000001, "TELCEL"));
                //GRUPO SOPORTE
                voFields.addField(new Field(1000000015, "CORP-TD-GESTOR DE REQUERIMIENTOS"));
                voFields.addField(new Field(1000003229, "CORP-OPMA-SOC"));
                
                
                voFields.addField(new Field(1000000000, ordenVO.getDes()));
                voFields.addField(new Field(1000000151, "Numero de Empleado: " + ordenVO.getNumEmp() + "\nUsuario Universal: " + ordenVO.getUsuario() + "\nNombre completo: " + ordenVO.getNombre()));
                try {
                    final InsertStatus localInsertStatus = voRP.rmdInsert(this.vsUsuarioRC, this.vsFormularioRM, voFields, (String)null);
                    ordenVO.setIdWO(localInsertStatus.getCreatedEntry());
                    final UpdateStatus vuStatus = voRP.rmdUpdate(this.vsUsuarioRC, this.vsFormularioRM, localInsertStatus.getCreatedEntry(), this.fncParciar("'1000003229'='CORP-OPMA-SOC'"));
                    if (!vuStatus.getStatus().contains("UPDATED")) {
                        continue;
                    }
                    vbStatus = true;
                }
                catch (Exception e) {
                    RemedyControl.log.error((Object)e);
                }
            }
        }
        catch (Exception e2) {
            RemedyControl.log.error((Object)e2);
        }
        return vbStatus;
    }
    
    private ListOfFields fncParciar(final String psValor) {
        final ListOfFields voFields = new ListOfFields();
        for (String vsFiel : psValor.split("' '")) {
            final Field voField = new Field();
            vsFiel = vsFiel.replaceAll("'", "");
            final String[] voAux = vsFiel.split("=");
            voField.setId(Integer.parseInt(voAux[0]));
            voField.setValue(voAux[1]);
            voFields.addField(voField);
        }
        return voFields;
    }
    
    private RemedyPort getPort(final String psEndPoint) throws MalformedURLException {
        final QName serviceQN = new QName(this.prop.getProperty("namespace"), this.prop.getProperty("serviceName"));
        final QName portQN = new QName(this.prop.getProperty("namespace"), this.prop.getProperty("portName"));
        final Service service = Service.create(new URL(psEndPoint), serviceQN);
        return (RemedyPort)service.getPort(portQN, (Class)RemedyPort.class);
    }
    
    static {
        log = Logger.getLogger((Class)RemedyControl.class);
    }
}