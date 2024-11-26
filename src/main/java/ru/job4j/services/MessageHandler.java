package ru.job4j.services;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.job4j.model.User;
import ru.job4j.repositories.UserRepository;

@Component
public class MessageHandler {
    private final UserRepository userRepository;
    private final TgUI tgUI;
    private final MoodResponseProvider moodResponseProvider;

    public MessageHandler(UserRepository userRepository, TgUI tgUI, MoodResponseProvider moodResponseProvider) {
        this.userRepository = userRepository;
        this.tgUI = tgUI;
        this.moodResponseProvider = moodResponseProvider;
    }

    public SendMessage handleStartMessage(Long chatId, Long clientId) {
        User user = new User();
        user.setClientId(clientId);
        user.setChatId(chatId);
        userRepository.save(user);

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Как настроение сегодня?");
        message.setReplyMarkup(tgUI.buildButtons());
        return message;
    }

    public SendMessage handleMoodResponse(Long chatId, String moodKey) {
        String response = moodResponseProvider.getResponse(moodKey);

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(response);
        return message;
    }
}
