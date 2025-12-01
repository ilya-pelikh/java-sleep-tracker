package ru.yandex.practicum.sleeptracker.functions;


import ru.yandex.practicum.sleeptracker.enums.RatesOfSleep;
import ru.yandex.practicum.sleeptracker.models.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.models.SleepingSession;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class CountNumberOfBadQualitySessions implements Function<SleepAnalysisResult, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(SleepAnalysisResult sleepingSessions) {
        List<SleepingSession> sleepingSessionList = sleepingSessions.getSessions();

        long data = sleepingSessionList.stream()
                .filter(s -> Objects.equals(s.getRate(), RatesOfSleep.BAD))
                .count();

        String analyze = String.format("Количество сессий с плохим качеством сна: %s\n", data);
        String result = sleepingSessions.getResult() + analyze;
        return new SleepAnalysisResult(sleepingSessions.getSessions(), result);
    }
}
