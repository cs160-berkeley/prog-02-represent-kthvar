package com.example.keshav.represent;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.TextView;
import android.view.inputmethod.EditorInfo;
import android.content.Intent;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {
    private EditText edit_txt;
    public String zipCodeEntry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_txt = (EditText) findViewById(R.id.search_edit);

        edit_txt.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || ((event.getKeyCode() == KeyEvent.KEYCODE_ENTER))) {
                    zipCodeEntry = edit_txt.getText().toString();
                    Intent wIntent = new Intent(getBaseContext(), PhoneToWatchService.class); //watch
                    wIntent.putExtra("ZIP_CODE", zipCodeEntry);
                    //Log.d("represent", "here");
                    startService(wIntent);

                    Intent myintent = new Intent(MainActivity.this, Reps.class); //phone
                    startActivity(myintent);
                    return true;
                }
                return false;
            }
        });
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String fakeZip = "94101";
                Intent wIntent = new Intent(getBaseContext(), PhoneToWatchService.class); //watch
                wIntent.putExtra("ZIP_CODE", fakeZip);
//                Log.d("represent", "here");
                startService(wIntent);

                Intent myintent = new Intent(MainActivity.this, Reps.class); //phone
                startActivity(myintent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
