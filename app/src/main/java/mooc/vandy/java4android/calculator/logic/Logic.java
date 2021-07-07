package mooc.vandy.java4android.calculator.logic;


import mooc.vandy.java4android.calculator.logic.Add;
import mooc.vandy.java4android.calculator.logic.Divide;
import mooc.vandy.java4android.calculator.logic.Multiply;
import mooc.vandy.java4android.calculator.logic.Subtract;
import mooc.vandy.java4android.calculator.ui.ActivityInterface;

/**
 * Performs an operation selected by the user.
 */
public class Logic 
       implements LogicInterface {
    /**
     * Reference to the Activity output.
     */
    protected ActivityInterface mOut;

    /**
     * Constructor initializes the field.
     */
    public Logic(ActivityInterface out){
        mOut = out;
    }
    /**
     * Perform the @a operation on @a argumentOne and @a argumentTwo.
     */
    public void process(int argumentOne,
                        int argumentTwo,
                        int operation){
        // TODO -- start your code here
        /*Using switch case to choose the operation required to be proceeded with.
        * The if conditions in the cases find out if the result will be in range or not.
        * the else condition, shows a toast with the possible error that has occurred and makes the 3 editTexts-> setText to empty!*/
        switch (operation){
            case Add.identity:
                if(checkOpCondAdd(argumentOne,argumentTwo)) {
                    Add a = new Add(argumentOne, argumentTwo);
                    mOut.print(a.toString());
                }
                else {
                    mOut.makeAToast("Result is out of Range or an ERROR has occurred.");
                    mOut.printBlank(1,"");
                    mOut.printBlank(2,"");
                    mOut.print("");
                }
                break;
            case Subtract.identity:
                if(checkOpCondSubtract(argumentOne,argumentTwo)) {
                    Subtract s = new Subtract(argumentOne, argumentTwo);
                    mOut.print(s.toString());
                }
                else {
                    mOut.makeAToast("Result is out of Range or an ERROR has occurred.");
                    mOut.printBlank(1,"");
                    mOut.printBlank(2,"");
                    mOut.print("");
                }
                break;
            case Multiply.identity:
                if(checkOpCondMultiply(argumentOne,argumentTwo)) {
                    Multiply m = new Multiply(argumentOne, argumentTwo);
                    mOut.print(m.toString());
                }
                else {
                    if(argumentOne!=0&&argumentTwo!=0) {
                        mOut.makeAToast("Result is out of Range or an ERROR has occurred.");
                    }
                    mOut.printBlank(1, "");
                    mOut.printBlank(2, "");
                    mOut.print("");
                }
                break;
            case Divide.identity:
                if(checkOpCondDivide(argumentOne,argumentTwo)) {
                    Divide d = new Divide(argumentOne, argumentTwo);
                    mOut.print(d.toString());
                }
                else {
                    if(argumentOne!=0) {
                        mOut.makeAToast("Division by 0 is not possible: Illegal Expression!");
                    }
                    mOut.printBlank(2, "");
                    mOut.print("");
                }
                break;
        }
    }
    //Checks if the result of division will be in the range of int or not.
    public boolean checkOpCondDivide(int num1,int num2){
        //checks for 0/0 Illegal expression
        if(num1==0&&num2==0){
            mOut.makeAToast("0/0 form is not possible: Illegal Expression!");
            mOut.printBlank(1,"");
            return false;
        }
        return num2!=0;
    }
    //Checks if the result of addition will be in the range of int or not.
    public boolean checkOpCondAdd(int num1,int num2){
        int MAX=2147483647;
        if(num2>=0){
            if(num1<=0)
                return true;
            else{
                int sub=MAX-num1;
                return sub >= num2;
            }
        }
        else{
            if(num1>=0)
                return true;
            else{
                int sub=-MAX-1-num1;
                return sub <= negative(num2);
            }
        }
    }
    //Checks if the result of subtraction will be in the range of int or not.
    public boolean checkOpCondSubtract(int num1,int num2){
        int MAX=2147483647;
        if(num2>=0){
            if(num1>=0)
                return true;
            else{
                int sub=-MAX-1-num1;
                return sub <= negative(num2);
            }
        }
        else{
            if(num1<0)
                return true;
            else{
                int sub=MAX-num1;
                return sub >= positive(num2);
            }
        }
    }
    //Checks if the result of multiplication will be in the range of int or not.
    public boolean checkOpCondMultiply(int num1,int num2) {
        int MAX;
        if ((num1 < 0 && num2 > 0) || num1 > 0 && num2 < 0) {
            MAX = -2147483648;
            return ((num2<0&&num2>=(MAX/num1))||(num1<0&&num1>=(MAX/num2)));
        }
        else {
            MAX = 2147483647;
            //checks for 0x0 Illegal expression
            if(num1==0&&num2==0){
                mOut.makeAToast("0 x 0 form is not possible: Illegal Expression!");
                return false;
            }
            return (num1!=0&&num2<=(MAX/num1))||(num2!=0&&num1<=(MAX/num2));
        }
    }
    //Makes a number positive
    private int positive(int num){
        if(num<0){
            return -num;
        }
        return num;
    }
    //Makes a number negative
    private int negative(int num){
        if(num>0){
            return -num;
        }
        return num;
    }
}