package com.example.euro_pc.mypapplication;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;

import static android.graphics.Color.RED;

public class MainActivity extends AppCompatActivity {

    public static final String COLOR_ID="color id";
    public static int picked_color = 0;
    private MediaPlayer backgroundPlayer;
    private MediaPlayer buttonPlayer;
    static public Uri sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button BackButton = findViewById(R.id.button);
        final Button ButtButton = findViewById(R.id.button2);
        final Button FontButton = findViewById(R.id.button3);
        final FloatingActionButton fab = findViewById(R.id.fab);

       sound=Uri.parse("android.resource://com.example.euro_pc.mypapplication/"+R.raw.dman);
       buttonPlayer=new MediaPlayer();
       buttonPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
       buttonPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                backgroundPlayer.pause();

                // mp.pause();
            }
        });
    buttonPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            backgroundPlayer.start();
        }
    });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonPlayer.reset();
                try{
                    buttonPlayer.setDataSource(getApplicationContext(),sound);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                buttonPlayer.prepareAsync();
            }
        } );

        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backPick = new Intent(getApplicationContext(),SecondActivity.class);
                backPick.putExtra(COLOR_ID,picked_color);
                startActivityForResult(backPick,1);
            }
        } );

        ButtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent buttPick = new Intent(getApplicationContext(),SecondActivity.class);
                buttPick.putExtra(COLOR_ID,picked_color);
                startActivityForResult(buttPick,2);
            }
        } );

        FontButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fontPick = new Intent(getApplicationContext(),SecondActivity.class);
                fontPick.putExtra(COLOR_ID,picked_color);
                startActivityForResult(fontPick,3);
            }
        } );


        }

    @Override
    protected void onPause() {
        super.onPause();
        backgroundPlayer.pause();
        buttonPlayer.pause();
    }
    @Override
    protected void onResume() {
        super.onResume();
        backgroundPlayer=MediaPlayer.create(this,R.raw.dman);
        backgroundPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                mp.start();
            }
        });
    }
    @Override
    protected void onStop(){
        super.onStop();
        backgroundPlayer.release();
    }

     public void setActivityBackgroundColor(String clr) {
        View view = this.getWindow().getDecorView();
        switch(clr) {
            case "Red":
                view.setBackgroundColor(Color.RED);
                break;
            case "Green":
                view.setBackgroundColor(Color.GREEN);
                break;
            case "Blue":
                view.setBackgroundColor(Color.BLUE);
                break;
            default:
                view.setBackgroundColor(Color.YELLOW);
                break;
        }
    }

    public void setButtonBackgroundColor(String clr) {
        Button BackButton = (Button)findViewById(R.id.button);
        Button ButtButton = (Button)findViewById(R.id.button2);
        Button FontButton = (Button)findViewById(R.id.button3);
        switch(clr) {
            case "Red":
                BackButton.setBackgroundColor(Color.RED);
                ButtButton.setBackgroundColor(Color.RED);
                FontButton.setBackgroundColor(Color.RED);
                break;
            case "Green":
                BackButton.setBackgroundColor(Color.GREEN);
                ButtButton.setBackgroundColor(Color.GREEN);
                FontButton.setBackgroundColor(Color.GREEN);
                break;
            case "Blue":
                BackButton.setBackgroundColor(Color.BLUE);
                ButtButton.setBackgroundColor(Color.BLUE);
                FontButton.setBackgroundColor(Color.BLUE);
                break;
            default:
                BackButton.setBackgroundColor(Color.YELLOW);
                ButtButton.setBackgroundColor(Color.YELLOW);
                FontButton.setBackgroundColor(Color.YELLOW);
                break;
        }
    }

    public void setButtonTextColor(String clr) {
        Button BackButton = findViewById(R.id.button);
        Button ButtButton = findViewById(R.id.button2);
        Button FontButton = findViewById(R.id.button3);
        switch(clr) {
            case "Red":
                BackButton.setTextColor(Color.RED);
                ButtButton.setTextColor(Color.RED);
                FontButton.setTextColor(Color.RED);
                break;
            case "Green":
                BackButton.setTextColor(Color.GREEN);
                ButtButton.setTextColor(Color.GREEN);
                FontButton.setTextColor(Color.GREEN);
                break;
            case "Blue":
                BackButton.setTextColor(Color.BLUE);
                ButtButton.setTextColor(Color.BLUE);
                FontButton.setTextColor(Color.BLUE);
                break;
            default:
                BackButton.setTextColor(Color.YELLOW);
                ButtButton.setTextColor(Color.YELLOW);
                FontButton.setTextColor(Color.YELLOW);
                break;
        }
    }

        @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        try{
            if(resultCode == RESULT_OK) {
                Toast.makeText(getApplicationContext(),data.getStringExtra(COLOR_ID),Toast.LENGTH_SHORT).show();
                switch(requestCode) {
                    case 1:
                        setActivityBackgroundColor(data.getStringExtra(COLOR_ID));
                        break;
                    case 2:
                        setButtonBackgroundColor(data.getStringExtra(COLOR_ID));
                        break;
                    case 3:
                        setButtonTextColor(data.getStringExtra(COLOR_ID));
                        break;
                }
            }
            else if(resultCode==RESULT_CANCELED){
                Toast.makeText(getApplicationContext(),getText(R.string.back_message),Toast.LENGTH_SHORT).show();
            }
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
        }
        }

    }
