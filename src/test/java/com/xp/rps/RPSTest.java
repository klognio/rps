package com.xp.rps;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RPSTest {

    @Test
    void rockVsRock(){
        Result result = RPS.play(Throw.ROCK, Throw.ROCK);
        assertEquals(Result.DRAW, result);
    }
    @Test
    void rockVsPaper(){
        Result result = RPS.play(Throw.ROCK, Throw.PAPER);
        assertEquals(Result.P2_WINS, result);
    }

    @Test
    void rockVsScissor(){
        Result result = RPS.play(Throw.ROCK, Throw.SCISSOR);
        assertEquals(Result.P1_WINS, result);
    }

    @Test
    void paperVsRock(){
        Result result = RPS.play(Throw.PAPER, Throw.ROCK);
        assertEquals(Result.P1_WINS, result);
    }

    @Test
    void paperVsPaper(){
        Result result = RPS.play(Throw.PAPER, Throw.PAPER);
        assertEquals(Result.DRAW, result);
    }

    @Test
    void paperVsScissor(){
        Result result = RPS.play(Throw.PAPER, Throw.SCISSOR);
        assertEquals(Result.P2_WINS, result);
    }

    @Test
    void ScissorVsRock(){
        Result result = RPS.play(Throw.SCISSOR, Throw.ROCK);
        assertEquals(Result.P2_WINS, result);
    }

    @Test
    void ScissorVsPaper(){
        Result result = RPS.play(Throw.SCISSOR, Throw.PAPER);
        assertEquals(Result.P1_WINS, result);
    }

    @Test
    void ScissorVsScissor(){
        Result result = RPS.play(Throw.SCISSOR, Throw.SCISSOR);
        assertEquals(Result.DRAW, result);
    }
}
