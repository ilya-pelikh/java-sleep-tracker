package ru.yandex.practicum.sleeptracker.functions;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.database.DatabaseController;
import ru.yandex.practicum.sleeptracker.models.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.models.SleepingSession;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class DeterminateTypeOfPersonTest {
    @Test
    void determinateTypeOfPersonTest_shouldDeterminateTypeOfPersonAsDjavoronok() {
        List<SleepingSession> sleepingSessionList = new ArrayList<>();
        sleepingSessionList.add(DatabaseController.parseSleepingSession("01.10.25 21:30;02.10.25 06:39;GOOD"));

        SleepAnalysisResult initialResult = new SleepAnalysisResult(
                sleepingSessionList,
                ""
        );

        DeterminateTypeOfPerson countFunction = new DeterminateTypeOfPerson();

        SleepAnalysisResult result = countFunction.apply(initialResult);

        assertTrue(result.getResult().contains("Ваш хронотип: Жаворонок"));
    }

    @Test
    void determinateTypeOfPersonTest_shouldDeterminateTypeOfPersonAsSova() {
        List<SleepingSession> sleepingSessionList = new ArrayList<>();
        sleepingSessionList.add(DatabaseController.parseSleepingSession("01.10.25 23:30;02.10.25 09:30;GOOD"));

        SleepAnalysisResult initialResult = new SleepAnalysisResult(
                sleepingSessionList,
                ""
        );

        DeterminateTypeOfPerson countFunction = new DeterminateTypeOfPerson();

        SleepAnalysisResult result = countFunction.apply(initialResult);

        assertTrue(result.getResult().contains("Ваш хронотип: Сова"));
    }

    @Test
    void determinateTypeOfPersonTest_shouldDeterminateTypeOfPersonAsGolub() {
        List<SleepingSession> sleepingSessionList = new ArrayList<>();
        sleepingSessionList.add(DatabaseController.parseSleepingSession("01.10.25 22:30;02.10.25 09:30;GOOD"));

        SleepAnalysisResult initialResult = new SleepAnalysisResult(
                sleepingSessionList,
                ""
        );

        DeterminateTypeOfPerson countFunction = new DeterminateTypeOfPerson();

        SleepAnalysisResult result = countFunction.apply(initialResult);

        assertTrue(result.getResult().contains("Ваш хронотип: Голубь"));
    }

    @Test
    void determinateTypeOfPersonTest_shouldDeterminateTypeOfPersonAsDjavoronokFromMix() {
        List<SleepingSession> sleepingSessionList = new ArrayList<>();
        sleepingSessionList.add(DatabaseController.parseSleepingSession("01.10.25 21:30;02.10.25 06:39;GOOD"));
        sleepingSessionList.add(DatabaseController.parseSleepingSession("02.10.25 23:30;03.10.25 09:30;GOOD"));
        sleepingSessionList.add(DatabaseController.parseSleepingSession("03.10.25 22:30;04.10.25 09:30;GOOD"));
        sleepingSessionList.add(DatabaseController.parseSleepingSession("04.10.25 21:30;05.10.25 06:39;GOOD"));

        SleepAnalysisResult initialResult = new SleepAnalysisResult(
                sleepingSessionList,
                ""
        );

        DeterminateTypeOfPerson countFunction = new DeterminateTypeOfPerson();

        SleepAnalysisResult result = countFunction.apply(initialResult);

        assertTrue(result.getResult().contains("Ваш хронотип: Жаворонок"));
    }

    @Test
    void determinateTypeOfPersonTest_shouldDeterminateTypeOfPersonAsSovaFromMix() {
        List<SleepingSession> sleepingSessionList = new ArrayList<>();
        sleepingSessionList.add(DatabaseController.parseSleepingSession("01.10.25 21:30;02.10.25 06:39;GOOD"));
        sleepingSessionList.add(DatabaseController.parseSleepingSession("02.10.25 23:30;03.10.25 09:30;GOOD"));
        sleepingSessionList.add(DatabaseController.parseSleepingSession("03.10.25 22:30;04.10.25 09:30;GOOD"));
        sleepingSessionList.add(DatabaseController.parseSleepingSession("04.10.25 23:30;05.10.25 09:30;GOOD"));

        SleepAnalysisResult initialResult = new SleepAnalysisResult(
                sleepingSessionList,
                ""
        );

        DeterminateTypeOfPerson countFunction = new DeterminateTypeOfPerson();

        SleepAnalysisResult result = countFunction.apply(initialResult);

        assertTrue(result.getResult().contains("Ваш хронотип: Сова"));
    }

    @Test
    void determinateTypeOfPersonTest_shouldDeterminateTypeOfPersonAsGolubFromMix() {
        List<SleepingSession> sleepingSessionList = new ArrayList<>();
        sleepingSessionList.add(DatabaseController.parseSleepingSession("01.10.25 21:30;02.10.25 06:39;GOOD"));
        sleepingSessionList.add(DatabaseController.parseSleepingSession("02.10.25 23:30;03.10.25 09:30;GOOD"));
        sleepingSessionList.add(DatabaseController.parseSleepingSession("03.10.25 22:30;04.10.25 09:30;GOOD"));
        sleepingSessionList.add(DatabaseController.parseSleepingSession("04.10.25 22:30;05.10.25 09:30;GOOD"));

        SleepAnalysisResult initialResult = new SleepAnalysisResult(
                sleepingSessionList,
                ""
        );

        DeterminateTypeOfPerson countFunction = new DeterminateTypeOfPerson();

        SleepAnalysisResult result = countFunction.apply(initialResult);

        assertTrue(result.getResult().contains("Ваш хронотип: Голубь"));
    }
}
