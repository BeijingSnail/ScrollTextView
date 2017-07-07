package com.example.snail.scrolltextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DoubleVertical doubleVertical = (DoubleVertical) findViewById(R.id.double_vertical);
        doubleVertical.start();
    }

}
