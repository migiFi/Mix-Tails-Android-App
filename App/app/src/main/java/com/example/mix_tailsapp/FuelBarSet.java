package com.example.mix_tailsapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class FuelBarSet extends AppCompatActivity {

    SeekBar drinkAmount;
    ProgressBar connection;
    TextView nums, emo;
    Button send;
    protected static final String LIMIT_AMOUNT = "WE_ARE_DRINKING_ONLY_WHEN_IT_IS_TIME_FOR_THAT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_bar_set);

        drinkAmount = findViewById(R.id.amountOfDrinks);
        connection =  findViewById(R.id.DrinksNum);
        nums = findViewById(R.id.drinksInsideaCircle);
        emo = findViewById(R.id.emotion);
        send = findViewById(R.id.buttonSetLimit);

        drinkAmount.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int drinkNum = 3;
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                drinkNum = progress;
                connection.setProgress(drinkNum*10, true);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                nums.setText(Integer.toString(drinkNum));

                if(drinkNum == 25){
                    emo.setText(R.string.fuel_text_1);
                } else if(drinkNum < 25 && drinkNum >=20){
                    emo.setText(R.string.fuel_text_2);
                } else if(drinkNum < 20 && drinkNum >= 15){
                    emo.setText(R.string.fuel_text3);
                } else if(drinkNum < 15 && drinkNum >= 7){
                    emo.setText(R.string.fuel_text4);
                } else if(drinkNum < 7 && drinkNum >= 3){
                    emo.setText(R.string.fuel_text5);
                } else if(drinkNum < 3 && drinkNum >= 1){
                    emo.setText(R.string.fuel_text6);
                } else if(drinkNum == 0){
                    emo.setText(R.string.fuel_text7);
                }
                send.setOnClickListener(v ->  {
                    SharedPreferences drinkLimit = getSharedPreferences(SignupActivity.TEMP_STORAGE, Activity.MODE_PRIVATE);
                    Intent sender = new Intent(FuelBarSet.this, HomePage.class);
                    Toast.makeText(FuelBarSet.this, "Your limit is " + drinkNum + " drinks", Toast.LENGTH_LONG).show();
                    SharedPreferences.Editor amountEditor = drinkLimit.edit();
                    amountEditor.putInt(LIMIT_AMOUNT, drinkNum);
                    if(amountEditor.commit()){
                        startActivity(sender);
                        Log.d("FuelBar", Integer.toString(drinkNum));
                    }
                });
            }
        });


    }


}