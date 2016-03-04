package com.example.keshav.represent;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.Serializable;

/**
 * Created by Keshav on 3/2/16.
 */
public class DetailActivity extends AppCompatActivity{
    private Button button3;
    private ImageView piccand;
    private TextView namet;
    private TextView partyt;
    private TextView termendt;
    private TextView committeest;
    private TextView billst;


    int img_resource = R.drawable.barbara;
    String name = "Senator Barbara Boxer";
    String party ="DEMOCRAT";
    String termend="Term End: January 3, 2017";
    String committees="Committees: Ethics, Environment and Public Works, Foreign Relations";
    String bills="Bills: Female Veteran Suicide Prevention Act (2/3/2016), End of Suffering Act (10/22/2015)";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        piccand=(ImageView) findViewById(R.id.rep_img);
        piccand.setImageResource(img_resource);
        namet = (TextView) findViewById(R.id.rep_name);
        namet.setText(name);
        partyt = (TextView)findViewById(R.id.rep_party);
        partyt.setText(party);
        termendt = (TextView)findViewById(R.id.rep_termend);
        termendt.setText(termend);
        committeest = (TextView)findViewById(R.id.rep_committees);
        committeest.setText(committees);
        billst = (TextView)findViewById(R.id.rep_bills);
        billst.setText(bills);





        button3=(Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();

            }


        });

    }


}
