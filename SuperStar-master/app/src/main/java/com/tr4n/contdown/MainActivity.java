package com.tr4n.contdown;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView star;
    EditText solution;
    TextView NumberofMoving;
    TextView score, result;
    public static ImageView[] imgPoint = new ImageView[50];


    Button[] button = new Button[10];
    Button Reset;
    public static int Score = 0, SCORE = 0;
    public static int numberofMove = 18;
    final int Nmax = 800, Mmax = 900;
    final int[] Y = {-100, 0, 100, 0};
    final int[] X = {0, -100, 0, 100};
    public static int[][] Caro = new int[50][50];
    public static Point[] point = new Point[50];
    public static int[] pointX = new int[50];
    public static int[] pointY = new int[50];
    public static int numberPoint = 5;
    public static int X0 = 0, Y0 = 0;
    public static Random rand = new Random();
    public static int[][] Dmin = new int[50][50];
    public static boolean[] used = new boolean[20];
    public static int[] Res = new int[20];
    public final int oo = 1999999999;
    public static int Smin = 999999999;
    public static int M = 10;
    public static int N = 9;
    public static int[] Target = new int[20];
    public static int help = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        result = (TextView) findViewById(R.id.textView3);
        star = (ImageView) findViewById(R.id.star);
        button[0] = (Button) findViewById(R.id.Up);
        button[1] = (Button) findViewById(R.id.Left);
        button[2] = (Button) findViewById(R.id.Down);
        button[3] = (Button) findViewById(R.id.Right);
        Reset = (Button) findViewById(R.id.RESET);
        imgPoint[1] = (ImageView) findViewById(R.id.Point1);
        imgPoint[2] = (ImageView) findViewById(R.id.Point2);
        imgPoint[3] = (ImageView) findViewById(R.id.Point3);
        imgPoint[4] = (ImageView) findViewById(R.id.Point4);
        imgPoint[5] = (ImageView) findViewById(R.id.Point5);
        NumberofMoving = (TextView) findViewById(R.id.textView);
        score = (TextView) findViewById(R.id.textView2);
        solution = (EditText) findViewById(R.id.hint);


        Init();
        Target[0] = 0;

        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                while ( 1==1)
                {
                    if(help == 0) help ++;
                    int k = Target[help];
                    if (pointX[k] < 0 || pointY[k] < 0)
                        help++;
                    else {
                       /* Toast.makeText(MainActivity.this, "Goto (" + String.valueOf(pointX[k]) + "," + String.valueOf(pointY[k]) + "):" +
                                String.valueOf(Dmin[Target[help - 1]][k]) + "-steps", Toast.LENGTH_SHORT).
                                show();
                                */
                       solution.setText("Goto (" + String.valueOf(pointX[k]) + "," + String.valueOf(pointY[k]) + "):" +
                               String.valueOf(Dmin[Target[help - 1]][k]) + "steps");
                        break;
                    }
                }

                NumberofMoving.setText("" + numberofMove);
                result.setText("");


            }
        });


    }


    public void Init() {

        for (int i = 0; i <= 2 * M + 1; i++) {
            for (int j = 0; j <= 2 * N + 1; j++) {
                Caro[i][j] = 0;
            }
        }


        for (int k = 1; k <= numberPoint; k++) {
            while (true) {
                int x = rand.nextInt(M);
                int y = rand.nextInt(N);

                if (Caro[x][y] == 0 && (x * x + y * y != 0)) {
                    Caro[x][y] = 1;
                    pointX[k] = x;
                    pointY[k] = y;
                    break;

                }
            }
        }
        for (int k = 1; k <= numberPoint; k++) {
            imgPoint[k].setX(pointX[k] * 100);
            imgPoint[k].setY(pointY[k] * 100);
        }

        InitNumberMove();
        //  Toast.makeText(MainActivity.this," " + Smin,Toast.LENGTH_SHORT).show();
        SCORE = numberofMove;
        NumberofMoving.setText(String.valueOf(numberofMove));
        score.setText(String.valueOf(Score));
        for (int i = 0; i < 4; i++)
            Press(i);
    }


    public void Press(final int k) {
        button[k].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Move(star, k);
                score.setText(String.valueOf(SCORE));
                // Toast.makeText(MainActivity.this, (X0/100) + " " + (Y0/100), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void Disappear(ImageView Object, int X, int Y) {
        TranslateAnimation animation = new TranslateAnimation(X, 1000, Y, 900);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        Object.startAnimation(animation);

    }

    @SuppressLint("WrongConstant")
    public void Move(ImageView Object, final int k) {
        numberofMove--;
        NumberofMoving.setText(String.valueOf(numberofMove));
        if (Score == numberPoint) {
            Toast.makeText(MainActivity.this, " CONGRATULATION! ", Toast.LENGTH_LONG).show();
            result.setText("SUCCESSFULL!");
            NumberofMoving.setText("0");
            return;
        } else if (numberofMove < 0) {
            Toast.makeText(MainActivity.this, "HAHA! LIKE A DONKEY !", 10000).show();
            result.setText("MISSION\nFAILED!");
            NumberofMoving.setText("0");
            return;
        } else {
            result.setText(" ");

        }

        int X1 = (X0 + X[k] + Mmax + 100) % (Mmax + 100);
        int Y1 = (Y0 + Y[k] + Nmax + 100) % (Nmax + 100);
        boolean add = true;

        for (int i = 1; i <= numberPoint; i++) {
            if (X1 == pointX[i] * 100 && Y1 == pointY[i] * 100) {
                Score++;
                SCORE += (int) (Score * numberofMove);
                Disappear(imgPoint[i], 0, 0);
                pointX[i] -= 1000;
                pointY[i] -= 1000;

                if (Score == numberPoint) {
                    Toast.makeText(MainActivity.this, " CONGRATULATION! ", Toast.LENGTH_LONG).show();
                    result.setText("SUCCESSFULL!");
                    return;
                }
                add = false;

            }
        }
        if (add) {
            SCORE -= 5;
        }
        TranslateAnimation animation = new TranslateAnimation(X0, X1, Y0, Y1);
        X0 = X1;
        Y0 = Y1;
        animation.setDuration(100);
        animation.setFillAfter(true);
        Object.startAnimation(animation);
    }

    public void InitNumberMove() {
        pointX[0] = pointY[0] = 0;
        for (int i = 0; i <= numberPoint; i++) {
            for (int j = 0; j <= numberPoint; j++) {
                Dmin[i][j] = oo;
            }
            Res[i] = 1;
        }

        for (int i = 0; i <= numberPoint - 1; i++) {
            for (int j = i + 1; j <= numberPoint; j++) {
                int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
                x1 = pointX[i];
                x2 = pointX[j];
                y1 = pointY[i];
                y2 = pointY[j];
                int Xmin = Math.min(Math.abs(x2 - x1), M - Math.abs(x1 - x2));
                int Ymin = Math.min(Math.abs(y2 - y1), N - Math.abs(y1 - y2));
                Dmin[j][i] = Dmin[i][j] = Xmin + Ymin;
            }
        }


        for (int i = 0; i <= numberPoint; i++) {
            used[i] = false;
        }

        Backtrack(1);


        numberofMove = Smin;

    }

    public void Result() {
        int S = 0;
        Target[0] = -1;
        Res[0] = 0;
        for (int i = 1; i <= numberPoint; i++) {
            S += Dmin[Res[i - 1]][Res[i]];

        }
        if (S < Smin) {
            Smin = S;
            for (int i = 1; i <= numberPoint; i++) {
                Target[i] = Res[i];
            }
        }
    }

    public void Backtrack(int pos) {
        if (pos == numberPoint + 1) {
            Result();
            return;
        }
        for (int i = 1; i <= numberPoint; i++) {
            if (!used[i]) {
                used[i] = true;
                Res[pos] = i;
                Backtrack(pos + 1);
                used[i] = false;
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
