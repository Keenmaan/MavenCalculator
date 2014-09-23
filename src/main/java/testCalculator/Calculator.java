package testCalculator;
import java.text.*;
import java.util.ArrayList;

/**
 * Model for MavenCalculator project.
 */
public class Calculator{


    //lastWasANumber is true if last was a number and not an operation
    /**
     * Contains all the entered data. Each element in this list resembles each step of calculating.
     */
    ArrayList<String> calculatorMemory=new ArrayList<String>();
    ArrayList<String> calculatorSequence=new ArrayList<String>();
    private boolean naN=false;
    private int position =0;
    private boolean hasDot=false;

    /**
     * Counts position in the calculatorMemory array.
     */
    private void count(){
        this.position = position +1;
        hasDot=false;
    }

    /**
     * Checks if s is a number.
     * @param s the String to be checked. It counts '.' as part of a number, so it can process double type.
     * @return true if s is a number
     */
    private boolean isANumber(String s){
        boolean isNumeric =false;
        if (s != null && !s.equals("")){
            isNumeric=true;
            char chars[] = s.toCharArray();
            for (int i=0;i<chars.length;i++){
                isNumeric &= Character.isDigit(chars[i]);
                if (!isNumeric && chars[i]!='.')
                    return false;
                if (chars[i]=='.')
                    isNumeric=true;
            }
        }
        return isNumeric;
    }

    /**
     * Saves the calculatorSequence into calculatorMemory.
     * Adds on top of existing calculatorMemory elements and seperates them with ', '.
     */
    private void saveMemory(){
        naN=false;
        int n=calculatorMemory.size();
        for (int i=0;i<5;i++){
            this.calculatorMemory.add(n+i,calculatorSequence.get(i));
        }
        for (int i=4;i>=0;i--){
            calculatorSequence.remove(i);
        }
        this.calculatorMemory.add(calculatorMemory.size(),", ");
        position =0;
    }

    /**
     * Gets the output calculator message for the user
     * from calculatorMemory and calculatorSequence ArrayLists.
     * @return calculation line values and operations
     */
    private String getMessage(){
        int i=0;
        while(checkCalculatorSequence(i)) {
            i++;
        }
        String x = "";
        for(String s : calculatorMemory){
            x = " " + x + " " + s;
        }
        for (String s : calculatorSequence) {
            x = " " + x + " " + s;
        }
        return x;
    }

    /**
     * Checks if calculatorSequence is big enough,
     * avoiding the Array out of boundaries exception.
     * @param i position to be checked
     * @return true if calculatorSequence.size() is big enough
     */
    private boolean checkCalculatorSequence(int i){
        return calculatorSequence.size() > i;
    }

    /**
     * Adds two doubles.
     * @param a a double number
     * @param b a double number
     * @return product of a+b
     */
    private double Add(double a, double b){
        return (a+b);
    }
    /**
     * Subtracts two doubles.
     * @param a a double number
     * @param b a double number
     * @return product of a-b
     */
    private double Subtract(double a,double b){
        return (a-b);
    }
    /**
     * Multiplies two doubles.
     * @param a a double number
     * @param b a double number
     * @return product of a*b
     */
    private double Multiply(double a,double b){
        return (a*b);
    }
    /**
     * Divides two doubles.
     * @param a a double number
     * @param b a double number
     * @return product of a/b
     */
    private double Divide(double a,double b){
        if (b!=0){
            return (a/b);
        }
        naN=true;
        if (a>0)
           return(Double.POSITIVE_INFINITY);
        if (a<0)
            return(Double.NEGATIVE_INFINITY);
        return(Double.NaN);
    }

    /**
     * Formats a double number into a String format.
     * @param i a double number
     * @return formatted
     */
    private String getDouble(double i){
        DecimalFormat df = new DecimalFormat("0.###");
        return df.format(i);
    }

    /**
     * Adds a dot to a number if no dot is already added and then returns the getMessage()
     * @return getMessage()
     */
    public String addDot(){
        if (hasDot)
            return getMessage();
        else
            return NumbersCalculation(10);
    }

