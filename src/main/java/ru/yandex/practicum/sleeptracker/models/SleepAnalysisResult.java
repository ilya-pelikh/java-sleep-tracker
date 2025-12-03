package ru.yandex.practicum.sleeptracker.models;

import java.util.List;

public class SleepAnalysisResult {
    private final List<SleepingSession> sessions;
    private final String result;

    public SleepAnalysisResult(List<SleepingSession> sessions, String result) {
        this.sessions = sessions;
        this.result = result;
    }

    public List<SleepingSession> getSessions() {
        return sessions;
    }

    public String getResult() {
        return result;
    }
}