package com.tukktukk;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

import static com.tukktukk.CourtType.*;
import static com.tukktukk.MatchStatus.*;
import static com.tukktukk.MatchStatus.AVAILABLE;
import static org.junit.jupiter.api.Assertions.*;

public class MatchTest {

    @Nested
    class addPlayer {

        @Test
        void 정상_일반_케이스() {
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
            Match match = Match.createMatch(court, LocalDateTime.of(2024, 8, 20, 18, 0, 0),
                    LocalDateTime.of(2024, 8, 20, 20, 0, 0), 2);

            //when
            IntStream.range(0, SIX_TO_SIX.getMinimumPlayer() - 4).forEach(i -> match.addPlayer(new User()));

            //then
            assertEquals(match.getPlayers().size(), SIX_TO_SIX.getMinimumPlayer() - 4);
        }


        @Test
        void 경기에서_동일한_참여자를_추가할_때_발생하는_예외_발생_테스트() {
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
            Match match = Match.createMatch(court, LocalDateTime.of(2024, 8, 20, 18, 0, 0),
                    LocalDateTime.of(2024, 8, 20, 20, 0, 0), 2);
            User player = new User();

            //when
            match.addPlayer(player);

            //then
            IllegalStateException exception = assertThrows(IllegalStateException.class, () -> match.addPlayer(player));
            assertEquals("Player already exists", exception.getMessage());
        }

        @Test
        void MatchStatus가_CLOSED일_때_플레이어_추가_시_예외_발생_테스트() {
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
            Match match = Match.createMatch(court, LocalDateTime.of(2024, 8, 20, 18, 0, 0),
                    LocalDateTime.of(2024, 8, 20, 20, 0, 0), 2);

            //when
            IntStream.range(0, SIX_TO_SIX.getMaximumPlayer()).forEach(i -> match.addPlayer(new User()));
            IllegalStateException exception = assertThrows(IllegalStateException.class, () -> match.addPlayer(new User()));

            //then
            assertEquals(match.getStatus(), CLOSED);
            assertEquals("Can't add player. Match is not available for new players.", exception.getMessage());
        }
    }

    @Nested
    class removePlayer {

        @Test
        void CourtStatus_변경없는_정상_일반_테스트() {
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
            Match match = Match.createMatch(court, LocalDateTime.of(2024, 8, 20, 18, 0, 0),
                    LocalDateTime.of(2024, 8, 20, 20, 0, 0), 2);
            User player = new User();
            match.addPlayer(player);

            //when
            match.removePlayer(player);

            //then
            assertFalse(match.getPlayers().contains(player));
        }

        @Test
        void CourtStatus_변경있는_정상_일반_테스트() {
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
            Match match = Match.createMatch(court, LocalDateTime.of(2024, 8, 20, 18, 0, 0),
                    LocalDateTime.of(2024, 8, 20, 20, 0, 0), 2);
            User player = new User();
            match.addPlayer(player);

            //when
            IntStream.range(0, SIX_TO_SIX.getMaximumPlayer() - 1).forEach(i -> match.addPlayer(new User()));
            //then
            assertEquals(match.getStatus(), CLOSED);

            //when
            match.removePlayer(player);
            //then
            assertEquals(match.getStatus(), AVAILABLE);
        }

        @Test
        void 매치에_참여하지않는_유저_경기취소_예외_발생_테스트() {
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
            Match match = Match.createMatch(court, LocalDateTime.of(2024, 8, 20, 18, 0, 0),
                    LocalDateTime.of(2024, 8, 20, 20, 0, 0), 2);
            User player1 = new User();
            match.addPlayer(player1);

            //when
            User player2 = new User();
            IllegalStateException exception = assertThrows(IllegalStateException.class, () -> match.removePlayer(player2));

            //then
            assertEquals(exception.getMessage(), "Player not found");
        }
    }
}
