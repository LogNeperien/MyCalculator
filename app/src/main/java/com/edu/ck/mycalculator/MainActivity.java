package com.edu.ck.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void myClickHandler(View view) {
        TextView calcul;
        calcul = (TextView) findViewById(R.id.calcul);

        TextView result;
        result = (TextView) findViewById(R.id.result);

        switch (view.getId()) {
            case R.id.button1:
                calcul.setText('1');
                break;
            case R.id.button2:
                calcul.setText('2');
                break;
            case R.id.button3:
                calcul.setText('3');
                break;
            case R.id.button4:
                calcul.setText('4');
                break;
            case R.id.button5:
                calcul.setText('5');
                break;
            case R.id.button6:
                calcul.setText('6');
                break;
            case R.id.button7:
                calcul.setText('7');
                break;
            case R.id.button8:
                calcul.setText('8');
                break;
            case R.id.button9:
                calcul.setText('9');
                break;

        }
    }

}
