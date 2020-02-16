package com.example.cusehacks;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class ClotingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloting);

        Button btn = findViewById(R.id.riskcalc);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MAIN ACTIVITY: ", "Button Clicked" );
                Intent intent = new Intent(ClotingActivity.this, FrostbiteCalcActivity.class);

                Spinner mySpinner = (Spinner) findViewById(R.id.layers);
                String message = mySpinner.getSelectedItem().toString();
                intent.putExtra("com.example.cusehacks.MESSAGE", message);
                startActivity(intent);
            }
        });


        String[] arraySpinner = new String[] {
                "1", "2", "3", "4+"
        };
        Spinner s = (Spinner) findViewById(R.id.layers);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);



    }
}
