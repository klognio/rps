package com.xp.rps;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(JDBCRepo.class)
@Transactional
public class RpsJdbcTest {

    @Autowired
    RpsRepo repo;

    @BeforeEach
    void setup(){
//        repo = new JDBCRepo();
    }

    @Test
    void createGame(){
        Game game = new Game("player1","player2",1,"DummyDecision");
        int gameId = repo.createGame(game);

        Game game1 = repo.getGame(gameId);
        assertEquals(game.getPlayer1(), game1.getPlayer1());

        //P1 Wins
        repo.addRound(gameId, new Round(Throw.ROCK, Throw.SCISSOR, Result.P1_WINS));
        repo.addRound(gameId, new Round(Throw.ROCK, Throw.SCISSOR, Result.P1_WINS));
        repo.addRound(gameId, new Round(Throw.ROCK, Throw.SCISSOR, Result.P1_WINS));

        //P2 Wins
        repo.addRound(gameId, new Round(Throw.ROCK,Throw.PAPER, Result.P2_WINS));
        repo.addRound(gameId, new Round(Throw.ROCK,Throw.PAPER, Result.P2_WINS));
        game = repo.getGame(gameId);
        assertEquals(game.getResult(), Result.P1_WINS);

        repo.addRound(gameId, new Round(Throw.ROCK,Throw.PAPER, Result.P2_WINS));
        game = repo.getGame(gameId);
        assertEquals(game.getResult(), Result.DRAW);

        repo.addRound(gameId, new Round(Throw.ROCK,Throw.PAPER, Result.P2_WINS));
        game = repo.getGame(gameId);
        assertEquals(game.getResult(), Result.P2_WINS);
    }
}
