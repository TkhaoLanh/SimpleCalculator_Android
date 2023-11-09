package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

     TextView resultTextView, historyTextView;
     Calculator calculator;
     Button oneBtn, twoBtn, threeBtn, plusBtn, fourBtn, fiveBtn, sixBtn, minusBtn,
             sevenBtn, eightBtn, nineBtn, multiplyBtn, zeroBtn, equalBtn, divideBtn, clearBtn;
    Button buttonAdvance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_layout);

        resultTextView = findViewById(R.id.resultTextView);

        historyTextView = findViewById(R.id.historyTextView);

        //pass the application
        calculator = new Calculator(getApplication());
        // Initialize buttons and set their click listeners
        oneBtn = findViewById(R.id.oneBtn);
        twoBtn = findViewById(R.id.twoBtn);
        threeBtn = findViewById(R.id.threeBtn);
        plusBtn = findViewById(R.id.plusBtn);
        fourBtn = findViewById(R.id.fourBtn);
        fiveBtn = findViewById(R.id.fiveBtn);
        sixBtn = findViewById(R.id.sixBtn);
        minusBtn = findViewById(R.id.minusBtn);
        sevenBtn = findViewById(R.id.sevenBtn);
        eightBtn = findViewById(R.id.eightBtn);
        nineBtn = findViewById(R.id.nineBtn);
        multiplyBtn = findViewById(R.id.multiply);
        zeroBtn = findViewById(R.id.zeroBtn);
        equalBtn = findViewById(R.id.equalBtn);
        divideBtn = findViewById(R.id.divideBtn);
        clearBtn = findViewById(R.id.cBTN);

        buttonAdvance = findViewById(R.id.AdvanceBtn);

        // Set click listeners for all buttons
        oneBtn.setOnClickListener(this);
        twoBtn.setOnClickListener(this);
        threeBtn.setOnClickListener(this);
        plusBtn.setOnClickListener(this);
        fourBtn.setOnClickListener(this);
        fiveBtn.setOnClickListener(this);
        sixBtn.setOnClickListener(this);
        minusBtn.setOnClickListener(this);
        sevenBtn.setOnClickListener(this);
        eightBtn.setOnClickListener(this);
        nineBtn.setOnClickListener(this);
        multiplyBtn.setOnClickListener(this);
        zeroBtn.setOnClickListener(this);
        equalBtn.setOnClickListener(this);
        divideBtn.setOnClickListener(this);
        clearBtn.setOnClickListener(this);
        buttonAdvance.setOnClickListener(this);


        // Retrieve the calculation history from the MyApp application context
        MyApp myApp = (MyApp) getApplication();
        String history = myApp.getCalculationHistory();

        historyTextView.setVisibility(View.INVISIBLE);
        displayCalculationHistory();
    }


    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        switch (buttonText) {
            case "=":
                int calculatedResult = calculator.calculate();

                if(calculatedResult ==222){
                    //show error message
                    Toast.makeText(this,"Calculation error: Division by zero",Toast.LENGTH_SHORT).show();
                }else if(calculatedResult ==111){
                    //show invalid input message
                    Toast.makeText(this, "Invalid input: All operands must have one digit only", Toast.LENGTH_SHORT).show();
                }else if(calculatedResult ==333){
                    Toast.makeText(this, "Invalid input: All operands must have one digit only", Toast.LENGTH_SHORT).show();
                }
                else{
                    //push result
                    calculator.push(String.valueOf(calculatedResult));

                    // Append user inputs with the result
                    // Combine the buttonText and calculatedResult
                    String combinedText = buttonText + calculatedResult;
                    resultTextView.append(combinedText);

                    if(calculator.isAdvanceMode()){
                        displayCalculationHistory();

                    }
                }

                break;
            case "C":
                resultTextView.setText("");
                calculator.clear();
                break;

            case "ADVANCE - WITH HISTORY":
                // Change the title to "Standard No History"
                button.setText("STANDARD - NO HISTORY");
                button.setBackgroundColor(Color.GRAY);
                calculator.toggleMode();
                displayCalculationHistory();
                historyTextView.setVisibility(View.VISIBLE);
                resultTextView.setText("");
                calculator.clear();
                break;
            case "STANDARD - NO HISTORY":
                button.setText("ADVANCE - WITH HISTORY");
                button.setBackgroundColor(Color.RED);
                calculator.toggleMode();
                historyTextView.setVisibility(View.INVISIBLE);
                historyTextView.setText("");
                resultTextView.setText("");
               // calculator.clear();
                break;
            default:
                    //If this is a single digit, push it to the textview
                    calculator.push(buttonText);
                    resultTextView.append(buttonText);
                break;
        }
    }

    //display history of calculation
    public void displayCalculationHistory() {

        // Call the getCalculationHistory method to get the history as a string
        String history = ((MyApp)getApplication()).getCalculationHistory();

        // Display the history in the historyTextView
        historyTextView.setText(history);

    }
}