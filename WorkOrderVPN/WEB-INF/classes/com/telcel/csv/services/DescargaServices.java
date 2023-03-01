package com.telcel.csv.services;

import com.infomedia.utils.PropertyLoader;
import com.csvreader.CsvReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import com.telcel.sir.pojos.WorkOrder;
import java.util.List;
import javax.mail.MessagingException;
import java.util.logging.Level;
import java.io.File;
import org.apache.log4j.Logger;
import java.util.Properties;

public class DescargaServices
{
    public static final Properties vpProp;
    private static final Logger log;
    private String vsRutaLocal;
    
    public DescargaServices() {
        this.vsRutaLocal = DescargaServices.vpProp.getProperty("RUTA_LOCAL");
    }
    
    public boolean fncControl() {
        System.out.println("Inicio");
        final boolean vbRes = false;
        String file4 = "";
        final RemedyControl voRControl = new RemedyControl();
        final Correos co = new Correos();
        final File folder = new File(this.vsRutaLocal);
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.getName().endsWith(".csv")) {
                System.out.println(fileEntry.getName());
                file4 = this.vsRutaLocal + fileEntry.getName();
                final List<WorkOrder> poWorkOrders = this.fncLeerWorkOrders(file4);
                voRControl.fncInsertRutinas(poWorkOrders);
                try {
                    co.fncEnviarCorreo(poWorkOrders);
                }
                catch (MessagingException ex) {
                    java.util.logging.Logger.getLogger(DescargaServices.class.getName()).log(Level.SEVERE, null, (Throwable)ex);
                }
                fileEntry.getAbsoluteFile().renameTo(new File(fileEntry.getAbsolutePath() + "_"));
            }
        }
        System.out.println("Completed");
        return vbRes;
    }
    
    public String fncFecha() {
        final SimpleDateFormat voFormat = new SimpleDateFormat("ddMMyyyy");
        final Calendar voCalendario = new GregorianCalendar();
        voCalendario.add(6, 0);
        final String fecha = voFormat.format(voCalendario.getTime());
        return fecha;
    }
    
    public List<WorkOrder> fncLeerWorkOrders(final String psRuta) {
        final List<WorkOrder> voWorkOrders = new ArrayList<WorkOrder>();
        CsvReader voLector = null;
        try {
            voLector = new CsvReader(psRuta);
            voLector.readHeaders();
            while (voLector.readRecord()) {
                if (!voLector.get(0).isEmpty()) {
                    final WorkOrder voWorkOrder = new WorkOrder();
                    voWorkOrder.setNumEmp(voLector.get(0));
                    voWorkOrder.setUsuario(voLector.get(1));
                    voWorkOrder.setNombre(voLector.get(2));
                    voWorkOrder.setDes(voLector.get(3));
                    voWorkOrders.add(voWorkOrder);
                }
            }
            voLector.close();
        }
        catch (Exception e) {
            DescargaServices.log.error((Object)e);
        }
        finally {
            if (voLector != null) {
                voLector.close();
            }
        }
        return voWorkOrders;
    }
    
    static {
        vpProp = PropertyLoader.load("rutas.properties");
        log = Logger.getLogger((Class)DescargaServices.class);
    }
}