package ru.yandex.practicum.sleeptracker.functions;


import ru.yandex.practicum.sleeptracker.models.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.models.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class CountNumberOfSessions implements Function<SleepAnalysisResult, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(SleepAnalysisResult sleepingSessions) {
        List<SleepingSession> sleepingSessionList = sleepingSessions.getSessions();

        int data = sleepingSessionList.size();

        String analyze = String.format("Всего сессий сна: %s\n", data);
        String result = sleepingSessions.getResult() + analyze;
        return new SleepAnalysisResult(sleepingSessions.getSessions(), result);
    }
}
