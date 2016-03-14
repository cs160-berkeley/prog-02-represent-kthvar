package com.example.keshav.represent;

/**
 */

import android.content.Intent;
import android.util.Log;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;

public class WatchListenerService extends WearableListenerService {

    private String zipcode;
    private String name;
    private String party;

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d("WatchListenerService", "in WatchListenerService, got: " + messageEvent.getPath());

        String pathresult=messageEvent.getPath();
        if(pathresult.equals("/zipCode")){
            zipcode=new String(messageEvent.getData(), StandardCharsets.UTF_8);
            Log.d("WatchListenerService", "zipcode");

        }
        else if (pathresult.equals("/nameresult")){
            name=new String(messageEvent.getData(), StandardCharsets.UTF_8);
            Log.d("WatchListenerService", "nameresult");
        }
        else if(pathresult.equals("/partyresult")){
            party=new String(messageEvent.getData(), StandardCharsets.UTF_8);
            Intent intent = new Intent(this, MainActivity.class );
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("zipcode", zipcode);
            intent.putExtra("name", name);
            intent.putExtra("party", party);
            Log.d("WatchListenerService", "partyresult");
            startActivity(intent);
        }

        //String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
        super.onMessageReceived(messageEvent);
    }
}