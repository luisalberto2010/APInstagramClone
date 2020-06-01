package com.example.apinstagramclone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpLoginActivity extends AppCompatActivity {

    private EditText edtUserName, edtPassword, edtUserNameForLogin, edtPasswordForLogin;
    private Button btnSignUpNewUser, btnLogin;



    @Override
    protected void onCreate(@Nullable Bundle saveInstanceState){
        super.onCreate(saveInstanceState);

        setContentView(R.layout.signup_login_activity);

        edtUserName = findViewById(R.id.edtUserName);
        edtPassword=findViewById(R.id.edtPassword);
        edtUserNameForLogin = findViewById(R.id.edtUserNameForLogin);
        edtPasswordForLogin=findViewById(R.id.edtPswordForLogin);
        btnSignUpNewUser = findViewById(R.id.btnSignUpNewUser);
        btnLogin = findViewById(R.id.btnLogin);

        btnSignUpNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser appUser = new ParseUser();
                appUser.setUsername(edtUserName.getText().toString());
                appUser.setPassword(edtPassword.getText().toString());
                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null){
                            Toast.makeText(SignUpLoginActivity.this,"User is signedup succesfully",Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(SignUpLoginActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                        }

                    }
                });


            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser.logInInBackground(edtUserNameForLogin.getText().toString(), edtPasswordForLogin.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user!=null){
                            Toast.makeText(SignUpLoginActivity.this,"User logged in successfully",Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(SignUpLoginActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                        }

                    }
                });

            }
        });

    }



}
