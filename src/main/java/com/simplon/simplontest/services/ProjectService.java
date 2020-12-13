package com.simplon.simplontest.services;

import com.simplon.simplontest.entity.Project;
import com.simplon.simplontest.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    public boolean isEndingDateTomorrow(Date date) {
        Calendar oneDayBefore = Calendar.getInstance();
        oneDayBefore.setTime(date);
        oneDayBefore.add(Calendar.DAY_OF_MONTH, -1);
        Calendar today = Calendar.getInstance();
        if (today.getTime().after(oneDayBefore.getTime()) || today.getTime().equals(oneDayBefore.getTime())) {
            return true;
        }
        return false;
    }

    public boolean isEndingDateOneMonth(Date date) {
        Calendar thirtyDaysAgo = Calendar.getInstance();
        thirtyDaysAgo.setTime(date);
        thirtyDaysAgo.add(Calendar.MONTH, -1);
        Calendar todayCalendar = Calendar.getInstance();
        if (todayCalendar.getTime().after(thirtyDaysAgo.getTime())) {
            return true;
        }
        return false;
    }
}
