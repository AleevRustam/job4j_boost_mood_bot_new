package ru.job4j.services;

import org.junit.jupiter.api.Test;
import ru.job4j.content.Content;
import ru.job4j.model.Mood;
import ru.job4j.model.MoodLog;
import ru.job4j.model.User;
import ru.job4j.repositories.MoodFakeRepository;
import ru.job4j.repositories.MoodLogFakeRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class ReminderServiceTest {

    @Test
    public void whenMoodGood() {
        var result = new ArrayList<Content>();
        var sentContent = new SentContent() {
            @Override
            public void sent(Content content) {
                result.add(content);
            }
        };
        var moodRepository = new MoodFakeRepository();
        moodRepository.save(new Mood("Good", true));
        var moodLogRepository = new MoodLogFakeRepository();
        var user = new User();
        user.setChatId(100);
        var moodLog = new MoodLog();
        moodLog.setUser(user);
        var today = LocalDate.now()
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli();
        moodLog.setCreatedAt(today);
        moodLogRepository.save(moodLog);
        var tgUI = new TgUI(moodRepository);
        new ReminderService(sentContent, moodLogRepository, tgUI)
                .remindUsers();
        assertThat(result.iterator().next().getMarkup().getKeyboard()
                .iterator().next().iterator().next().getText()).isEqualTo("Good");
    }

    @Test
    public void whenMoodBad() {
        var result = new ArrayList<Content>();
        var sentContent = new SentContent() {
            @Override
            public void sent(Content content) {
                result.add(content);
            }
        };
        var moodRepository = new MoodFakeRepository();
        moodRepository.save(new Mood("Bad", false));
        var moodLogRepository = new MoodLogFakeRepository();
        var user = new User();
        user.setChatId(100);
        var moodLog = new MoodLog();
        moodLog.setUser(user);
        var today = LocalDate.now()
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli();
        moodLog.setCreatedAt(today);
        moodLogRepository.save(moodLog);
        var tgUI = new TgUI(moodRepository);
        new ReminderService(sentContent, moodLogRepository, tgUI)
                .remindUsers();
        assertThat(result.iterator().next().getMarkup().getKeyboard()
                .iterator().next().iterator().next().getText()).isEqualTo("Bad");
    }
}