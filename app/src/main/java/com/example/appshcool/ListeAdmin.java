package com.example.appshcool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListeAdmin extends AppCompatActivity {
    ImageView deconnexion;
    CardView cpt ,pf,et,emp;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_admin);

        deconnexion=(ImageView) findViewById(R.id.logoutbtn) ;
        cpt=(CardView) findViewById(R.id.compte) ;
        et=(CardView) findViewById(R.id.etudiant) ;
        pf=(CardView) findViewById(R.id.prof) ;

        emp=(CardView) findViewById(R.id.emploi) ;

        deconnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });

        cpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(getApplicationContext(),RegisterUser.class));
            }
        });
        pf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(getApplicationContext(),GestionProf.class));
            }
        });
        et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(getApplicationContext(),GestionEtudiant.class));
            }
        });
        emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(getApplicationContext(),EmploiTemps.class));
            }
        });

    }

}