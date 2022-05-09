package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText display;
    private boolean isNewOperator=true;
    private String operator="";
    private String oldNumber="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display=findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);
    }

    private void updateText(String strToAdd){
        if(isNewOperator) display.setText("");
        isNewOperator=false;
        String oldStr=display.getText().toString();
        int cursor=display.getSelectionStart();
        String leftStr=oldStr.substring(0,cursor);
        String rightStr=oldStr.substring(cursor);
        display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
        display.setSelection(cursor+1);
    }

    private void setOperator(String operator){
        isNewOperator=true;
        oldNumber=display.getText().toString();
        this.operator=operator;
    }

    private void calculate(){
        String newNumber=display.getText().toString();
        double result=0.0;
        try{
            switch(operator){
                case "+":
                    result=Double.parseDouble(oldNumber)+Double.parseDouble(newNumber);
                    break;
                case "-":
                    result=Double.parseDouble(oldNumber)-Double.parseDouble(newNumber);
                    break;
                case "x":
                    result=Double.parseDouble(oldNumber)*Double.parseDouble(newNumber);
                    break;
                case "/":
                    if(Double.parseDouble(newNumber)==0.0){
                        display.setText("Not a number");
                        return;
                    }
                    result=Double.parseDouble(oldNumber)/Double.parseDouble(newNumber);
                    break;
            }
        }
        catch (NumberFormatException e) {
            display.setText("Not a number");
        }
        result=Math.round(result*10000.0)/10000.0;
        display.setText(result+"");
    }

    public void zeroBtn(View view){
        updateText("0");
    }

    public void oneBtn(View view){
        updateText("1");
    }

    public void twoBtn(View view){
        updateText("2");
    }

    public void threeBtn(View view){
        updateText("3");
    }

    public void fourBtn(View view){
        updateText("4");
    }

    public void fiveBtn(View view){
        updateText("5");
    }

    public void sixBtn(View view){
        updateText("6");
    }

    public void sevenBtn(View view){
        updateText("7");
    }

    public void eightBtn(View view){
        updateText("8");
    }

    public void nightBtn(View view){
        updateText("9");
    }

    public void backspaceBtn(View view){
        int cursor=display.getSelectionStart();
        int textLen=display.getText().length();
        if(cursor!=0 && textLen!=0){
            SpannableStringBuilder selection= (SpannableStringBuilder) display.getText();
            selection.replace(cursor-1,cursor,"");
            if(textLen==1){
                display.setText("0");
                display.setSelection(1);
                isNewOperator=true;
                return;
            }
            display.setText(selection);
            display.setSelection(cursor-1);
        }
    }

    public void clearBtn(View view){
        isNewOperator=true;
        operator="";
        display.setText("0");
        display.setSelection(1);
    }

    public void addBtn(View view){
        if(operator==""){
            setOperator("+");
            return;
        }
        calculate();
        setOperator("+");
    }

    public void subtractBtn(View view){
        if(operator==""){
            setOperator("-");
            return;
        }
        calculate();
        setOperator("-");
    }

    public void multiplyBtn(View view){
        if(operator==""){
            setOperator("x");
            return;
        }
        calculate();
        setOperator("x");
    }

    public void divideBtn(View view){
        if(operator==""){
            setOperator("/");
            return;
        }
        calculate();
        setOperator("/");
    }

    public void equalBtn(View view){
        if(operator=="") return;
        calculate();
        setOperator("");
    }

    public void ceBtn(View view){
        isNewOperator=true;
        display.setText("0");
        display.setSelection(1);
    }

    public void plusMinusBtn(View view){
        int cursor=display.getSelectionStart();
        String number=display.getText().toString();
        int len=number.length();
        if(number.charAt(0)!='-'){
            number="-"+number;
            display.setText(number);
            display.setSelection(cursor+1);
            return;
        }
        number=number.substring(1,len);
        display.setText(number);
        display.setSelection(cursor-1);
    }

    public void pointBtn(View view){
        updateText(".");
    }
}