package ru.job4j.services;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.job4j.content.Content;
import ru.job4j.repositories.UserRepository;

@Service
public class ReminderService implements BeanNameAware {

    private String beanName;

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

   // private final TgRemoteService tgRemoteService;
    private final TelegramBotService tgRemoteService;
    private final UserRepository userRepository;

    public ReminderService(TelegramBotService tgRemoteService, UserRepository userRepository) {
        this.tgRemoteService = tgRemoteService;
        this.userRepository = userRepository;
    }

    @Scheduled(fixedRateString = "${remind.period}")
    public void ping() {
        for (var user : userRepository.findAll()) {
            /*var message = new SendMessage();
            message.setChatId(user.getChatId());
            message.setText("Ping");
            tgRemoteService.sent(message);*/

            var content = new Content(user.getChatId());
            content.setText("Ping");
            tgRemoteService.sent(content);
        }
    }
}
