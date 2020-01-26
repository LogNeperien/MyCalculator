package com.edu.ck.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private int number1 = -1;
    private int number2 = -1;
    private String calculFinal;
    private double resultatFinal;

    private int buffer;

    //getter
    public String getCalculFinal() {
        return calculFinal;
    }

    public int getNumber1() {
        return number1;
    }

    public int getNumber2() {
        return number2;
    }

    public double getResultatFinal() {
        return resultatFinal;
    }

    //setter
    public void setNumber1(int nb) {
        number1 = nb;
    }

    public void setNumber2(int nb) {
        number2 = nb;
    }

    public void setCalculFinal(String str) {
        calculFinal = str;
    }

    public  void setResultatFinal(double nb) {
        resultatFinal = nb;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void myClickHandler(View view) {
        TextView calcul = (TextView) findViewById(R.id.calcul);
        TextView result = (TextView) findViewById(R.id.result);

        switch (view.getId()) {
            case R.id.button1:
                calcul.setText("1");
                buffer = 1;
                break;
            case R.id.button2:
                calcul.setText("2");
                buffer = 2;
                break;
            case R.id.button3:
                calcul.setText("3");
                buffer = 3;
                break;
            case R.id.button4:
                calcul.setText("4");
                buffer = 4;
                break;
            case R.id.button5:
                calcul.setText("5");
                buffer = 5;
                break;
            case R.id.button6:
                calcul.setText("6");
                buffer = 6;
                break;
            case R.id.button7:
                calcul.setText("7");
                buffer = 7;
                break;
            case R.id.button8:
                calcul.setText("8");
                buffer = 8;
                break;
            case R.id.button9:
                calcul.setText("9");
                buffer = 9;
                break;
            case R.id.button0:
                calcul.setText("0");
                buffer = -1;
                break;
            case R.id.buttonMoins:
                calcul.setText("-");
                buffer = -1;
                break;
            case R.id.buttonPlus:
                calcul.setText("+");
                buffer = -1;
                break;
            case R.id.buttonDivision:
                calcul.setText("/");
                buffer = -1;
                break;
            case R.id.buttonMultiplication:
                calcul.setText("*");
                buffer = -1;
                break;

        }

        //result.setText(String.valueOf(buffer));

        if(getNumber1() == -1)
        {
            setNumber1(buffer);
            result.setText(String.valueOf(buffer));
        }
        else if(getNumber2() == -1)
            setNumber2(buffer);

        buffer = -1;


    }

}
