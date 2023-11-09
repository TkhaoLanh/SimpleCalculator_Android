package com.example.myapplication;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class MyApp extends Application {
    private List<String> historyList;

    public String getCalculationHistory() {
        if (historyList == null) {
            historyList = new ArrayList<>();
        }
        StringBuilder history = new StringBuilder();
        for (String calculation : historyList) {
            history.append(calculation).append("\n");
        }
        return history.toString();
    }

    public void addCalculation(String calculation) {

        historyList.add(calculation);
    }

}
