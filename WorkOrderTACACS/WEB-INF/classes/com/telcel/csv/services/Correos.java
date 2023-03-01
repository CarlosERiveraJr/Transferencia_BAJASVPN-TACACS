package com.telcel.csv.services;

import javax.mail.MessagingException;
import javax.mail.Transport;
import java.util.Iterator;
import javax.mail.internet.AddressException;
import javax.mail.Message;
import javax.mail.Address;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.telcel.sir.pojos.WorkOrder;
import java.util.List;
import javax.mail.PasswordAuthentication;
import javax.mail.Authenticator;
import com.infomedia.utils.PropertyLoader;
import org.apache.log4j.Logger;
import javax.mail.Session;
import java.util.Properties;

public class Correos
{
    Properties voPropertiesproperties;
    Session veEmailSession;
    Properties prop;
    private String vsCliente1;
    private String vsCliente2;
    private String vsCliente3;
    private String vsCliente4;
    private String vsCliente5;
    private String vsCliente6;
    private String vsCliente7;
    private String vsCliente8;
    private String vsCliente9;
    private String vsCliente10;
    
   
   
    public final Logger LOG;
    
    public Correos() {
        this.prop = PropertyLoader.load("rutas.properties");
        this.vsCliente1 = this.prop.getProperty("CLIENTE1");
        this.vsCliente2 = this.prop.getProperty("CLIENTE2");
        this.vsCliente3 = this.prop.getProperty("CLIENTE3");
        this.vsCliente4 = this.prop.getProperty("CLIENTE4");
        this.vsCliente5 = this.prop.getProperty("CLIENTE5");
        this.vsCliente6 = this.prop.getProperty("CLIENTE6");
        this.vsCliente7 = this.prop.getProperty("CLIENTE7");
        this.vsCliente8 = this.prop.getProperty("CLIENTE8");
        this.vsCliente9 = this.prop.getProperty("CLIENTE9");
        this.vsCliente10 = this.prop.getProperty("CLIENTE10");
        
       
        this.LOG = Logger.getLogger((Class)DescargaServices.class);
    }
    
    private void init() {
        (this.voPropertiesproperties = new Properties()).put("mail.host", this.prop.getProperty("HOST_MAIL"));
        this.voPropertiesproperties.put("mail.user", this.prop.getProperty("USER_MAIL"));
        this.voPropertiesproperties.put("mail.transport.protocol", "smtp");
        this.voPropertiesproperties.put("mail.smtp.sendpartial", "true");
        this.voPropertiesproperties.put("mail.debug", "true");
        (this.veEmailSession = Session.getInstance(this.voPropertiesproperties, (Authenticator)new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Correos.this.prop.getProperty("USER_MAIL"), Correos.this.prop.getProperty("G3l4t#n4"));
            }
        })).setDebug(true);
    }
    
    public void fncEnviarCorreo(final List<WorkOrder> poListWO) throws MessagingException {
        try {
            if (!this.vsCliente1.isEmpty() || this.vsCliente1 != null || !this.vsCliente1.equals("NA")) {
                this.init();
                final MimeMessage voMensaje = new MimeMessage(this.veEmailSession);
                voMensaje.setFrom((Address)new InternetAddress("remedycontrol.amx@mail.telcel.com"));
                voMensaje.addRecipient(Message.RecipientType.TO, (Address)new InternetAddress(this.vsCliente1));
                voMensaje.addRecipient(Message.RecipientType.TO, (Address)new InternetAddress(this.vsCliente2));
                voMensaje.addRecipient(Message.RecipientType.TO, (Address)new InternetAddress(this.vsCliente3));
                voMensaje.addRecipient(Message.RecipientType.TO, (Address)new InternetAddress(this.vsCliente4));
                voMensaje.addRecipient(Message.RecipientType.TO, (Address)new InternetAddress(this.vsCliente5));
                voMensaje.addRecipient(Message.RecipientType.TO, (Address)new InternetAddress(this.vsCliente6));
                voMensaje.addRecipient(Message.RecipientType.TO, (Address)new InternetAddress(this.vsCliente7));
                voMensaje.addRecipient(Message.RecipientType.TO, (Address)new InternetAddress(this.vsCliente8));
                voMensaje.addRecipient(Message.RecipientType.TO, (Address)new InternetAddress(this.vsCliente9));
                voMensaje.addRecipient(Message.RecipientType.TO, (Address)new InternetAddress(this.vsCliente10));
               
                voMensaje.setSubject(this.prop.getProperty("MOTIVO_MAIL"));
                String bajas = "\n\n";
                for (final WorkOrder ordenVO : poListWO) {
                    bajas = bajas + "Numero de empleado: " + ordenVO.getNumEmp() + "\n";
                    bajas = bajas + "Usuario universal: " + ordenVO.getUsuario() + "\n";
                    bajas = bajas + "Nombre: " + ordenVO.getNombre() + "\n";
                    bajas = bajas + "Work Order: " + ordenVO.getIdWO() + "\n\n";
                }
                voMensaje.setText(this.prop.getProperty("MENSAJE_MAIL") + bajas);
                final Transport voTransport = this.veEmailSession.getTransport("smtp");
                voTransport.connect();
                voTransport.sendMessage((Message)voMensaje, voMensaje.getAllRecipients());
                voTransport.close();
            }
        }
        catch (AddressException ex) {
            this.LOG.error((Object)("ERROR AL ENVIAR EL CORREO: " + ex));
        }
    }
}