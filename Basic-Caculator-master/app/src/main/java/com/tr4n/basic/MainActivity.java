package com.tr4n.basic;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigInteger;
import java.util.Scanner;


public class MainActivity extends AppCompatActivity {


    TextView textviewOne;
    /*
    Button button1, button2, button3, button4, button5, button6;
    Button button7, button8, button9, button0;
    */
    Button[] button = new Button[13];
    Button add, subtract, multiply, div, equal, buttondelete;
    EditText ONE, mid, TWO, Result;

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

        button[0] = (Button) findViewById(R.id.button0);
        button[1] = (Button) findViewById(R.id.button1);
        button[2] = (Button) findViewById(R.id.button2);
        button[3] = (Button) findViewById(R.id.button3);
        button[4] = (Button) findViewById(R.id.button4);
        button[5] = (Button) findViewById(R.id.button5);
        button[6] = (Button) findViewById(R.id.button6);
        button[7] = (Button) findViewById(R.id.button7);
        button[8] = (Button) findViewById(R.id.button8);
        button[9] = (Button) findViewById(R.id.button9);
        add = (Button) findViewById(R.id.add);
        subtract = (Button) findViewById(R.id.subtract);
        multiply = (Button) findViewById(R.id.multiply);
        div = (Button) findViewById(R.id.div);
        equal = (Button) findViewById(R.id.equal);

        ONE = (EditText) findViewById(R.id.ONE);
        TWO = (EditText) findViewById(R.id.TWO);
        mid = (EditText) findViewById(R.id.mid);
        Result = (EditText) findViewById(R.id.Result);

        mid.setText("+");
        ONE.setText("0");
        TWO.setText("0");
        Enter();

    }

    public void Show() {

        if (mid.getText().toString().charAt(0) == '_')
            return;

        BigInteger a = new BigInteger(ONE.getText().toString());
        BigInteger b = new BigInteger(TWO.getText().toString());
        String S = mid.getText().toString();


        if (S.charAt(0) == '+')
            Result.setText((a.add(b)).toString());
        else if (S.charAt(0) == '-')
            Result.setText(a.subtract(b).toString());
        else if (S.charAt(0) == '*')
            Result.setText(a.multiply(b).toString());

        else if (S.charAt(0) == '/') {
            if (b.equals(BigInteger.ZERO)) {
                b = BigInteger.ONE;
            }
            Result.setText(a.divide(b).toString());

        }

        ONE.setText(Result.getText().toString());
        TWO.setText("0");


    }


    public void Enter() {

        EnterNumber(TWO);
        EnterMid();


    }


    public void EnterNumber(final EditText TEMP) {

        button[1].setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                TEMP.setText(TEMP.getText().toString() + 1);

            }

        });
        button[2].setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                TEMP.setText(TEMP.getText().toString() + 2);

            }

        });
        button[3].setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                TEMP.setText(TEMP.getText().toString() + 3);

            }

        });
        button[4].setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                TEMP.setText(TEMP.getText().toString() + 4);

            }

        });
        button[5].setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                TEMP.setText(TEMP.getText().toString() + 5);

            }

        });
        button[6].setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                TEMP.setText(TEMP.getText().toString() + 6);

            }

        });
        button[7].setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                TEMP.setText(TEMP.getText().toString() + 7);

            }

        });
        button[8].setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                TEMP.setText(TEMP.getText().toString() + 8);

            }

        });
        button[9].setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                TEMP.setText(TEMP.getText().toString() + 9);

            }

        });
        button[0].setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if (mid.getText().toString() == "0") mid.setText("");
                TEMP.setText(TEMP.getText().toString() + 0);

            }

        });

    }

    public void EnterMid() {

        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mid.setText("+");
                Show();
            }

        });

        subtract.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mid.setText("-");
                Show();
            }

        });
        multiply.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mid.setText("*");
                Show();
            }

        });
        div.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mid.setText("/");
                Show();
            }

        });

        equal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ONE.setText("0");
                TWO.setText("0");
                Result.setText("0");
                mid.setText("+");
            }
        });

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
