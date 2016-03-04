package com.example.keshav.represent;

import android.os.Bundle;
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
import android.widget.Toast;
import android.content.Intent;
import android.widget.ListView;

import java.io.Serializable;


public class Reps extends AppCompatActivity {
    private Button button2;
    ListView listview;
    int twot=R.drawable.twot;
    int[] img_resource = {R.drawable.barbara, R.drawable.dianne, R.drawable.nancy, R.drawable.jackie};
    String[] name = {"Senator Barbara Boxer","Senator Dianne Feinstein","Rep Nancy Pelosi","Rep Jackie Speier"};
    String[] party ={"Democrat","Democrat","Democrat","Democrat",};
    String[] email ={"barbara.boxer@gmail.com","dianne.feinstein@gmail.com","nancy.pelosi@gmail.com","jackie.speier@gmail.com"};
    String[] website={"barbaraboxer.com","feinstein.net","pelosi.gov","jackiespeier.com"};
    String[] tweet={"Putting the country first means Obama nominating a Justice and the Senate doing its constitutional duty by voting on the nominee.",
    "#BlackHistoryMonth: My friend Willie Brown was the first African-American mayor of San Francisco.",
    " I am determined that the president will find the most suitable person for the position.",
    "Make America Great Again!"};
    String[] termend={"January 3, 2017","February 7, 2018","November 8, 2016","October 21, 2016"};
    String[] committees={"Ethics, Environment and Public Works, Foreign Relations","Public Policy, Science and Technology",
    "Agriculture, Appropriations","Banking and Financial Service"};
    String[] bills={"Female Veteran Suicide Prevention Act (2/3/2016), Tule Lake Establishment(12/17/15)","Feinstein Act(3/1/16)",
            "No bills passed","End of Suffering Act(10/22/15)"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reps_layout);
        listview=(ListView) findViewById(R.id.main_list_view);
        RepAdapter adapter=new RepAdapter(getApplicationContext(),R.layout.row_layout);
        listview.setAdapter(adapter);
        int i=0;
        for(String Name: name){
            RepCand obj =new RepCand(img_resource[i], twot, Name, party[i], email[i],website[i],tweet[i],termend[i],committees[i],bills[i]);
            adapter.add(obj);
            i++;
        }
        Intent mIntent=getIntent();
        int location = mIntent.getIntExtra("location", 94101);
        int zip = mIntent.getIntExtra("zip",94101);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            RepCand itema;
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itema=new RepCand(img_resource[position], twot, name[position], party[position], email[position],
                        website[position],tweet[position],termend[position], committees[position], bills[position]);
                Intent inte=new Intent(Reps.this,DetailActivity.class);
                inte.putExtra("congman", (Serializable) itema);
                startActivity(inte);

            }
        });



        button2=(Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();

            }


        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        onBackPressed();
        return true;
    }
}
