package gmail;

import com.sun.mail.pop3.POP3Store;

import javax.mail.*;
import java.io.IOException;
import java.util.Properties;

public class ReceiveMail {

    public static void receiveEmail(String pop3Host, String storeType,
                                    String username, String password) {
        try {
            //1)creeam obiectul sesiune
            Properties properties = new Properties();
            properties.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            properties.put("mail.pop3.socketFactory.fallback", "false");
            properties.put("mail.pop3.socketFactory.port", 995);
            properties.put("mail.pop3.port", 995);
            properties.put("mail.pop3.username", username);
            properties.put("mail.store.protocol", "pop3");
            properties.put("mail.pop3.ssl.protocols", "TLSv1.2");
            properties.put("mail.pop3.host", pop3Host);
            properties.put("mail.pop3.ssl.enable", true);
            properties.put("mail.pop3.ssl.trust", "*");


            Session emailSession = Session.getDefaultInstance(properties);

            //2) cream obiectul store POP3 și ne conectam la serverul pop
            POP3Store emailStore = (POP3Store) emailSession.getStore(storeType);
            emailStore.connect(username, password);

            //3) cream obiectul folder și il deschidem
            Folder emailFolder = emailStore.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            //4) preluam mesajele din folder într-o matrice și le imprimam
            Message[] messages = emailFolder.getMessages();
            for (int i = 0; i < messages.length; i++) {
                Message message = messages[i];
                System.out.println("*************************************");
                System.out.println("No. " + (i + 1));
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);
                System.out.println("Text: " + message.getContent().toString());
            }

            emailFolder.close(false);
            emailStore.close();

        } catch (IOException | MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        String username = "mihaimalairau0@gmail.com";
        String password = "www123ww";
        String host = "pop.gmail.com";
        String mailStoreType = "pop3";

        receiveEmail(host, mailStoreType, username, password);

    }
}  