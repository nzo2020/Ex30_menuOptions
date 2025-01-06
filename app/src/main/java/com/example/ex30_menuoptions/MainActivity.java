package com.example.ex30_menuoptions;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText num1et,num2et;
    TextView answerTv;
    String num1st,num2st;
    double answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1et = findViewById(R.id.num1);
        num2et = findViewById(R.id.num2);
        answerTv = findViewById(R.id.answerTv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean check(String st) {
        if (st.equals("+") || st.equals("+.") || st.equals("-") || st.equals("-.") || st.equals(".") || st.isEmpty()) {
            return true;
        }
        return false;
    }

    public static String differentView(double term) {
        if (term % 1 == 0 && term < 10000 && term > -10000) {
            return String.valueOf((int) term);
        }
        if (term >= 10000 || term <= -10000) {
            int exponent = 0;
            double coefficient = term;

            while (Math.abs(coefficient) >= 10000) {
                coefficient /= 10;
                exponent++;
            }
            return String.format("%d * 10^%d", (int) coefficient, exponent);
        }
        int exponent = 0;
        double coefficient = term;
        if (Math.abs(term) >= 1) {
            while (Math.abs(coefficient) >= 10) {
                coefficient /= 10;
                exponent++;
            }
        } else {
            while (Math.abs(coefficient) < 1) {
                coefficient *= 10;
                exponent--;
            }
        }
        return String.format("%.3f * 10^%d", coefficient, exponent);
    }

    public boolean onOptionsItemSelected(@Nullable MenuItem item){
        int id = item.getItemId();
        num1st = num1et.getText().toString();
        num2st = num2et.getText().toString();
        if (check(num1st) || check(num2st)){
            Toast.makeText(this, "Please enter again, There is invalid input here", Toast.LENGTH_SHORT).show();
            answerTv.setText("");
            num1et.setText("");
            num2et.setText("");
        }else{
            if (id == R.id.menuAdd){
                answer = Double.parseDouble(num1st)+Double.parseDouble(num2st);
                answerTv.setText(differentView(answer));
            } else if (id == R.id.menuSub){
                answer = Double.parseDouble(num1st)-Double.parseDouble(num2st);
                answerTv.setText(differentView(answer));
            }else if(id == R.id.menuMul){
                answer = Double.parseDouble(num1st)*Double.parseDouble(num2st);
                answerTv.setText(differentView(answer));
            }else if (id == R.id.menuDiv){
                answer = Double.parseDouble(num1st)/Double.parseDouble(num2st);
                answerTv.setText(differentView(answer));
            }else{
                answerTv.setText("");
                num1et.setText("");
                num2et.setText("");
            }
        }
        return true;
    }
}