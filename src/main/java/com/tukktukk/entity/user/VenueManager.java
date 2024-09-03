package com.tukktukk.entity.user;

import com.tukktukk.entity.stadium.Stadium;
import com.tukktukk.entity.court.Court;
import com.tukktukk.entity.match.Match;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VenueManager {

    private final List<Stadium> stadiums = new ArrayList<>();

    public void registerStadium(final Stadium stadium) {
        stadiums.add(stadium);
    }

    public void registerCourt(final Stadium stadium, final Court court) {
        stadium.addCourt(court);
    }

    public Match createMatch(final Court court, final LocalDateTime startTime, final LocalDateTime endTime, final Integer playTime) {
        return Match.createMatch(court, startTime, endTime, playTime);
    }

    public boolean isStadiumRegistered(final Stadium stadium) {
        return stadiums.contains(stadium);
    }
}
