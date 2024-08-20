package com.tukktukk;

public class Player {

    private String name;
    private String email;

    public void enrollMatch(Match match){
        match.addPlayer(this);
    }
}
