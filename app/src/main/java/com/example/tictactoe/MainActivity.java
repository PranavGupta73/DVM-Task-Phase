package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ImageViewCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.logging.XMLFormatter;

public class MainActivity extends AppCompatActivity {
    // 0-x
    //1-o
    int activePlayer=0;
    int[] gameState={2, 2, 2, 2, 2, 2, 2, 2, 2};
    //state meaning
    // 0-x
    // 1-o
    // 2-null
    int [][] winPositions={{0,1,2} , {3,4,5} , {6,7,8} , {0,3,6} , {1,4,7} , {2,5,8} , {0,4,8} , {2,4,6}};
    boolean gameActive = true;
    int z=0;
    private Button btn_restart;
    public void playerTap(View view){
        /*if(gameActive == false){
            gameReset(view);
        }*/
        ImageView img =(ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(gameState[tappedImage]==2 && gameActive==true){
            gameState[tappedImage]=activePlayer;
            img.setTranslationY(-1000f);
            if(activePlayer==0) {
                activePlayer = 1;
                img.setImageResource(R.drawable.cross);
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn");
            }
            else{
                    img.setImageResource(R.drawable.circle);
                    activePlayer=0;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn");

                }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        // Check if someone has won
        for(int[] winPosition: winPositions){
           if(gameState[winPosition[0]]==gameState[winPosition[1]] &&
                   gameState[winPosition[1]]==gameState[winPosition[2]] &&
                            gameState[winPosition[0]]!=2){
               String winnerStr;
               z=1;
              // gameActive = false;
               if(gameState[winPosition[0]]==0){
                   winnerStr = "X has Won";
                   TextView status = findViewById(R.id.status);
                   status.setText(winnerStr);
               }
               else{
                   winnerStr = "O  has Won";
                   TextView status = findViewById(R.id.status);
                   status.setText(winnerStr);

               }
               //Updating status bar for winner!

               gameActive = false;
               //TextView replay = findViewById(R.id.replay);
               //replay.setText("Tap On the grid to replay");
           }

        }
        if(z==0){
            int p=0;
            for(int i=0;i<9;i++){
                if(gameState[i]==0||gameState[i]==1)
                {
                 p++;
                }
            }
            if(p==9){
                TextView status = findViewById(R.id.status);
                status.setText("DRAW");

                //TextView replay = findViewById(R.id.replay);
               // replay.setText("Tap On the grid to replay");
               // gameActive = false;
            }
        }

    }
    /*public void gameReset (View view) {
        gameActive = true;
        int activePlayer=0;
        for(int i=0;i<9;i++){
            gameState[i]=2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        ((TextView)findViewById(R.id.replay)).setText(0);

        TextView status = findViewById(R.id.status);
        status.setText("X's Turn");
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_restart = findViewById(R.id.restartButton);

        btn_restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(getIntent());
            }
        });
    }

}
