package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;




public class SecondActivity extends AppCompatActivity {



    double finalSum = 0;

    char operator = 'n';
    double tempSum = 0;
    double firstVal = 0;
    String firstValStr = "";

    double secondVal = 0;
    String secondValStr = "";
    boolean opClicked = false;

    double calculateStuff(char operator, double firstVal, double secondVal, double tempSum)
    {

        if(operator == '+')
        {
            tempSum = tempSum + firstVal + secondVal;
        }
        else if(operator == '-')
        {
            tempSum = tempSum - firstVal - secondVal;
        }
        else if(operator == '*')
        {
            tempSum = tempSum * firstVal * secondVal;
        }
        else if(operator == '*')
        {
            tempSum = tempSum / firstVal / secondVal;
        }
        else
        {
            tempSum = firstVal;
        }

        return tempSum;
    }

    //called if equal button was pressed
    double equalReturn(double tempSum, double finalSum)
    {
        finalSum = tempSum;
        return finalSum;

    }

    //global variable
    String tempResultText = "";
    int btnVal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView resultTextView = (TextView) findViewById(R.id.calcResult);


        //create all buttons
        FloatingActionButton frwdButton = (FloatingActionButton) findViewById(R.id.floatBut);

        //buttons 0-9
        Button zeroButton = (Button) findViewById(R.id.zeroBtn);
        Button oneButton = (Button) findViewById(R.id.oneBtn);
        Button twoButton = (Button) findViewById(R.id.twoBtn);
        Button threeButton = (Button) findViewById(R.id.threeBtn);
        Button fourButton = (Button) findViewById(R.id.fourBtn);
        Button fiveButton = (Button) findViewById(R.id.fiveBtn);
        Button sixButton = (Button) findViewById(R.id.sixBtn);
        Button sevenButton = (Button) findViewById(R.id.sevenBtn);
        Button eightButton = (Button) findViewById(R.id.eightBtn);
        Button nineButton = (Button) findViewById(R.id.nineBtn);

       //misc buttons
        Button dotButton = (Button) findViewById(R.id.dotBtn);
        Button enterButton = (Button) findViewById(R.id.enterBtn);
        Button addButton = (Button) findViewById(R.id.addBtn);
        Button subtractButton = (Button) findViewById(R.id.subBtn);
        Button multiplyButton = (Button) findViewById(R.id.multBtn);
        Button divideButton = (Button) findViewById(R.id.divBtn);
        Button clearButton = (Button) findViewById(R.id.clrBtn);


        //goes to  other activity
        frwdButton.setOnClickListener(v -> {
            Intent inte = new Intent(this, com.example.firstapp.MainActivity.class);
            startActivity(inte);
        });

        //listens for divide being clicked
        clearButton.setOnClickListener(v -> {
            resultTextView.setText("");
            firstVal = 0;
            secondVal = 0;
            tempSum = 0;
            finalSum = 0;
            operator = 'n';
            firstValStr = "";
            secondValStr = "";
            tempResultText = "";
            opClicked = false;
            btnVal = 0;
        });



        //listens for add being clicked
        addButton.setOnClickListener(v -> {
            tempResultText = tempResultText + " + ";
            resultTextView.setText(tempResultText);
            operator = '+';
            opClicked = true;
        });

        //listens for subtract being clicked
        subtractButton.setOnClickListener(v -> {
            tempResultText = tempResultText + " - ";
            resultTextView.setText(tempResultText);
            operator = '-';
            opClicked = true;
        });

        //listens for multiply being clicked
        multiplyButton.setOnClickListener(v -> {
            tempResultText = tempResultText + " * ";
            resultTextView.setText(tempResultText);
            operator = '*';
            opClicked = true;
        });

        //listens for divide being clicked
        divideButton.setOnClickListener(v -> {
            tempResultText = tempResultText + " / ";
            resultTextView.setText(tempResultText);
            operator = '/';
            opClicked = true;
        });








    }

    public void numberEvent(View view) {
        
    }

}

