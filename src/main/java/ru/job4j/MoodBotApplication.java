package ru.job4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import ru.job4j.config.AppConfig;

@SpringBootApplication
public class MoodBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoodBotApplication.class, args);
    }

    @Bean("commandLineRunnerMoodBotApplication")
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
      return args -> {
          AppConfig appConfig = ctx.getBean(AppConfig.class);
          appConfig.printConfig();
      };
    }
}
