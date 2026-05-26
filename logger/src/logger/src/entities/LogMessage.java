package logger.src.entities;

import java.time.LocalDateTime;

public class LogMessage {
    private final LocalDateTime timestamp;
    private final LogLevel level;
    private final String loggerName;
    private final String message;

    public LogMessage(LogLevel level, String loggerName, String message) {
        this.timestamp = LocalDateTime.now();
        this.level = level;
        this.loggerName = loggerName;
        this.message = message;
    }

    // Getters for all fields
    public LocalDateTime getTimestamp() { return timestamp; }
    public LogLevel getLevel() { return level; }
    public String getLoggerName() { return loggerName; }
    public String getMessage() { return message; }
}

//Encapsulates the details of a log event.