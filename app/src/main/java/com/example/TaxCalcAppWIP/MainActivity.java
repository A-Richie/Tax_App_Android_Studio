package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //uses this activity

        //spinner creation/ connect spinner to array in strings.xml file
        final Spinner thisSpinner = findViewById(R.id.stateSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.States, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        thisSpinner.setAdapter(adapter);
        thisSpinner.setOnItemSelectedListener(this);

        //create variables for each object on screen
        final EditText firstNumEditText = (EditText) findViewById(R.id.firstNumEditText);

        //makes it so only two input after decimal point
        firstNumEditText.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(5,2)});

        final EditText secondNumEditText = (EditText) findViewById(R.id.secondNumEditText);
        final TextView resultTextView = (TextView) findViewById(R.id.resultTextView);

        Button calcButton = (Button) findViewById(R.id.calcBtn); //create calculateButton
        Button resetButton = (Button) findViewById(R.id.rstBtn); //create reset Button
        FloatingActionButton frwdButton = (FloatingActionButton) findViewById(R.id.floatBut);



        // create array to pair with names of places. length of 51 --> 50 states but cali has 2 dedicated to it
        final double[] taxVal = {0.0, 4.0, 0.0, 5.6, 6.5, 7.25, 9.25, 2.9, 6.35, 0.0, 6.0, 4.0, 4.0, 6.00, 6.25, 7.0, 6.0, 6.5, 6.0, 4.45, 5.5, 6.0, 6.25, 6.0, 6.875, 7.00, 4.225, 0.0, 5.50, 6.85, 0.0, 6.625, 5.125, 4.0, 4.75, 5.0, 5.75, 4.5, 0.0, 6.0, 7.00, 6.00, 4.50, 7.00, 6.25, 4.85, 6.0, 4.3, 6.5, 6.0, 5.0, 4.0 };
                                //AL, AK, AZ,   AR,  CA, CAHigh, CO,  CT,   DE,  FL,  GA,  HI,  ID, IL,     IN,  IA,  KS,  KY,  LA,   ME, MD,   MA,   MI,  MN,      MS, MO,    MT,  NE,   NV,   NH,   NJ,   NM,    NY,   NC,   ND,  OH,    OK,    OR, PA,  RI,  SC,   SD,   TN, TX,     UT, VT,   VA,  WA, WV,  WI,     WY



        //listens for button being clicked
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);


                //gets selected item in spinner
                String stateSelected = thisSpinner.getSelectedItem().toString();
                //private variables used in function
                double num1;
                double num2;
                double taxRateDecimal;
                double result;
                double roundOff;

                //if both the text feilds are not empty do basic calculation
                if(!secondNumEditText.getText().toString().isEmpty() && !firstNumEditText.getText().toString().isEmpty())
                {
                    //Toast.makeText(MainActivity.this, "Not Broken", Toast.LENGTH_LONG).show();
                     num1 = Double.parseDouble(firstNumEditText.getText().toString());
                     num2 = Double.parseDouble(secondNumEditText.getText().toString());

                     taxRateDecimal = num2 / 100;
                     result = num1 + (taxRateDecimal * num1);
                     roundOff = (double) Math.round(result * 100) / 100;

                    //output
                    resultTextView.setText(roundOff + "");

                    //Toast.makeText(MainActivity.this, thisSpinner.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
                }
                //if the selection of state is not empty and the cost of item is not empty -> checks that the second field IS EMPTY
                else if (!stateSelected.isEmpty() && !firstNumEditText.getText().toString().isEmpty() && secondNumEditText.getText().toString().isEmpty())
                {
                    //Toast.makeText(MainActivity.this, "Not Broken", Toast.LENGTH_LONG).show();
                     num1 = Double.parseDouble(firstNumEditText.getText().toString());
                     num2 = assValue(stateSelected);

                     //calculation
                     taxRateDecimal = num2 / 100;
                     result = num1 + (taxRateDecimal * num1);
                     roundOff = (double) Math.round(result * 100) / 100;

                    //output
                    resultTextView.setText(roundOff + "");

                    Toast.makeText(MainActivity.this, thisSpinner.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
                }
                else
                {
                    //makes a little thing at bottom of screen telling user what to do
                    Toast.makeText(MainActivity.this, "Please enter a cost & rate.", Toast.LENGTH_LONG).show();
                }
            }

            private double assValue(String selected) {
                double tempNum;
                //Associates value to a state if state spinner is not empty.
                //for(i = 0, i < R.array.States.length() - 1, i++)
                int spinnerIndex = thisSpinner.getSelectedItemPosition();
                                                //AL Pos 1
                tempNum = taxVal[spinnerIndex];

                return tempNum;
            }
        });

        //waits for button to be clicked and does something.
        resetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                firstNumEditText.setHint("Enter price");
                firstNumEditText.setText("");
                secondNumEditText.setHint("Enter tax rate");
                secondNumEditText.setText("");
                thisSpinner.setSelection(0);
                resultTextView.setText("Result");
            }
        });

        //goes to  other activity
        frwdButton.setOnClickListener(v -> {
             Intent inte = new Intent(this, SecondActivity.class);
             startActivity(inte);
        });


    } // end of initalization


        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String grabText = parent.getItemAtPosition(position).toString();
            Toast.makeText(parent.getContext(), grabText, Toast.LENGTH_SHORT).show();

            // Check if no view has focus:

            //int pos;
            //parent.getItemAtPosition(pos);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {


        }


//if changed view --> minimize keypad
    public void onFocusChange(View v) {

        switch(v.getId()){
            case R.id.firstNumEditText:
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                break;
            case R.id.stateSpinner:
                inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                break;
            case R.id.rstBtn:
                inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                break;
            case R.id.calcBtn:
                inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                break;
            case R.id.resultTextView:
                inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                break;

        }
    }

/*
InputMethodManager inputManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                //Find the currently focused view, so we can grab the correct window token from it.
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);

if (view == null) {
        view = new View(activity);
    }
    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);*/

}
//class used to restrict edittext
class DecimalDigitsInputFilter implements InputFilter {
    private Pattern mPattern;
    DecimalDigitsInputFilter(int digitsBeforeZero, int digitsAfterZero) {
        mPattern = Pattern.compile("[0-9]{0," + (digitsBeforeZero - 1) + "}+((\\.[0-9]{0," + (digitsAfterZero - 1) + "})?)||(\\.)?");
    }
    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        Matcher matcher = mPattern.matcher(dest);
        if (!matcher.matches())

            return "";
        return null;
    }
}


