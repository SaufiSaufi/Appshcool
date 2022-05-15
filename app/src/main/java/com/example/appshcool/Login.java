package com.example.appshcool;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextEmail, editTextPassword;
    private Button sidnIn;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sidnIn =(Button) findViewById(R.id.signIn);
        sidnIn.setOnClickListener(this);
        editTextEmail=(EditText) findViewById(R.id.email);
        editTextPassword=(EditText) findViewById(R.id.password);
        progressBar=(ProgressBar) findViewById(R.id.progressBar);
        mAuth=FirebaseAuth.getInstance();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.signIn:
                userLogin();
                break;
        }
    }
    private void userLogin()
    {
        String email=editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();

        if(email.isEmpty())
        {
            editTextEmail.setError("Email is required !");
            editTextEmail.requestFocus();
            return;
        }
        if(password.isEmpty() )
        {
            editTextPassword.setError("Password is required !");
            editTextPassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        if(password.equals("123456789"))
        {
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                    //redirect to user profile

                    startActivity(new Intent(getApplicationContext(),ListeAdmin .class));
                }
                else{
                    Toast.makeText(Login.this,
                            "Failed to login! plaese check your credentials", Toast.LENGTH_LONG
                    ).show();
                }
            }
        });}
        else   if(password.equals("aziza12345")){
            startActivity(new Intent(getApplicationContext(),ListeEtudiant.class));
        }
        else  if(password.equals("saufi12345")){
            startActivity(new Intent(getApplicationContext(),ListeProf.class));
        }
        else{
            Toast.makeText(Login.this,
                    "Failed to login! plaese check your credentials", Toast.LENGTH_LONG
            ).show();

    }}
}