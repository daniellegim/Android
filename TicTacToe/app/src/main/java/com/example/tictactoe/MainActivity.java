package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;

    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};

    public static int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        if (gameActive) {
            if (gameState[tappedImage] == 2) {
                counter++;

                if (counter == 9) {
                    gameActive = false;
                }

                gameState[tappedImage] = activePlayer;

                if (activePlayer == 0) {
                    img.setImageResource(R.drawable.x);
                    activePlayer = 1;

                    ImageView status = findViewById(R.id.status);
                    status.setImageResource(R.drawable.oplay);
                } else {
                    img.setImageResource(R.drawable.o);
                    activePlayer = 0;

                    ImageView status = findViewById(R.id.status);
                    status.setImageResource(R.drawable.xplay);
                }
            }
            int flag = 0;
            // Check if any player has won
            for (int i = 0; i < winPositions.length; i++) {
                int[] winPosition = winPositions[i];
                if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                        gameState[winPosition[1]] == gameState[winPosition[2]] &&
                        gameState[winPosition[0]] != 2) {
                    flag = 1;

                    gameActive = false;
                    ImageView status = findViewById(R.id.status);

                    if (gameState[winPosition[0]] == 0) {
                        status.setImageResource(R.drawable.xwin);
                    } else {
                        status.setImageResource(R.drawable.owin);
                    }

                    ImageView win = findViewById(R.id.win);
                    switch(i) {
                        case 0:
                            win.setImageResource(R.drawable.mark6);
                            break;
                        case 1:
                            win.setImageResource(R.drawable.mark7);
                            break;
                        case 2:
                            win.setImageResource(R.drawable.mark8);
                            break;
                        case 3:
                            win.setImageResource(R.drawable.mark3);
                            break;
                        case 4:
                            win.setImageResource(R.drawable.mark4);
                            break;
                        case 5:
                            win.setImageResource(R.drawable.mark5);
                            break;
                        case 6:
                            win.setImageResource(R.drawable.mark1);
                            break;
                        case 7:
                            win.setImageResource(R.drawable.mark2);
                            break;
                    }

                    Button playAgain = (Button)findViewById(R.id.button_again);
                    playAgain.setVisibility(View.VISIBLE);
                }
            }
            // set the status if the match draw
            if (counter == 9 && flag == 0) {
                ImageView status = findViewById(R.id.status);
                status.setImageResource(R.drawable.nowin);

                Button playAgain = (Button)findViewById(R.id.button_again);
                playAgain.setVisibility(View.VISIBLE);
            }
        }
    }

    public void gameReset(View view) {
        gameActive = true;
        activePlayer = 0;
        counter = 0;
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        // remove all the images from the boxes inside the grid
        ((ImageView) findViewById(R.id.imageView0)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.win)).setImageResource(R.drawable.empty);

        ImageView status = findViewById(R.id.status);
        status.setImageResource(R.drawable.xplay);

        Button playAgain = (Button)findViewById(R.id.button_again);
        playAgain.setVisibility(View.INVISIBLE);
    }
}