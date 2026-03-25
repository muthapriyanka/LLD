package notificationsystem.entities;

public class SMSSender implements NotificationSender{
     @Override

    public void send(User user, NotificationMessage message) {
        System.out.println("Sending sms to " + user.getPhoneNumber());
        System.out.println("Body: " + message.getMessage());
    }
}
