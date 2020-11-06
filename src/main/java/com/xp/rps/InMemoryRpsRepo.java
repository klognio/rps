package com.xp.rps;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

//@Repository
public class InMemoryRpsRepo implements RpsRepo{
    private static final AtomicInteger INT_GENERATOR = new AtomicInteger(0);
    private static final Map<Integer, Game> DATA = new HashMap<>();

    @Override
    public int createGame(Game game) {
        int gameId = INT_GENERATOR.incrementAndGet();
        game.setId(gameId);
        DATA.put(gameId, game);
        return gameId;
    }
    @Override
    public Game getGame(int gameId) {
        return DATA.get(gameId);
    }

    @Override
    public int addRound(int gameId, Round round) {
        Game game = getGame(gameId);
        round.setId(INT_GENERATOR.incrementAndGet());
        if(game!=null){
            game.getRoundList().add(round);
        }
        return round.getId();
    }
}
