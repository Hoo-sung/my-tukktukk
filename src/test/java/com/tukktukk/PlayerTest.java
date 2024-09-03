package com.tukktukk;


import com.tukktukk.entity.court.Court;
import com.tukktukk.entity.match.Match;
import com.tukktukk.entity.stadium.Stadium;
import com.tukktukk.entity.user.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static com.tukktukk.entity.court.CourtType.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PlayerTest {

    @Test
    void 경기_신청_테스트() {
        //given
        Stadium stadium = Stadium.builder()
                .name("Estadio Santiago Bernabeu")
                .address("Concha Espinaga 1, 28036")
                .build();

        Court court = Court.builder()
                .name("베르나베우 A 구장")
                .courtType(SIX_TO_SIX)
                .build();
        stadium.addCourt(court);
        Match match = Match.createMatch(court, LocalDateTime.of(2024, 8, 20, 18, 0,0),
                LocalDateTime.of(2024, 8, 20, 20, 0, 0), 2);
        User player = new User();

        //when
        player.joinMatch(match);

        //then
        Assertions.assertThat(match.getPlayers().contains(player));
        Assertions.assertThat(match.getPlayers().size()).isEqualTo(1);
    }

    @Test
    void 경기_취소_테스트() {
        //given
        Stadium stadium = Stadium.builder()
                .name("Estadio Santiago Bernabeu")
                .address("Concha Espinaga 1, 28036")
                .build();

        Court court = Court.builder()
                .name("베르나베우 A 구장")
                .courtType(SIX_TO_SIX)
                .build();
        stadium.addCourt(court);
        Match match = Match.createMatch(court, LocalDateTime.of(2024, 8, 20, 18, 0,0),
                LocalDateTime.of(2024, 8, 20, 20, 0, 0), 2);
        User player = new User();
        player.joinMatch(match);

        //when
        player.leaveMatch(match);

        //then
        assertFalse(match.getPlayers().contains(player));
    }

}
