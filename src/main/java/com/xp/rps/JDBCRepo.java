package com.xp.rps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class JDBCRepo implements RpsRepo{

    private final String SQL_GET_GAME = "SELECT * FROM GAME WHERE ID=?";
    private final String SQL_CREATE_GAME = "INSERT INTO GAME (PLAYER1, PLAYER2, ROUND_NO, DECISION) VALUES (?,?,?,?)";

    private final String SQL_GET_ROUND = "SELECT * FROM ROUND WHERE GAME_ID=?";
    private final String SQL_CREATE_ROUND = "INSERT INTO ROUND (GAME_ID, THROW1, THROW2, RESULT) VALUES (?,?,?,?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int createGame(Game game) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(SQL_CREATE_GAME, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, game.getPlayer1());
            ps.setString(2, game.getPlayer2());
            ps.setInt(3,game.getRoundNo());
            ps.setString(4, game.getDecision());
            return ps;
        }, keyHolder);
        return (int)keyHolder.getKey().intValue();
    }

    @Override
    public Game getGame(int gameId) {
        Game g = this.getGameOnly(gameId);
        List<Round> rs = this.getRounsByGameID(gameId);
        g.setRoundList(rs);
        return g;
    }

    @Override
    public int addRound(int gameId, Round round) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(SQL_CREATE_ROUND,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,gameId);
            ps.setString(2, round.getThrow1().toString());
            ps.setString(3, round.getThrow2().toString());
            ps.setString(4, round.getResult().toString());
            return ps;
        }, keyHolder);
        return (int)keyHolder.getKey().intValue();
    }



    //Below for internal usage
    private Game getGameOnly(int gameId) {
        Game game =  jdbcTemplate.queryForObject(SQL_GET_GAME, new Object[]{gameId},
                (rs, rowNum) ->
                        new Game(
                                rs.getInt("ID"),
                                rs.getString("PLAYER1"),
                                rs.getString("PLAYER2"),
                                rs.getInt("ROUND_NO"),
                                rs.getString("DECISION")
                        ));
        return game;
    }

    private List<Round> getRounsByGameID (int gameId) {
        List<Round> rounds = jdbcTemplate.query(
                SQL_GET_ROUND,
                new Object[]{gameId},
                mapper);
        return rounds;
    }

    private final RowMapper<Round> mapper = (rs, rowNum) -> new Round(
            rs.getInt("ID"),
            Throw.valueOf(rs.getString("THROW1")),
            Throw.valueOf(rs.getString("THROW2")),
            Result.valueOf(rs.getString("RESULT"))
    );
}
