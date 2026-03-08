package pharma.logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConsoleLogger implements Logger {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Override
    public void log(String message) {
        String ts = LocalDateTime.now().format(FORMATTER);
        System.out.println("[" + ts + "] " + message);
    }
}
