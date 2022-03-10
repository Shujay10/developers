package com.example.developers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

public class SignUpMainActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputEditText email,pass,conf;
    Button signin,signup;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_main);

        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.email_sup);
        pass = findViewById(R.id.pass_sup);
        conf = findViewById(R.id.conf_sup);
        signin = findViewById(R.id.signin_sup);
        signup = findViewById(R.id.signup_sup);

        signin.setOnClickListener(this);
        signup.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.signin_sup){
            startActivity(new Intent(getApplicationContext(),SignInMainActivity.class));
            finish();
        }

        if(view.getId() == R.id.signup_sup){

            String ema = email.getText().toString();
            String pas = pass.getText().toString();

            if(valid()){
                mAuth.createUserWithEmailAndPassword(ema,pas)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Intent intn = new Intent(getApplicationContext(),MainActivity.class);
                                    intn.putExtra("email",mAuth.getCurrentUser().getEmail());
                                    startActivity(intn);
                                    finish();
                                }else{
                                    Toast.makeText(getApplicationContext(),"Auth Failed",Toast.LENGTH_SHORT);
                                }
                            }
                        });
            }else {
                Toast.makeText(getApplicationContext(),"Invalid Input",Toast.LENGTH_SHORT);
            }

        }

    }

    private boolean valid(){

        boolean val = true;

        String getEmail = email.getText().toString();
        String getPass = pass.getText().toString();
        String getCPass = conf.getText().toString();

        if(getEmail.isEmpty()){
            val = false;
            email.setError("Required");
        }

        if(getPass.isEmpty()){
            val = false;
            pass.setError("Required");
        }

        if(getPass.length() < 6){
            val = false;
            pass.setError("6+ char required");
            conf.setText("");
        }

        if(getCPass.isEmpty()){
            val = false;
            conf.setError("Required");
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(getEmail).matches()){
            val = false;
            email.setError("Enter Valid email id");
        }

        if(!getPass.equals(getCPass)){
            val = false;
            conf.setText("");
            conf.setError("Re Enter Password");
        }

        return val;
    }

}