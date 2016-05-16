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

//Author: FontMath
//Github: https://github.com/FontMath/JavaMailSender

public class SendMailSSL {
    public static void main(String[] args) {

        System.setProperty("java.net.preferIPv6Stack", "true");

        final String username = "gerardo.mathus94@gmail.com";
        final String password = "We<3Quagga";

        Properties props = new Properties();
        props.put("mail.smtp.host", "2607:f8b0:4003:c14::6d");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
            }
        });

        try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                //message.setRecipients(Message.RecipientType.CC, InternetAddress.parse("abc@abc.com,abc@def.com,ghi@abc.com"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("gerardo.mathus@me.com,"+username));

                String msg = "<div style=\"color:red;\">BRIDGEYE</div>";

                message.setSubject("Email con archivo");

                BodyPart messageBodyPart1 = new MimeBodyPart();
                BodyPart messageBodyPart2 = new MimeBodyPart();
                Multipart multipart = new MimeMultipart();

                messageBodyPart1.setText("Adjunto un archivo de prueba\n\nSaludos,\nGMGS.\n\n\n\n");

                String path = "prueba.txt";
                DataSource source = new FileDataSource(path);
                messageBodyPart2.setDataHandler(new DataHandler(source));
                messageBodyPart2.setFileName(path);
                multipart.addBodyPart(messageBodyPart1);
                multipart.addBodyPart(messageBodyPart2);
                message.setContent(multipart);

                Transport.send(message);

                System.out.println("Done");

        } catch (MessagingException e) {
                throw new RuntimeException(e);
        }
    }
}