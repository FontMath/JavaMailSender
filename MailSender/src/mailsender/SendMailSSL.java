/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailsender;

import java.io.File;
import java.util.Properties;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendMailSSL {
	public static void main(String[] args) {
            
            System.setProperty("java.net.preferIPv6Stack", "true");

		final String username = "<TU CORREO>";
		final String password = "<TU CONTRASENA>";
                
		Properties props = new Properties();
		props.put("mail.smtp.host", "2607:f8b0:4003:c14::6d");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			//message.setRecipients(Message.RecipientType.CC, InternetAddress.parse("abc@abc.com,abc@def.com,ghi@abc.com"));
                        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("DESTINATARIO@EXAMPLE.COM,"+username));
                        
                        String msg = "<div style=\"color:red;\">BRIDGEYE</div>";
                        
			message.setSubject("Email con archivo");
                        
                        BodyPart messageBodyPart = new MimeBodyPart();
                        Multipart multipart = new MimeMultipart();
                        
                        messageBodyPart.setText("Adjunto un archivo de prueba\n\nSaludos,\nGBV.");
                        
                        String filename= "prueba.txt";
                        String path = "/"+filename;
                        DataSource source = new FileDataSource(path);
                        messageBodyPart.setDataHandler(new DataHandler(source));
                        messageBodyPart.setFileName(filename);
                        multipart.addBodyPart(messageBodyPart);
                        message.setContent(multipart);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}