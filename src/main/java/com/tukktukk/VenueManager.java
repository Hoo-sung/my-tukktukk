package com.tukktukk;

import java.util.ArrayList;
import java.util.List;

public class VenueManager {

    private List<Stadium> stadiums = new ArrayList<>();

    public void registerStadium(Stadium stadium) {
        stadiums.add(stadium);
    }

    public boolean isStadiumRegistered(Stadium stadium) {
        return stadiums.contains(stadium);
    }
}
