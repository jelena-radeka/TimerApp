package com.example.timerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView countdownTv;
    SeekBar seekBar;
    Boolean counterActive=false;
    Button goBtn;
    CountDownTimer countDownTimer;

   public void goCountdown(View view) {

       if (counterActive) {
           countdownTv.setText("0:30");
           seekBar.setProgress(30);
           seekBar.setEnabled(true);
           countDownTimer.cancel();
           goBtn.setText("GO");
           counterActive = false;

       } else {

           counterActive = true;
           seekBar.setEnabled(false);
           goBtn.setText("STOP");
           countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 +100, 1000) {


               @Override
               public void onTick(long l) {
                   updateTimer((int) l / 1000);
               }

               @Override
               public void onFinish() {
                   MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.alert);
                   mediaPlayer.start();
                   countdownTv.setText("0:30");
                   seekBar.setProgress(30);
                   seekBar.setEnabled(true);
                   countDownTimer.cancel();
                   goBtn.setText("GO");
                   counterActive = false;

               }
           }.start();
       }
   }
    public void  updateTimer(int leftSeconds){
        int minutes=leftSeconds/60;
        int seconds=leftSeconds- (minutes*60);

        String seconds1=Integer.toString(seconds);
        if(seconds <=9){
            seconds1="0" + seconds1;

        }

        countdownTv.setText(Integer.toString(minutes)+ ":" + seconds1);


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         seekBar=findViewById(R.id.seekBar);
         countdownTv=findViewById(R.id.countdownTv);
         goBtn=findViewById(R.id.goBtn);

        seekBar.setMax(600);
        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
             updateTimer(i);
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
