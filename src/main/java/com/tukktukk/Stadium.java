package com.tukktukk;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
public class Stadium {
    private String stadiumName;
    private String address;
    private final List<Court> courts = new ArrayList<>();

    public void addCourt(final Court court) {
        courts.add(court);
    }

    public List<Court> getCourts() {
        return Collections.unmodifiableList(courts);
    }
}
