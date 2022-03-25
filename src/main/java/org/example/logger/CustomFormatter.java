package org.example.logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class CustomFormatter extends Formatter {

    @Override
    public String format(LogRecord record) {
        return (record.getSourceClassName() + " " + record.getSourceMethodName() + " " + getTimeFormat() + " " + record.getLevel() + " " + record.getMessage() + "\n");
    }

    private String getTimeFormat() {
        return LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT));
    }
}
