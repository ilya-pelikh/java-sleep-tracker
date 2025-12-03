package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.database.DatabaseController;
import ru.yandex.practicum.sleeptracker.functions.*;
import ru.yandex.practicum.sleeptracker.models.SleepAnalysisResult;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;

public class SleepTrackerApp {

    public static List<Function<SleepAnalysisResult, SleepAnalysisResult>> functionList = List.of(
            new CountNumberOfSessions(),
            new CalcMinimumDurationOfSessions(),
            new CalcMaximumDurationOfSessions(),
            new CalcAverageDurationOfSessions(),
            new CountNumberOfBadQualitySessions(),
            new CountSleeplessNightsOfSessions(),
            new DeterminateTypeOfPerson()
    );

    public static void main(String[] args) {
        DatabaseController db = new DatabaseController();

        try {
            db.load(args[0]);
        } catch (IOException e) {
            System.out.println("Не удалось загрузить словарь. Выход");
            System.exit(1);
        }

        SleepAnalysisResult initialResult = new SleepAnalysisResult(
                db.getDatabase().getData(),
                "Аналитика сна: \n"
        );


        SleepAnalysisResult finalResult = functionList.stream()
                .reduce(initialResult,
                        (result, func) -> func.apply(result),
                        (r1, r2) -> r2);

        System.out.println(finalResult.getResult());
    }
}