package com.xp.rps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RpsController {

    @Autowired
    private RpsRepo rpsRepo;

    @GetMapping("/")
    public String hi(){
        return "Hellooo MS!";
    }

    @PostMapping("/play")
    public Round play(@RequestBody Round round){
        Result result = RPS.play(round.getThrow1(), round.getThrow2());
        round.setResult(result);
        return round;
    }

    @PostMapping("/game")
    public int createGame(@RequestBody Game game){
        return rpsRepo.createGame(game);
    }

    @GetMapping("/game/{gameId}")
    public Game getGame(@PathVariable int gameId){
        return rpsRepo.getGame(gameId);
    }

    @PostMapping("/game/{id}/round")
    int playRond(@PathVariable int id, @RequestBody Round round){
        Result result = RPS.play(round.getThrow1(), round.getThrow2());
        round.setResult(result);
        return rpsRepo.addRound(id, round);
    }
}
