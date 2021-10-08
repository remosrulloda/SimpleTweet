package com.codepath.apps.restclienttemplate.models;

import android.provider.ContactsContract;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.codepath.apps.restclienttemplate.TimeFormatter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
@Entity(foreignKeys = @ForeignKey(entity= User.class, parentColumns="id", childColumns="userId"))
public class Tweet {

    @ColumnInfo
    @PrimaryKey
    public long id;

    @ColumnInfo
    public String body;

    @ColumnInfo
    public String createdAt;

    @ColumnInfo
    public long userId;

    @ColumnInfo
    public String username;

    @Ignore
    public User user;

    // empty constructor needed by the Parceler library
    public Tweet(){}

    // Give in a JSON object that represent a tweet and turn it into a Java Tweet Object
    public static Tweet fromJson(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet(); // Build the tweet as per the fields
        tweet.body = jsonObject.getString("text"); // from API
        tweet.createdAt = jsonObject.getString("created_at");
//        tweet.username = jsonObject.getString("name");
        tweet.id = jsonObject.getLong("id");
        // will take in a JSON object and returns the User model
        User user = User.fromJson(jsonObject.getJSONObject("user"));
        tweet.user = user; // this refers to JSON object but we need a JAVA object
        tweet.userId = user.id;
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
