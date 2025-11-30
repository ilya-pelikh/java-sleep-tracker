package ru.yandex.practicum.sleeptracker.functions;


import ru.yandex.practicum.sleeptracker.enums.TypeOfSleepSession;
import ru.yandex.practicum.sleeptracker.models.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.models.SleepingSession;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class DeterminateTypeOfPerson implements Function<SleepAnalysisResult, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(SleepAnalysisResult sleepingSessions) {
        List<SleepingSession> sleepingSessionList = sleepingSessions.getSessions();

        HashMap<String, Integer> mapOfSessions = new HashMap<>(Map.of(
                "Жаворонок", 0,
                "Сова", 0,
                "Голубь", 0
        ));


        String data = sleepingSessionList.stream()
                .filter(session -> session.getTypeOfSession() == TypeOfSleepSession.NIGHTLY)
                .reduce(mapOfSessions,
                (acc, session) -> {
                    LocalTime bedTime = session.getBedDateTime().toLocalTime();
                    LocalTime wakeUpTime = session.getWakeUpDateTime().toLocalTime();

                    if (bedTime.isBefore(LocalTime.of(22, 0)) && wakeUpTime.isBefore(LocalTime.of(7, 0))) {
                        acc.compute("Жаворонок", (k, v) -> v + 1);
                    } else if (bedTime.isAfter(LocalTime.of(23, 0)) && wakeUpTime.isAfter(LocalTime.of(9, 0))) {
                        acc.compute("Сова", (k, v) -> v + 1);
                    } else {
                        acc.compute("Голубь", (k, v) -> v + 1);
                    }
                    return  acc;
                },
                (map1, map2) -> map1)
                .entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse("Голубь");


        String analyze = String.format("Ваш хронотип: %s\n", data);
        String result = sleepingSessions.getResult() + analyze;
        return new SleepAnalysisResult(sleepingSessions.getSessions(), result);
    }
}
