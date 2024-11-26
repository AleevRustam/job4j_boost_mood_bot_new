package ru.job4j;

import ru.job4j.services.BotCommandHandler;
import ru.job4j.services.TelegramBotService;

public class DIByDirectInjectMain {
    public static void main(String[] args) {
        var handler = new BotCommandHandler();
        var tg = new TelegramBotService(handler);
//        tg.receive(new Content());
    }
}
