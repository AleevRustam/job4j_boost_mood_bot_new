package ru.job4j.services;

import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.job4j.model.Mood;
import ru.job4j.repositories.MoodFakeRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TgUITest {

    @Test
    public void whenBuildButtonsThenContainsGoodButton() {

        var moodRepository = new MoodFakeRepository();
        moodRepository.save(new Mood("Good", true));
        moodRepository.save(new Mood("Bad", true));

        var tgUI = new TgUI(moodRepository);

        var inlineKeyboardMarkup = tgUI.buildButtons();

        List<List<InlineKeyboardButton>> keyboard = inlineKeyboardMarkup.getKeyboard();

        boolean goodButtonExists = keyboard.stream()
                .flatMap(List::stream)
                .anyMatch(button -> "Good".equals(button.getText()));

        assertThat(goodButtonExists).isTrue();
    }

}