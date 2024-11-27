package ru.job4j.services;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.job4j.content.Content;
import ru.job4j.model.User;
import ru.job4j.repositories.UserRepository;

import java.util.Optional;

@Service
public class BotCommandHandler implements BeanNameAware {

    private String beanName;

    void receive(Content content) {
        System.out.println(content);
    }

    @PostConstruct
    public void init() {

        System.out.println("Bean is going through @PostConstruct init.");
    }

    @PreDestroy
    public void destroy() {

        System.out.println("Bean will be destroyed via @PreDestroy.");
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
        System.out.println("Bean beanName from BeanNameAware: " + this.beanName);
    }

    private final UserRepository userRepository;
    private final MoodService moodService;
    private final TgUI tgUI;

    public BotCommandHandler(UserRepository userRepository,
                             MoodService moodService,
                             TgUI tgUI) {
        this.userRepository = userRepository;
        this.moodService = moodService;
        this.tgUI = tgUI;
    }

    Optional<Content> commands(Message message) {
        String text = message.getText();
        Long chatId = message.getChatId();
        Long clientId = message.getFrom().getId();
        switch (text) {
            case "/start":
                handleStartCommand(chatId, clientId);
                return Optional.empty();
            case "/week_mood_log":
                return moodService.weekMoodLogCommand(chatId, clientId);
            case "/month_mood_log":
                return moodService.monthMoodLogCommand(chatId, clientId);
            case "/award":
                return moodService.awards(chatId, clientId);
            default:
                return Optional.empty();
        }
    }

    Optional<Content> handleCallback(CallbackQuery callback) {
        var moodId = Long.valueOf(callback.getData());
        var user = userRepository.findById(callback.getFrom().getId());
        return user.map(value -> moodService.chooseMood(value, moodId));
    }

    private Optional<Content> handleStartCommand(Long chatId, Long clientId) {
        var user = new User();
        user.setClientId(clientId);
        user.setChatId(chatId);
        userRepository.save(user);
        var content = new Content(user.getChatId());
        content.setText("Как настроение?");
        content.setMarkup(tgUI.buildButtons());
        return Optional.of(content);
    }
}
