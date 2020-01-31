package com.edu.ck.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {


    private int number1 = -1;
    private int number2 = -1;
    private String operation = "";
    private String calculFinal = "";
    private double resultatFinal;
    private boolean isNumber1Finished = false;
    private boolean isNumber2Finished = false;

    private int buffer;
    private Handler handler;
    private TextView calcul;
    private TextView result;

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

    public boolean getIsNumber1Finished () {return isNumber1Finished; }

    public boolean getIsNumber2Finished () {return isNumber2Finished; }

    public String getOperation () {return operation;}

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

    public void setIsNumber1Finished (boolean res) {isNumber1Finished = res;}

    public void setIsNumber2Finished (boolean res) {isNumber2Finished = res;}

    public void setOperation (String newOperation) {operation = newOperation;}

    public void addCalculFinal (String str) { calculFinal += str; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calcul = (TextView) findViewById(R.id.calcul);
        result = (TextView) findViewById(R.id.result);

        //ajout du bouton 2
        Button buttonEgal = new Button(this);
        buttonEgal.setId(R.id.buttonEgal);
        buttonEgal.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1.0f)
        );
        buttonEgal.setText("=");

        //handler method
        handler = new Handler();

        LinearLayout layout = findViewById(R.id.allButton);
        layout.addView(buttonEgal);
    }




    public void myClickHandler(View view) {

        whatButtonDidIPressed(view);

        if(buffer > -1) //si on ne change pas de nombre
        {
            if(getIsNumber1Finished() == false) { //si on a pas encore fini de prendre le premier nombre
                if (getNumber1() == -1) {
                    //si on ajoute un chiffre pour la premiere fois au nombre
                    //on met ce chiffre directement dans le nombre
                    setNumber1(buffer);
                    setCalculFinal(String.valueOf(buffer));
                } else {
                    //si on a deja un ou des chiffres dans notre nombre et qu'on veut en ajouter un
                    //par exemple on veut 6503 et qu'on a deja taper 650
                    //alors on modifie le nombre en le multipliant par 10 (ici 6500)
                    //et en ajoutant le nouveau bouton sur lequel on a taper (ici 3)
                    setNumber1(getNumber1() * 10 + buffer);
                    addCalculFinal(String.valueOf(buffer));
                }
            }
            else{ //si on a fini de prendre le premier nombre
                addCalculFinal(String.valueOf(buffer));
                if (getNumber2() == -1) {
                    setNumber2(buffer);
                } else {
                    setNumber2(getNumber2() * 10 + buffer);
                }
            }
        }
        else if(buffer > -5 && getOperation()== "" && getNumber1()!= -1) // si on effectue une opération
        {
            setIsNumber1Finished(true);
            addCalculFinal(" ");
            switch(buffer){
                case -1:
                    setOperation("-");
                    break;
                case -2:
                    setOperation("+");
                    break;
                case -3:
                    setOperation("/");
                    break;
                case -4:
                    setOperation("*");
                    break;
            }
            addCalculFinal(getOperation());
            addCalculFinal(" ");
        }
        else if(buffer == -5 && getIsNumber1Finished() == true && getOperation() != "" && getNumber2() != -1 )
        { // si on fait egal et qu'on peut faire l'opération
            switch (getOperation()){
                case "-":
                    setResultatFinal(getNumber1() - getNumber2());
                    break;
                case "+":
                    setResultatFinal(getNumber1() + getNumber2());
                    break;
                case "*":
                    setResultatFinal(getNumber1() * getNumber2());
                    break;
                case "/":
                    setResultatFinal(((double)getNumber1()) / ((double)getNumber2()));
                    break;
            }
            result.setText(String.valueOf(getResultatFinal()));

            //réinitialisation
            setNumber1(-1);
            setNumber2(-1);
            setOperation("");
            setIsNumber1Finished(false);

        }
        else
        {
            result.setText("ERROR");
        }

        calcul.setText(getCalculFinal());
    }

    private void whatButtonDidIPressed(View view)
    {
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
                buffer = 0;
                break;
            case R.id.buttonMoins:
                calcul.setText("-");
                buffer = -1;
                break;
            case R.id.buttonPlus:
                calcul.setText("+");
                /*Toast.makeText(getApplicationContext(), "+",
                        Toast.LENGTH_LONG).show();*/
                buffer = -2;
                break;
            case R.id.buttonDivision:
                calcul.setText("/");
                buffer = -3;
                break;
            case R.id.buttonMultiplication:
                calcul.setText("*");
                buffer = -4;
                break;
            case R.id.buttonEgal:
                calcul.setText("=");
                buffer = -5;
                break;

        }

    }

}
