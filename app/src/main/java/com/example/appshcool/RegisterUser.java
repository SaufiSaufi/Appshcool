package com.example.appshcool;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
public class RegisterUser extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth mAuth;
    private TextView banner,registerUser;
    private EditText editTextFullName,editTextAge,editTextEmail,editTextPassword;
    private ProgressBar progressBar;
    FirebaseFirestore fStor;
    String userID;
    String tag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mAuth = FirebaseAuth.getInstance();
        fStor=FirebaseFirestore.getInstance();

        registerUser=(Button)findViewById(R.id.registeruser);
        registerUser.setOnClickListener(this);
        /////////////////////////////////////////////
        editTextFullName=(EditText) findViewById(R.id.prenom);
        editTextAge=(EditText) findViewById(R.id.nom);
        editTextEmail=(EditText) findViewById(R.id.email);
        editTextPassword=(EditText) findViewById(R.id.password);
        // progressBar=(ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.registeruser:
                registerUser();
                break;
        }
    }
    private void registerUser()
    {
        String email=editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();
        String fullName=editTextFullName.getText().toString().trim();
        String age=editTextAge.getText().toString().trim();

        if(fullName.isEmpty()){
            editTextFullName.setError("full name is required");
            editTextFullName.requestFocus();
            return;
        }
        ////////////
        if(age.isEmpty()){
            editTextAge.setError("age is required");
            editTextAge.requestFocus();
            return;
        }
        ////////////
        if(email.isEmpty()){
            editTextEmail.setError("email is required");
            editTextEmail.requestFocus();
            return;
        }
        /////////////////
        if(password.isEmpty()){
            editTextPassword.setError("password is required");
            editTextPassword.requestFocus();
            return;
        }
        if (password.length()<6)
        {
            editTextPassword.setError("min password length should be 6 characters!");
            editTextPassword.requestFocus();
            return;
        }

        //progressBar .setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {

            if(task.isSuccessful())
            {
                Toast.makeText(RegisterUser.this, "User created", Toast.LENGTH_SHORT).show();
                userID=mAuth.getCurrentUser().getUid();
                DocumentReference  documentReference=fStor.collection("Users").document(userID);
                Map<String,Object> user=new HashMap<>();
                user.put("fullName",fullName);
                user.put("mail",email);
                user.put("AGE",age);
                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(tag,"user profile is create");
                    }
                });
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
            else
            {
                Toast.makeText(RegisterUser.this, "Erreur !", Toast.LENGTH_SHORT).show();
                //progressBar .setVisibility(View.GONE);
                startActivity(new Intent(this, RegisterUser.class));
            }
        });
    }
}





