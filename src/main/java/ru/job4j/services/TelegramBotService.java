package ru.job4j.services;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Service;
import ru.job4j.content.Content;

@Service
public class TelegramBotService implements BeanNameAware {

    private String beanName;

    private final BotCommandHandler handler;

    public TelegramBotService(BotCommandHandler handler) {
        this.handler = handler;
    }

    public void receive(Content content) {
        handler.receive(content);
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
        System.out.println("Bean beanName from BeanNameAware: " + this.beanName);
    }
}
