package ru.yandex.practicum.sleeptracker.functions;


import ru.yandex.practicum.sleeptracker.models.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.models.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class CalcAverageDurationOfSessions implements Function<SleepAnalysisResult, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(SleepAnalysisResult sleepingSessions) {
        List<SleepingSession> sleepingSessionList = sleepingSessions.getSessions();

        double data = sleepingSessionList.stream()
                .mapToLong(s -> s.getDuration().toMinutes())
                .average()
                .orElse(0);

        String analyze = String.format("Средняя продолжительность сессии сна: %s\n", (int) data);
        String result = sleepingSessions.getResult() + analyze;
        return new SleepAnalysisResult(sleepingSessions.getSessions(), result);
    }
}
