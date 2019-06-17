package com.example.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
     TextView timer;
     Button controllerButton;
     CountDownTimer countDownTimer;
     boolean counterIsActive=false;
     public void resetTimer(){
         timer.setText("0:00");
         seekBar.setProgress(30);
         controllerButton.setText("Go");
         seekBar.setEnabled(true);
         countDownTimer.cancel();
         counterIsActive =false;

     }
    public void updateTimer(int secLeft){
        int min=secLeft/60;
        int sec=secLeft-(min*60);
        String secString =Integer.toString(sec);

         if(sec <=9){
            secString="0"+secString;
        }
        String firstString=Integer.toString(min);
        timer.setText(firstString + ":" +secString);


    }
    public void timerStart(View view) {
        if (counterIsActive== false) {
            controllerButton.setText("Stop");
            counterIsActive = true;
            seekBar.setEnabled(false);
            countDownTimer=new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished/1000);
                }

                 @Override
                public void onFinish() {
                    resetTimer();
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.traffic);
                    mplayer.start();
                }
            }.start();
        } else {
            resetTimer();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         controllerButton=findViewById(R.id.controllerButton);
       timer =  findViewById(R.id.timer);
        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(3300);
        seekBar.setProgress(30);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                 updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
