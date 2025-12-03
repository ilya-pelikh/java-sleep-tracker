package ru.yandex.practicum.sleeptracker.database.exceptions;

public class DateTimeConvertError extends RuntimeException {
    public DateTimeConvertError() {
        super("Невозможно сконвертировать данные");
    }
}
