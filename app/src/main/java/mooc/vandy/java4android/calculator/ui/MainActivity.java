package mooc.vandy.java4android.calculator.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mooc.vandy.java4android.calculator.R;
import mooc.vandy.java4android.calculator.logic.Logic;
import mooc.vandy.java4android.calculator.logic.LogicInterface;

/**
 * This Activity prompts the user for two integer values and
 * and operation to perform on these values.
 */
public class MainActivity extends
             Activity implements ActivityInterface {
    /**
     * The Spinner (drop down selector) that you choose which
     * operation to use from.
     */
    private Spinner mMathSpinner;

    /**
     * Button the user presses to perform the computation.
     */
    private Button mCalculate;

    /**
     * EditText that holds the first value entered by the user.
     */
    private EditText mValueOne;

    /**
     * EditText that holds the second value entered by the user.
     */
    private EditText mValueTwo;

    /**
     * EditText that stores the results of the computation.
     */
    private EditText mResult;

    /**
     * Reference to the Logic object.
     */
    private LogicInterface mLogic;

    /**
     * This 'Adapts' the Array of CharSequence to make it useable by
     * the mMathSpinner.
     */
    private ArrayAdapter<CharSequence> mAdapter;

    /**
     * Hook method called back by the Activity Manager Service when
     * the Activity is created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Call up to the super class.
        super.onCreate(savedInstanceState);

        // Initialize the UI.
        initializeUI();

        // Initialize the Logic instance.
        mLogic = (LogicInterface) new Logic(this);
    }

    /**
     * Initialize the UI.
     */
    private void initializeUI(){
        // Set the layout.
        setContentView(R.layout.activity_main);

        // Store a reference to the mCalculate button.
        mCalculate = (Button) findViewById(R.id.calculate_button);

        // Store references to the two values entered by the user.
        mValueOne = (EditText) findViewById(R.id.valueOneEditText);
        mValueTwo = (EditText) findViewById(R.id.valueTwoEditText);

        // Store a reference to the MathSpinner.
        mMathSpinner = (Spinner) findViewById(R.id.mathSpinner);

        // Store a reference to the EditText containing the result.
        mResult = (EditText) findViewById(R.id.results);

        // Initialize the adapter.
        mAdapter =
            ArrayAdapter.createFromResource(this,
                                            R.array.math_options,
                                            android.R.layout.simple_spinner_item);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Associate the ArrayAdapter with the Spinner.
        mMathSpinner.setAdapter(mAdapter);

        // Set the default selection of the mMathSpinner to be "add".
        mMathSpinner.setSelection(mAdapter.getPosition("add"));
    }
    //pass1 and passed2 show exceptions for Value1 and Value2 entered
    private boolean pass1;
    private boolean passed2;
    /**
     * Called back by the Android UI framework when the user presses
     * the "Calculate" button.
     */
    public void buttonPressed(View view){
        // Operation selected by the user.
        final int operation = getOperationNumber();
        // First argument specified by the user.
        final int argOne = getValueOne();

        // Second argument specified by the user.
        final int argTwo = getValueTwo();

        // Perform the operation on the two arguments.
        if(pass1 && passed2)
            mLogic.process(argOne, argTwo, operation);
        else{
            String message="";
            if(pass1==false){
                message+="Value one";
            }
            if(passed2==false){
                if(pass1==false) message+=" and ";
                message+="Value two";
            }
            message+=": OUT OF RANGE";
            makeAToast(message);
        }
    }
    /**
     * Get the value of the first user input operand.
     */
    @Override
    public int getValueOne() {
        String value=mValueOne.getText().toString();
        int operation=getOperationNumber();
        //checks if the field of Value one is empty.
        if(value.isEmpty()) {
            if(operation==3||operation==4) {
                value = "1";
            }
            else {
                value = "0";
            }
            printBlank(1,value);
        }
        //Exception: Out of range of int.
        try {
            pass1=true;
            return Integer.valueOf(value);
        }catch (Exception e2){
            pass1=false;
            printBlank(1,"");
            return 0;
        }
    }

    /**
     * Get the value of the second user input operand.
     */
    @Override
    public int getValueTwo() {
        String value=mValueTwo.getText().toString();
        int operation=getOperationNumber();
        //checks if the field of Value two is empty.
        if(value.isEmpty()){
            if(operation==3||operation==4) {
                value = "1";
            }
            else {
                value = "0";
            }
            printBlank(2,value);
        }
        try {
            passed2=true;
            return Integer.valueOf(value);
        }catch (Exception e){
            passed2=false;
            printBlank(2,"");
            return 0;
        }
    }

    /**
     * Get the value of the user input operation.
     */
    @Override
    public int getOperationNumber() {
        return Arrays.asList(getResources()
                     .getStringArray(R.array.math_options))
                     // Added 1 to start the selected operation from 1 rather than 0.
                     .indexOf(mMathSpinner.getSelectedItem().toString()) + 1;
    }

    /**
     * Print the result to the user's display.
     */
    @Override
    public void print(String resultString) {
        mResult.setText(resultString);
    }
    /*
    Prints to the EditText(one->i=1 and two->i=2)
    message->text to print
    */
    public void printBlank(int i,String message){
        if(i==1)
            mValueOne.setText(message);
        else
            mValueTwo.setText(message);
    }
    /*
    It shows a Toast containing the type of error occurred.
    message->Text shown my the toast.
     */
    public void makeAToast(String message){
        int duration= Toast.LENGTH_LONG;
        Context context=getApplicationContext();
        Toast toast=Toast.makeText(context,message,duration);
        toast.show();
    }
}
