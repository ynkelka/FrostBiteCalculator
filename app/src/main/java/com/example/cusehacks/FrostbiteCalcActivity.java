package com.example.cusehacks;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

public class FrostbiteCalcActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        Button btn = findViewById(R.id.dial);
        btn.setOnClickListener(new View.OnClickListener() { - this is the line that crashed it
            @Override
            public void onClick(View v) {
                {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:911"));
                    startActivity(callIntent);
                }
            }
        });
        */
        setContentView(R.layout.activity_frostbite_calc);
        String layerNum = "Inputted # of layers: " + getIntent().getStringExtra("com.example.cusehacks.MESSAGE");
        TextView t1 = (TextView) findViewById(R.id.layerN);
        t1.setText(layerNum);
        calcFrostBite();
    }

    //new way
    public void dial_onClick(View view){
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:911"));

        startActivity(callIntent);
    }

    public void calcFrostBite(){
        String layerNum = getIntent().getStringExtra("com.example.cusehacks.MESSAGE");
        int T = 5;
        int V = 30;
        TextView temp = (TextView) findViewById(R.id.temp);
        TextView wind = (TextView) findViewById(R.id.wind);
        String currentTemp = "Current Temperature: " + T + "°F";
        String currentWind = "Current windspeed: " + V + " mph";
        temp.setText(currentTemp);
        wind.setText(currentWind);
        double WC;


        final TextView frostbiteDisplay = (TextView) findViewById(R.id.frostbiteT);
        if(V >= 3) {
             WC = (35.74 + 0.6215 * T) - (35.75 * Math.pow(V, 0.16)) + (0.4275 * T * Math.pow(V, 0.16));
        }
        else{
            WC = T;
        }

        if(layerNum.equals("1")){
            WC -= 5;

        }
        else if(layerNum.equals("3")){
            WC += 3;
        }
        else if(layerNum.equals("4+")){
            WC += 5;
        }


        float time = 0;

        if (T >= 33){
            String output  = "You are most likely not at risk of frostbite in current conditions.";
            frostbiteDisplay.setText(output);
        }
        else if (WC >= -17){
            String output  = "You are most likely not at risk of frostbite in current conditions.";
            frostbiteDisplay.setText(output);
        }
        else if(WC >= -33){
            time = 30;
        }
        else if(WC >= -55){
            time = 10;
        }
        else {
            time = 5;
        }
        if(time != 0) {
            String output = "You'll likely get frostbite in " + time + " minutes.";
            frostbiteDisplay.setText(output);

            new CountDownTimer((long)(time * 60000), (long)1000) {
                TextView timer = (TextView) findViewById(R.id.timer);

                public void onTick(long millisUntilFinished) {
                    String hms = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)), TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
                    timer.setText(hms);
                }

                public void onFinish() {
                    timer.setText(" ");
                    frostbiteDisplay.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                    frostbiteDisplay.setText("Symptoms of frostbite:\n•First response - cold skin, prickling feeling\n•numbness\n•change in appearance of skin\n\nSymptoms of hypothermia:\n•First response - shivering\n•slurred speech\n•slow, shallow breath");

                }
            }.start();
        }
    }
}
