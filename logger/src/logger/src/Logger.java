package logger.src;

import java.util.ArrayList;
import java.util.List;
import logger.src.appender.LogAppender;
import logger.src.entities.LogLevel;
import logger.src.entities.LogMessage;

public class Logger {

    private final String name;
    private LogLevel minLevel = LogLevel.DEBUG; // default threshold
    private final List<LogAppender> appenders = new ArrayList<>();
    private final Logger parent;

    public Logger(String name, Logger parent) {
        this.name = name;
        this.parent = parent;
    }

    public void setLevel(LogLevel minLevel) {
        if (minLevel != null) this.minLevel = minLevel;
    }

    public void addAppender(LogAppender appender) {
        appenders.add(appender);
    }

    public void log(LogLevel level, String message) {
        if (level == null || message == null) return;

        if (level.isGreaterOrEqual(minLevel)) {
            LogMessage logMessage = new LogMessage(level, name, message);

            for (LogAppender appender : appenders) {
                appender.append(logMessage);
            }
        }
    }

    // convenience methods
    public void debug(String msg) { log(LogLevel.DEBUG, msg); }
    public void info(String msg)  { log(LogLevel.INFO, msg); }
    public void warn(String msg)  { log(LogLevel.WARN, msg); }
    public void error(String msg) { log(LogLevel.ERROR, msg); }
    public void fatal(String msg) { log(LogLevel.FATAL, msg); }
}


//A logger is an object that prints messages about what your program is doing.
// System.out.println("User logged in");
// That is basic logging.

// But in real systems, we don’t use System.out.println().

// We use a Logger.

// If interviewer asks:

// Why use greater-or-equal for log levels?

// You say:

// Because log levels are ordered by severity, and the minimum level acts as a threshold. Messages with equal or higher severity should be logged.