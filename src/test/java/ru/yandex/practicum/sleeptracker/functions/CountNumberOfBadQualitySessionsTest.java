package ru.yandex.practicum.sleeptracker.functions;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.database.DatabaseController;
import ru.yandex.practicum.sleeptracker.models.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.models.SleepingSession;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CountNumberOfBadQualitySessionsTest {
    @Test
    void countNumberOfSessions_shouldCountSleepingSessions() {
        List<SleepingSession> sleepingSessionList = new ArrayList<>();

        sleepingSessionList.add(DatabaseController.parseSleepingSession("01.10.25 14:10;02.10.25 15:00;BAD"));
        sleepingSessionList.add(DatabaseController.parseSleepingSession("02.10.25 23:15;03.10.25 07:30;GOOD"));
        sleepingSessionList.add(DatabaseController.parseSleepingSession("03.10.25 14:10;04.10.25 15:00;BAD"));
        sleepingSessionList.add(DatabaseController.parseSleepingSession("04.10.25 14:10;05.10.25 15:00;NORMAL"));

        SleepAnalysisResult initialResult = new SleepAnalysisResult(
                sleepingSessionList,
                ""
        );

        CountNumberOfBadQualitySessions countFunction = new CountNumberOfBadQualitySessions();

        SleepAnalysisResult result = countFunction.apply(initialResult);

        assertTrue(result.getResult().contains("Количество сессий с плохим качеством сна: 2"));
    }

    @Test
    void countNumberOfSessions_shouldCountSleepingSessionsByZero() {
        List<SleepingSession> sleepingSessionList = new ArrayList<>();

        sleepingSessionList.add(DatabaseController.parseSleepingSession("02.10.25 23:15;03.10.25 07:30;GOOD"));
        sleepingSessionList.add(DatabaseController.parseSleepingSession("04.10.25 14:10;05.10.25 15:00;NORMAL"));

        SleepAnalysisResult initialResult = new SleepAnalysisResult(
                sleepingSessionList,
                ""
        );

        CountNumberOfBadQualitySessions countFunction = new CountNumberOfBadQualitySessions();

        SleepAnalysisResult result = countFunction.apply(initialResult);

        assertTrue(result.getResult().contains("Количество сессий с плохим качеством сна: 0"));
    }
}
