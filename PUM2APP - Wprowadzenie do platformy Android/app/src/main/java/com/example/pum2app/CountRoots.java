package com.example.pum2app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CountRoots extends AppCompatActivity {

    EditText aValue;
    EditText bValue;
    EditText cValue;
    Button check;
    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_roots);

        aValue = findViewById(R.id.eTA);
        bValue =  findViewById(R.id.eTB);
        cValue = findViewById(R.id.eTC);
        resultText = findViewById(R.id.textViewResult);

        findViewById(R.id.btnCheck).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validateAndRunCounter();
            }
        });
    }

    private void validateAndRunCounter() {
        Double aDouble, bDouble, cDouble;
        String aString, bString, cString;

        aString = aValue.getText().toString();
        if(aString.isEmpty() || aString.equals("0"))
        {
            setTextView("A nie może być puste i musi być większe od zera.");
        }
        else
        {
            aDouble = Double.parseDouble(aString);
            bString = bValue.getText().toString();
            if(bString.isEmpty())
                bDouble = 0.0;
            else
                bDouble = Double.parseDouble(bString);

            cString = cValue.getText().toString();

            if(cString.isEmpty())
                cDouble = 0.0;
            else
                cDouble = Double.parseDouble(cString);

            countRoot(aDouble, bDouble, cDouble);
        }
    }

    private void countRoot(double a, double b, double c)
    {
        if(a == 0)
        {
            setTextView("Błąd. A równe 0.");
        }

        double delta = b*b-4*a*c;

        if(delta > 0)
        {
            double x1 = (-b+Math.sqrt(delta))/(2*a) ;
            double x2 = (-b-Math.sqrt(delta))/(2*a) ;

            String resultText = String.format("x1 = %.2f x2 = %.2f", x1,x2);

            setTextView("Są dwa rozwiązania: " + resultText);
        }
        else if(delta == 0)
        {
            double x = -b/(2*a);
            String resultText = "x = " + Double.toString(x);
            setTextView("Istnieje jedno rozwiązanie: " + resultText);
        }
        else
        {
            setTextView("Błąd! Delta mniejsza od 0.");
        }

    }

    private void setTextView(String s) {
        resultText.setText(s);
    }


}
