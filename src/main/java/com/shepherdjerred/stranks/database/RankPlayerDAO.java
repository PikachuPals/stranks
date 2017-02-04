package com.shepherdjerred.stranks.database;

import com.shepherdjerred.stranks.objects.RankPlayer;
import com.shepherdjerred.stranks.objects.trackers.RankPlayers;
import org.codejargon.fluentjdbc.api.FluentJdbc;
import org.codejargon.fluentjdbc.api.query.Mapper;
import org.codejargon.fluentjdbc.api.query.Query;

import java.util.Optional;
import java.util.UUID;

public class RankPlayerDAO {

    private final FluentJdbc fluentJdbc;
    private final RankPlayers rankPlayers;

    public RankPlayerDAO(FluentJdbc fluentJdbc, RankPlayers rankPlayers) {
        this.fluentJdbc = fluentJdbc;
        this.rankPlayers = rankPlayers;
    }

    public void insert(RankPlayer rankPlayer) {
        Query query = fluentJdbc.query();

        query
                .update("INSERT INTO players VALUES (?, ? ,?)")
                .params(
                        rankPlayer.getUuid().toString(),
                        rankPlayer.getRank(),
                        rankPlayer.getLastRankUp()
                ).run();
    }

    public void setRank(RankPlayer rankPlayer) {
        Query query = fluentJdbc.query();

        query
                .update("UPDATE players SET rank = ? WHERE player_uuid = ?")
                .params(
                        rankPlayer.getRank(),
                        rankPlayer.getUuid().toString()
                ).run();

    }

    public void setLastRankUp(RankPlayer rankPlayer) {
        Query query = fluentJdbc.query();

        query
                .update("UPDATE players SET lastRankUp = ? WHERE player_uuid = ?")
                .params(
                        rankPlayer.getLastRankUp(),
                        rankPlayer.getUuid().toString()
                ).run();

    }

    public RankPlayer load(UUID uuid) {

        Mapper<RankPlayer> rankPlayerMapper = rs -> new RankPlayer(
                uuid,
                rs.getInt("rank"),
                rs.getLong("lastRankUp")
        );

        Query query = fluentJdbc.query();

        Optional<RankPlayer> rankPlayer = query
                .select("SELECT * FROM players WHERE player_uuid = ?")
                .params(
                        uuid.toString()
                ).firstResult(rankPlayerMapper);

        return rankPlayer.orElse(null);

    }

}
