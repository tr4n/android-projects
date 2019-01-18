package com.example.expensemanagement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.expensemanagement.R;
import com.example.expensemanagement.adapters.ExpenseAdapter;
import com.example.expensemanagement.collections.Collect;
import com.example.expensemanagement.databases.RealmHandle;
import com.example.expensemanagement.models.ExpenseModel;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StatisticActivity extends AppCompatActivity {
    @BindView(R.id.bt_incommings)
    Button btIncommings;
    @BindView(R.id.bt_outgoings)
    Button btOutgoings;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.bt_total)
    Button btTotal;
    @BindView(R.id.tv_total_incomings)
    TextView tvTotalIncomings;
    @BindView(R.id.tv_total_outgoings)
    TextView tvTotalOutgoings;
    @BindView(R.id.tv_surplus)
    TextView tvSurplus;
    @BindView(R.id.ll_surplus)
    LinearLayout llSurplus;
    @BindView(R.id.bt_delete)
    Button btDelete;
    @BindView(R.id.bt_home)
    Button btHome;
    private List<ExpenseModel> expenseModelList = new ArrayList<>();
    private List<ExpenseModel> incomingList = new ArrayList<>();
    private List<ExpenseModel> outgoingList = new ArrayList<>();
    private static final String TAG = "StatisticActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        ButterKnife.bind(this);


        expenseModelList = RealmHandle.getInstance().expenseModelList();

        for (ExpenseModel expenseModel : expenseModelList) {
            if (expenseModel.getType() == Collect.INCOMING) {
                incomingList.add(expenseModel);

            } else {
                outgoingList.add(expenseModel);
            }
        }
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        seeTotal();


    }

    private void seeTotal() {
        recyclerView.setVisibility(View.GONE);
        llSurplus.setVisibility(View.VISIBLE);
        BigInteger totalIncomings = BigInteger.ZERO, totalOutgoings = BigInteger.ZERO;
        for (ExpenseModel expenseModel : expenseModelList) {
            BigInteger temp = new BigInteger(expenseModel.getMoney());
            if (expenseModel.getType() == Collect.INCOMING) {
                totalIncomings = totalIncomings.add(temp);
            } else {
                totalOutgoings = totalOutgoings.add(temp);
            }
        }
        tvTotalIncomings.setText("Tổng số khoản thu: " + Collect.getFormatNumber(totalIncomings) + " vnd");
        tvTotalOutgoings.setText("Tổng số khoản chi: " + Collect.getFormatNumber(totalOutgoings) + " vnd");
        tvSurplus.setText("Khoản dư: " + Collect.getFormatNumber(totalIncomings.subtract(totalOutgoings)) + " vnd");
    }

    @OnClick({R.id.bt_incommings, R.id.bt_outgoings, R.id.bt_total, R.id.bt_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_incommings:
                recyclerView.setVisibility(View.VISIBLE);
                llSurplus.setVisibility(View.GONE);
                recyclerView.setAdapter(new ExpenseAdapter(incomingList, this));
                break;
            case R.id.bt_outgoings:
                recyclerView.setVisibility(View.VISIBLE);
                llSurplus.setVisibility(View.GONE);

                recyclerView.setAdapter(new ExpenseAdapter(outgoingList, this));
                break;
            case R.id.bt_total:
                seeTotal();
                break;
            case R.id.bt_delete:
                CountDownTimer countDownTimer = new CountDownTimer(200, 100) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        RealmHandle.getInstance().deleteAllData();
                    }

                    @Override
                    public void onFinish() {
                        Intent intent = new Intent(StatisticActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }.start();

                break;

        }
    }


    @OnClick(R.id.bt_home)
    public void onViewClicked() {
        startActivity(new Intent(StatisticActivity.this, MainActivity.class));
    }
}
