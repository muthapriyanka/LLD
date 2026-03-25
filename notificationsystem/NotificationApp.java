package notificationsystem;
import notificationsystem.entities.Channel;
import notificationsystem.entities.NotificationMessage;
import notificationsystem.entities.User;

public class NotificationApp {
    public static void main(String[] args) {
        NotificationDispatcher dispatcher = new NotificationDispatcher();
        User user1 = new User("Priyanka", "pmutha27@gmail.com", "444444444444");
        NotificationMessage message = new NotificationMessage("this is the demo");
        dispatcher.send(user1, message, Channel.EMAIL);
        dispatcher.send(user1 , message, Channel.SMS);
        dispatcher.send(user1,message, Channel.PUSH);
    }
}
