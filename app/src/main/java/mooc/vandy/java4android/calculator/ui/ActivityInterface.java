package mooc.vandy.java4android.calculator.ui;

import android.content.Context;

/**
 * Defines the interface for user input/output.
 */
public interface ActivityInterface {
    /**
     * Get the value of the first user input operand.
     */ 
    public int getValueOne();

    /**
     * Get the value of the second user input operand.
     */ 
    public int getValueTwo();

    /**
     * Get the value of the user input operation.
     */
    public int getOperationNumber();

    /**
     * Print the result to the user's display.
     */
    public void print(String resultString);
    public void printBlank(int i,String message);
    public void makeAToast(String message);
}
