package com.example.tabeebook.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tabeebook.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    //widgets
    private EditText logEmail, logPassword;
    private Button logLogin;
    private ProgressBar progressBar;
    private TextView txtRegister, txtEnterTheApp;

    //vars
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logEmail = findViewById(R.id.login_email);
        logPassword = findViewById(R.id.login_password);
        logLogin = findViewById(R.id.login_btn);
        txtEnterTheApp = findViewById(R.id.login_txt_enter_theApp);
        progressBar = findViewById(R.id.register_pb);

        progressBar.setVisibility(View.INVISIBLE);

        mAuth = FirebaseAuth.getInstance();

        txtRegister = findViewById(R.id.login_txt_register);

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        txtEnterTheApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, Home.class));
            }
        });

        logLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                logLogin.setVisibility(View.INVISIBLE);


                final String Email = logEmail.getText().toString();
                final String Password = logPassword.getText().toString();


                if (Email.length() == 0 || Password.length() == 0) {
                    showMessage("please fill the fields");
                    logLogin.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                } else {
                    loginUser(Email, Password);
                }

            }
        });
    }

    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    showMessage("Authentication Failed");
                    logLogin.setVisibility(View.VISIBLE);
                } else {
                   // if (mAuth.getCurrentUser().isEmailVerified()) {
                        Intent intent = new Intent(LoginActivity.this, Home.class);
                        startActivity(intent);
                        finish();
                   // }else {
                       // showMessage("Please Verify Your Email");
                       // logLogin.setVisibility(View.VISIBLE);


                }
            }
        });
    }

    private void showMessage(String message) {

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null && user.isEmailVerified()) {
            // redirect user to his home page
            startActivity(new Intent(LoginActivity.this, Home.class));
            finish();
        }
    }
}