package ru.yandex.practicum.sleeptracker.functions;


import ru.yandex.practicum.sleeptracker.models.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.models.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class CalcMinimumDurationOfSessions implements Function<SleepAnalysisResult, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(SleepAnalysisResult sleepingSessions) {
        List<SleepingSession> sleepingSessionList = sleepingSessions.getSessions();

        long data = sleepingSessionList.stream()
                .mapToLong(s -> s.getDuration().toMinutes())
                .min()
                .orElse(0);

        String analyze = String.format("Минимальная продолжительность сессии сна: %s\n", data);
        String result = sleepingSessions.getResult() + analyze;
        return new SleepAnalysisResult(sleepingSessions.getSessions(), result);
    }
}
