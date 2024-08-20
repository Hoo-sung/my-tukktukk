package com.tukktukk;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class Match {
    private Stadium stadium;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer playTime;
}
