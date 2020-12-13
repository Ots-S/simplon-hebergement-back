package com.simplon.simplontest.Services;

import com.simplon.simplontest.entity.Project;
import com.simplon.simplontest.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class SendEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectRepository projectRepository;

    public void sendEmail(String to, String body, String topic) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("simplontestsender@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(topic);
        simpleMailMessage.setText(body);
        javaMailSender.send(simpleMailMessage);
    }

    /**
     * @param projects
     * @return
     *
     * Construit le corps d'un email pour les projets dont l'hébergement expirent dans un mois ou dans un jour.
     */
    public String buildMessage(List<Project> projects) {
        String oneDayBeforeMail = "L'hébergement des projets suivants prend fin demain : \n \n";
        String oneMonthBeforeMail = "L'hébergement des projets suivants prend fin dans moins d'un mois : \n \n";
        boolean oneMonthBefore = false;
        boolean oneDayBefore = false;
        for (Project project : projects) {
            if (projectService.isEndingDateTomorrow(project.getEndingDate())) {
                oneDayBefore = true;
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                oneDayBeforeMail += project.getId() + " - " + project.getClient() + " - " + project.getProject() + " expire le " + simpleDateFormat.format(project.getEndingDate()) + " chez " + project.getDomain() + ". \n";
            } else if (projectService.isEndingDateOneMonth(project.getEndingDate()) && !project.isOneMonthBeforeExpirationEmailSended()) {
                oneMonthBefore = true;
                project.setOneMonthBeforeExpirationEmailSended(true);
                projectRepository.save(project);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                oneMonthBeforeMail += project.getId() + " - " + project.getClient() + " - " + project.getProject() + " expire le " + simpleDateFormat.format(project.getEndingDate()) + " chez " + project.getDomain() + ". \n";
            }
        }
        String bodyMail = "";
        if (oneMonthBefore && oneDayBefore) {
            bodyMail = oneDayBeforeMail + "\n" + oneMonthBeforeMail;
        } else if (oneDayBefore) {
            bodyMail = oneDayBeforeMail;
        } else if (oneMonthBefore) {
            bodyMail = oneMonthBeforeMail;
        }
        return bodyMail;
    }
}
