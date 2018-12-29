package com.example.mypc.fifteenpuzzle.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mypc.fifteenpuzzle.Databases.DataManager;
import com.example.mypc.fifteenpuzzle.Models.ScoreModel;
import com.example.mypc.fifteenpuzzle.Models.SoundModel;
import com.example.mypc.fifteenpuzzle.Models.SpecialPuzzleModel;
import com.example.mypc.fifteenpuzzle.Models.TimeModel;
import com.example.mypc.fifteenpuzzle.R;
import com.example.mypc.fifteenpuzzle.Solutions.SolvingPuzzle;

import java.util.List;
import java.util.Random;
import java.util.Stack;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameActivity extends AppCompatActivity {
    private static final int RANDOM_TIMES = 505;
    String showSolution = "";
    Stack<Integer> stackResult = new Stack<Integer>();


    // declaration


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_current_moving)
    TextView tvCurrentMoving;
    @BindView(R.id.tv_best_moving)
    TextView tvBestMoving;
    @BindView(R.id.cl_main_board)
    ConstraintLayout clMainBoard;
    @BindView(R.id.iv_continue)
    ImageView ivContinue;
    @BindView(R.id.iv_newgame)
    ImageView ivNewgame;
    @BindView(R.id.iv_solve)
    ImageView ivSolve;
    @BindView(R.id.iv_quit)
    ImageView ivQuit;
    @BindView(R.id.cl_menu_box)
    ConstraintLayout clMenuBox;
    @BindView(R.id.cl_score_board)
    ConstraintLayout clScoreBoard;
    @BindView(R.id.iv_menu)
    ImageView ivMenu;
    @BindView(R.id.tv_current_result)
    TextView tvCurrentResult;
    @BindView(R.id.tv_best_result)
    TextView tvBestResult;
    @BindView(R.id.tv_best_timer)
    TextView tvBestTimer;
    ConstraintLayout clDielogReset, clDielogForget, clDielogSaveName;
    View vCancelDielog, vYesDielog, vNoDielog, vCancelDielogForget, vOkDielogSaveName, vCancelDielogSaveName;
    ImageView ivImageForget, ivCustom;
    EditText etSaveName;

    SoundModel soundModel = new SoundModel(this);


    TimeModel timeModel;
    private View vReset, vForget;


    private static final String TAG = "GameActivity";
    Chronometer chronometer;
    private ImageView[][] ivPuzzle = new ImageView[10][10];
    private GestureDetector gestureDetector;
    private SpecialPuzzleModel emptyPuzzle;
    private int[][] puzzle = new int[6][6];
    private boolean onTouchable = false;
    private boolean turnOnSound = false, isChangling = false;
    private boolean wasMoved = false;
    private final int[] directX = {0, -1, 0, 1};
    private final int[] directY = {1, 0, -1, 0};
    private final int LEFT_TO_RIGHT = 0, DOWN_TO_UP = 1, RIGHT_TO_LEFT = 2, UP_TO_DOWN = 3;
    private final int WIDTH = 4, HEIGHT = 4;
    private int numberMoving = 0;
    private int positionMainImage = 1;
    private int[] idForgetImages = {
            R.drawable.zero, R.drawable.imageoneqk, R.drawable.imagetwothienanh, R.drawable.imagethreehai,
            R.drawable.imagefourtrung, R.drawable.imagefiveqk, R.drawable.imagesixhoang, R.drawable.imageseven,
            R.drawable.imagetech, R.drawable.imagezero, R.drawable.imageeight, R.drawable.imageninegirl,
            R.drawable.imageten, R.drawable.imageeleven, 0
    };
    private int[][][] idIvPuzzles = {
            {
                    {R.drawable.zeroaa, R.drawable.zeroab, R.drawable.zeroac, R.drawable.zeroad},
                    {R.drawable.zeroba, R.drawable.zerobb, R.drawable.zerobc, R.drawable.zerobd},
                    {R.drawable.zeroca, R.drawable.zerocb, R.drawable.zerocc, R.drawable.zerocd},
                    {R.drawable.zeroda, R.drawable.zerodb, R.drawable.zerodc, R.drawable.zerodd}
            },
            {
                    {R.drawable.oneaa, R.drawable.oneab, R.drawable.oneac, R.drawable.onead},
                    {R.drawable.oneba, R.drawable.onebb, R.drawable.onebc, R.drawable.onebd},
                    {R.drawable.oneca, R.drawable.onecb, R.drawable.onecc, R.drawable.onecd},
                    {R.drawable.oneda, R.drawable.onedb, R.drawable.onedc, R.drawable.onedd}
            },
            {
                    {R.drawable.twoaa, R.drawable.twoab, R.drawable.twoac, R.drawable.twoad},
                    {R.drawable.twoba, R.drawable.twobb, R.drawable.twobc, R.drawable.twobd},
                    {R.drawable.twoca, R.drawable.twocb, R.drawable.twocc, R.drawable.twocd},
                    {R.drawable.twoda, R.drawable.twodb, R.drawable.twodc, R.drawable.twodd}
            },
            {
                    {R.drawable.threeaa, R.drawable.threeab, R.drawable.threeac, R.drawable.threead},
                    {R.drawable.threeba, R.drawable.threebb, R.drawable.threebc, R.drawable.threebd},
                    {R.drawable.threeca, R.drawable.threecb, R.drawable.threecc, R.drawable.threecd},
                    {R.drawable.threeda, R.drawable.threedb, R.drawable.threedc, R.drawable.threedd}
            },
            {
                    {R.drawable.fouraa, R.drawable.fourab, R.drawable.fourac, R.drawable.fourad},
                    {R.drawable.fourba, R.drawable.fourbb, R.drawable.fourbc, R.drawable.fourbd},
                    {R.drawable.fourca, R.drawable.fourcb, R.drawable.fourcc, R.drawable.fourcd},
                    {R.drawable.fourda, R.drawable.fourdb, R.drawable.fourdc, R.drawable.fourdd}
            },
            {
                    {R.drawable.fiveaa, R.drawable.fiveab, R.drawable.fiveac, R.drawable.fivead},
                    {R.drawable.fiveba, R.drawable.fivebb, R.drawable.fivebc, R.drawable.fivebd},
                    {R.drawable.fiveca, R.drawable.fivecb, R.drawable.fivecc, R.drawable.fivecd},
                    {R.drawable.fiveda, R.drawable.fivedb, R.drawable.fivedc, R.drawable.fivedd}
            },
            {
                    {R.drawable.sixaa, R.drawable.sixab, R.drawable.sixac, R.drawable.sixad},
                    {R.drawable.sixba, R.drawable.sixbb, R.drawable.sixbc, R.drawable.sixbd},
                    {R.drawable.sixca, R.drawable.sixcb, R.drawable.sixcc, R.drawable.sixcd},
                    {R.drawable.sixda, R.drawable.sixdb, R.drawable.sixdc, R.drawable.sixdd}
            },
            {
                    {R.drawable.sevenaa, R.drawable.sevenab, R.drawable.sevenac, R.drawable.sevenad},
                    {R.drawable.sevenba, R.drawable.sevenbb, R.drawable.sevenbc, R.drawable.sevenbd},
                    {R.drawable.sevenca, R.drawable.sevencb, R.drawable.sevencc, R.drawable.sevencd},
                    {R.drawable.sevenda, R.drawable.sevendb, R.drawable.sevendc, R.drawable.sevendd}
            },
            {
                    {R.drawable.aa, R.drawable.ab, R.drawable.ac, R.drawable.ad},
                    {R.drawable.ba, R.drawable.bb, R.drawable.bc, R.drawable.bd},
                    {R.drawable.ca, R.drawable.cb, R.drawable.cc, R.drawable.cd},
                    {R.drawable.da, R.drawable.db, R.drawable.dc, R.drawable.dd}
            },
            {
                    {R.drawable.eightaa, R.drawable.eightab, R.drawable.eightac, R.drawable.eightad},
                    {R.drawable.eightba, R.drawable.eightbb, R.drawable.eightbc, R.drawable.eightbd},
                    {R.drawable.eightca, R.drawable.eightcb, R.drawable.eightcc, R.drawable.eightcd},
                    {R.drawable.eightda, R.drawable.eightdb, R.drawable.eightdc, R.drawable.eightdd}
            },
            {
                    {R.drawable.techaa, R.drawable.techab, R.drawable.techac, R.drawable.techad},
                    {R.drawable.techba, R.drawable.techbb, R.drawable.techbc, R.drawable.techbd},
                    {R.drawable.techca, R.drawable.techcb, R.drawable.techcc, R.drawable.techcd},
                    {R.drawable.techda, R.drawable.techdb, R.drawable.techdc, R.drawable.techdd}
            },
            {
                    {R.drawable.nineaa, R.drawable.nineab, R.drawable.nineac, R.drawable.ninead},
                    {R.drawable.nineba, R.drawable.ninebb, R.drawable.ninebc, R.drawable.ninebd},
                    {R.drawable.nineca, R.drawable.ninecb, R.drawable.ninecc, R.drawable.ninecd},
                    {R.drawable.nineda, R.drawable.ninedb, R.drawable.ninedc, R.drawable.ninedd}
            },
            {
                    {R.drawable.tenaa, R.drawable.tenab, R.drawable.tenac, R.drawable.tenad},
                    {R.drawable.tenba, R.drawable.tenbb, R.drawable.tenbc, R.drawable.tenbd},
                    {R.drawable.tenca, R.drawable.tencb, R.drawable.tencc, R.drawable.tencd},
                    {R.drawable.tenda, R.drawable.tendb, R.drawable.tendc, R.drawable.tendd}
            },
            {
                    {R.drawable.elevenaa, R.drawable.elevenab, R.drawable.elevenac, R.drawable.elevenad},
                    {R.drawable.elevenba, R.drawable.elevenbb, R.drawable.elevenbc, R.drawable.elevenbd},
                    {R.drawable.elevenca, R.drawable.elevencb, R.drawable.elevencc, R.drawable.elevencd},
                    {R.drawable.elevenda, R.drawable.elevendb, R.drawable.elevendc, R.drawable.elevendd}
            }


    };


    // end declaration

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        Define();
        Initialization();

        setupUI();


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.iv_back, R.id.tv_current_moving, R.id.tv_best_moving, R.id.cl_main_board, R.id.iv_continue, R.id.iv_newgame, R.id.iv_solve, R.id.iv_quit, R.id.cl_menu_box, R.id.iv_menu, R.id.cl_score_board, R.id.v_cancel_dielog, R.id.v_no_dielog, R.id.v_yes_dielog, R.id.v_forget, R.id.v_reset, R.id.v_cancel_dielog_forget, R.id.iv_custom, R.id.v_ok_dielog_savename, R.id.v_cancel_dielog_savename})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_menu:
                if (turnOnSound) soundModel.playSound(R.raw.snapping);
                if (!onTouchable) break;
                if (wasMoved) timeModel.Pause();
                wasMoved = false;
                clMenuBox.setVisibility(View.VISIBLE);
                onTouchable = false;
                break;
            case R.id.iv_back:
                if (!onTouchable) break;
                if (turnOnSound) soundModel.playSound(R.raw.snapping);
                this.finish();
                break;

            case R.id.iv_continue:
                if (turnOnSound) soundModel.playSound(R.raw.snapping);
                clMenuBox.setVisibility(View.GONE);
                onTouchable = true;
                break;
            case R.id.iv_newgame:
                if (turnOnSound) soundModel.playSound(R.raw.snapping);
                clMenuBox.setVisibility(View.GONE);
                Toast.makeText(GameActivity.this, "Your result may be saved in High Scores", Toast.LENGTH_SHORT).show();
                Initialization();
                getRandomMap();
                break;
            case R.id.iv_solve:
                isChangling = false;
                if (turnOnSound) soundModel.playSound(R.raw.snapping);
                turnOnSound = false;
                onTouchable = false;
                if (timeModel.started) timeModel.Continue();
                boolean getSolutionable = getSolution();
                if (!getSolutionable) {
                    Toast.makeText(GameActivity.this, "All the puzzles is correct !", Toast.LENGTH_SHORT).show();
                }

                turnOnSound = true;
                clMenuBox.setVisibility(View.GONE);
                onTouchable = true;
                break;
            case R.id.iv_quit:
                if (turnOnSound) soundModel.playSound(R.raw.snapping);
                this.finish();
                break;
            case R.id.v_reset:
                if (turnOnSound) soundModel.playSound(R.raw.snapping);
                clDielogReset.setVisibility(View.VISIBLE);
                if (wasMoved) timeModel.Pause();
                wasMoved = false;
                onTouchable = false;
                break;
            case R.id.v_cancel_dielog:
                Log.d(TAG, "onViewClicked: " + "cancel Reset result");
                if (turnOnSound) soundModel.playSound(R.raw.snapping);
                clDielogReset.setVisibility(View.GONE);
                onTouchable = true;
                break;
            case R.id.v_yes_dielog:
                Log.d(TAG, "onViewClicked: " + "Yes Reset result");
                onTouchable = true;
                if (isChangling) {
                    Toast.makeText(GameActivity.this, "Can't reset result during changling", Toast.LENGTH_SHORT).show();

                    break;
                }

                if (turnOnSound) soundModel.playSound(R.raw.snapping);
                timeModel = new TimeModel(chronometer, 0);
                timeModel.Reset();
                timeModel.Pause();
                wasMoved = false;
                numberMoving = 0;
                tvCurrentMoving.setText("0");
                clDielogReset.setVisibility(View.GONE);
                break;
            case R.id.v_no_dielog:
                if (turnOnSound) soundModel.playSound(R.raw.snapping);
                clDielogReset.setVisibility(View.GONE);
                onTouchable = true;
                break;
            case R.id.v_forget:
                if (wasMoved) timeModel.Pause();
                wasMoved = false;
                if (turnOnSound) soundModel.playSound(R.raw.snapping);
                clDielogForget.setVisibility(View.VISIBLE);
                ivImageForget.setImageResource(idForgetImages[positionMainImage]);
                onTouchable = false;
                break;

            case R.id.v_cancel_dielog_forget:
                if (turnOnSound) soundModel.playSound(R.raw.snapping);
                clDielogForget.setVisibility(View.GONE);
                onTouchable = true;
                break;
            case R.id.iv_custom:
                isChangling = false;
                if (turnOnSound) soundModel.playSound(R.raw.snapping);
                numberMoving = 0;
                tvCurrentMoving.setText("0");
                onTouchable = true;
                Toast.makeText(GameActivity.this, "Your result will not be saved in High Scores", Toast.LENGTH_SHORT).show();
                clMenuBox.setVisibility(View.GONE);
                Initialization();
                break;
            case R.id.v_cancel_dielog_savename:
                if (turnOnSound) soundModel.playSound(R.raw.snapping);
                clDielogSaveName.setVisibility(View.GONE);
                Initialization();
                Intent intentToHighScoreCancel = new Intent(GameActivity.this, HighScoresActivity.class);
                startActivity(intentToHighScoreCancel);
                break;
            case R.id.v_ok_dielog_savename:
                String name = etSaveName.getText().toString();
                name = name.length() > 0 ? name : "Anonymous";
                Log.d(TAG, "onViewClicked: ok save name " + name);
                if (turnOnSound) soundModel.playSound(R.raw.snapping);
                clDielogSaveName.setVisibility(View.GONE);
                saveName(name);
                Initialization();
                Intent intentToHighScore = new Intent(GameActivity.this, HighScoresActivity.class);
                startActivity(intentToHighScore);
                break;
        }
    }

    private void Define() {
        etSaveName = findViewById(R.id.et_get_namw);
        vOkDielogSaveName = findViewById(R.id.v_ok_dielog_savename);
        vCancelDielogSaveName = findViewById(R.id.v_cancel_dielog_savename);
        clDielogSaveName = findViewById(R.id.cl_dielog__savename);
        ivImageForget = findViewById(R.id.iv_dielog_image_forget);
        clDielogForget = findViewById(R.id.cl_dielog__forget);
        vCancelDielogForget = findViewById(R.id.v_cancel_dielog_forget);
        chronometer = findViewById(R.id.cr_chromometer);
        clDielogReset = findViewById(R.id.cl_dielog__reset);
        vCancelDielog = findViewById(R.id.v_cancel_dielog);
        vYesDielog = findViewById(R.id.v_yes_dielog);
        vNoDielog = findViewById(R.id.v_no_dielog);
        vReset = findViewById(R.id.v_reset);
        vForget = findViewById(R.id.v_forget);

        ivPuzzle[0][0] = (ImageView) findViewById(R.id.iv_puzzle_0_0);
        ivPuzzle[0][1] = (ImageView) findViewById(R.id.iv_puzzle_0_1);
        ivPuzzle[0][2] = (ImageView) findViewById(R.id.iv_puzzle_0_2);
        ivPuzzle[0][3] = (ImageView) findViewById(R.id.iv_puzzle_0_3);
        ivPuzzle[1][0] = (ImageView) findViewById(R.id.iv_puzzle_1_0);
        ivPuzzle[1][1] = (ImageView) findViewById(R.id.iv_puzzle_1_1);
        ivPuzzle[1][2] = (ImageView) findViewById(R.id.iv_puzzle_1_2);
        ivPuzzle[1][3] = (ImageView) findViewById(R.id.iv_puzzle_1_3);
        ivPuzzle[2][0] = (ImageView) findViewById(R.id.iv_puzzle_2_0);
        ivPuzzle[2][1] = (ImageView) findViewById(R.id.iv_puzzle_2_1);
        ivPuzzle[2][2] = (ImageView) findViewById(R.id.iv_puzzle_2_2);
        ivPuzzle[2][3] = (ImageView) findViewById(R.id.iv_puzzle_2_3);
        ivPuzzle[3][0] = (ImageView) findViewById(R.id.iv_puzzle_3_0);
        ivPuzzle[3][1] = (ImageView) findViewById(R.id.iv_puzzle_3_1);
        ivPuzzle[3][2] = (ImageView) findViewById(R.id.iv_puzzle_3_2);
        ivPuzzle[3][3] = (ImageView) findViewById(R.id.iv_puzzle_3_3);


    }

    private void getBestResult() {
        DataManager dataManager = new DataManager(this);
        //dataManager.deleteAllItem();

        //   dataManager.addNewScore("tr4n","00:01",1);
        // dataManager.addNewScore("hekl0", "00:01",2);


        //  dataManager.deleteAllItem();
        List<ScoreModel> scoreModelList = dataManager.getAllItems();

        if (!scoreModelList.isEmpty()) {
            tvBestTimer.setText(scoreModelList.get(0).time);
            tvBestMoving.setText(scoreModelList.get(0).move + "");
        }


    }

    private void Initialization() {
        Log.d(TAG, "Initialization: " + "run Initialization");

        isChangling = false;
        turnOnSound = true;
        onTouchable = true;
        wasMoved = false;
        timeModel = new TimeModel(chronometer, 0);
        timeModel.Reset();
        if (!wasMoved) timeModel.Pause();
        tvCurrentMoving.setText("0");
        numberMoving = 0;

        positionMainImage = getIntent().getIntExtra("PositionOfMainImage", positionMainImage);

        emptyPuzzle = new SpecialPuzzleModel(HEIGHT - 1, WIDTH - 1, 0, true);
        for (int line = 0; line < HEIGHT; line++) {
            for (int column = 0; column < WIDTH; column++) {
                puzzle[line][column] = (line * WIDTH + column + 1) % (HEIGHT * WIDTH);
            }
        }
        puzzle[emptyPuzzle.x][emptyPuzzle.y] = emptyPuzzle.value;
        for (int line = 0; line < HEIGHT; line++) {
            for (int column = 0; column < WIDTH; column++) {
                ivPuzzle[line][column].setImageResource(idIvPuzzles[positionMainImage][line][column]);
                ivPuzzle[line][column].setBackgroundResource(R.drawable.border4);
            }
        }
        ivPuzzle[HEIGHT - 1][WIDTH - 1].setImageDrawable(null);
        ivPuzzle[HEIGHT - 1][WIDTH - 1].setBackground(null);


    }

    private String getStringTable(int[][] table) {
        String string = "";
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                string += (String.format("%4d", table[i][j]));
            }
            string += "\n";
        }
        return string;
    }

    private boolean getSolution() {

        if (autoCheckCorrect(puzzle)) return false;
        Log.d(TAG, "getSolution: " + getStringTable(puzzle));
        Log.d(TAG, "getSolution: " + getStringTable(getNewTable(puzzle)));


        String resultString = SolvingPuzzle.solving(puzzle);
        if (resultString.length() < 1) return false;
        stackResult.clear();


        for (int position = resultString.length() - 1; position >= 0; position--) {
            switch ((int) resultString.charAt(position)) {
                case (int) 'R':
                    stackResult.add(LEFT_TO_RIGHT);
                    break;
                case (int) 'U':
                    stackResult.add(DOWN_TO_UP);
                    break;
                case (int) 'L':
                    stackResult.add(RIGHT_TO_LEFT);
                    break;
                case (int) 'D':
                    stackResult.add(UP_TO_DOWN);
                    break;

            }
        }
        Log.d(TAG, "getSolution: " + stackResult);

        clMainBoard.setOnTouchListener(null);
        final String[] direct = {"RIGHT", "UP", "LEFT", "DOWN"};
        showSolution = "";


        turnOnSound = false;
        final int numberSteps = (int)(stackResult.size()*0.67) > 15 ? (int)(stackResult.size()*0.67)  : stackResult.size();


        CountDownTimer countDownTimer = new CountDownTimer((numberSteps ) * 250, 250) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (!stackResult.isEmpty()) {
                    int dir = stackResult.pop();
                    showSolution += (direct[dir] + "-");
                    swapContent(dir);
                   // numberMoving += 2;
                }
            }

            @Override
            public void onFinish() {
                if (!stackResult.isEmpty()){
                    Toast.makeText(GameActivity.this, "Let's Continue ^^ ", Toast.LENGTH_LONG).show();
                    //show forget
                    if (wasMoved) timeModel.Pause();
                    wasMoved = false;
                    if (turnOnSound) soundModel.playSound(R.raw.snapping);
                    clDielogForget.setVisibility(View.VISIBLE);
                    ivImageForget.setImageResource(idForgetImages[positionMainImage]);
                    onTouchable = false;
                }

                Toast.makeText(GameActivity.this, "Your result will not be saved in High Scores", Toast.LENGTH_SHORT).show();

                clMainBoard.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        gestureDetector.onTouchEvent(event);
                        return true;
                    }
                });
                timeModel.Pause();


                //  Toast.makeText(GameActivity.this, showSolution, Toast.LENGTH_SHORT).show();
            }
        }.start();
        onTouchable = true;
        clMainBoard.setEnabled(true);
        return true;

    }

    private void getRandomMap() {
        isChangling = true;

        int countRandomTimes = 0;
        Random random = new Random();
        while (countRandomTimes < RANDOM_TIMES) {

            int dir = random.nextInt(4);
            int currentX = emptyPuzzle.x;
            int currentY = emptyPuzzle.y;
            int newX = currentX - directX[dir];
            int newY = currentY - directY[dir];
            if (newX < 0 || newY < 0 || newX >= HEIGHT || newY >= WIDTH) {
                continue;
            }

            countRandomTimes++;
            // swapPuzzle
            puzzle[currentX][currentY] = puzzle[newX][newY];
            ivPuzzle[currentX][currentY].setImageDrawable(ivPuzzle[newX][newY].getDrawable());
            ivPuzzle[currentX][currentY].setBackgroundResource(R.drawable.border4);
            puzzle[newX][newY] = 0;
            ivPuzzle[newX][newY].setImageDrawable(null);
            ivPuzzle[newX][newY].setBackground(null);
            emptyPuzzle.x = newX;
            emptyPuzzle.y = newY;
        }
    }

    private void setupUI() {
        getRandomMap();
        getBestResult();
        ivImageForget.setImageResource(idForgetImages[positionMainImage]);
        gestureDetector = new GestureDetector(this, new myGestureDetector());

        if (onTouchable) clMainBoard.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (onTouchable)
                    gestureDetector.onTouchEvent(motionEvent);
                return true;
            }
        });


    }

    private int[][] getNewTable(int[][] currentTable) {
        int[][] table = new int[6][6];
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (table[i][j] > 0 && table[i][j] < HEIGHT * WIDTH)
                    table[i][j] = (currentTable[i][j] + 1 + HEIGHT * WIDTH) % (HEIGHT * WIDTH);
            }
        }

        return table;
    }

    private boolean swapContent(int dir) {

        int oldX = emptyPuzzle.x - directX[dir];
        int oldY = emptyPuzzle.y - directY[dir];
        int newX = emptyPuzzle.x;
        int newY = emptyPuzzle.y;
        if (oldX < 0 || oldX >= HEIGHT || oldY < 0 || oldY >= HEIGHT) return false;

        if (!wasMoved) {
            wasMoved = true;
            timeModel.Continue();
        }
        ivPuzzle[newX][newY].setImageDrawable(ivPuzzle[oldX][oldY].getDrawable());
        ivPuzzle[newX][newY].setBackgroundResource(R.drawable.border4);
        ivPuzzle[oldX][oldY].setImageDrawable(null);
        ivPuzzle[oldX][oldY].setBackground(null);

        puzzle[newX][newY] = puzzle[oldX][oldY];
        puzzle[oldX][oldY] = 0;
        emptyPuzzle = new SpecialPuzzleModel(oldX, oldY, 0, true);

        tvCurrentMoving.setText(String.valueOf(++numberMoving));
        if (!isChangling) {
            tvCurrentMoving.setTextColor(Color.YELLOW);

        } else {
            tvCurrentMoving.setTextColor(Color.WHITE);

        }
        boolean isWin = autoCheckCorrect(puzzle);
        if (isWin) {
            timeModel.Pause();
            wasMoved = false;

            Toast.makeText(GameActivity.this, "Congratulations", Toast.LENGTH_SHORT).show();
            if (isChangling) {
                clDielogSaveName.setVisibility(View.VISIBLE);
                onTouchable = false;
            }
        }
        return true;
    }

    private boolean saveName(String name) {

        DataManager dataManager = new DataManager(this);
        List<ScoreModel> scoreModelList = dataManager.getAllItems();

        ScoreModel newScore = new ScoreModel(name, chronometer.getText().toString(), numberMoving);
        dataManager.addNewScore(newScore);
        if (!scoreModelList.isEmpty()) {
            Log.d(TAG, "swapContent: " + "not emptyList");

            tvBestMoving.setText(dataManager.getMaxScore().move + "");
            tvBestTimer.setText(dataManager.getMaxScore().time);
        }
        Log.d(TAG, "swapContent: " + " empty List");
        return true;
    }

    private boolean autoCheckCorrect(int[][] puzzles) {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (puzzles[i][j] != (i * WIDTH + j + 1 + WIDTH * HEIGHT) % (HEIGHT * WIDTH))
                    return false;
            }
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        if (turnOnSound) soundModel.playSound(R.raw.snapping);
        clMenuBox.setVisibility(View.VISIBLE);
        onTouchable = false;
    }

    @OnClick(R.id.iv_menu)
    public void onViewClicked() {
    }

    private class myGestureDetector implements GestureDetector.OnGestureListener {
        final int RIGHT_QUARTER = 0;
        final int UP_QUARTER = 1;
        final int LEFT_QUARTER = 2;
        final int DOWN_QUARTER = 3;
        final int SWIP_VELOCITY = 100;
        final int SWIP_THERSHOLD = 100;


        @Override
        public boolean onDown(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onFling(MotionEvent motionEvent1, MotionEvent motionEvent2, float velocityX, float velocityY) {

            if (!onTouchable) return false;
            if (turnOnSound) soundModel.playSound(R.raw.swap);

            float fromX = motionEvent1.getX();
            float fromY = motionEvent1.getY();
            float toX = motionEvent2.getX();
            float toY = motionEvent2.getY();
            int theQuarter = RIGHT_QUARTER;

            theQuarter = toX > fromX
                    ? (fromX - toX < toY - fromY && toY - fromY < toX - fromX)
                    ? RIGHT_QUARTER : toY > fromY ? DOWN_QUARTER : UP_QUARTER
                    : (fromX - toX > toY - fromY && toY - fromY > toX - fromX)
                    ? LEFT_QUARTER : toY > fromY ? DOWN_QUARTER : UP_QUARTER;


            if ((theQuarter == RIGHT_QUARTER || theQuarter == LEFT_QUARTER) && Math.abs(velocityX) > SWIP_VELOCITY) {
                if (fromX - toX > SWIP_THERSHOLD) {
                    swapContent(RIGHT_TO_LEFT);
                    Log.d(TAG, "onFling: " + "RIGHT TO LEFT");
                    //    Toast.makeText(GameActivity.this, "Right to Left", Toast.LENGTH_SHORT).show();
                }
                if (toX - fromX > SWIP_THERSHOLD) {
                    swapContent(LEFT_TO_RIGHT);
                    Log.d(TAG, "onFling: " + "LEFT TO RIGHT");
                    //  Toast.makeText(GameActivity.this, "Left to Right", Toast.LENGTH_SHORT).show();
                }
            }

            if ((theQuarter == UP_QUARTER || theQuarter == DOWN_QUARTER) && Math.abs(velocityY) > SWIP_VELOCITY) {
                if (fromY - toY > SWIP_THERSHOLD) {
                    swapContent(DOWN_TO_UP);
                    Log.d(TAG, "onFling: " + "DOWN TO UP");
                    //  Toast.makeText(GameActivity.this, "DOWN to Up", Toast.LENGTH_SHORT).show();
                }

                if (toY - fromY > SWIP_THERSHOLD) {
                    swapContent(UP_TO_DOWN);
                    Log.d(TAG, "onFling: " + "UP TO DOWN");
                    //  Toast.makeText(GameActivity.this, "UP to DOWN", Toast.LENGTH_SHORT).show();
                }
            }


            return true;
        }
    }
}