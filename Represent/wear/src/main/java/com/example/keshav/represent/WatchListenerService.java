package com.example.keshav.represent;

/**
 */

import android.content.Intent;
import android.util.Log;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;

public class WatchListenerService extends WearableListenerService {

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d("WatchListenerService", "in WatchListenerService, got: " + messageEvent.getPath());

        String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
        Intent intent = new Intent(this, MainActivity.class );
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("ZIP_CODE", value);
        Log.d("WatchListenerService", "about to start watch MainActivity with CAT_NAME: Fred");
        startActivity(intent);
        super.onMessageReceived(messageEvent);
    }
}