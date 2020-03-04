package com.edu.ck.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static com.edu.ck.mycalculator.R.id.ancienResultat;

public class Historique extends AppCompatActivity {

    private TextView ancienCalcul;
    private TextView ancienResult;
    private String nomURL = "";
    private EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique);

        System.out.println("1");

        ancienCalcul = (TextView) findViewById(R.id.ancienCalcul);
        ancienResult = (TextView) findViewById(ancienResultat);
        text = (EditText) findViewById(R.id.editText);

        System.out.println("1bis");


        Intent intent = getIntent();

        System.out.println("2");

        String calcul = intent.getStringExtra("calcul");
        String resultat = intent.getStringExtra("resultat");

        System.out.println("3");


        ancienCalcul.setText(calcul);
        ancienResult.setText(resultat);

        System.out.println("4");

    }

    public void myClickHandler (View view)
    {
        switch (view.getId()) {
            case R.id.buttonWeb:
                if (text.getText().length() > 0) {
                    nomURL = text.getText().toString();
                    Intent intent2 = new Intent(this, WebActivity.class);
                    intent2.putExtra("URL", nomURL);
                    startActivity(intent2);
                }
                break;
        }
    }
}
