package com.xp.rps;

public class RPS {
    public static Result play(Throw p1, Throw p2) {
        if(p1.equals(p2)){
            return Result.DRAW;
        }
        if(p1.equals(Throw.ROCK)){
            if(p2.equals(Throw.PAPER)){
                return Result.P2_WINS;
            }
            return Result.P1_WINS;
        }else if(p1.equals(Throw.PAPER)){
            if(p2.equals(Throw.ROCK)){
                return Result.P1_WINS;
            }
            return Result.P2_WINS;
        }else{
            if(p2.equals(Throw.ROCK)){
                return Result.P2_WINS;
            }
            return Result.P1_WINS;
        }
    }
}
