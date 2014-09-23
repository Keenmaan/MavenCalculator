package testCalculator;
import java.text.*;
import java.util.ArrayList;

/**
 * Created by keen on 9/18/14.
 */
public class Calculator{


    //lastWasANumber is true if last was a number and not an operation
    /**
     * Contains all the entered data. Each element in this list resembles each step of calculating.
     */
    ArrayList<String> calculatorMemory=new ArrayList<String>();
    ArrayList<String> calculatorSequence=new ArrayList<String>();
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
     * @return
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
                if (chars[i]=='.');
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
     * Gets
     * @return
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

    private boolean checkCalculatorSequence(int i){
        if (calculatorSequence.size()>i)
            return true;
        else
            return false;
    }

    private double Add(double a, double b){
        return (a+b);
    }

    private double Subtract(double a,double b){
        return (a-b);
    }

    private double Multiply(double a,double b){
        return (a*b);
    }

    private double Divide(double a,double b){
        if (b!=0){
            return (a/b);
        }
        else if (a>0)
            return(Double.POSITIVE_INFINITY);
        else
            return(Double.NEGATIVE_INFINITY);
    }

    private String getDouble(double i){
        DecimalFormat df = new DecimalFormat("0.###");
        return df.format(i);
    }

    public String addDot(String s){
        if (hasDot)
            return getMessage();
        else
            return NumbersCalculation(10);
    }

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

    private void setCalculatorSequence(int i,String s){
        if(checkCalculatorSequence(i)){
            this.calculatorSequence.set(i,s);
        }

        else{
            this.calculatorSequence.add(i,s);
            count();
        }

    }
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
                setCalculatorSequence(position,makeAnOperation(position -4, position -2));
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
                setCalculatorSequence(position, makeAnOperation(position - 4, position - 2));
                saveMemory();
            }
            else{
                setCalculatorSequence(position,"=");
                setCalculatorSequence(position, makeAnOperation(position - 4, position - 2));
                saveMemory();
                setCalculatorSequence(position, calculatorMemory.get(calculatorMemory.size() - 2));
                setCalculatorSequence(position,s);
            }
            return getMessage();
        }
        if (position ==4){
            setCalculatorSequence(position,s);
        }

        return getMessage();
    }

    public String makeAnOperation(int i,int j){
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
