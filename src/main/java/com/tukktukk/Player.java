package com.tukktukk;

public class Player {

    private String name;
    private String email;

    public void joinMatch(Match match){
        match.addPlayer(this);
    }

    public void leaveMatch(Match match){
        match.removePlayer(this);
    }
}
