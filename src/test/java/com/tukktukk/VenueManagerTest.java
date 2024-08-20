package com.tukktukk;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class VenueManagerTest {

    @Test
    @DisplayName("경기장(Stadium) 등록 테스트")
    void registerStadiumTest() {
        //given
        VenueManager venueManager = new VenueManager();
        Stadium stadium = new Stadium("Estadio Santiago Bernabeu", "Concha Espinaga 1, 28036");
        //when
        venueManager.registerStadium(stadium);
        //then
        assertTrue(venueManager.isStadiumRegistered(stadium));
    }

    @Test
    @DisplayName("코트(court) 등록 테스트")
    void registerCourtTest() {
        //given
        VenueManager venueManager = new VenueManager();
        Stadium stadium = new Stadium("Estadio Santiago Bernabeu", "Concha Espinaga 1, 28036");
        venueManager.registerStadium(stadium);
        Court court = new Court("베르나베우 A 구장", 10, 18);

        //when
        venueManager.registerCourt(stadium, court);
        //then
        Assertions.assertTrue(stadium.getCourts().contains(court));
    }

    @Test
    @DisplayName("경기(Match) 등록 테스트")
    void createMatchTest() {
        //given
        VenueManager venueManager = new VenueManager();
        Stadium stadium = new Stadium("Estadio Santiago Bernabeu", "Concha Espinaga 1, 28036");
        venueManager.registerStadium(stadium);
        Court court = new Court("베르나베우 A 구장", 10, 18);
        venueManager.registerCourt(stadium, court);

        //when
        Match match = venueManager.createMatch(court, LocalDateTime.of(2024, 8, 25, 18, 0),
                LocalDateTime.of(2024, 8, 25, 20, 0), 2);

        //then
        assertEquals(match.getCourt(), court);
        assertEquals(match.getPlayTime(), 2);
        assertEquals(match.getStartTime(), LocalDateTime.of(2024, 8, 25, 18, 0));
        assertEquals(match.getEndTime(), LocalDateTime.of(2024, 8, 25, 20, 0));
    }
}
