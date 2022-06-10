package gmail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaAppSentMail {
    public static void sendMail(String recepient) throws Exception {
        System.out.println("Se trimite..");
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

        String username = "mihaimalairau0@gmail.com";
        String password = "www123ww";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        Message message = createMessages(session, username, recepient);

        Transport.send(message);
        System.out.println("Mesaj trimis cu succes!");
    }

    private static Message createMessages(Session session, String myAccountEmail, String recepient) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Cazare camin");
            message.setText("Salut, \n Completeaza cererea pina pe 1 mai.");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(JavaAppSentMail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}


