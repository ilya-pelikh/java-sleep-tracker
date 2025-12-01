package ru.yandex.practicum.sleeptracker.functions;


import ru.yandex.practicum.sleeptracker.enums.Chronotypes;
import ru.yandex.practicum.sleeptracker.enums.TypesOfSleepSession;
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

        HashMap<Chronotypes, Integer> mapOfSessions = new HashMap<>(Map.of(
                Chronotypes.LARK, 0,
                Chronotypes.OWL, 0,
                Chronotypes.PIGEON, 0
        ));


        Chronotypes data = sleepingSessionList.stream()
                .filter(session -> session.getTypeOfSession() == TypesOfSleepSession.NIGHTLY)
                .reduce(mapOfSessions,
                (acc, session) -> {
                    LocalTime bedTime = session.getBedDateTime().toLocalTime();
                    LocalTime wakeUpTime = session.getWakeUpDateTime().toLocalTime();

                    if (bedTime.isBefore(LocalTime.of(22, 0)) && wakeUpTime.isBefore(LocalTime.of(7, 0))) {
                        acc.compute(Chronotypes.LARK, (k, v) -> v + 1);
                    } else if (bedTime.isAfter(LocalTime.of(23, 0)) && wakeUpTime.isAfter(LocalTime.of(9, 0))) {
                        acc.compute(Chronotypes.OWL, (k, v) -> v + 1);
                    } else {
                        acc.compute(Chronotypes.PIGEON, (k, v) -> v + 1);
                    }
                    return  acc;
                },
                (map1, map2) -> map1)
                .entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(Chronotypes.PIGEON);


        String analyze = String.format("Ваш хронотип: %s\n", data.getName());
        String result = sleepingSessions.getResult() + analyze;
        return new SleepAnalysisResult(sleepingSessions.getSessions(), result);
    }
}
