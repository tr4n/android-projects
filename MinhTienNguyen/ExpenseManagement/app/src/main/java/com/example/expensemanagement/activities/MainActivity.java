package com.example.expensemanagement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.expensemanagement.R;
import com.example.expensemanagement.collections.Collect;
import com.example.expensemanagement.databases.RealmHandle;

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
        //Realm.deleteRealm(Realm.getDefaultConfiguration());

    }


    @OnClick({R.id.bt_incommings, R.id.bt_outgoings, R.id.bt_statictics})
    public void onViewClicked(View view) {
        Intent intentDetailExpense = new Intent(MainActivity.this, DetailExpenseActivity.class);


        switch (view.getId()) {
            case R.id.bt_incommings:
               intentDetailExpense.putExtra("type", Collect.INCOMING);
                startActivity(intentDetailExpense);
                break;
            case R.id.bt_outgoings:
                intentDetailExpense.putExtra("type", Collect.OUTGOING);
                startActivity(intentDetailExpense);
                break;
            case R.id.bt_statictics:
                Intent intent = new Intent(MainActivity.this, StatisticActivity.class);
                startActivity(intent);
                break;
        }
    }
}
