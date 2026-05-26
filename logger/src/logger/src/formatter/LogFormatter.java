package logger.src.formatter;
import logger.src.entities.LogMessage;

public interface LogFormatter {
    String format(LogMessage logMessage);
}