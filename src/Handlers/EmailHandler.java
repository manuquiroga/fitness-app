package Handlers;

import Users.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailHandler {

    private static final String SENDER_EMAIL = "nutribros.email@gmail.com";
    private static final String SENDER_APPLICATION_PASSWORD = "vzfhvkueoertdana";

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
            // Create a new MimeMessage object using the session
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

    public static String welcomeText(String name){
        return name + ", thank you for joining the Nutribros platform, any questions you may have can be reported to this email";
    }

    public static String forgotPasswordText(User user){
        return "Hello, "+ user.getName() +" your password is: "+ user.getPassword();
    }
}
