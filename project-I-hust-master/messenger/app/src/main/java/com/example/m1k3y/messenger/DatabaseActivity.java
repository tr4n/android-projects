package com.example.m1k3y.messenger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DatabaseActivity extends AppCompatActivity {

    private static final String TAG = "DatabaseActivity";
    @BindView(R.id.et_id)
    EditText etId;
    @BindView(R.id.et_book)
    EditText etBook;
    @BindView(R.id.bt_add)
    Button btAdd;
    @BindView(R.id.bt_read)
    Button btRead;
    @BindView(R.id.et_author)
    EditText etAuthor;
    @BindView(R.id.bt_edit)
    Button btEdit;
    @BindView(R.id.bt_delete)
    Button btDelete;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        ButterKnife.bind(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("books");
    }

    @OnClick({R.id.bt_add, R.id.bt_read, R.id.bt_edit, R.id.bt_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_add:
                databaseReference.push().setValue(new BookModel(
                        etId.getText().toString(),
                        etBook.getText().toString(),
                        etAuthor.getText().toString()
                ));
                Log.d(TAG, "onViewClicked: " + etId.getText().toString());
                break;
            case R.id.bt_read:
                break;
            case R.id.bt_edit:
                break;
            case R.id.bt_delete:
                break;
        }
    }
}
