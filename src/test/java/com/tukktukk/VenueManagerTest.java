package com.tukktukk;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class VenueManagerTest {

    @DisplayName("경기장 등록 테스트")
    @Test
    void testRegisterStadium() {
        //given
        VenueManager venueManager = new VenueManager();
        Stadium stadium = new Stadium("Estadio Santiago Bernabeu", "Concha Espinaga 1, 28036", 18);
        //when
        venueManager.registerStadium(stadium);
        //then
        Assertions.assertTrue(venueManager.isStadiumRegistered(stadium));
    }
}
