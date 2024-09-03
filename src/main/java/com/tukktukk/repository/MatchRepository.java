package com.tukktukk.repository;

import com.tukktukk.entity.match.Match;
import com.tukktukk.entity.match.MatchStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {

    @Query("SELECT m FROM Match m WHERE m.startTime >= :now " +
            "AND m.startTime <= :threshold " +
            "AND m.status = :status")
    public List<Match> findMatchesToCancel(@Param("now") LocalDateTime now,
                                            @Param("threshold") LocalDateTime threshold,
                                            @Param("status") MatchStatus status);
}
