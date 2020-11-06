package com.xp.rps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    private int id;
    private String player1;
    private String player2;
    private int roundNo;
    private String decision;
    private List<Round> roundList  = new ArrayList<>();

    public Game(){}

    public Game(String player1, String player2, int roundNo, String decision) {
        this.player1 = player1;
        this.player2 = player2;
        this.roundNo = roundNo;
        this.decision = decision;
    }

    public Game(int id, String player1, String player2, int roundNo, String decision) {
        this.id = id;
        this.player1 = player1;
        this.player2 = player2;
        this.roundNo = roundNo;
        this.decision = decision;
    }

    public Result getResult(){
        int p1=0,p2=0;
        for(Round round : roundList){
            if(Result.P1_WINS.equals(round.getResult()))
                p1++;
            else if(Result.P2_WINS.equals(round.getResult()))
                p2++;
        }
        return p1==p2?Result.DRAW : p1>p2 ? Result.P1_WINS : Result.P2_WINS;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public int getRoundNo() {
        return roundNo;
    }

    public void setRoundNo(int roundNo) {
        this.roundNo = roundNo;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public List<Round> getRoundList() {
        return roundList;
    }

    public void setRoundList(List<Round> roundList) {
        this.roundList = roundList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        if (id != game.id) return false;
        if (roundNo != game.roundNo) return false;
        if (!player1.equals(game.player1)) return false;
        if (player2 != null ? !player2.equals(game.player2) : game.player2 != null) return false;
        return decision != null ? decision.equals(game.decision) : game.decision == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + player1.hashCode();
        result = 31 * result + (player2 != null ? player2.hashCode() : 0);
        result = 31 * result + roundNo;
        result = 31 * result + (decision != null ? decision.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", player1='" + player1 + '\'' +
                ", player2='" + player2 + '\'' +
                ", roundNo=" + roundNo +
                ", decision='" + decision + '\'' +
                '}';
    }
}
