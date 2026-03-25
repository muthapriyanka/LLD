package notificationsystem.entities;

public class NotificationSenderFactory {

    public NotificationSender getMessageSender(Channel channel) {
       switch(channel) {
        case EMAIL:
            return new EmailSender();
        case SMS:
            return new SMSSender();
        case PUSH:
            return new PushSender();
         default:
                throw new IllegalArgumentException("Unsupported channel: " + channel);
       }
    }
    
}
