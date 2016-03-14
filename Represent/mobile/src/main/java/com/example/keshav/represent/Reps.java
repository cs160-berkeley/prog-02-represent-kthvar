package com.example.keshav.represent;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.view.ViewGroup;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.app.Activity;
import android.widget.Spinner;
import android.widget.TextView;
import twitter4j.*;
import twitter4j.conf.*;
import twitter4j.TwitterAPIConfiguration.*;
import twitter4j.management.*;
import twitter4j.api.*;



import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;
import com.koushikdutta.async.future.FutureCallback;
import android.widget.Toast;
import android.content.Intent;
import android.widget.ListView;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;


public class Reps extends AppCompatActivity {

    private Handler uiThreadHandler = new Handler(Looper.getMainLooper());

    private Button button2;
    private Context context = getBaseContext();
    ListView listview;
    Twitter twitter;
    TwitterFactory tf;
    String url;
    RepAdapter adapter;
    int twot = R.drawable.twot;
    String longitude, latitude;
    ArrayList<String> twitterpics = new ArrayList<String>();
    ArrayList<String> name = new ArrayList<String>();
    ArrayList<String> party = new ArrayList<String>();
    ArrayList<String> email = new ArrayList<String>();
    ArrayList<String> website = new ArrayList<String>();
    ArrayList<String> tweet = new ArrayList<String>();
    ArrayList<String> termend = new ArrayList<String>();
    ArrayList<String> committees = new ArrayList<String>();
    ArrayList<String> bills = new ArrayList<String>();

