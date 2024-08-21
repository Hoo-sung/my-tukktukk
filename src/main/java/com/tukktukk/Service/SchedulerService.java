package com.tukktukk.Service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SchedulerService {

    @Scheduled(fixedRate = 5000)
    public void confirmOrCancelMatch() {

    }
}
