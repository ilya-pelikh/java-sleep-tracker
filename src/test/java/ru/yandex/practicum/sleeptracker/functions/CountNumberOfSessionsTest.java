package ru.yandex.practicum.sleeptracker.functions;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.database.DatabaseController;
import ru.yandex.practicum.sleeptracker.models.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.models.SleepingSession;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CountNumberOfSessionsTest {
    @Test
    void countNumberOfSessions_shouldCountSleepingSessions() {
        List<SleepingSession> sleepingSessionList = new ArrayList<>();

        sleepingSessionList.add(DatabaseController.parseSleepingSession(("01.10.25 23:15;02.10.25 07:30;GOOD")));
        sleepingSessionList.add(DatabaseController.parseSleepingSession(("03.10.25 14:10;03.10.25 15:00;NORMAL")));

        SleepAnalysisResult initialResult = new SleepAnalysisResult(
                sleepingSessionList,
                ""
        );

        CountNumberOfSessions countFunction = new CountNumberOfSessions();

        SleepAnalysisResult result = countFunction.apply(initialResult);

        assertTrue(result.getResult().contains("Всего сессий сна: 2"));
    }

    @Test
    void countNumberOfSessions_shouldCountSleepingSessionsByZero() {
        List<SleepingSession> sleepingSessionList = new ArrayList<>();

        SleepAnalysisResult initialResult = new SleepAnalysisResult(
                sleepingSessionList,
                ""
        );

        CountNumberOfSessions countFunction = new CountNumberOfSessions();

        SleepAnalysisResult result = countFunction.apply(initialResult);

        assertTrue(result.getResult().contains("Всего сессий сна: 0"));
    }
}
