# Logger System

This is a low-level design implementation of a simple logging framework.

The goal of a logger is to record what an application is doing without using
raw `System.out.println()` everywhere. A logger gives us filtering by severity,
multiple output destinations, and configurable message formatting.

## Core Ideas

### Log Level

`LogLevel` represents severity.

```text
DEBUG < INFO < WARN < ERROR < FATAL
```

Each level has a numeric priority:

```java
DEBUG(1), INFO(2), WARN(3), ERROR(4), FATAL(5)
```

This allows filtering:

```java
messageLevel.isGreaterOrEqual(configuredLevel)
```

If the logger is configured at `WARN`, then only `WARN`, `ERROR`, and `FATAL`
messages should be logged.

### Log Message

`LogMessage` represents one log event.

It stores:

```text
timestamp
level
loggerName
message
```

Instead of passing raw strings everywhere, the logger wraps details into one
object.

### Logger

`Logger` is the main object used by application code.

It is responsible for:

```text
checking log level
creating LogMessage
sending LogMessage to appenders
providing helper methods like debug(), info(), warn(), error(), fatal()
```

Example:

```java
logger.info("User logged in");
logger.error("Payment failed");
```

### Log Manager

`LogManager` is responsible for creating and caching loggers.

It ensures:

```text
same logger name returns same Logger object
root logger exists
child loggers can have a parent based on dot-separated names
```

Example:

```java
Logger paymentLogger = manager.getLogger("com.app.payment");
```

Calling this again returns the same logger instance.

### Appender

`LogAppender` decides where the log should go.

Examples:

```text
ConsoleAppender -> prints logs to console
FileAppender    -> writes logs to a file
```

This is useful because the logger should not care about the destination. It
only creates log messages. The appender handles output.

### Formatter

`LogFormatter` decides how a log message should look.

Example format from `SimpleTextFormatter`:

```text
2026-06-07 10:15:30.123 [INFO] com.app.payment - Payment started
```

This is useful because output style can change without changing logger or
appender logic.

## Current Flow

```text
Application
    |
    v
Logger.info("message")
    |
    v
Logger checks LogLevel threshold
    |
    v
Logger creates LogMessage
    |
    v
Logger sends LogMessage to each LogAppender
    |
    v
Appender uses LogFormatter
    |
    v
Log is printed or written
```

## File Responsibilities

```text
Logger.java
Main logging class. Filters messages by level and sends them to appenders.

LogManager.java
Singleton manager that creates and caches loggers by name.

LoggerDemo.java
Demo class showing logger creation, level filtering, caching, and appenders.

entities/LogLevel.java
Enum representing log severity and comparison logic.

entities/LogMessage.java
Data object representing one log event.

appender/LogAppender.java
Interface for all log destinations.

appender/ConsoleAppender.java
Writes formatted logs to console.

appender/FileAppender.java
Writes formatted logs to a file.

formatter/LogFormatter.java
Interface for formatting log messages.

formatter/SimpleTextFormatter.java
Formats logs as timestamp, level, logger name, and message.
```

## Why This Design Is Useful

This design separates responsibilities:

```text
Logger     -> when to log
Appender   -> where to log
Formatter  -> how logs look
LogManager -> how loggers are created/reused
```

Because of this separation, we can add new features easily:

```text
DatabaseAppender
JsonFormatter
AsyncAppender
RemoteServiceAppender
```

without changing the core `Logger` logic.

## Interview Notes

For a 30-40 minute LLD interview, explain:

```text
1. Application gets a Logger from LogManager.
2. Logger has a minimum LogLevel threshold.
3. Logger creates LogMessage only if message level is high enough.
4. Logger passes LogMessage to appenders.
5. Appenders use formatters before writing output.
```

Good design patterns to mention:

```text
Singleton -> LogManager
Strategy  -> LogAppender and LogFormatter
```

Current extension point:

```text
Logger has a parent field, but full parent propagation/additivity is not fully
implemented yet. A future improvement would be to pass log messages to parent
appenders when child logger additivity is enabled.
```
