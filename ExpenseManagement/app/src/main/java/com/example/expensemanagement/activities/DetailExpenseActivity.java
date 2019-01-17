package com.example.expensemanagement.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.expensemanagement.R;
import com.example.expensemanagement.collections.Collect;
import com.example.expensemanagement.models.ExpenseTypePasser;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailExpenseActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_expense);
        ButterKnife.bind(this);

        Initialization();
    }

    private void Initialization() {
        ExpenseTypePasser expenseTypePasser = (ExpenseTypePasser) getIntent().getSerializableExtra("type");
        tvType.setText(expenseTypePasser.getType());
        tvTime.setText(Collect.getTime("HH:mm - dd/MM/yy"));
    }

    @OnClick({R.id.bt_save, R.id.bt_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_save:
                break;
            case R.id.bt_cancel:
                onBackPressed();
                break;
        }
    }
}
