package com.tukktukk;

import com.tukktukk.entity.match.Match;
import com.tukktukk.entity.match.MatchStatus;
import com.tukktukk.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MatchScheduler {

    private final MatchRepository matchRepository;

    @Transactional
    @Scheduled(cron = "0 */38 * * * ?") //매 30분 마다 실행
    public void decideToCancel() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime threshold = now.plusHours(1).plusMinutes(30);

        //한시간 반도 안남은 경기중에 AVAILABLE인 경기들 가져와서 상태 바꾸기
        List<Match> matchesToCancel = matchRepository.findMatchesToCancel(now, threshold, MatchStatus.AVAILABLE);

        for(Match match : matchesToCancel) {
            System.out.println(match.toString());
            match.cancel();
            matchRepository.save(match);
            //환불 처리 및 안내 가야함
        }
    }

}
