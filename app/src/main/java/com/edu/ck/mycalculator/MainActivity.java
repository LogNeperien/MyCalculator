package com.edu.ck.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.os.Handler;
import android.annotation.SuppressLint;
import android.os.AsyncTask;


import java.io.*;
import java.net.*;

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
    protected void onCreate(Bundle savedInstanceState){
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

        //Methode Handler
        buttonEgal.setOnClickListener(handlerVersion());

        //Methode AsyncTask
        //buttonEgal.setOnClickListener(asyncTaskVersion());
    }


    //Version Handler
    View.OnClickListener handlerVersion() {

        return new View.OnClickListener() {
            public void onClick(View view) {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                result.setText(calcul());
                                calcul.setText(getCalculFinal());
                            }
                        });
                    }
                };
                new Thread(runnable).start();
            }
        };
    }

    //Version Async
    View.OnClickListener asyncTaskVersion()
    {
        return new View.OnClickListener()
        {
            public void onClick(View v)
            {
                AsyncEqual task = new AsyncEqual();
                task.execute();
            }
        };
    }

    private class AsyncEqual extends AsyncTask<Void, Void, String>{
        @SuppressLint("WrongThread")
        protected String doInBackground(Void... vals) {



            try {
                //creation Socket
                Socket s = new Socket("192.168.81.1", 9876);
                //création des streams
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                //on envoie les données au serveur
                dos.writeDouble((double)(getNumber1())); //on transforme le number 1 en double
                dos.flush();
                dos.writeDouble((double)(getNumber2())); // on transforme le number 2 en double
                dos.flush();
                dos.writeDouble(getOperation().charAt(0)); // on transforme la string opération en char
                dos.flush();

                //on récupère la donnée
                setResultatFinal(dis.readDouble());

                //on ferme les streams
                dis.close();
                dos.close();
                //on ferme le socket
                s.close();


            } catch (UnknownHostException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            //envoie
            /*
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            out.println("hello");
            out.flush();

            //reception
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

             */
            return String.valueOf(getResultatFinal());
        }
        protected void onProgressUpdate(Integer... progress) {
            //...
        }
        protected void onPostExecute(String res) {
            //Affiche le resultat
            result.setText(res);
        }
    }



    //Thread basique
    public void myClickHandler(View view) {

        whatButtonDidIPressed(view);
        //calcul();
        //calcul.setText(getCalculFinal());
    }

    private void whatButtonDidIPressed(View view)
    {
        switch (view.getId()) {
            case R.id.button1:
                buffer = 1;
                break;
            case R.id.button2:
                buffer = 2;
                break;
            case R.id.button3:
                buffer = 3;
                break;
            case R.id.button4:
                buffer = 4;
                break;
            case R.id.button5:
                buffer = 5;
                break;
            case R.id.button6:
                buffer = 6;
                break;
            case R.id.button7:
                buffer = 7;
                break;
            case R.id.button8:
                buffer = 8;
                break;
            case R.id.button9:
                buffer = 9;
                break;
            case R.id.button0:
                buffer = 0;
                break;
            case R.id.buttonMoins:
                buffer = -1;
                break;
            case R.id.buttonPlus:
                buffer = -2;
                break;
            case R.id.buttonDivision:
                buffer = -3;
                break;
            case R.id.buttonMultiplication:
                buffer = -4;
                break;
            case R.id.buttonEgal:
                buffer = -5;
                break;

        }

        if(buffer > -1) //si on ne change pas de nombre
        {
            if (getIsNumber1Finished() == false) { //si on a pas encore fini de prendre le premier nombre
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
            } else { //si on a fini de prendre le premier nombre
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
        calcul.setText(getCalculFinal());

    }

    private String calcul()
    {

        if(getIsNumber1Finished() && getOperation() != "" && getNumber2() != -1 )
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

            //réinitialisation
            setNumber1(-1);
            setNumber2(-1);
            setOperation("");
            setIsNumber1Finished(false);

            return String.valueOf(getResultatFinal());
        }
        else
        {
            return "Error";
        }
    }

    //fonction qui fait le lien avec le serveur
    /*
    private void connection()
    {
        String hostName = "coucou";
        int portNumber = 0;

        try (
                Socket echoSocket = new Socket(hostName, portNumber);

                PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
        )
    }

     */

}
