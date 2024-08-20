package com.tukktukk;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class PlayerTest {

    @Test
    @DisplayName("경기 신청 테스트")
    void enrollMatch() {
        //given
        Stadium stadium = new Stadium("Estadio Santiago Bernabeu", "Concha Espinaga 1, 28036");
        Court court = new Court("베르나베우 A 구장", 10, 18);
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
