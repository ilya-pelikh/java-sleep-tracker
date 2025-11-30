package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.enums.TypeOfSleepSession;
import ru.yandex.practicum.sleeptracker.models.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.models.SleepingSession;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.function.Function;

public class CountSleeplessNightsOfSessions implements Function<SleepAnalysisResult, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(SleepAnalysisResult sleepingSessions) {
        List<SleepingSession> sleepingSessionList = sleepingSessions.getSessions();

        LocalDate startDate = sleepingSessionList.getFirst().getBedDateTime().toLocalDate();
        LocalDate endDate = sleepingSessionList.getLast().getWakeUpDateTime().toLocalDate();

        long countOfTrackingDays = Period.between(startDate, endDate).getDays();

        long countOfSleepingDays = sleepingSessionList
                .stream()
                .filter(session -> session.getTypeOfSession() == TypeOfSleepSession.NIGHTLY)
                .map(session -> session.getWakeUpDateTime().toLocalDate())
                .distinct()
                .count();

        long data = countOfTrackingDays - countOfSleepingDays;

        String analyze = String.format("Всего бессонных ночей: %s\n", data);
        String result = sleepingSessions.getResult() + analyze;
        return new SleepAnalysisResult(sleepingSessions.getSessions(), result);
    }
}
