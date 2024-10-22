package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView displayTextView;
    private String currentInput = "";
    private String operator = "";
    private double firstOperand = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayTextView = findViewById(R.id.display);

        // Set OnClickListener for all buttons
        int[] buttonIds = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6, R.id.button7,
                R.id.button8, R.id.button9, R.id.buttonAdd, R.id.buttonSubtract,
                R.id.buttonMultiply, R.id.buttonDivide, R.id.buttonDecimal,
                R.id.buttonEquals, R.id.buttonClear
        };

        for (int id : buttonIds) {
            findViewById(id).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        String buttonText = ((Button) v).getText().toString();

        switch (buttonText) {
            case "C":
                clearDisplay();
                break;
            case "=":
                calculateResult();
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                handleOperator(buttonText);
                break;
            default:currentInput += buttonText;
                displayTextView.setText(currentInput);
                break;
        }
    }

    private void clearDisplay() {
        currentInput = "";
        operator = "";
        firstOperand = 0;
        displayTextView.setText("0");
    }

    private void handleOperator(String op) {
        operator = op;
        firstOperand = Double.parseDouble(currentInput);
        currentInput = "";
    }

    private void calculateResult() {
        if (!currentInput.isEmpty() && !operator.isEmpty()) {
            double secondOperand = Double.parseDouble(currentInput);
            double result = 0;

            switch (operator) {
                case "+":
                    result = firstOperand + secondOperand;
                    break;
                case "-":
                    result = firstOperand - secondOperand;
                    break;
                case "*":
                    result = firstOperand * secondOperand;
                    break;
                case "/":
                    if (secondOperand != 0) {
                        result = firstOperand / secondOperand;
                    } else {
                        displayTextView.setText("Error");
                        return;
                    }
                    break;
            }

            displayTextView.setText(String.valueOf(result));
            currentInput = String.valueOf(result);
            operator = "";
        }
    }
}