package com.shepherdjerred.stranks.config;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
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
                System.out.println(name);
            }
        }

    }

}
