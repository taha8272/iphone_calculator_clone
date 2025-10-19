package com.example.assignment1_calculator;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.mXparser;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText inputField, resultField;
    private StringBuilder currentExpression;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputField = findViewById(R.id.inputField);
        resultField = findViewById(R.id.resultField);

        currentExpression = new StringBuilder();


        setButtonListeners();
    }

    private void setButtonListeners() {

        int[] buttonIds = {

                R.id.btn_0,
                R.id.btn_1,
                R.id.btn_2,
                R.id.btn_3,
                R.id.btn_4,
                R.id.btn_5,
                R.id.btn_6,
                R.id.btn_7,
                R.id.btn_8,
                R.id.btn_9,
                R.id.btn_add,
                R.id.btn_subtract,
                R.id.btn_multiply,
                R.id.btn_divide,
                R.id.btn_dot,
                R.id.btn_percent,
                R.id.btn_parenthesis_open,
                R.id.btn_parenthesis_close,
                R.id.btn_sin,
                R.id.btn_cos,
                R.id.btn_tan,
                R.id.btn_sinh,
                R.id.btn_cosh,
                R.id.btn_tanh,
                R.id.btn_log10,
                R.id.btn_ln,
                R.id.btn_square,
                R.id.btn_cube,
                R.id.btn_power,
                R.id.btn_ten_power_x,
                R.id.btn_exponent,
                R.id.btn_sqrt,
                R.id.btn_inverse,
                R.id.btn_factorial,
                R.id.btn_pi,
                R.id.btn_e
        };

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                String value = b.getText().toString();
                appendToExpression(value);
            }
        };


        for (int id : buttonIds) {
            Button btn = findViewById(id);
            btn.setOnClickListener(listener);
        }


        findViewById(R.id.btn_AC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearExpression();
            }
        });


        findViewById(R.id.btn_equal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluateExpression();
            }
        });
    }

    private void appendToExpression(String value) {
        switch (value) {
            case "sin":
            case "cos":
            case "tan":
            case "sinh":
            case "cosh":
            case "tanh":
                currentExpression.append(value).append("(");
                break;
            case "ln":
                currentExpression.append("ln(");
                break;
            case "log₁₀":
                currentExpression.append("log10(");
                break;
            case "x²":
                currentExpression.append("^2");
                break;
            case "x³":
                currentExpression.append("^3");
                break;
            case "xʸ":
                currentExpression.append("^");
                break;
            case "10ˣ":
                currentExpression.append("10^(");
                break;
            case "eˣ":
                currentExpression.append("e^(");
                break;
            case "√x":
                currentExpression.append("sqrt(");
                break;
            case "x!":
                currentExpression.append("!");
                break;
            case "1/x":
                currentExpression.append("1/(");
                break;
            case "π":
                currentExpression.append("pi");
                break;
            case "e":
                currentExpression.append("e");
                break;
            case "×":
                currentExpression.append("*");
                break;


            default:
                currentExpression.append(value);
        }

        inputField.setText(currentExpression.toString());
        resultField.setText("");
    }

    private void clearExpression() {
        currentExpression.setLength(0);
        inputField.setText("");
        resultField.setText("");
    }



    private void evaluateExpression() {
        String expr = currentExpression.toString();
        mXparser.setDegreesMode();

        Expression e = new Expression(expr);

        double result = e.calculate();


        if (Double.isNaN(result)) {
            resultField.setText("Error");
        } else {
            
            String formatted = (result == Math.floor(result))
                    ? String.valueOf((long) result)
                    : String.valueOf(result);
            resultField.setText(formatted);
        }
    }

}

