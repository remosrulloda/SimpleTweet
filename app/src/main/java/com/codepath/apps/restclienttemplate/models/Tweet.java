package com.codepath.apps.restclienttemplate.models;

import com.codepath.apps.restclienttemplate.TimeFormatter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Tweet {

    public String body;
    public String createdAt;
    public long id;
    public User user;

    // Give in a JSON object that represent a tweet and turn it into a Java Tweet Object
    public static Tweet fromJson(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet(); // Build the tweet as per the fields
        tweet.body = jsonObject.getString("text"); // from API
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.id = jsonObject.getLong("id");
        // will take in a JSON object and returns the User model
        tweet.user = User.fromJson(jsonObject.getJSONObject("user")); // this refers to JSON object but we need a JAVA object

        return tweet;

    }

    public static List<Tweet> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Tweet> tweets = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            tweets.add(fromJson(jsonArray.getJSONObject(i)));
        }
        return tweets;

    }

    public String getFormattedTimestamp(){
        // returns the correct time for the tweet
        return TimeFormatter.getTimeDifference(createdAt);
    }



}
