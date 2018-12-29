package com.tr4n.moving;

import android.app.Activity;
import android.content.res.Resources;
import android.os.CountDownTimer;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {

    TextView Healthy_Point;
    TextView totalDistance;
    TextView txtv, viewDistanceToPokemon, viewValuePokemon;
    ImageView star;
    Button catchPokemon;
    ImageButton Object, viewPokemon;
    ImageButton[] Pokemon = new ImageButton[100];
    ImageButton[] PokemonDied = new ImageButton[100];
    final int WIDTH_OF_SCREEN = Resources.getSystem().getDisplayMetrics().widthPixels;
    final int HEIGHT_OF_SCREEN = Resources.getSystem().getDisplayMetrics().heightPixels;
    final int HEALTHY_POINT = 5000;
    final int SPEED_OF_OBJECT = 25;
    final int NUMBER_OF_POKEMON = 14;
    final int DISTANCE_AMONG_POKEMONS = 80;
    final int BROAD_WIDTH = ((WIDTH_OF_SCREEN * 4) / 5);
    final int BROAD_HEIGHT = ((HEIGHT_OF_SCREEN * 9) / 10);
    public View background;
    public RelativeLayout mainBroad;
    public LinearLayout rightBroad,bottomBroad;
    public static Random rand = new Random();
    public static boolean freeTouchInScreen = true;
    public static int distance = 0, distanceTotally = 0, sumOfPrice = 0;
    public static int recentX = 0, recentY = 0, recentViewPokemon = 1;
    public static int[][] gameBroad = new int[500][500];
    public static int[] valuePokemon = new int[100];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        DEFINE();
        Object.setImageResource(R.drawable.girldowna);
        mainBroad.setOnTouchListener(handleTouch);
        placePokemon();

    }

    public void DEFINE() {
        background = (View) findViewById(R.id.background);
        star = (ImageView) findViewById(R.id.star);
        catchPokemon = (Button) findViewById(R.id.btnCATCH);
        viewDistanceToPokemon = (TextView) findViewById(R.id.txtvDistancePokemon);
        viewValuePokemon = (TextView) findViewById(R.id.txtvValuePokemon);
        Healthy_Point = (TextView) findViewById(R.id.HealthyPoint);
        totalDistance = (TextView) findViewById(R.id.totalDistance);
        txtv = (TextView) findViewById(R.id.TMP);
        mainBroad = (RelativeLayout) findViewById(R.id.mainbroad);
        rightBroad = (LinearLayout) findViewById(R.id.rightBroad);
        bottomBroad = (LinearLayout) findViewById(R.id.underBroad);
        viewPokemon = (ImageButton) findViewById(R.id.viewImagePokemon);
        Object = (ImageButton) findViewById(R.id.Object);
        Pokemon[1] = (ImageButton) findViewById(R.id.Pokemon1);
        Pokemon[2] = (ImageButton) findViewById(R.id.Pokemon2);
        Pokemon[3] = (ImageButton) findViewById(R.id.Pokemon3);
        Pokemon[4] = (ImageButton) findViewById(R.id.Pokemon4);
        Pokemon[5] = (ImageButton) findViewById(R.id.Pokemon5);
        Pokemon[6] = (ImageButton) findViewById(R.id.Pokemon6);
        Pokemon[7] = (ImageButton) findViewById(R.id.Pokemon7);
        Pokemon[8] = (ImageButton) findViewById(R.id.Pokemon8);
        Pokemon[9] = (ImageButton) findViewById(R.id.Pokemon9);
        Pokemon[10] = (ImageButton) findViewById(R.id.Pokemon10);
        Pokemon[11] = (ImageButton) findViewById(R.id.Pokemon11);
        Pokemon[12] = (ImageButton) findViewById(R.id.Pokemon12);
        Pokemon[13] = (ImageButton) findViewById(R.id.Pokemon13);
        Pokemon[14] = (ImageButton) findViewById(R.id.Pokemon14);
        PokemonDied[1] = (ImageButton) findViewById(R.id.PokemonDied1);
        PokemonDied[2] = (ImageButton) findViewById(R.id.PokemonDied2);
        PokemonDied[3] = (ImageButton) findViewById(R.id.PokemonDied3);
        PokemonDied[4] = (ImageButton) findViewById(R.id.PokemonDied4);
        PokemonDied[5] = (ImageButton) findViewById(R.id.PokemonDied5);
        PokemonDied[6] = (ImageButton) findViewById(R.id.PokemonDied6);
        PokemonDied[7] = (ImageButton) findViewById(R.id.PokemonDied7);
        PokemonDied[8] = (ImageButton) findViewById(R.id.PokemonDied8);
        PokemonDied[9] = (ImageButton) findViewById(R.id.PokemonDied9);
        PokemonDied[10] = (ImageButton) findViewById(R.id.PokemonDied10);
        PokemonDied[11] = (ImageButton) findViewById(R.id.PokemonDied11);
        PokemonDied[12] = (ImageButton) findViewById(R.id.PokemonDied12);
        PokemonDied[13] = (ImageButton) findViewById(R.id.PokemonDied13);
        PokemonDied[14] = (ImageButton) findViewById(R.id.PokemonDied14);


    }

    public void placePokemon() {
        for (int i = 0; i <= BROAD_WIDTH / DISTANCE_AMONG_POKEMONS; i++) {
            for (int j = 0; j <= BROAD_HEIGHT / DISTANCE_AMONG_POKEMONS; j++) {
                gameBroad[i][j] = 0;
            }
        }
        for (int i = 1; i <= NUMBER_OF_POKEMON; i++) {
            while (1 == 1) {
                int x = 1 + (int) (rand.nextInt(BROAD_WIDTH - 3 * DISTANCE_AMONG_POKEMONS) / DISTANCE_AMONG_POKEMONS);
                int y = 1 + (int) (rand.nextInt(BROAD_HEIGHT - 3 * DISTANCE_AMONG_POKEMONS) / DISTANCE_AMONG_POKEMONS);
                if (gameBroad[x][y] == 0 && x * x + y * y > 0) {

                    gameBroad[x][y] = 1;
                    Pokemon[i].setX(x * DISTANCE_AMONG_POKEMONS);
                    Pokemon[i].setY(y * DISTANCE_AMONG_POKEMONS);
                    valuePokemon[i] = x * y;
                    break;
                }
            }
        }

         showInformationOfPokemon();
    }

    public void Pokemon_setOnClickListener(final int key) {
        if (!freeTouchInScreen) return;

        Pokemon[key].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!freeTouchInScreen) return;
                recentViewPokemon = key;
                viewImagePokemon(key);

            }
        });
    }

    public void showInformationOfPokemon() {
        if (!freeTouchInScreen) return;
        for (int i = 1; i <= NUMBER_OF_POKEMON; i++)
            Pokemon_setOnClickListener(i);
        catchPokemon();


    }

    public void viewImagePokemon(int key) {
        int XPokemon = (int) Pokemon[key].getX();
        int YPokemon = (int) Pokemon[key].getY();
        viewPokemon.setImageDrawable(Pokemon[key].getDrawable());
        int Distance = (int) Math.sqrt((recentX - XPokemon) * (recentX - XPokemon) + (recentY - YPokemon) * (recentY - YPokemon));
        viewDistanceToPokemon.setText(Distance +" m");
        viewValuePokemon.setText(2*valuePokemon[key] +" $" );
    }

    public void catchPokemon() {

        catchPokemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!freeTouchInScreen) return;
                int key = recentViewPokemon;
                PokemonDied[key].setImageDrawable(Pokemon[key].getDrawable());

                sumOfPrice += (valuePokemon[key] * 2);
                MakeMoving(Object, recentX, recentY, (int) Pokemon[key].getX(), (int) Pokemon[key].getY());
                star.setX(Pokemon[key].getX());
                star.setY(Pokemon[key].getY());
                int delta = (WIDTH_OF_SCREEN * (distanceTotally - sumOfPrice)) / HEALTHY_POINT;
                Healthy_Point.setX(-delta);
                txtv.setText("Money: " + sumOfPrice +" $");
                totalDistance.setText("Total Distance : " + distanceTotally + " km");
                Pokemon[key].setImageResource(0);
            }
        });


    }


    public void Moving(ImageButton imageButtonObject, int X0, int Y0, int X1, int Y1, int Time) {
        TranslateAnimation animation = new TranslateAnimation(X0, X1, Y0, Y1);       //    new TranslateAnimation(xFrom,xTo, yFrom,yTo)
        animation.setDuration(Time);  // animation duration
        animation.setRepeatCount(0);  // animation repeat count
        animation.setRepeatMode(0);   // repeat animation (left to right, right to left )
        animation.setFillAfter(true);
        imageButtonObject.startAnimation(animation);
    }

    private View.OnTouchListener handleTouch = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (!freeTouchInScreen) return false;
            int toX = (int) event.getX();
            int toY = (int) event.getY();
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:

                    break;
                case MotionEvent.ACTION_MOVE:
                    star.setX(toX);
                    star.setY(toY);
                    break;
                case MotionEvent.ACTION_UP:
                    MakeMoving(Object, recentX, recentY, toX, toY);
                    int delta = (WIDTH_OF_SCREEN * distanceTotally) / HEALTHY_POINT;
                    Healthy_Point.setX(-delta);
                    totalDistance.setText("Total Distance : " + distanceTotally + " m");
                    txtv.setText("Money: " + sumOfPrice +" $");
                    if(delta >= WIDTH_OF_SCREEN) freeTouchInScreen = false;

                    break;
            }
            return true;
        }
    };


    public void MakeMoving(ImageButton object, int fromX, int fromY, int toX, int toY) {
        if (!freeTouchInScreen) return;
        distance = (int) Math.sqrt((toX - fromX) * (toX - fromX) + (toY - fromY) * (toY - fromY));
        int Time = (int) (distance / SPEED_OF_OBJECT);
        distanceTotally += distance;
        int mode = 0;
        if (toX > fromX) {
            if (fromX - toX < toY - fromY && toY - fromY < toX - fromX)
                mode = 0;
            else if (toY > fromY)
                mode = 3;
            else
                mode = 1;
        } else {
            if (fromX - toX > toY - fromY && toY - fromY > toX - fromX)
                mode = 2;
            else if (toY > fromY)
                mode = 3;
            else
                mode = 1;
        }

        Moving(Object, fromX, fromY, toX, toY, 100 * Time);
        walkInTime(Time * 100, mode);
        recentX = toX;
        recentY = toY;
    }

    public void walkInTime(final long Time, final int mode) {
        if (!freeTouchInScreen) return ;
        freeTouchInScreen = false;
        new CountDownTimer(Time, 100) {
            public void onTick(long millisUntilFinished) {
                long time = millisUntilFinished / 100;
                if (mode == 0)
                    switch ((int) time % 3) {
                        case 2:
                            Object.setImageResource(R.drawable.girlrighta);
                            break;
                        case 1:
                            Object.setImageResource(R.drawable.girlrightb);
                            break;
                        case 0:
                            Object.setImageResource(R.drawable.girlrightc);
                            break;
                    }
                else if (mode == 1)
                    switch ((int) time % 3) {
                        case 2:
                            Object.setImageResource(R.drawable.girlupa);
                            break;
                        case 1:
                            Object.setImageResource(R.drawable.girlupb);
                            break;
                        case 0:
                            Object.setImageResource(R.drawable.girlupc);
                            break;
                    }
                else if (mode == 2)
                    switch ((int) time % 3) {
                        case 2:
                            Object.setImageResource(R.drawable.girllefta);
                            break;
                        case 1:
                            Object.setImageResource(R.drawable.girlleftb);
                            break;
                        case 0:
                            Object.setImageResource(R.drawable.girlleftc);
                            break;
                    }
                else if (mode == 3)
                    switch ((int) time % 3) {
                        case 2:
                            Object.setImageResource(R.drawable.girldowna);
                            break;
                        case 1:
                            Object.setImageResource(R.drawable.girldownb);
                            break;
                        case 0:
                            Object.setImageResource(R.drawable.girldownc);
                            break;
                    }
            }

            public void onFinish() {
                freeTouchInScreen = true;
            }
        }.start();
    }


}
