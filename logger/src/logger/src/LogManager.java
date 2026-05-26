package logger.src;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import logger.src.entities.LogLevel;
    
public final class LogManager {
    
        private static LogManager INSTANCE;
    
        private final Map<String, Logger> loggers = new ConcurrentHashMap<>();
        private final Logger rootLogger;
    
        private LogManager() {
            rootLogger = new Logger("root", null);
            rootLogger.setLevel(LogLevel.INFO);   // default root level
            loggers.put("root", rootLogger);
        }
    
        public static LogManager getInstance() {
            if(INSTANCE == null) {
                INSTANCE = new LogManager();
            }
            return INSTANCE;
        }
    
        public Logger getRootLogger() {
            return rootLogger;
        }
    
        public Logger getLogger(String name) {
            if (name == null || name.isBlank()) return rootLogger;
            if (name.equals("root")) return rootLogger;
    
            // computeIfAbsent ensures "one logger per name"
            return loggers.computeIfAbsent(name, this::createLogger);
        }
    
        // Creates logger + attaches correct parent based on dots
        private Logger createLogger(String name) {
            String parentName = parentNameOf(name);
            Logger parent = getLogger(parentName);   // ensures parent exists
            return new Logger(name, parent);
        }
    
        private String parentNameOf(String name) {
            int lastDot = name.lastIndexOf('.');
            return (lastDot == -1) ? "root" : name.substring(0, lastDot);
        }
    }


