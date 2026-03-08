package pharma.logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AuditLogger implements Logger {

    private final List<String> logs = new ArrayList<>();
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Override
    public void log(String message) {
        String ts = LocalDateTime.now().format(FORMATTER);
        String entry = "[AUDIT " + ts + "] " + message;
        logs.add(entry);
        System.out.println(entry);
    }

    public List<String> getLogs() {
        return new ArrayList<>(logs);
    }

    public void clear() {
        logs.clear();
    }
}
