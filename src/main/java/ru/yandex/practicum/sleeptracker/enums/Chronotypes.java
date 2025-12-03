package ru.yandex.practicum.sleeptracker.enums;

public enum Chronotypes {
    LARK("Жаворонок"),    // Заменено ';' на ','
    OWL("Сова"),
    PIGEON("Голубь");

    private final String name;

    Chronotypes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
