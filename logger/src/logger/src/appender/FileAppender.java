package logger.src.appender;

import java.io.FileWriter;
import java.io.IOException;

import logger.src.entities.LogMessage;
import logger.src.formatter.*;

public class FileAppender implements LogAppender {
    private FileWriter writer; // FileWriter is a Java class used to: Write character data (text) into a file.
    private LogFormatter formatter;

    public FileAppender(String filePath) {
        this.formatter = new SimpleTextFormatter();
        try {
            this.writer = new FileWriter(filePath, true); // Append mode
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void append(LogMessage logMessage) {
        try {
            String formattedMessage = formatter.format(logMessage);
            writer.write(formattedMessage);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();    
        }
    }
     @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            System.out.println("Failed to close logs file, exception: " + e.getMessage());
        }
    }

    @Override
    public void setFormatter(LogFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public LogFormatter getFormatter() {
        return formatter;
    }
}
