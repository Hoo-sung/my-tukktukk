package com.tukktukk;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static com.tukktukk.CourtType.SIX_TO_SIX;
import static com.tukktukk.MatchStatus.*;
import static org.junit.jupiter.api.Assertions.*;

public class VenueManagerTest {

    @Test
    void Stadium_등록_테스트() {
        //given
        VenueManager venueManager = new VenueManager();
        Stadium stadium = Stadium.builder()
                .name("Estadio Santiago Bernabeu")
                .address("Concha Espinaga 1, 28036")
                .build();
        //when
        venueManager.registerStadium(stadium);
        //then
        assertTrue(venueManager.isStadiumRegistered(stadium));
    }

    @Test
    void Court_등록_테스트() {
        //given
        VenueManager venueManager = new VenueManager();
        Stadium stadium = Stadium.builder()
                .name("Estadio Santiago Bernabeu")
                .address("Concha Espinaga 1, 28036")
                .build();

        venueManager.registerStadium(stadium);
        Court court = Court.builder()
                .name("베르나베우 A 구장")
                .courtType(SIX_TO_SIX)
                .build();

        //when
        venueManager.registerCourt(stadium, court);
        //then
        Assertions.assertTrue(stadium.getCourts().contains(court));
    }

    @Test
    void Match_등록_테스트() {
        //given
        VenueManager venueManager = new VenueManager();
        Stadium stadium = Stadium.builder()
                .name("Estadio Santiago Bernabeu")
                .address("Concha Espinaga 1, 28036")
                .build();

        venueManager.registerStadium(stadium);
        Court court = Court.builder()
                .name("베르나베우 A 구장")
                .courtType(SIX_TO_SIX)
                .build();
        venueManager.registerCourt(stadium, court);

        //when
        Match match = Match.createMatch(court, LocalDateTime.of(2024, 8, 20, 18, 0, 0),
                LocalDateTime.of(2024, 8, 20, 20, 0, 0), 2);

        //then
        assertEquals(match.getCourt(), court);
        assertEquals(match.getPlayTime(), 2);
        assertEquals(match.getStartTime(), LocalDateTime.of(2024, 8, 20, 18, 0, 0));
        assertEquals(match.getEndTime(), LocalDateTime.of(2024, 8, 20, 20, 0, 0));
        assertEquals(match.getStatus(), AVAILABLE);
    }
}
