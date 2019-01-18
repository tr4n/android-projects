package com.example.expensemanagement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.expensemanagement.R;
import com.example.expensemanagement.collections.Collect;
import com.example.expensemanagement.databases.RealmHandle;
import com.example.expensemanagement.models.ExpenseModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailExpenseActivity extends AppCompatActivity {
    private static final String TAG = "DetailExpenseActivity";

    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.et_money)
    EditText etMoney;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.bt_save)
    Button btSave;
    @BindView(R.id.bt_cancel)
    Button btCancel;

    boolean condition = false;
    int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_expense);
        ButterKnife.bind(this);

        Initialization();
    }

    private void Initialization() {

        type = getIntent().getIntExtra("type", 0);
        tvType.setText(type == Collect.INCOMING ? "Thu" : "Chi");
        tvTime.setText(Collect.getTime("HH:mm - dd/MM/yyyy"));
    }

    @OnClick({R.id.bt_save, R.id.bt_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_save:
                condition = true;
                ExpenseModel expenseModel = new ExpenseModel();
                String time = tvTime.getText().toString().trim();
                String money = etMoney.getText().toString();
                String content = etContent.getText().toString();
                if (money.length() == 0 || content.length() == 0) {
                    condition = false;
                    return;
                }
                expenseModel.get(time, type, money, content);
                RealmHandle.getInstance().addExpense(expenseModel);
                if (condition) {
                    Toast.makeText(DetailExpenseActivity.this, "Lưu thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DetailExpenseActivity.this, StatisticActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(DetailExpenseActivity.this, "Cần nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                }


                break;
            case R.id.bt_cancel:
                onBackPressed();
                break;
        }
    }
}
