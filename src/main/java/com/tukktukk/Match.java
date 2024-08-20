package com.tukktukk;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.tukktukk.MatchStatus.*;

@Getter
public class Match {
    private final Court court;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final Integer playTime;
    private final List<Player> players;
    private MatchStatus status;

    private Match(final Court court, final LocalDateTime startTime, final LocalDateTime endTime, final Integer playTime) {
        this.court = court;
        this.startTime = startTime;
        this.endTime = endTime;
        this.playTime = playTime;
        this.players = new ArrayList<>();
        this.status = AVAILABLE;
    }

    public static Match createMatch(final Court court, final LocalDateTime startTime,
                                    final LocalDateTime endTime, final Integer playTime) {
        return new Match(court, startTime, endTime, playTime);
    }

    public boolean contains(final Player player) {
        return players.contains(player);
    }

    public void addPlayer(final Player player) {
        if (players.contains(player)) {
            throw new IllegalStateException("Player already exists");
        }
        if (status != AVAILABLE) {
            throw new IllegalStateException("Can't add player. Match is not available for new players.");
        }
        players.add(player);
        if (players.size() >= court.getCourtType().getMinimumPlayer()) {
            status = MatchStatus.CLOSED;
        }
    }
}
