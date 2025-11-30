package ru.yandex.practicum.sleeptracker.models;

import ru.yandex.practicum.sleeptracker.enums.TypeOfSleepSession;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class SleepingSession {
    private final LocalDateTime bedDateTime;
    private final LocalDateTime wakeUpDateTime;
    private final String rate;

    public SleepingSession(LocalDateTime bedDateTime, LocalDateTime wakeUpDateTime, String rate) {
        this.bedDateTime = bedDateTime;
        this.wakeUpDateTime = wakeUpDateTime;
        this.rate = rate;
    }

    public LocalDateTime getBedDateTime() {
        return bedDateTime;
    }

    public LocalDateTime getWakeUpDateTime() {
        return wakeUpDateTime;
    }

    public String getRate() {
        return rate;
    }

    public Duration getDuration() {
        return Duration.between(bedDateTime, wakeUpDateTime);
    }

    public TypeOfSleepSession getTypeOfSession() {
        LocalTime nightStart = LocalTime.of(0, 0);
        LocalTime nightEnd = LocalTime.of(6, 0);

        LocalTime bedTime = bedDateTime.toLocalTime();
        LocalTime wakeUpTime = wakeUpDateTime.toLocalTime();

        boolean isInsideOfDay = bedDateTime.toLocalDate().isEqual(wakeUpDateTime.toLocalDate());
        boolean isCrossedLeftEdge = bedTime.isBefore(nightEnd) && !bedTime.isBefore(nightStart);
        boolean isCrossedRightEdge = wakeUpTime.isAfter(nightStart) && wakeUpTime.isBefore(nightEnd);

        if (isCrossedLeftEdge || isCrossedRightEdge || !isInsideOfDay) {
            return TypeOfSleepSession.NIGHTLY;
        }

        return TypeOfSleepSession.DAILY;
    }
}
