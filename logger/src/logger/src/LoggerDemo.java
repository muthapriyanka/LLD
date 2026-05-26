package logger.src;

import logger.src.appender.ConsoleAppender;
import logger.src.appender.LogAppender;
import logger.src.entities.LogLevel;

public class LoggerDemo {

    public static void main(String[] args) {

        LogManager manager = LogManager.getInstance();

        Logger root = manager.getRootLogger();
        root.setLevel(LogLevel.INFO);
        LogAppender consoleAppender =  new ConsoleAppender();
        root.addAppender(consoleAppender);

        Logger paymentLogger = manager.getLogger("com.app.payment");

        System.out.println("---- Testing Level Filtering ----");

        paymentLogger.debug("Debug message");   // Should NOT print (root level INFO)
        paymentLogger.info("Info message");     // Should print
        paymentLogger.error("Error message");   // Should print

        System.out.println("\n---- Testing Logger Caching ----");

        Logger again = manager.getLogger("com.app.payment");

        System.out.println("Same instance? " + (paymentLogger == again));

        System.out.println("\n---- Testing Additivity ----");

        // Add extra appender only to child
        paymentLogger.addAppender(new ConsoleAppender());

        paymentLogger.warn("Warning with two appenders");

        System.out.println("\n---- Testing Child Level Override ----");

        paymentLogger.setLevel(LogLevel.ERROR);

        paymentLogger.info("This should NOT print");
        paymentLogger.error("This should print");
    }
}
