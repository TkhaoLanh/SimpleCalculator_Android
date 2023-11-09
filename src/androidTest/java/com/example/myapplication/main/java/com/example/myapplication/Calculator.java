package com.example.myapplication;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
    // Add a list to store user input
     List<String> stringList;
    // Add a list to store the calculation history
    List<String> historyList;

    private boolean isAdvanceMode;

    private Application application;

    public Calculator(Application application){

        stringList = new ArrayList<>();
        historyList = new ArrayList<>();
        isAdvanceMode = false; // default to Standard mode
        this.application = application;
    }

    public void clear() {
        stringList.clear();
        Log.d("Calculator", "ArrayList cleared");
    }

    //create push()
    public void push(String value){
            stringList.add(value);
            Log.d("Calculator", "ArrayList contents: " + stringList.toString());
    }

    public int calculate() {
        boolean errorMessage = false;
        boolean operatorFlag = false;
        boolean calculationDone = false;
        boolean digitFound = false;
        char operator = '+';
        int result = 0;
        int number1 = 0;
        int number2 = 0;

        StringBuilder concatenated = new StringBuilder();
        for (String s : stringList) {
            concatenated.append(s);
        }

        // Convert the concatenated string to a char array
        String concatenatedString = concatenated.toString();
        char[] charArray = concatenatedString.toCharArray();

        // Check the charArray as specified
        for (int i = 0; i < charArray.length - 1; i += 2) {
            // Check if char_array[i] is a digit or not and log the result
            if (!Character.isDigit(charArray[i]) || Character.isDigit(charArray[i+1])) {
                errorMessage = true;
                return 111;
            }
        }
        for (int i = 0; i < charArray.length && !errorMessage;i ++){

            switch (charArray[i]){
                case '+':
                case '-':
                case '*':
                case '/':
                    operatorFlag = true;
                     operator = charArray[i];
                    break;

                case '=':
                    if(i !=1)
                        calculationDone = true;
                        break;
                default:
                    break;
            }
            if(operatorFlag && i>0){
                if(!digitFound){
                    if(Character.isDigit(charArray[i-1])){
                       number1 = Integer.parseInt(String.valueOf(charArray[i-1]));
                    }else{
                        errorMessage = true;
                    }
                }
                if(Character.isDigit(charArray[i+1])){
                     number2 = Integer.parseInt(String.valueOf(charArray[i+1]));
                }else{
                    errorMessage = true;
                }
                if(!errorMessage){
                    switch (operator){
                        case '+':
                            result = number1 + number2;
                            break;
                        case '-':
                            result = number1 - number2;
                            break;
                        case '*':
                            result = number1 * number2;
                            break;
                        case '/':
                            if (number2 != 0) {
                                result = number1 / number2;
                            } else {
                                errorMessage = true;
                                //Division by zero
                                return 222;
                            }

                            break;
                        default:
                            errorMessage = true;
                            return 333;
                            //
                    }
                    number1 = result;
                    digitFound = true;
                    operatorFlag = false;
                }
            }
        }
        if(isAdvanceMode){
            String calculation = concatenatedString + " = " + result;
            //Retrieve the custom App class
            // Store the updated historyList in the MyApp application context
            MyApp myApp = (MyApp) application;
            myApp.addCalculation(calculation);
        }
        return result;
    }

    public void toggleMode() {
        if(isAdvanceMode){
            isAdvanceMode = false;
        }else {
            isAdvanceMode = true;
        }

    }
    public boolean isAdvanceMode() {
        return isAdvanceMode;
    }

}
