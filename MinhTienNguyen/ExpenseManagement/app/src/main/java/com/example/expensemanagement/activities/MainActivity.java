package com.example.expensemanagement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.expensemanagement.R;
import com.example.expensemanagement.collections.Collect;
import com.example.expensemanagement.models.ExpenseTypePasser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.bt_incommings)
    Button btIncommings;
    @BindView(R.id.bt_outgoings)
    Button btOutgoings;
    @BindView(R.id.bt_statictics)
    Button btStatictics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Realm.init(getApplicationContext());

    }


    @OnClick({R.id.bt_incommings, R.id.bt_outgoings, R.id.bt_statictics})
    public void onViewClicked(View view) {
        Intent intentDetailExpense = new Intent(MainActivity.this, DetailExpenseActivity.class);
        ExpenseTypePasser expenseTypePasser;

        switch (view.getId()) {
            case R.id.bt_incommings:
               expenseTypePasser = new ExpenseTypePasser(Collect.INCOMING);
               intentDetailExpense.putExtra("type", expenseTypePasser);
                startActivity(intentDetailExpense);
                break;
            case R.id.bt_outgoings:
                expenseTypePasser = new ExpenseTypePasser(Collect.OUTGOING);
                intentDetailExpense.putExtra("type", expenseTypePasser);
                startActivity(intentDetailExpense);
                break;
            case R.id.bt_statictics:
                Intent intent = new Intent(MainActivity.this, StatisticActivity.class);
                startActivity(intent);
                break;
        }
    }
}
