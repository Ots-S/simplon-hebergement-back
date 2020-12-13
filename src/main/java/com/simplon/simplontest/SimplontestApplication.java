package com.simplon.simplontest;

import com.simplon.simplontest.Services.ProjectService;
import com.simplon.simplontest.Services.SendEmailService;
import com.simplon.simplontest.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class SimplontestApplication {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private SendEmailService sendEmailService;

    @Configuration
    @EnableScheduling
    @ConditionalOnProperty(value = "scheduling.enabled", matchIfMissing = true)
    class SchedulingConfiguration {
    }

    public static void main(String[] args) {
        SpringApplication.run(SimplontestApplication.class, args);
    }

    /**
     * Se déclenche à chaque démarrage du server, vérifie les alertes et envoie un mail si nécessaire.
     */
    @EventListener(ApplicationReadyEvent.class)
    public void triggerWhenStarts() {
        String message = sendEmailService.buildMessage(projectService.getProjects());
        if (!message.isEmpty()) {
            sendEmailService.sendEmail("explodor@gmail.com", message, "Alerte : hébergements bientôt expirés");
        }
    }

    /**
     * Vérifie les alertes chaque jour à 6h et envoie un mail si nécessaire.
     */
//    @Scheduled(cron = "0 0 6 * * *")
//    public void checkEndingDates() {
//
//    }
}
