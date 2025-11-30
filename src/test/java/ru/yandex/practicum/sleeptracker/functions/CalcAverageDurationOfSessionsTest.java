package ru.yandex.practicum.sleeptracker.functions;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.models.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.models.SleepingSession;
import ru.yandex.practicum.sleeptracker.database.DatabaseController;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class CalcAverageDurationOfSessionsTest {
    @Test
    void calcAverageDurationOfSessions_shouldCalcAverageDurationOfSessions() {
        List<SleepingSession> sleepingSessionList = new ArrayList<>();

        sleepingSessionList.add(DatabaseController.parseSleepingSession("01.10.25 23:10;02.10.25 07:30;GOOD"));
        sleepingSessionList.add(DatabaseController.parseSleepingSession("02.10.25 23:15;03.10.25 07:30;GOOD"));
        sleepingSessionList.add(DatabaseController.parseSleepingSession("03.10.25 23:20;04.10.25 07:30;GOOD"));

        SleepAnalysisResult initialResult = new SleepAnalysisResult(
                sleepingSessionList,
                ""
        );

        CalcAverageDurationOfSessions countFunction = new CalcAverageDurationOfSessions();

        SleepAnalysisResult result = countFunction.apply(initialResult);

        assertTrue(result.getResult().contains("Средняя продолжительность сессии сна: 495"));
    }

    @Test
    void calcAverageDurationOfSessions_shouldCalcAverageDurationOfSessionsByZero() {
        List<SleepingSession> sleepingSessionList = new ArrayList<>();

        SleepAnalysisResult initialResult = new SleepAnalysisResult(
                sleepingSessionList,
                ""
        );

        CalcAverageDurationOfSessions countFunction = new CalcAverageDurationOfSessions();

        SleepAnalysisResult result = countFunction.apply(initialResult);

        assertTrue(result.getResult().contains("Средняя продолжительность сессии сна: 0"));
    }
}
