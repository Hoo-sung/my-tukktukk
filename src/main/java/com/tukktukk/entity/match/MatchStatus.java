package com.tukktukk.entity.match;

public enum MatchStatus {
    SCHEDULED("경기 예정"),
    CONFIRMED("진행 확정"),
    CANCELED("경기 취소"),
    IN_PROGRESS("진행 중"),
    COMPLETED("종료");

    MatchStatus(String status) {
        this.status = status;
    }

    private final String status;
}
