package com.example.elsayedfahmy_pc.myweather_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent newIntent=getIntent();
        String value=newIntent.getStringExtra("C");
        TextView txt=(TextView)findViewById(R.id.txtDetails);
        txt.setText(value);
    }
}
