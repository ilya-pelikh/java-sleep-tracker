package ru.yandex.practicum.sleeptracker.database;

import ru.yandex.practicum.sleeptracker.database.exceptions.DateTimeConvertError;
import ru.yandex.practicum.sleeptracker.enums.RatesOfSleep;
import ru.yandex.practicum.sleeptracker.models.SleepingSession;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DatabaseController {
    public static final DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    private final Charset charset;
    private final Database database;


    public DatabaseController() {
        this.charset = StandardCharsets.UTF_8;
        database = new Database();
    }

    public void load(String path) throws IOException {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(path, charset))) {
            while (fileReader.ready()) {
                String line = fileReader.readLine().trim();
                SleepingSession session = parseSleepingSession(line);
                database.addSleepingSession(session);
            }
        }
    }

    public static SleepingSession parseSleepingSession(String note) throws DateTimeConvertError {
        List<String> data = List.of(note.split(";"));
        LocalDateTime bedDateTime = LocalDateTime.parse(data.get(0), pattern);
        LocalDateTime wakeUpDateTime = LocalDateTime.parse(data.get(1), pattern);
        RatesOfSleep rate = RatesOfSleep.valueOf(data.get(2));

       return new SleepingSession(bedDateTime, wakeUpDateTime, rate);
    }

    public Database getDatabase() {
        return database;
    }
}
