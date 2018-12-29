package com.example.m1k3y.projecti.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.m1k3y.projecti.R;
import com.example.m1k3y.projecti.models.PassingDataModel;
import com.example.m1k3y.projecti.utils.Utils;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 1;
    private static final String TAG = "SignInActivity";
    @BindView(R.id.tv_username)
    TextView tvUsername;
    private boolean isSignedIn = false;

    @BindView(R.id.sign_in_button)
    SignInButton signInButton;


    GoogleSignInClient googleSignInClient;
    @BindView(R.id.iv_profile)
    ImageView ivProfile;
    @BindView(R.id.bt_sign_out)
    Button btSignOut;
    @BindView(R.id.bt_enter_room)
    Button btEnterRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//Remove notification bar
        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_in);

        getSupportActionBar().hide();
        ButterKnife.bind(this);



        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);


    }


    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    private void updateUI(GoogleSignInAccount account) {
        if (account == null) {
            tvUsername.setText("Anynomous");
            Picasso.get().load(Utils.getProfilePhotoUrl(null)).into(ivProfile);
            isSignedIn = false;

        } else {
            isSignedIn = true;

            Picasso.get().load(Utils.getProfilePhotoUrl(account.getPhotoUrl())).into(ivProfile);

            tvUsername.setText(account.getDisplayName());
            String username = tvUsername.getText().toString().length() < 1 ? "Anynomous" : tvUsername.getText().toString();
            tvUsername.setText(username);
            Toast.makeText(SignInActivity.this, getString(R.string.logging_in_notification) + " with " + account.getDisplayName(), Toast.LENGTH_SHORT).show();


        }

    }

    private void signOut() {
        googleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        updateUI(null);
                        Toast.makeText(SignInActivity.this, getString(R.string.logging_out_notification), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @OnClick({R.id.sign_in_button, R.id.bt_sign_out, R.id.bt_enter_room})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sign_in_button:
                Intent signInIntent = googleSignInClient.getSignInIntent();

                startActivityForResult(signInIntent, RC_SIGN_IN);

                break;
            case R.id.bt_sign_out:
                signOut();
                break;
            case R.id.bt_enter_room:
                GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
                if (!isSignedIn || account == null) {
                    Toast.makeText(SignInActivity.this, "Please sign in with an account", Toast.LENGTH_SHORT).show();
                    break;
                }


                PassingDataModel passingDataModel = new PassingDataModel(
                        account.getDisplayName(),
                        account.getId(),
                        Utils.getProfilePhotoUrl(account.getPhotoUrl()),
                        ivProfile.getDrawable()
                );


                Intent intent = new Intent(SignInActivity.this, RoomActivity.class);
                intent.putExtra("passing_data_model", (Parcelable) passingDataModel);
                startActivity(intent);
                break;
        }
    }
};


