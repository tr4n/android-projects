package com.tr4n.superstar2018;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Stack;


public class MainGame extends Activity {

    private Random random = new Random();

    private Sound sound = new Sound(this);
    private Stack<TracingObject> TracingStack = new Stack<TracingObject>();
    private TextView textViewNumberMoving;
    public ImageView[][] ImageViewBox = new ImageView[20][20];
    private ImageView ImageViewBack, ImageViewSolving;
    private RelativeLayout[] RelativeLayoutDirection = new RelativeLayout[5];
    private LinearLayout LinearLayoutContainBack, LinearLayoutContainSolving;
    private int SolutionMode = 0;
    public int[][] MainBoard = new int[20][20];
    private int[][] SolutionBoard = new int[20][20];
    public int PositionCharacterX = 1;
    public int PositionCharacterY = 1;
    public int NumberMoving = 0;
    private int ID_OF_CHARACTER = 0;
    private int ID_OF_STAR = R.drawable.coinstar;
    private int TurnOnSound = 1, TurnOnMusic = 1;
    private int NUMBER_STAR = 10;
    private final int ID_OF_SOUND = R.raw.walking;
    private final int ID_OF_MUSIC = R.raw.backgroundtwo;
    public final int WIDTH_OF_MAP = 12;
    public final int HEIGHT_OF_MAP = 12;
    public final int EMPTY_BOX = 0, CHARACTER_BOX = 1, COIN_BOX = 2;
    public final int SOLUTION_ON = 1, SOLUTION_OFF = 0;
    private final int[] directY = {1, 0, -1, 0};
    private final int[] directX = {0, -1, 0, 1};
    public final int[] ID_OF_STARNUMBER = {R.drawable.starcharacter, R.drawable.starone, R.drawable.startwo, R.drawable.starthree, R.drawable.starfour,
            R.drawable.starfive, R.drawable.starsix, R.drawable.starseven, R.drawable.stareight, R.drawable.starnine, R.drawable.starten, R.drawable.stareleven,
            R.drawable.startwelve, R.drawable.starthirdteen, R.drawable.starfourteen, 0};


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_game);

        Define();
        Initialization();
        LayoutSetOnClick();

    }

    private void CallBackBundle() {
        Intent intent = getIntent();
        Bundle BundleFromSettingsToMainGame = intent.getBundleExtra("BundleFromSettingsToMainGame");
        TurnOnSound = BundleFromSettingsToMainGame.getInt("TurnOnSound");
        TurnOnMusic = BundleFromSettingsToMainGame.getInt("TurnOnMusic");
        NUMBER_STAR = BundleFromSettingsToMainGame.getInt("NUMBER_STAR");
        ID_OF_CHARACTER = BundleFromSettingsToMainGame.getInt("ID_OF_CHARACTER");

    }

    private void Define() {
        ImageViewSolving = (ImageView) findViewById(R.id.ImageViewSolving);
        LinearLayoutContainSolving = (LinearLayout) findViewById(R.id.LayoutContainSolving);
        ImageViewBack = (ImageView) findViewById(R.id.ImageViewBack);
        LinearLayoutContainBack = (LinearLayout) findViewById(R.id.LayoutContainBack);
        textViewNumberMoving = (TextView) findViewById(R.id.TextViewMoving);
        ImageViewBox[1][1] = (ImageView) findViewById(R.id.Box1_1);
        ImageViewBox[1][2] = (ImageView) findViewById(R.id.Box1_2);
        ImageViewBox[1][3] = (ImageView) findViewById(R.id.Box1_3);
        ImageViewBox[1][4] = (ImageView) findViewById(R.id.Box1_4);
        ImageViewBox[1][5] = (ImageView) findViewById(R.id.Box1_5);
        ImageViewBox[1][6] = (ImageView) findViewById(R.id.Box1_6);
        ImageViewBox[1][7] = (ImageView) findViewById(R.id.Box1_7);
        ImageViewBox[1][8] = (ImageView) findViewById(R.id.Box1_8);
        ImageViewBox[1][9] = (ImageView) findViewById(R.id.Box1_9);
        ImageViewBox[1][10] = (ImageView) findViewById(R.id.Box1_10);
        ImageViewBox[1][11] = (ImageView) findViewById(R.id.Box1_11);
        ImageViewBox[1][12] = (ImageView) findViewById(R.id.Box1_12);
        ImageViewBox[1][13] = (ImageView) findViewById(R.id.Box1_13);
        ImageViewBox[1][14] = (ImageView) findViewById(R.id.Box1_14);
        ImageViewBox[2][1] = (ImageView) findViewById(R.id.Box2_1);
        ImageViewBox[2][2] = (ImageView) findViewById(R.id.Box2_2);
        ImageViewBox[2][3] = (ImageView) findViewById(R.id.Box2_3);
        ImageViewBox[2][4] = (ImageView) findViewById(R.id.Box2_4);
        ImageViewBox[2][5] = (ImageView) findViewById(R.id.Box2_5);
        ImageViewBox[2][6] = (ImageView) findViewById(R.id.Box2_6);
        ImageViewBox[2][7] = (ImageView) findViewById(R.id.Box2_7);
        ImageViewBox[2][8] = (ImageView) findViewById(R.id.Box2_8);
        ImageViewBox[2][9] = (ImageView) findViewById(R.id.Box2_9);
        ImageViewBox[2][10] = (ImageView) findViewById(R.id.Box2_10);
        ImageViewBox[2][11] = (ImageView) findViewById(R.id.Box2_11);
        ImageViewBox[2][12] = (ImageView) findViewById(R.id.Box2_12);
        ImageViewBox[2][13] = (ImageView) findViewById(R.id.Box2_13);
        ImageViewBox[2][14] = (ImageView) findViewById(R.id.Box2_14);
        ImageViewBox[3][1] = (ImageView) findViewById(R.id.Box3_1);
        ImageViewBox[3][2] = (ImageView) findViewById(R.id.Box3_2);
        ImageViewBox[3][3] = (ImageView) findViewById(R.id.Box3_3);
        ImageViewBox[3][4] = (ImageView) findViewById(R.id.Box3_4);
        ImageViewBox[3][5] = (ImageView) findViewById(R.id.Box3_5);
        ImageViewBox[3][6] = (ImageView) findViewById(R.id.Box3_6);
        ImageViewBox[3][7] = (ImageView) findViewById(R.id.Box3_7);
        ImageViewBox[3][8] = (ImageView) findViewById(R.id.Box3_8);
        ImageViewBox[3][9] = (ImageView) findViewById(R.id.Box3_9);
        ImageViewBox[3][10] = (ImageView) findViewById(R.id.Box3_10);
        ImageViewBox[3][11] = (ImageView) findViewById(R.id.Box3_11);
        ImageViewBox[3][12] = (ImageView) findViewById(R.id.Box3_12);
        ImageViewBox[3][13] = (ImageView) findViewById(R.id.Box3_13);
        ImageViewBox[3][14] = (ImageView) findViewById(R.id.Box3_14);
        ImageViewBox[4][1] = (ImageView) findViewById(R.id.Box4_1);
        ImageViewBox[4][2] = (ImageView) findViewById(R.id.Box4_2);
        ImageViewBox[4][3] = (ImageView) findViewById(R.id.Box4_3);
        ImageViewBox[4][4] = (ImageView) findViewById(R.id.Box4_4);
        ImageViewBox[4][5] = (ImageView) findViewById(R.id.Box4_5);
        ImageViewBox[4][6] = (ImageView) findViewById(R.id.Box4_6);
        ImageViewBox[4][7] = (ImageView) findViewById(R.id.Box4_7);
        ImageViewBox[4][8] = (ImageView) findViewById(R.id.Box4_8);
        ImageViewBox[4][9] = (ImageView) findViewById(R.id.Box4_9);
        ImageViewBox[4][10] = (ImageView) findViewById(R.id.Box4_10);
        ImageViewBox[4][11] = (ImageView) findViewById(R.id.Box4_11);
        ImageViewBox[4][12] = (ImageView) findViewById(R.id.Box4_12);
        ImageViewBox[4][13] = (ImageView) findViewById(R.id.Box4_13);
        ImageViewBox[4][14] = (ImageView) findViewById(R.id.Box4_14);
        ImageViewBox[5][1] = (ImageView) findViewById(R.id.Box5_1);
        ImageViewBox[5][2] = (ImageView) findViewById(R.id.Box5_2);
        ImageViewBox[5][3] = (ImageView) findViewById(R.id.Box5_3);
        ImageViewBox[5][4] = (ImageView) findViewById(R.id.Box5_4);
        ImageViewBox[5][5] = (ImageView) findViewById(R.id.Box5_5);
        ImageViewBox[5][6] = (ImageView) findViewById(R.id.Box5_6);
        ImageViewBox[5][7] = (ImageView) findViewById(R.id.Box5_7);
        ImageViewBox[5][8] = (ImageView) findViewById(R.id.Box5_8);
        ImageViewBox[5][9] = (ImageView) findViewById(R.id.Box5_9);
        ImageViewBox[5][10] = (ImageView) findViewById(R.id.Box5_10);
        ImageViewBox[5][11] = (ImageView) findViewById(R.id.Box5_11);
        ImageViewBox[5][12] = (ImageView) findViewById(R.id.Box5_12);
        ImageViewBox[5][13] = (ImageView) findViewById(R.id.Box5_13);
        ImageViewBox[5][14] = (ImageView) findViewById(R.id.Box5_14);
        ImageViewBox[6][1] = (ImageView) findViewById(R.id.Box6_1);
        ImageViewBox[6][2] = (ImageView) findViewById(R.id.Box6_2);
        ImageViewBox[6][3] = (ImageView) findViewById(R.id.Box6_3);
        ImageViewBox[6][4] = (ImageView) findViewById(R.id.Box6_4);
        ImageViewBox[6][5] = (ImageView) findViewById(R.id.Box6_5);
        ImageViewBox[6][6] = (ImageView) findViewById(R.id.Box6_6);
        ImageViewBox[6][7] = (ImageView) findViewById(R.id.Box6_7);
        ImageViewBox[6][8] = (ImageView) findViewById(R.id.Box6_8);
        ImageViewBox[6][9] = (ImageView) findViewById(R.id.Box6_9);
        ImageViewBox[6][10] = (ImageView) findViewById(R.id.Box6_10);
        ImageViewBox[6][11] = (ImageView) findViewById(R.id.Box6_11);
        ImageViewBox[6][12] = (ImageView) findViewById(R.id.Box6_12);
        ImageViewBox[6][13] = (ImageView) findViewById(R.id.Box6_13);
        ImageViewBox[6][14] = (ImageView) findViewById(R.id.Box6_14);
        ImageViewBox[7][1] = (ImageView) findViewById(R.id.Box7_1);
        ImageViewBox[7][2] = (ImageView) findViewById(R.id.Box7_2);
        ImageViewBox[7][3] = (ImageView) findViewById(R.id.Box7_3);
        ImageViewBox[7][4] = (ImageView) findViewById(R.id.Box7_4);
        ImageViewBox[7][5] = (ImageView) findViewById(R.id.Box7_5);
        ImageViewBox[7][6] = (ImageView) findViewById(R.id.Box7_6);
        ImageViewBox[7][7] = (ImageView) findViewById(R.id.Box7_7);
        ImageViewBox[7][8] = (ImageView) findViewById(R.id.Box7_8);
        ImageViewBox[7][9] = (ImageView) findViewById(R.id.Box7_9);
        ImageViewBox[7][10] = (ImageView) findViewById(R.id.Box7_10);
        ImageViewBox[7][11] = (ImageView) findViewById(R.id.Box7_11);
        ImageViewBox[7][12] = (ImageView) findViewById(R.id.Box7_12);
        ImageViewBox[7][13] = (ImageView) findViewById(R.id.Box7_13);
        ImageViewBox[7][14] = (ImageView) findViewById(R.id.Box7_14);
        ImageViewBox[8][1] = (ImageView) findViewById(R.id.Box8_1);
        ImageViewBox[8][2] = (ImageView) findViewById(R.id.Box8_2);
        ImageViewBox[8][3] = (ImageView) findViewById(R.id.Box8_3);
        ImageViewBox[8][4] = (ImageView) findViewById(R.id.Box8_4);
        ImageViewBox[8][5] = (ImageView) findViewById(R.id.Box8_5);
        ImageViewBox[8][6] = (ImageView) findViewById(R.id.Box8_6);
        ImageViewBox[8][7] = (ImageView) findViewById(R.id.Box8_7);
        ImageViewBox[8][8] = (ImageView) findViewById(R.id.Box8_8);
        ImageViewBox[8][9] = (ImageView) findViewById(R.id.Box8_9);
        ImageViewBox[8][10] = (ImageView) findViewById(R.id.Box8_10);
        ImageViewBox[8][11] = (ImageView) findViewById(R.id.Box8_11);
        ImageViewBox[8][12] = (ImageView) findViewById(R.id.Box8_12);
        ImageViewBox[8][13] = (ImageView) findViewById(R.id.Box8_13);
        ImageViewBox[8][14] = (ImageView) findViewById(R.id.Box8_14);
        ImageViewBox[9][1] = (ImageView) findViewById(R.id.Box9_1);
        ImageViewBox[9][2] = (ImageView) findViewById(R.id.Box9_2);
        ImageViewBox[9][3] = (ImageView) findViewById(R.id.Box9_3);
        ImageViewBox[9][4] = (ImageView) findViewById(R.id.Box9_4);
        ImageViewBox[9][5] = (ImageView) findViewById(R.id.Box9_5);
        ImageViewBox[9][6] = (ImageView) findViewById(R.id.Box9_6);
        ImageViewBox[9][7] = (ImageView) findViewById(R.id.Box9_7);
        ImageViewBox[9][8] = (ImageView) findViewById(R.id.Box9_8);
        ImageViewBox[9][9] = (ImageView) findViewById(R.id.Box9_9);
        ImageViewBox[9][10] = (ImageView) findViewById(R.id.Box9_10);
        ImageViewBox[9][11] = (ImageView) findViewById(R.id.Box9_11);
        ImageViewBox[9][12] = (ImageView) findViewById(R.id.Box9_12);
        ImageViewBox[9][13] = (ImageView) findViewById(R.id.Box9_13);
        ImageViewBox[9][14] = (ImageView) findViewById(R.id.Box9_14);
        ImageViewBox[10][1] = (ImageView) findViewById(R.id.Box10_1);
        ImageViewBox[10][2] = (ImageView) findViewById(R.id.Box10_2);
        ImageViewBox[10][3] = (ImageView) findViewById(R.id.Box10_3);
        ImageViewBox[10][4] = (ImageView) findViewById(R.id.Box10_4);
        ImageViewBox[10][5] = (ImageView) findViewById(R.id.Box10_5);
        ImageViewBox[10][6] = (ImageView) findViewById(R.id.Box10_6);
        ImageViewBox[10][7] = (ImageView) findViewById(R.id.Box10_7);
        ImageViewBox[10][8] = (ImageView) findViewById(R.id.Box10_8);
        ImageViewBox[10][9] = (ImageView) findViewById(R.id.Box10_9);
        ImageViewBox[10][10] = (ImageView) findViewById(R.id.Box10_10);
        ImageViewBox[10][11] = (ImageView) findViewById(R.id.Box10_11);
        ImageViewBox[10][12] = (ImageView) findViewById(R.id.Box10_12);
        ImageViewBox[10][13] = (ImageView) findViewById(R.id.Box10_13);
        ImageViewBox[10][14] = (ImageView) findViewById(R.id.Box10_14);
        ImageViewBox[11][1] = (ImageView) findViewById(R.id.Box11_1);
        ImageViewBox[11][2] = (ImageView) findViewById(R.id.Box11_2);
        ImageViewBox[11][3] = (ImageView) findViewById(R.id.Box11_3);
        ImageViewBox[11][4] = (ImageView) findViewById(R.id.Box11_4);
        ImageViewBox[11][5] = (ImageView) findViewById(R.id.Box11_5);
        ImageViewBox[11][6] = (ImageView) findViewById(R.id.Box11_6);
        ImageViewBox[11][7] = (ImageView) findViewById(R.id.Box11_7);
        ImageViewBox[11][8] = (ImageView) findViewById(R.id.Box11_8);
        ImageViewBox[11][9] = (ImageView) findViewById(R.id.Box11_9);
        ImageViewBox[11][10] = (ImageView) findViewById(R.id.Box11_10);
        ImageViewBox[11][11] = (ImageView) findViewById(R.id.Box11_11);
        ImageViewBox[11][12] = (ImageView) findViewById(R.id.Box11_12);
        ImageViewBox[11][13] = (ImageView) findViewById(R.id.Box11_13);
        ImageViewBox[11][14] = (ImageView) findViewById(R.id.Box11_14);
        ImageViewBox[12][1] = (ImageView) findViewById(R.id.Box12_1);
        ImageViewBox[12][2] = (ImageView) findViewById(R.id.Box12_2);
        ImageViewBox[12][3] = (ImageView) findViewById(R.id.Box12_3);
        ImageViewBox[12][4] = (ImageView) findViewById(R.id.Box12_4);
        ImageViewBox[12][5] = (ImageView) findViewById(R.id.Box12_5);
        ImageViewBox[12][6] = (ImageView) findViewById(R.id.Box12_6);
        ImageViewBox[12][7] = (ImageView) findViewById(R.id.Box12_7);
        ImageViewBox[12][8] = (ImageView) findViewById(R.id.Box12_8);
        ImageViewBox[12][9] = (ImageView) findViewById(R.id.Box12_9);
        ImageViewBox[12][10] = (ImageView) findViewById(R.id.Box12_10);
        ImageViewBox[12][11] = (ImageView) findViewById(R.id.Box12_11);
        ImageViewBox[12][12] = (ImageView) findViewById(R.id.Box12_12);
        ImageViewBox[12][13] = (ImageView) findViewById(R.id.Box12_13);
        ImageViewBox[12][14] = (ImageView) findViewById(R.id.Box12_14);
        ImageViewBox[13][1] = (ImageView) findViewById(R.id.Box13_1);
        ImageViewBox[13][2] = (ImageView) findViewById(R.id.Box13_2);
        ImageViewBox[13][3] = (ImageView) findViewById(R.id.Box13_3);
        ImageViewBox[13][4] = (ImageView) findViewById(R.id.Box13_4);
        ImageViewBox[13][5] = (ImageView) findViewById(R.id.Box13_5);
        ImageViewBox[13][6] = (ImageView) findViewById(R.id.Box13_6);
        ImageViewBox[13][7] = (ImageView) findViewById(R.id.Box13_7);
        ImageViewBox[13][8] = (ImageView) findViewById(R.id.Box13_8);
        ImageViewBox[13][9] = (ImageView) findViewById(R.id.Box13_9);
        ImageViewBox[13][10] = (ImageView) findViewById(R.id.Box13_10);
        ImageViewBox[13][11] = (ImageView) findViewById(R.id.Box13_11);
        ImageViewBox[13][12] = (ImageView) findViewById(R.id.Box13_12);
        ImageViewBox[13][13] = (ImageView) findViewById(R.id.Box13_13);
        ImageViewBox[13][14] = (ImageView) findViewById(R.id.Box13_14);
        ImageViewBox[14][1] = (ImageView) findViewById(R.id.Box14_1);
        ImageViewBox[14][2] = (ImageView) findViewById(R.id.Box14_2);
        ImageViewBox[14][3] = (ImageView) findViewById(R.id.Box14_3);
        ImageViewBox[14][4] = (ImageView) findViewById(R.id.Box14_4);
        ImageViewBox[14][5] = (ImageView) findViewById(R.id.Box14_5);
        ImageViewBox[14][6] = (ImageView) findViewById(R.id.Box14_6);
        ImageViewBox[14][7] = (ImageView) findViewById(R.id.Box14_7);
        ImageViewBox[14][8] = (ImageView) findViewById(R.id.Box14_8);
        ImageViewBox[14][9] = (ImageView) findViewById(R.id.Box14_9);
        ImageViewBox[14][10] = (ImageView) findViewById(R.id.Box14_10);
        ImageViewBox[14][11] = (ImageView) findViewById(R.id.Box14_11);
        ImageViewBox[14][12] = (ImageView) findViewById(R.id.Box14_12);
        ImageViewBox[14][13] = (ImageView) findViewById(R.id.Box14_13);
        ImageViewBox[14][14] = (ImageView) findViewById(R.id.Box14_14);

        RelativeLayoutDirection[0] = (RelativeLayout) findViewById(R.id.RelaytiveContainRightArrow);
        RelativeLayoutDirection[1] = (RelativeLayout) findViewById(R.id.RelaytiveContainUpArrow);
        RelativeLayoutDirection[2] = (RelativeLayout) findViewById(R.id.RelaytiveContainLeftArrow);
        RelativeLayoutDirection[3] = (RelativeLayout) findViewById(R.id.RelaytiveContainDownArrow);

    }

    private void Initialization() {
        CallBackBundle();
        CreateRandomMap();
        if (TurnOnMusic > 0) sound.turnOnBackgroundSound(ID_OF_MUSIC);

        Solve solve = new Solve(MainBoard);
        NumberMoving = solve.Answer;
        textViewNumberMoving.setText(" " + solve.Answer + " ");

    }

    private void CreateRandomMap() {

        for (int i = 1; i <= WIDTH_OF_MAP; i++) {
            for (int j = 1; j <= HEIGHT_OF_MAP; j++) {
                MainBoard[i][j] = EMPTY_BOX;
            }
        }
        PositionCharacterX = PositionCharacterY = 1;
        MainBoard[PositionCharacterX][PositionCharacterY] = CHARACTER_BOX;

        int CountNumberStar = 0;
        while (CountNumberStar < NUMBER_STAR) {
            int y = 1 + random.nextInt(HEIGHT_OF_MAP);
            int x = 1 + random.nextInt(WIDTH_OF_MAP);
            if (MainBoard[x][y] != EMPTY_BOX) continue;
            MainBoard[x][y] = COIN_BOX;
            ++CountNumberStar;
        }
        SetImageViewBox();
        ImageViewBack.setImageResource(R.drawable.backblack);
    }

    public void SetImageViewBox() {
        for (int i = 1; i <= WIDTH_OF_MAP; i++) {
            for (int j = 1; j <= HEIGHT_OF_MAP; j++) {
                switch (MainBoard[i][j]) {
                    case EMPTY_BOX:
                        ImageViewBox[i][j].setImageResource(0);
                        break;
                    case CHARACTER_BOX:
                        ImageViewBox[i][j].setImageResource(ID_OF_CHARACTER);
                        break;
                    case COIN_BOX:
                        ImageViewBox[i][j].setImageResource(ID_OF_STAR);
                        break;

                }
            }
        }

    }

    private void LayoutSetOnClick() {
        setBackFunction();
        setSlovingFunction();
        MovingCharacter();

    }

    private int setNewDirectPosition(int mode, int key) {
        if (mode == 0) {
            int newX = PositionCharacterX + directX[key];
            if (newX == 0) return WIDTH_OF_MAP;
            else if (newX > WIDTH_OF_MAP) return (newX % WIDTH_OF_MAP);
            else return newX;

        } else {
            int newY = PositionCharacterY + directY[key];
            if (newY == 0) return HEIGHT_OF_MAP;
            else if (newY > HEIGHT_OF_MAP) return (newY - HEIGHT_OF_MAP);
            else return newY;
        }
    }

    private void DirectButtonSetOnClick(final int key) {
        RelativeLayoutDirection[key].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newX = setNewDirectPosition(0, key);
                int newY = setNewDirectPosition(1, key);
                if (MainBoard[newX][newY] == EMPTY_BOX) {
                    sound.playSound(R.raw.walking);
                } else if (MainBoard[newX][newY] == COIN_BOX) {
                    TracingStack.push(new TracingObject(PositionCharacterX, PositionCharacterY, NumberMoving));
                    sound.playSound(R.raw.confirmationalbert);
                }
                ImageViewBox[newX][newY].setImageResource(ID_OF_CHARACTER);
                ImageViewBox[PositionCharacterX][PositionCharacterY].setImageResource(EMPTY_BOX);
                MainBoard[newX][newY] = CHARACTER_BOX;
                MainBoard[PositionCharacterX][PositionCharacterY] = EMPTY_BOX;
                PositionCharacterX = newX;
                PositionCharacterY = newY;
                setTextViewNumberMoving();
                ImageViewBack.setImageResource(R.drawable.back);
            }
        });
    }

    private void MovingCharacter() {

        for (int i = 0; i < 4; i++)
            DirectButtonSetOnClick(i);
    }

    private void setBackFunction() {
        TracingStack.push(new TracingObject(1, 1, NumberMoving));

        LinearLayoutContainBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TracingObject character = new TracingObject(PositionCharacterX, PositionCharacterY, NumberMoving);

                if (character.isEqual(TracingStack.peek())) {
                    if (TracingStack.size() == 1) {
                        ImageViewBack.setImageResource(R.drawable.backblack);
                        return;
                    }
                    MainBoard[PositionCharacterX][PositionCharacterY] = COIN_BOX;
                    ImageViewBox[PositionCharacterX][PositionCharacterY].setImageResource(ID_OF_STAR);
                    TracingStack.pop();
                } else {
                    MainBoard[PositionCharacterX][PositionCharacterY] = EMPTY_BOX;
                    ImageViewBox[PositionCharacterX][PositionCharacterY].setImageResource(0);
                }
                PositionCharacterX = TracingStack.peek().first;
                PositionCharacterY = TracingStack.peek().second;
                NumberMoving = TracingStack.peek().third;
                MainBoard[PositionCharacterX][PositionCharacterY] = CHARACTER_BOX;
                ImageViewBox[PositionCharacterX][PositionCharacterY].setImageResource(ID_OF_CHARACTER);
                textViewNumberMoving.setText(" " + NumberMoving + " ");
                sound.playSound(R.raw.snapping);
                if (TracingStack.size() == 1) {
                    ImageViewBack.setImageResource(R.drawable.backblack);
                    return;
                }

            }
        });


    }

    private void setTextViewNumberMoving() {
        NumberMoving--;
        // if(NumberMoving < 0) return;

        textViewNumberMoving.setText(" " + NumberMoving + " ");
    }

    private void setSlovingFunction() {

        LinearLayoutContainSolving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SOLUTION_ON == SolutionMode) {
                    SolutionMode = SOLUTION_OFF;
                    ImageViewSolving.setImageResource(R.drawable.plusgreen);
                    SetImageViewBox();
                } else {
                    Solve solve = new Solve(MainBoard);
                    if (solve.Answer > NumberMoving) {
                        Toast.makeText(MainGame.this, "Can't Solve ... "+NumberMoving + " " + solve.Answer, Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        SolutionMode = SOLUTION_ON;
                        ImageViewSolving.setImageResource(R.drawable.minusgreen);

                        for (int i = 1; i <= solve.NumberOfObjects; i++) {
                            int x = solve.getXbox()[solve.getResult()[i]];
                            int y = solve.getYbox()[solve.getResult()[i]];
                            ImageViewBox[x][y].setImageResource(ID_OF_STARNUMBER[i]);
                        }

                    }
                }

            }
        });
    }
}
