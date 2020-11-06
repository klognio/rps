package com.xp.rps;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RpsApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;


	@Test
	void contextLoads() {
	}

	@Test
	public void play(){
		ResponseEntity<Round> response = restTemplate.postForEntity("/play", new Round(Throw.ROCK, Throw.PAPER), Round.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(response.getBody().getResult(), Result.P2_WINS);
	}

	@Test
	void createGame(){
		Game game = new Game("raymond","James",3,"Buy a cup of coffee");
		ResponseEntity<Integer> response = restTemplate.postForEntity("/game", game, Integer.class);
		int gameId = response.getBody();
		ResponseEntity<Game> response1 = restTemplate.getForEntity("/game/"+gameId,Game.class);
		Game game1 = response1.getBody();
		assertEquals(game.getPlayer1(), game1.getPlayer1());
		ResponseEntity<Integer> response2 = restTemplate.postForEntity("/game/"+gameId + "/round",new Round(Throw.ROCK,Throw.PAPER), Integer.class);
//		assertEquals(game.getPlayer1(), game1.getPlayer1());
	}
}
