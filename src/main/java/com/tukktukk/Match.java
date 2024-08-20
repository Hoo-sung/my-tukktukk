package com.tukktukk;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Match {
    private final Court court;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final Integer playTime;
    private final List<Player> players;

    private Match(final Court court, final LocalDateTime startTime, final LocalDateTime endTime, final Integer playTime) {
        this.court = court;
        this.startTime = startTime;
        this.endTime = endTime;
        this.playTime = playTime;
        this.players = new ArrayList<>();
    }

    public static Match createMatch(final Court court, final LocalDateTime startTime,
                                    final LocalDateTime endTime, final Integer playTime) {
        return new Match(court, startTime, endTime, playTime);
    }

    public void addPlayer(final Player player) {
        players.add(player);
    }


}
