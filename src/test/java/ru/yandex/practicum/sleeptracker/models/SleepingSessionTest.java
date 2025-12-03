package ru.yandex.practicum.sleeptracker.models;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.enums.RatesOfSleep;
import ru.yandex.practicum.sleeptracker.enums.TypesOfSleepSession;

import java.time.LocalDateTime;
import java.time.Month;
import static org.junit.jupiter.api.Assertions.*;


public class SleepingSessionTest {
    @Test
    void getDuration_shouldReturnCorrectDuration() {
        LocalDateTime bedDateTime = LocalDateTime.of(2025, Month.DECEMBER, 30, 12, 0);
        LocalDateTime wakeUpDateTime = LocalDateTime.of(2025, Month.DECEMBER, 31, 0, 0);
        RatesOfSleep rate = RatesOfSleep.GOOD;
        SleepingSession sleepingSession = new SleepingSession(bedDateTime,wakeUpDateTime,rate);

        assertEquals(12, sleepingSession.getDuration().toHours());
    }

    @Test
    void getTypeOfSession_shouldReturnNightlyOfLeftEdge() {
        LocalDateTime bedDateTime = LocalDateTime.of(2025, Month.DECEMBER, 30, 23, 30);
        LocalDateTime wakeUpDateTime = LocalDateTime.of(2025, Month.DECEMBER, 31, 5, 30);
        RatesOfSleep rate = RatesOfSleep.GOOD;
        SleepingSession sleepingSession = new SleepingSession(bedDateTime,wakeUpDateTime,rate);

        assertEquals(TypesOfSleepSession.NIGHTLY, sleepingSession.getTypeOfSession());
    }

    @Test
    void getTypeOfSession_shouldReturnNightlyOfRightEdge() {
        LocalDateTime bedDateTime = LocalDateTime.of(2025, Month.DECEMBER, 30, 0, 30);
        LocalDateTime wakeUpDateTime = LocalDateTime.of(2025, Month.DECEMBER, 30, 6, 30);
        RatesOfSleep rate = RatesOfSleep.GOOD;
        SleepingSession sleepingSession = new SleepingSession(bedDateTime,wakeUpDateTime,rate);

        assertEquals(TypesOfSleepSession.NIGHTLY, sleepingSession.getTypeOfSession());
    }

    @Test
    void getTypeOfSession_shouldReturnNightlyOfDuration() {
        LocalDateTime bedDateTime = LocalDateTime.of(2025, Month.DECEMBER, 30, 0, 10);
        LocalDateTime wakeUpDateTime = LocalDateTime.of(2025, Month.DECEMBER, 30, 7, 0);
        RatesOfSleep rate = RatesOfSleep.GOOD;
        SleepingSession sleepingSession = new SleepingSession(bedDateTime,wakeUpDateTime,rate);

        assertEquals(TypesOfSleepSession.NIGHTLY, sleepingSession.getTypeOfSession());
    }

    @Test
    void getTypeOfSession_shouldReturnDaily() {
        LocalDateTime bedDateTime = LocalDateTime.of(2025, Month.DECEMBER, 30, 6, 10);
        LocalDateTime wakeUpDateTime = LocalDateTime.of(2025, Month.DECEMBER, 30, 6, 30);
        RatesOfSleep rate = RatesOfSleep.GOOD;
        SleepingSession sleepingSession = new SleepingSession(bedDateTime,wakeUpDateTime,rate);

        assertEquals(TypesOfSleepSession.DAILY, sleepingSession.getTypeOfSession());
    }
}


