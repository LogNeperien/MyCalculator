package com.edu.ck.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Historique extends AppCompatActivity {

    private TextView ancienCalcul;
    private TextView ancienResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique);

        Intent intent = getIntent();

        String calcul = intent.getStringExtra("calcul");
        String resultat = intent.getStringExtra("resultat");

        ancienCalcul = (TextView) findViewById(R.id.ancienCalcul);
        ancienResult = (TextView) findViewById(R.id.ancienResultat);

        ancienCalcul.setText(calcul);
        ancienResult.setText(resultat);


    }
}
