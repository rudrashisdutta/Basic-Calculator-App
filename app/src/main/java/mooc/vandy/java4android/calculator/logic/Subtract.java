package mooc.vandy.java4android.calculator.logic;

/**
 * Perform the Subtract operation.
 */
public class Subtract {
    // TODO -- start your code here
    public static final int identity=2;//identity for the operator
    private int result;
    //Operation performed in the constructor itself
    public Subtract(int arg1,int arg2){
        result=arg1-arg2;
    }
    /*Overridden toString() method returns the result in the correct format
    to the print() method of the MainActivity class
    */
    public String toString(){
        return ""+result;
    }
}