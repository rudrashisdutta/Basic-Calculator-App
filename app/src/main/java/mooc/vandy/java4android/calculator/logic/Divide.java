package mooc.vandy.java4android.calculator.logic;

/**
 * Perform the Divide operation.
 */
public class Divide {
    // TODO -- start your code here
    public static final int identity=4;//identity for the operator
    private int rem,quotient;
    //Operation performed in the constructor itself
    public Divide(int arg1,int arg2){
        rem=arg1%arg2;
        quotient=arg1/arg2;
    }
    /*Overridden toString() method returns the result in the correct format
    to the print() method of the MainActivity class
    */
    public String toString(){
        return quotient+"R: "+rem;
    }
}