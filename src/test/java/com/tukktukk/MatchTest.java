package com.tukktukk;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

import static com.tukktukk.CourtType.*;

public class MatchTest {

    @Nested
    class addPlayer {

        @Test
        void 정상_일반_케이스() {
            //given
            Stadium stadium = new Stadium("Estadio Santiago Bernabeu", "Concha Espinaga 1, 28036");
            Court court = new Court("베르나베우 A 구장", SIX_TO_SIX);
            stadium.addCourt(court);
            Match match = Match.createMatch(court, LocalDateTime.of(2024, 8, 20, 18, 0), LocalDateTime.of(2024, 8, 20, 20, 0), 2);

            //when
            IntStream.range(0, SIX_TO_SIX.getMinimumPlayer() - 4).forEach(i -> match.addPlayer(new Player()));

            //then
            Assertions.assertEquals(match.getPlayers().size(), SIX_TO_SIX.getMinimumPlayer() - 4);
        }


        @Test
        void 경기에서_동일한_참여자를_추가할_때_발생하는_예외_발생_테스트() {
            //given
            Stadium stadium = new Stadium("Estadio Santiago Bernabeu", "Concha Espinaga 1, 28036");
            Court court = new Court("베르나베우 A 구장", SIX_TO_SIX);
            stadium.addCourt(court);
            Match match = Match.createMatch(court, LocalDateTime.of(2024, 8, 20, 18, 0), LocalDateTime.of(2024, 8, 20, 20, 0), 2);
            Player player = new Player();

            //when
            match.addPlayer(player);

            //then
            IllegalStateException exception = Assertions.assertThrows(IllegalStateException.class, () -> match.addPlayer(player));
            Assertions.assertEquals("Player already exists", exception.getMessage());
        }

        @Test
        void MatchStatus가_CLOSED일_때_플레이어_추가_시_예외_발생_테스트() {
            //given
            Stadium stadium = new Stadium("Estadio Santiago Bernabeu", "Concha Espinaga 1, 28036");
            Court court = new Court("베르나베우 A 구장", SIX_TO_SIX);
            stadium.addCourt(court);
            Match match = Match.createMatch(court, LocalDateTime.of(2024, 8, 20, 18, 0), LocalDateTime.of(2024, 8, 20, 20, 0), 2);

            //when
            IntStream.range(0, SIX_TO_SIX.getMinimumPlayer()).forEach(i -> match.addPlayer(new Player()));

            //then
            Assertions.assertEquals(match.getStatus(), MatchStatus.CLOSED);
            IllegalStateException exception = Assertions.assertThrows(IllegalStateException.class, () -> match.addPlayer(new Player()));
            Assertions.assertEquals("Can't add player. Match is not available for new players.", exception.getMessage());
        }
    }
}
