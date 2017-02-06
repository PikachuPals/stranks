package com.shepherdjerred.stranks.config;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import com.shepherdjerred.stranks.objects.Rank;
import com.shepherdjerred.stranks.objects.trackers.Ranks;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class RankLoader {

    private final Ranks ranks;

    public RankLoader(Ranks ranks) {
        this.ranks = ranks;
    }

    public void loadRanks(File file) {

        String jsonString = null;
        try {
            jsonString = FileUtils.readFileToString(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (jsonString != null) {
            JsonValue json = Json.parse(jsonString);
            JsonObject ranks = json.asObject().get("ranks").asObject();

            for (JsonObject.Member rank : ranks) {
                String name = rank.getName();
                String description = rank.getValue().asObject().getString("description", "Default description");
                String permission = rank.getValue().asObject().getString("permission", "stRanks.rank." + name);
                Double cost = rank.getValue().asObject().getDouble("cost", 1000000);

                Rank newRank = new Rank(Integer.valueOf(name), permission, cost, description);
                this.ranks.addRank(newRank);
            }
        }

    }

}
