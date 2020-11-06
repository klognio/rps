package com.xp.rps;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RpsInMemoryRepoTest {

    @Test
    void testCreateGame(){
        InMemoryRpsRepo repo = new InMemoryRpsRepo();
        Game game = new Game("player1","player2",1,"DummyDecision");
        int gameId = repo.createGame(game);
        assertEquals(gameId, game.getId());

        Game game1 = repo.getGame(gameId);
        assertEquals(game, game1);

        //P1 Wins
        repo.addRound(gameId, new Round(Throw.ROCK, Throw.SCISSOR, Result.P1_WINS));
        repo.addRound(gameId, new Round(Throw.ROCK, Throw.SCISSOR, Result.P1_WINS));
        repo.addRound(gameId, new Round(Throw.ROCK, Throw.SCISSOR, Result.P1_WINS));

        //P2 Wins
        repo.addRound(gameId, new Round(Throw.ROCK,Throw.PAPER, Result.P2_WINS));
        repo.addRound(gameId, new Round(Throw.ROCK,Throw.PAPER, Result.P2_WINS));

        assertEquals(game.getResult(), Result.P1_WINS);

        repo.addRound(gameId, new Round(Throw.ROCK,Throw.PAPER, Result.P2_WINS));
        assertEquals(game.getResult(), Result.DRAW);

        repo.addRound(gameId, new Round(Throw.ROCK,Throw.PAPER, Result.P2_WINS));
        assertEquals(game.getResult(), Result.P2_WINS);

    }
}
