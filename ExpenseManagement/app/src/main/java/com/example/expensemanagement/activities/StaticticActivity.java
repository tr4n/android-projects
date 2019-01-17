package com.example.expensemanagement.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.expensemanagement.R;
import com.example.expensemanagement.adapters.IncomingAdapter;
import com.example.expensemanagement.adapters.OutgoingAdapter;
import com.example.expensemanagement.collections.Collect;
import com.example.expensemanagement.models.ExpenseModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StaticticActivity extends AppCompatActivity {
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
    private List<ExpenseModel> expenseModelList = new ArrayList<>();
    private List<ExpenseModel> incomingList = new ArrayList<>();
    private List<ExpenseModel> outgoingList = new ArrayList<>();
    private static final String TAG = "StaticticActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        ButterKnife.bind(this);

        expenseModelList.add(new ExpenseModel("12h", Collect.INCOMING, 12000, "nothing"));
        expenseModelList.add(new ExpenseModel("1h01", "incoming", 13000, "one"));
        expenseModelList.add(new ExpenseModel("12h22", "outgoing", 14000, "two"));
        expenseModelList.add(new ExpenseModel("12h23", "incoming", 125000, "notrring"));
        expenseModelList.add(new ExpenseModel("9h12", "outgoing", 14000, "two"));
        expenseModelList.add(new ExpenseModel("12h33", "incoming", 125000, "notrring"));
        expenseModelList.add(new ExpenseModel("22h22", "outgoing", 14000, "two"));
        expenseModelList.add(new ExpenseModel("3h33", "incoming", 125000, "notrring"));
        expenseModelList.add(new ExpenseModel("12h", Collect.INCOMING, 12000, "nothing"));
        expenseModelList.add(new ExpenseModel("1h01", "incoming", 13000, "one"));
        expenseModelList.add(new ExpenseModel("12h22", "outgoing", 14000, "two"));
        expenseModelList.add(new ExpenseModel("12h23", "incoming", 125000, "notrring"));
        expenseModelList.add(new ExpenseModel("9h12", "outgoing", 14000, "two"));
        expenseModelList.add(new ExpenseModel("12h33", "incoming", 125000, "notrring"));
        expenseModelList.add(new ExpenseModel("22h22", "outgoing", 14000, "two"));
        expenseModelList.add(new ExpenseModel("3h33", "incoming", 125000, "notrring"));

        for (ExpenseModel expenseModel : expenseModelList) {
            if (expenseModel.type == "incoming") {
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
        Log.d(TAG, "onCreate: " + outgoingList);


    }

    @OnClick({R.id.bt_incommings, R.id.bt_outgoings, R.id.bt_total})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_incommings:
                recyclerView.setVisibility(View.VISIBLE);
                llSurplus.setVisibility(View.GONE);
                recyclerView.setAdapter(new IncomingAdapter(incomingList, this));
                break;
            case R.id.bt_outgoings:
                recyclerView.setVisibility(View.VISIBLE);
                llSurplus.setVisibility(View.GONE);

                recyclerView.setAdapter(new OutgoingAdapter(outgoingList, this));
                break;
            case R.id.bt_total:
                recyclerView.setVisibility(View.GONE);
                llSurplus.setVisibility(View.VISIBLE);
                int totalIncomings = 0, totalOutgoings = 0;
                for (ExpenseModel expenseModel : expenseModelList) {
                    if (expenseModel.type == "incoming") {
                        totalIncomings += expenseModel.money;

                    } else {
                        totalOutgoings += expenseModel.money;
                    }
                }
                tvTotalIncomings.setText("Total Incomings: " + totalIncomings + " vnd");
                tvTotalOutgoings.setText("Total Outgoings: " + totalOutgoings + " vnd");
                tvSurplus.setText("Surplus: " + (totalIncomings - totalOutgoings) + " vnd");
                break;

        }
    }


}
