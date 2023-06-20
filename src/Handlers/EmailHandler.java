package Handlers;

import Users.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * The EmailHandler class provides functionality for sending emails using the SMTP protocol.
 * @see <a href=https://www.geeksforgeeks.org/send-email-using-java-program/> </a>
 */
public class EmailHandler {

    private static final String SENDER_EMAIL = "nutribros.email@gmail.com";
    private static final String SENDER_APPLICATION_PASSWORD = "vzfhvkueoertdana";

    /**
     * Sends an email to the specified recipient with the given subject and body content.
     * @param recipient the email address of the recipient
     * @param subject the subject of the email
     * @param body the body content of the email
     */
    public static void send(String recipient, String subject, String body){

        //Email server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        //Authentication
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_EMAIL, SENDER_APPLICATION_PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);

            // Set the sender
            message.setFrom(new InternetAddress(SENDER_EMAIL));

            // Set the recipient
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));

            // Set the subject
            message.setSubject(subject);

            // Set the body content of the email
            message.setText(body);

            // Send the message
            Transport.send(message);

            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            System.out.println("Error sending the email: " + e.getMessage());
        }
    }
    /**
     * Generates the welcome text for a new user.
     * @param name The name of the user.
     * @return The welcome text.
     */
    public static String welcomeText(String name){
        return name + ", thank you for joining the Nutribros platform, any questions you may have can be reported to this email";
    }

    /**
     * Generates the text for a forgot password email.
     * @param user The user for whom the email is being generated.
     * @return The forgot password text.
     */
    public static String forgotPasswordText(User user){
        return "Hello, "+ user.getName() +" your password is: "+ user.getPassword();
    }
}
