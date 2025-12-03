package ru.yandex.practicum.sleeptracker.functions;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.database.DatabaseController;
import ru.yandex.practicum.sleeptracker.models.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.models.SleepingSession;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CountSleeplessNightsOfSessionsTest {
    @Test
    void countSleeplessNightsOfSessions_shouldCountSleeplessNightsOfSessions() {
        List<SleepingSession> sleepingSessionList = new ArrayList<>();
        sleepingSessionList.add(DatabaseController.parseSleepingSession("01.10.25 23:15;02.10.25 07:30;GOOD"));
        sleepingSessionList.add(DatabaseController.parseSleepingSession("02.10.25 11:00;02.10.25 12:00;GOOD"));
        sleepingSessionList.add(DatabaseController.parseSleepingSession("03.10.25 11:10;03.10.25 20:00;NORMAL"));
        sleepingSessionList.add(DatabaseController.parseSleepingSession("04.10.25 07:10;04.10.25 18:00;BAD"));
        sleepingSessionList.add(DatabaseController.parseSleepingSession("04.10.25 23:10;05.10.25 07:00;NORMAL"));

        SleepAnalysisResult initialResult = new SleepAnalysisResult(
                sleepingSessionList,
                ""
        );

        CountSleeplessNightsOfSessions countFunction = new CountSleeplessNightsOfSessions();

        SleepAnalysisResult result = countFunction.apply(initialResult);

        assertTrue(result.getResult().contains("Всего бессонных ночей: 2"));
    }

    @Test
    void countSleeplessNightsOfSessions_shouldCountSleeplessNightsOfSessionsByAllSleepnessNights() {
        List<SleepingSession> sleepingSessionList = new ArrayList<>();
        sleepingSessionList.add(DatabaseController.parseSleepingSession("01.10.25 07:00;01.10.25 20:00;GOOD"));
        sleepingSessionList.add(DatabaseController.parseSleepingSession("02.10.25 08:00;02.10.25 21:00;GOOD"));
        sleepingSessionList.add(DatabaseController.parseSleepingSession("03.10.25 09:00;03.10.25 22:00;NORMAL"));

        SleepAnalysisResult initialResult = new SleepAnalysisResult(
                sleepingSessionList,
                ""
        );

        CountSleeplessNightsOfSessions countFunction = new CountSleeplessNightsOfSessions();

        SleepAnalysisResult result = countFunction.apply(initialResult);

        assertTrue(result.getResult().contains("Всего бессонных ночей: 2"));
    }

    @Test
    void countSleeplessNightsOfSessions_shouldCountSleeplessNightsOfSessionsByAllSleepNights() {
        List<SleepingSession> sleepingSessionList = new ArrayList<>();
        sleepingSessionList.add(DatabaseController.parseSleepingSession("01.10.25 23:00;02.10.25 07:00;GOOD"));
        sleepingSessionList.add(DatabaseController.parseSleepingSession("02.10.25 23:00;03.10.25 07:00;GOOD"));
        sleepingSessionList.add(DatabaseController.parseSleepingSession("03.10.25 23:00;04.10.25 07:00;NORMAL"));

        SleepAnalysisResult initialResult = new SleepAnalysisResult(
                sleepingSessionList,
                ""
        );

        CountSleeplessNightsOfSessions countFunction = new CountSleeplessNightsOfSessions();

        SleepAnalysisResult result = countFunction.apply(initialResult);

        assertTrue(result.getResult().contains("Всего бессонных ночей: 0"));
    }
}
