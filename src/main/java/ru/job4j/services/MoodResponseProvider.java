package ru.job4j.services;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MoodResponseProvider {
    private final Map<String, String> moodResponses;

    public MoodResponseProvider() {
        this.moodResponses = new HashMap<>();
        this.moodResponses.put("lost_sock", "Носки — это коварные создания. Но не волнуйся, второй обязательно найдётся!");
        this.moodResponses.put("cucumber", "Огурец тоже дело серьёзное! Главное, не мариноваться слишком долго.");
        this.moodResponses.put("dance_ready", "Супер! Танцуй, как будто никто не смотрит. Или, наоборот, как будто все смотрят!");
        this.moodResponses.put("need_coffee", "Кофе уже в пути! Осталось только подождать... И ещё немного подождать...");
        this.moodResponses.put("sleepy", "Пора на боковую! Даже супергерои отдыхают, ты не исключение.");
    }

    public String getResponse(String moodKey) {
        return moodResponses.getOrDefault(moodKey, "Настроение не распознано, но всё будет хорошо!");
    }
}
