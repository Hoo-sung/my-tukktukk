package com.tukktukk.entity.match;

import com.tukktukk.entity.user.User;
import com.tukktukk.entity.court.Court;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.tukktukk.entity.match.MatchStatus.*;

@Getter
@Entity
@Table(name = "matchs")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Match {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "court_id", nullable = false)
    private Court court;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @Column(name = "play_time", nullable = false)
    private Integer playTime;

    @ManyToMany
    @JoinTable(
            name = "match_user",
            joinColumns = @JoinColumn(name = "match_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> players = new ArrayList<>();

    @Enumerated(EnumType.STRING)
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


    public void addPlayer(final User player) {
        if (players.contains(player)) {
            throw new IllegalStateException("Player already exists");
        }
        if (status != AVAILABLE) {
            throw new IllegalStateException("Can't add player. Match is not available for new players.");
        }
        players.add(player);
        if (players.size() >= court.getCourtType().getMaximumPlayer()) {
            status = MatchStatus.CLOSED;
        }
    }

    public void removePlayer(final User player) {
        if (!players.remove(player)) {
            throw new IllegalStateException("Player not found");
        }
        if (players.size() < court.getCourtType().getMaximumPlayer()) {
            status = AVAILABLE;
        }
    }

    public void cancel() {
        status = CANCELED;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", playTime=" + playTime +
                '}';
    }
}
