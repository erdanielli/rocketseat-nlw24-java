package com.github.erdanielli.planner;

import com.github.erdanielli.planner.domain.notification.TripRepositoryWithNotifications;
import com.github.erdanielli.planner.domain.repository.TripRepository;
import com.github.erdanielli.planner.domain.repository.TripRepositoryFakeAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;

@SpringBootApplication
public class PlannerJavaApplication {

    @Bean
    TripRepository tripRepositoryWithNotification() {
        return new TripRepositoryWithNotifications(new TripRepositoryFakeAdapter(new HashMap<>()));
    }

    public static void main(String[] args) {
        SpringApplication.run(PlannerJavaApplication.class, args);
    }

}
