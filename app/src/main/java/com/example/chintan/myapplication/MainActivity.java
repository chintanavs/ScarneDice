package com.example.chintan.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {



    TextView PlayerRoundScore,PlayerTurnScore,ComputerRoundScore,ComputerTurnScore;
    int[] diceImage = new int[]{R.drawable.dice1,R.drawable.dice2,R.drawable.dice3,R.drawable.dice4,R.drawable.dice5,R.drawable.dice6};
    int activePlayer=1;
    ImageView diceView;
    Button roll,reset,hold,button;
    int counter;
    int turnScore0=0,turnScore1=0,totalScore0=0,totalScore1=0;

    final Handler handler = new Handler();

    public void ComputerTurn(){

            handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                roll.performClick();
            }
        }, 1000);
    }




    int randomValue(int v){

        //used to get random value
        int diceValue;
        Random random=new Random();
        diceValue=random.nextInt(v)+1;
        //Log.i("diceValue", String.valueOf(diceValue));
        return  diceValue;

    }

    public void hold(View view){


        if(activePlayer==1){
            totalScore1 += turnScore1;
            PlayerRoundScore.setText("PlayerRoundScore:"+totalScore1);
            PlayerTurnScore.setText("PlayerTurnScore:0");
            turnScore1=0;
            activePlayer=0;
            counter=randomValue(8);
            Toast.makeText(this, "Computer Plays!", Toast.LENGTH_SHORT).show();
            ComputerTurn();
        }else{
            totalScore0 += turnScore0;
            ComputerRoundScore.setText("ComputerRoundScore:"+totalScore0);
            ComputerTurnScore.setText("ComputerTurnScore:0");
            Toast.makeText(this, "Computer is on Hold!", Toast.LENGTH_SHORT).show();
            turnScore0=0;
            activePlayer=1;
        }

    }


    public void reset(View view){

        turnScore0=0;
        turnScore1=0;
        totalScore0=0;
        totalScore1=0;
        activePlayer=1;
        PlayerTurnScore.setText("PlayerRoundScore:0");
        ComputerTurnScore.setText("ComputerTurnScore:0");
        PlayerRoundScore.setText("PlayerTurnScore:0");
        ComputerRoundScore.setText("ComputerRoundScore:0");
        diceView.setImageResource(0);

    }

    public void roll(View view){


        int value;
        if(totalScore0<20 && totalScore1<20) {

            if (activePlayer == 0) {

                //computer plays
                if (counter < randomValue(25)) {

                    value = randomValue(5);
                    diceView = findViewById(R.id.diceView);
                    diceView.setImageResource(diceImage[value - 1]);
                    if (value != 1) {
                        turnScore0 += value;

                        ComputerTurnScore.setText("ComputerTurnScore:" + turnScore0);
                        counter++;
                        ComputerTurn();

                    } else {
                        turnScore0 = 0;
                        turnScore1 = 0;
                        Toast.makeText(this, "Players Turn!!", Toast.LENGTH_SHORT).show();
                        ComputerRoundScore.setText("ComputerRoundScore:" + totalScore0);
                        ComputerTurnScore.setText("ComputerTurnScore:0");
                        activePlayer = 1;
                    }


                } else {
                    hold(view);
                }
            } else if (activePlayer == 1) {

                //player plays
                value = randomValue(5);
                diceView = findViewById(R.id.diceView);
                diceView.setImageResource(diceImage[value - 1]);

                if (value != 1) {

                    turnScore1 += value;
                    PlayerTurnScore.setText("PlayerTurnScore:" + turnScore1);

                } else {
                    turnScore1 = 0;
                    turnScore0 = 0;
                    activePlayer = 0;
                    counter = randomValue(8);
                    Toast.makeText(this, "Computer Plays!!", Toast.LENGTH_SHORT).show();
                    PlayerTurnScore.setText("PlayerTurnScore:0");
                    PlayerRoundScore.setText("PlayerRoundScore:" + totalScore1);
                    ComputerTurn();

                }

            }

        }
        else{
            if(totalScore1>totalScore0){
                Toast.makeText(this, "YOU WON!!", Toast.LENGTH_SHORT).show();
                reset(view);
            }
            else{
                Toast.makeText(this, "Computer Wins!!", Toast.LENGTH_SHORT).show();
                reset(view);
            }
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PlayerRoundScore=(TextView)findViewById(R.id.PlayerRoundScore);
        PlayerTurnScore=(TextView)findViewById(R.id.PlayerTurnScore);
        ComputerRoundScore=(TextView)findViewById(R.id.ComputerRoundScore);
        ComputerTurnScore=(TextView)findViewById(R.id.ComputerTurnScore);
        roll=(Button)findViewById(R.id.roll);
        reset=(Button)findViewById(R.id.reset);
        hold=(Button)findViewById(R.id.hold);




    }
}
