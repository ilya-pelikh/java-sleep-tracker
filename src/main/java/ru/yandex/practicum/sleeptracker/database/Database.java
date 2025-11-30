package ru.yandex.practicum.sleeptracker.database;

import ru.yandex.practicum.sleeptracker.models.SleepingSession;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private final List<SleepingSession> data;

    Database() {
        data = new ArrayList<>();
    }

    public void addSleepingSession(SleepingSession session) {
        data.add(session);
    }

    public List<SleepingSession> getData() {
        return data;
    }
}
