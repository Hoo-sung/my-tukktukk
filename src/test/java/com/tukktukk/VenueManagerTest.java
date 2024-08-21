package com.tukktukk;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.tukktukk.MatchStatus.*;
import static org.junit.jupiter.api.Assertions.*;

public class VenueManagerTest {

    @Test
    void Stadium_등록_테스트() {
        //given
        VenueManager venueManager = new VenueManager();
        Stadium stadium = new Stadium("Estadio Santiago Bernabeu", "Concha Espinaga 1, 28036");
        //when
        venueManager.registerStadium(stadium);
        //then
        assertTrue(venueManager.isStadiumRegistered(stadium));
    }

    @Test
    void Court_등록_테스트() {
        //given
        VenueManager venueManager = new VenueManager();
        Stadium stadium = new Stadium("Estadio Santiago Bernabeu", "Concha Espinaga 1, 28036");
        venueManager.registerStadium(stadium);
        Court court = new Court("베르나베우 A 구장", CourtType.SIX_TO_SIX);

        //when
        venueManager.registerCourt(stadium, court);
        //then
        Assertions.assertTrue(stadium.getCourts().contains(court));
    }

    @Test
    void Match_등록_테스트() {
        //given
        VenueManager venueManager = new VenueManager();
        Stadium stadium = new Stadium("Estadio Santiago Bernabeu", "Concha Espinaga 1, 28036");
        venueManager.registerStadium(stadium);
        Court court = new Court("베르나베우 A 구장", CourtType.SIX_TO_SIX);
        venueManager.registerCourt(stadium, court);

        //when
        Match match = venueManager.createMatch(court, ZonedDateTime.of(2024, 8, 20, 18, 0, 0, 0,
                ZoneId.of("Asia/Seoul")), ZonedDateTime.of(2024, 8, 20, 20, 0, 0, 0,
                ZoneId.of("Asia/Seoul")), 2);

        //then
        assertEquals(match.getCourt(), court);
        assertEquals(match.getPlayTime(), 2);
        assertEquals(match.getStartTime(), ZonedDateTime.of(2024, 8, 20, 18, 0, 0, 0,
                ZoneId.of("Asia/Seoul")));
        assertEquals(match.getEndTime(), ZonedDateTime.of(2024, 8, 20, 20, 0, 0, 0,
                ZoneId.of("Asia/Seoul")));
        assertEquals(match.getStatus(), AVAILABLE);
    }
}
