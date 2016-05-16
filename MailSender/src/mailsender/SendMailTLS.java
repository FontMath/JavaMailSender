
package mailsender;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//Author: FontMath
//Github: https://github.com/FontMath/JavaMailSender

public class SendMailTLS {

    public static void main(String[] args) {

        System.setProperty("java.net.preferIPv6Stack", "true");

        final String username = "gerardo.mathus94@gmail.com";
        final String password = "We<3Quagga";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "2607:f8b0:4003:c14::6d");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
             @Override
             protected PasswordAuthentication getPasswordAuthentication() {
             return new PasswordAuthentication(username, password);
             }   
         });
        try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse("gerardo.mathus@me.com"));
                message.setSubject("HTML Message");

                //MENSAJE CON HTML
                /*message.setContent("<h1>Mail Message </h1> Dear Mail Crawler,<br><br> No <b>spam</b> to my email, please!"
                        , "text/html; charset=utf-8");*/

                //MENSAJE SIN HTML
                message.setText("Dear Mail Crawler,\n\n No spam to my email, please!");

                Transport.send(message);

                System.out.println("Done");

        } catch (MessagingException e) {
                throw new RuntimeException(e);
        }
    }
}