    /**
     * Calculates in which position of calculatorSequence should the entered 'i' parameter be put in and then returns getMessage().
     * @param i an int number that should be inserted into calculatorSequence
     * @return getMessage()
     */
    public String NumbersCalculation(int i) {
        String s=String.valueOf(i);
        if (i==10)
            s="0.";
        if(checkCalculatorSequence(position -1) && position >0) {
            if (isANumber(calculatorSequence.get(position - 1))){
                if (i==10)
                    s=".";
                setCalculatorSequence(position -1, calculatorSequence.get(position -1) + s);
            }
                else {
                setCalculatorSequence(position, s);
            }
        }
        else{
            setCalculatorSequence(position, s);
        }
        if (i==10)
            hasDot=true;
        return getMessage();
    }

    /**
     * Sets calculatorSequence value to String 's' or adds an element if none exists in 'i' index position.
     * @param i index position
     * @param s a String that is to be put into calculatorSequence array
     */
    private void setCalculatorSequence(int i,String s){
        if(checkCalculatorSequence(i)){
            this.calculatorSequence.set(i,s);
        }

        else{
            this.calculatorSequence.add(i,s);
            count();
        }

    }

    /**
     * Adds an operator contained in parameter s to calculatorSequence array(if its the proper position),
     * and then returns getMessage().
     * @param s a String containing an operator for calculations
     * @return getMessage()
     */
    public String addOperator(String s){
        if (position ==0){
            return getMessage();
        }
        if (position ==1){
            if(s.equals("="))
                return getMessage();
            setCalculatorSequence(position,s);
            return getMessage();
        }
        if (position ==2){
            if(s.equals("=")){
                setCalculatorSequence(position,calculatorSequence.get(position -2));
                setCalculatorSequence(position,s);
                setCalculatorSequence(position, runAnOperation(position - 4, position - 2));
                saveMemory();
                return getMessage();
            }
            else {
                setCalculatorSequence(position - 1, s);
                return getMessage();
            }
        }
        if (position ==3)  {
            if(s.equals("=")){
                setCalculatorSequence(position,s);
                setCalculatorSequence(position, runAnOperation(position - 4, position - 2));
                saveMemory();
            }
            else{
                setCalculatorSequence(position,"=");
                setCalculatorSequence(position, runAnOperation(position - 4, position - 2));
                if (!naN){
                    saveMemory();
                    setCalculatorSequence(position, calculatorMemory.get(calculatorMemory.size() - 2));
                    setCalculatorSequence(position,s);
                }
                else
                    saveMemory();
            }
            return getMessage();
        }
        if (position ==4){
            setCalculatorSequence(position,s);
        }

        return getMessage();
    }

    /**
     * Runs an operation, depending on what operator is in calculatorSequence and then
     * returns getDouble(z) which contains properly formatted data into String.
     * @param i position in calculatorSequence for double number 'a'
     * @param j position in calculatorSequence for double number 'b'
     * @return returns (String) getDouble(z)
     */
    private String runAnOperation(int i, int j){
        Double a=Double.parseDouble(calculatorSequence.get(i));
        String s=calculatorSequence.get(i+1);
        Double b=Double.parseDouble(calculatorSequence.get(j));
        double z=0;
        if (s.equals("+"))
            z= Add(a,b);
        if (s.equals("-"))
            z= Subtract(a,b);
        if (s.equals("*"))
            z= Multiply(a,b);
        if (s.equals("/"))
            z= Divide(a,b);
        return getDouble(z);
}

    /**
     * Clears all.
     * @return null
     */
    public String clearAll() {
        this.position =0;
        for (int i=calculatorSequence.size();i>0;i--){
            this.calculatorSequence.remove(i-1);
        }
        for (int i=calculatorMemory.size();i>0;i--){
            this.calculatorMemory.remove(i-1);
        }
        if(calculatorSequence.size()==0)
            return null;
        else
            return "not_NULL";
    }

    /**
     * Clears last entered data, unless it was already saved (saveMemory()) and returns getMessage().
     * @return getMessage()
     */
    public String clear(){
        if(position >=1){
            this.position = position -1;
            if(checkCalculatorSequence(position +2))
                this.calculatorSequence.remove(position +2);
            if(checkCalculatorSequence(position +1))
                this.calculatorSequence.remove(position +1);
            this.calculatorSequence.remove(position);
        }
        return getMessage();
    }
}
