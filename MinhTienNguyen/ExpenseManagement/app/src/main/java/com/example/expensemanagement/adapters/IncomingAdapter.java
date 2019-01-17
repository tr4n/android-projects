package com.example.expensemanagement.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.expensemanagement.R;
import com.example.expensemanagement.models.ExpenseModel;

import java.util.List;

public class IncomingAdapter extends RecyclerView.Adapter<IncomingAdapter.ExpenseHolder> {

    private List <ExpenseModel> expenseModelList;
    private Context context;

    public IncomingAdapter(List<ExpenseModel> expenseModelList, Context context) {
        this.expenseModelList = expenseModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ExpenseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.expense_layout, null);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(2, 2, 2, 2);
        view.setLayoutParams(layoutParams);


        return new ExpenseHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseHolder expenseHolder, int position) {
        expenseHolder.setData(expenseModelList.get(position));
    }

    @Override
    public int getItemCount() {
         return expenseModelList.size();
    }


    public class ExpenseHolder extends RecyclerView.ViewHolder{
        private Context context;
        private View itemView;
        private TextView tvDate;
        private TextView tvMoney;
        private TextView tvContent;

        public ExpenseHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.context = context;
            this.itemView = itemView;
            this.tvDate = itemView.findViewById(R.id.tv_date);
            this.tvContent = itemView.findViewById(R.id.tv_content);
            this.tvMoney = itemView.findViewById(R.id.tv_money);
        }

        public void setData(ExpenseModel expenseModel){
            tvMoney.setText("Money: "+ expenseModel.getMoney() + " vnd");
            tvContent.setText("Content: " + expenseModel.getContent());
            tvDate.setText("Time: " + expenseModel.getDate());
        }
    }

}