    HashMap<String, String> partymap = new HashMap<String, String>();
    HashMap<String, String> emailmap = new HashMap<String, String>();
    HashMap<String, String> webmap = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent mIntent = getIntent();
        String zip = mIntent.getStringExtra("ZIP_CODE");
        longitude = mIntent.getStringExtra("longitude");
        latitude = mIntent.getStringExtra("latitude");
        url = urlmaker(longitude, latitude, zip);
        Log.d("url", url);
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("NyvFT8Tesn4VZlSK5SxNU0tVq")
                .setOAuthConsumerSecret("hWOILw9yLoi7NkCcZYBOs0nQRXTrNAaAIxNSqmDbgNxR5081pR")
                .setOAuthAccessToken("709166645390934016-OEdUSYcCNSm0EjbmusQEFnHzw3tBZt8")
                .setOAuthAccessTokenSecret("irILwYMLUjNlipt4TSZKplZHl88CmkHiiepxM8BKvQByw");
        tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
        getall(context, url);
        setContentView(R.layout.reps_layout);
        listview = (ListView) findViewById(R.id.main_list_view);
        adapter = new RepAdapter(getApplicationContext(), R.layout.row_layout);
        listview.setAdapter(adapter);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            RepCand itema;

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itema = (RepCand) adapter.getItem(position);
                Intent inte = new Intent(Reps.this, DetailActivity.class);
                inte.putExtra("congman", (Serializable) itema);
                startActivity(inte);

            }
        });


        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();

            }


        });

    }

    public void getall(Context context, String url) {

        Ion.with(getBaseContext())
                .load(url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        // do stuff with the result or error
                        JsonArray reps = result.getAsJsonArray("results");
                        for (int i = 0; i < reps.size(); i++) {
                            JsonElement ithrep = reps.get(i);
                            JsonObject rep = ithrep.getAsJsonObject();
                            String nameofrep = (String) rep.get("title").toString() + rep.get("first_name").toString() + rep.get("last_name").toString();
                            nameofrep = nameofrep.replace("\"", " ");
                            nameofrep = nameofrep.replace("  ", " ");
                            name.add(nameofrep);
                            String repparty = rep.get("party").toString();
                            repparty = repparty.replace("\"", " ");
                            repparty = repparty.replace("  ", "");
                            repparty = repparty.replace(" ", "");
                            if (repparty.equals("D")) {
                                partymap.put(nameofrep, "Democrat");
                                party.add("Democrat");
                            } else if (repparty.equals("R")) {
                                partymap.put(nameofrep, "Republican");
                                party.add("Republican");
                            } else {
                                partymap.put(nameofrep, "Other");
                                party.add("Other");
                            }
                            String mail = rep.get("oc_email").toString();
                            mail = mail.replace("\"", " ");
                            mail = mail.replace("  ", "");
                            emailmap.put(nameofrep, mail);
                            email.add(mail);
                            String web = rep.get("website").toString();
                            web = web.replace("\"", " ");
                            web = web.replace("  ", "");
                            webmap.put(nameofrep, web);
                            website.add(web);
                            String termending = rep.get("term_end").toString();
                            termending = termending.replace("\"", " ");
                            termending = termending.replace("  ", "");
                            termend.add(termending);
                            String bioid = rep.get("bioguide_id").toString();
                            bioid = bioid.replace("\"", " ");
                            bioid = bioid.replace("  ", "");
                            bioid = bioid.replace(" ", "");
                            Log.d("bioid", bioid);
                            getBills(bioid, i);
                            getCommittees(bioid, i);
                            String twitterid = rep.get("twitter_id").toString();
                            twitterid = twitterid.replace("\"", " ");
                            twitterid = twitterid.replace("  ", "");
                            twitterid = twitterid.replace(" ", "");
                            String url = "https://twitter.com/" + twitterid + "/profile_image?size=original";
                            twitterpics.add(url);
                            getTweet(twitterid, i);
                            Log.d("imgurl", url);
                        }
                        int i = 0;
                        for (String Name : name) {
                            Log.d("i", termend.get(i));
                            RepCand obj = new RepCand(twitterpics.get(i), twot, Name, party.get(i), email.get(i), website.get(i), "t", termend.get(i), "com", "b");
                            adapter.add(obj);
                            i++;
                        }
                        Intent sendtowatch=new Intent(Reps.this,PhoneToWatchService.class);
                        Log.d("nullcheck",name.get(0));
                        sendtowatch.putStringArrayListExtra("names", name);
                        sendtowatch.putStringArrayListExtra("party",party);
                        sendtowatch.putExtra("ZIP_CODE",getIntent().getStringExtra("ZIP_CODE"));
                        startService(sendtowatch);

                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        onBackPressed();
        return true;
    }

    public String urlmaker(String longitude, String latitude, String zipcode) {
        if ((longitude == null | latitude == null)) {
            return "http://congress.api.sunlightfoundation.com/legislators/locate?zip=" + zipcode + "&apikey=027cb4c2eec241db8aeb9a5b48c9da1e";
        }
        return "http://congress.api.sunlightfoundation.com/legislators/locate?latitude=" + latitude + "&longitude=" + longitude + "&apikey=027cb4c2eec241db8aeb9a5b48c9da1e";
    }

    public void getBills(final String bioid, final int i) {
        Ion.with(getBaseContext())
                .load("http://congress.api.sunlightfoundation.com/bills?sponsor_id=" + bioid + "&apikey=027cb4c2eec241db8aeb9a5b48c9da1e")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        Log.d("url", "http://congress.api.sunlightfoundation.com/bills?sponsor_id=" + bioid + "&apikey=027cb4c2eec241db8aeb9a5b48c9da1e");
                        Log.d("load_bills", "result is: " + result.toString());
                        JsonArray bills = result.getAsJsonArray("results");
                        String res = "Bills: ";
                        for (int i = 0; i < bills.size(); i++) {
                            JsonElement comm = bills.get(i);
                            JsonObject commi = comm.getAsJsonObject();
                            String finbill = commi.get("official_title").toString();
                            finbill = finbill.replace("\"", " ");
                            finbill = finbill.replace("  ", "");
                            if (finbill == null) {
                                break;
                            }
                            res += finbill + " \n";
                        }
                        adapter.setBills(i, res);

                        // do stuff with the result or error
                    }
                });
    }

    public void getCommittees(final String bioid, final int i) {
        Ion.with(getBaseContext())
                .load("http://congress.api.sunlightfoundation.com/committees?member_ids=" + bioid + "&apikey=027cb4c2eec241db8aeb9a5b48c9da1e")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        Log.d("load_comms", "result is: " + result.toString());
                        JsonArray bills = result.getAsJsonArray("results");
                        String res = "Committees: ";
                        for (int i = 0; i < bills.size(); i++) {
                            JsonElement comm = bills.get(i);
                            JsonObject commi = comm.getAsJsonObject();
                            String finbill = commi.get("name").toString();
                            finbill = finbill.replace("\"", " ");
                            finbill = finbill.replace("  ", "");
                            if (finbill == null) {
                                break;
                            }
                            res += finbill + " \n";
                        }
                        adapter.setCommittees(i, res);
                        // do stuff with the result or error
                    }
                });
    }

    public void getTweet(final String twitterid, final int i){
            Runnable loadTweetRunnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        final List<Status> statuses = twitter.timelines().getUserTimeline(twitterid);
                        uiThreadHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                adapter.setTweet(i, statuses.get(0).getText()); // TODO
                            }
                        });
                    }  catch (TwitterException e) {
                        e.printStackTrace();
                    }

                }
            };
            new Thread(loadTweetRunnable).start();
    }
}
