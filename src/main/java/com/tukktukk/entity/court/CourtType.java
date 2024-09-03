package com.tukktukk.entity.court;

import lombok.Getter;

@Getter
public enum CourtType {
    FIVE_TO_FIVE("5 : 5 경기", 8, 15),
    SIX_TO_SIX("6 : 6 경기", 10, 18);

    private final String type;
    private final Integer minimumPlayer;
    private final Integer maximumPlayer;

    CourtType(final String type, final Integer minimumPlayer, final Integer maximumPlayer) {
        this.type = type;
        this.minimumPlayer = minimumPlayer;
        this.maximumPlayer = maximumPlayer;
    }
}
