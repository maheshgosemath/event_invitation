import javax.activation.CommandMap;
import javax.activation.DataHandler;
import javax.activation.MailcapCommandMap;
import javax.activation.MimetypesFileTypeMap;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Properties;

/**
 * Created by mahesh on 1/20/17.
 */
public class MailSender {

    /*
    @param inviteMessage contains message for the event
    @param emails contains list of emails involved in event
     */
    public void sendMail(String inviteMessage, List<String> emails) {
        try {
            Properties prop = new Properties();
            Session session = Session.getDefaultInstance(prop, null);

            //headers for calendar invite
            MimeMessage message = new MimeMessage(session);
            message.addHeaderLine("method=REQUEST");
            message.addHeaderLine("charset=UTF-8");
            message.addHeaderLine("component=VEVENT");

            message.setFrom(new InternetAddress("abc@xyz.com"));
            message.setSubject("Interview Request");

            for(String email: emails) {
                message.addRecipients(Message.RecipientType.TO, email);
            }

            BodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setContent("Invitation for an event.", "text/plain");

            //invite's calendar part
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.addHeader("Content-Class", "urn:content-classes:calendarmessage");
            messageBodyPart.addHeader("Content-ID", "calendar_message");
            messageBodyPart.setContent(inviteMessage, "text/calendar");

            Multipart multipart = new MimeMultipart("alternative");
            multipart.addBodyPart(textBodyPart);
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);

            Transport transport = session.getTransport ("smtp");
            transport.connect("mail.server.address","email-id","password");
            transport.sendMessage(message, message.getAllRecipients ());
            transport.close ();
        } catch (MessagingException me) {
            me.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
