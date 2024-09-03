package com.tukktukk.entity.match;

import com.tukktukk.entity.court.CourtType;

public enum ApplyStatus {
    AVAILABLE("신청 가능"), HURRY("마감임박"), FULL("마감");

    private final String status;

    ApplyStatus(String status) {
        this.status = status;
    }

    public static ApplyStatus calculateApplyStatus(Match match) {
        int playerCount = match.getPlayers().size();
        CourtType courtType = match.getCourt().getCourtType();

        if (playerCount < courtType.getMinimumPlayer()) {
            return AVAILABLE;
        } else if (playerCount < courtType.getMaximumPlayer()) {
            return HURRY;
        } else {
            return FULL;
        }
    }
}
