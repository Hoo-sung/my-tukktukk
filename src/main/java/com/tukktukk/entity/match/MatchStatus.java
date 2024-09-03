package com.tukktukk.entity.match;

public enum MatchStatus {
    AVAILABLE("신청 가능"),
    CLOSED("마감"),
    CANCELED("경기 취소");

    MatchStatus(String status) {
        this.status = status;
    }

    private final String status;
}
