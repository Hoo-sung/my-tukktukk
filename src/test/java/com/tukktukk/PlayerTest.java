package com.tukktukk;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static com.tukktukk.CourtType.*;

public class PlayerTest {

    @Test
    void 경기_신청_테스트() {
        //given
        Stadium stadium = new Stadium("Estadio Santiago Bernabeu", "Concha Espinaga 1, 28036");
        Court court = new Court("베르나베우 A 구장", SIX_TO_SIX);
        stadium.addCourt(court);
        Match match = Match.createMatch(court, LocalDateTime.of(2024, 8, 20, 18, 0), LocalDateTime.of(2024, 8, 20, 20, 0), 2);
        Player player = new Player();

        //when
        player.enrollMatch(match);

        //then
        Assertions.assertThat(match.contains(player));
        Assertions.assertThat(match.getPlayers().size()).isEqualTo(1);
    }

}
