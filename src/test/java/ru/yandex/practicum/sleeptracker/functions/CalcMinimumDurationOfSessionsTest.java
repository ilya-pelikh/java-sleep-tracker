package ru.yandex.practicum.sleeptracker.functions;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.database.DatabaseController;
import ru.yandex.practicum.sleeptracker.models.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.models.SleepingSession;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CalcMinimumDurationOfSessionsTest {
    @Test
    void calcMinimumDurationOfSessions_shouldCalcMinimumDurationOfSessions() {
        List<SleepingSession> sleepingSessionList = new ArrayList<>();

        sleepingSessionList.add(DatabaseController.parseSleepingSession("01.10.25 23:10;02.10.25 07:30;GOOD"));
        sleepingSessionList.add(DatabaseController.parseSleepingSession("02.10.25 23:15;03.10.25 07:30;GOOD"));
        sleepingSessionList.add(DatabaseController.parseSleepingSession("03.10.25 23:20;04.10.25 07:30;GOOD"));

        SleepAnalysisResult initialResult = new SleepAnalysisResult(
                sleepingSessionList,
                ""
        );

        CalcMinimumDurationOfSessions countFunction = new CalcMinimumDurationOfSessions();

        SleepAnalysisResult result = countFunction.apply(initialResult);

        assertTrue(result.getResult().contains("Минимальная продолжительность сессии сна: 490"));
    }

    @Test
    void calcMinimumDurationOfSessions_shouldCalcMinimumDurationOfSessionsByZero() {
        List<SleepingSession> sleepingSessionList = new ArrayList<>();

        SleepAnalysisResult initialResult = new SleepAnalysisResult(
                sleepingSessionList,
                ""
        );

        CalcMinimumDurationOfSessions countFunction = new CalcMinimumDurationOfSessions();

        SleepAnalysisResult result = countFunction.apply(initialResult);

        assertTrue(result.getResult().contains("Минимальная продолжительность сессии сна: 0"));
    }
}
