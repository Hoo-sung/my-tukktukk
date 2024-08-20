package com.tukktukk;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VenueManager {

    private List<Stadium> stadiums = new ArrayList<>();

    public void registerStadium(Stadium stadium) {
        stadiums.add(stadium);
    }

    public Match createMatch(Stadium stadium, LocalDateTime startTime, LocalDateTime endTime, Integer playTime) {
        return new Match(stadium, startTime, endTime, playTime);
    }

    public boolean isStadiumRegistered(Stadium stadium) {
        return stadiums.contains(stadium);
    }
}
