package com.example.developers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInMainActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputEditText email,pass;
    Button signin,signup;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_main);

        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.email_sin);
        pass = findViewById(R.id.pass_sin);
        signin = findViewById(R.id.signin_sin);
        signup = findViewById(R.id.signup_sin);

        signin.setOnClickListener(this);
        signup.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.signin_sin){

            String ema = email.getText().toString();
            String pas = pass.getText().toString();

            mAuth.signInWithEmailAndPassword(ema,pas)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent intn = new Intent(getApplicationContext(),MainActivity.class);
                                intn.putExtra("email",mAuth.getCurrentUser().getEmail());
                                startActivity(intn);
                                finish();
                            }
                        }
                    });

        }

        if(view.getId() == R.id.signup_sin){

            startActivity(new Intent(getApplicationContext(),SignUpMainActivity.class));
            finish();
        }

    }

    private boolean valid(){

        boolean val = true;

        String em = email.getText().toString();
        String ps = pass.getText().toString();


        if(em.equals("")){
            val = false;
            email.setError("Required");
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(em).matches()){
            val = false;
            email.setError("Enter Valid email id");
        }

        if(ps.equals("")){
            val = false;
            pass.setError("Required");
        }

        return val;
    }

}