package com.example.mrfooody;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class LoginActivity extends AppCompatActivity {

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView signInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signInBtn = findViewById(R.id.signIn_btn);

      gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
// Build a GoogleSignInClient with the options specified by gso.
       gsc = GoogleSignIn.getClient(this, gso);

       GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
       if(acct!=null){
           navigateToSecondActivity();
       }

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 signIn();
            }
        });

    }
    void signIn(){
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent,1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

           try {
               task.getResult(ApiException.class);
               navigateToSecondActivity();
           }catch(ApiException e){
               Toast.makeText(getApplicationContext(), "Please Turn On Your Internet and try again", Toast.LENGTH_SHORT).show();
           }
        }
    }
    void navigateToSecondActivity(){
        finish();
        Intent intent= new Intent(LoginActivity.this,Food_court.class);
        startActivity(intent);
    }
}