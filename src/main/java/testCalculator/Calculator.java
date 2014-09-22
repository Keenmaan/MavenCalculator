package testCalculator;
import org.apache.commons.lang3.math.NumberUtils;
import java.text.*;
import java.util.ArrayList;

/**
 * Created by keen on 9/18/14.
 */
public class Calculator{


    //lastWasANumber is true if last was a number and not an operation
    ArrayList<String> calculatorMemory=new ArrayList<String>();
    ArrayList<String> calculatorSequence=new ArrayList<String>();
    private int countNumbers=0;
    private boolean hasDot=false;

    private void count(){
        this.countNumbers=countNumbers+1;
        hasDot=false;
    }

    private void saveMemory(){
        int n=calculatorMemory.size();
        for (int i=0;i<5;i++){
            this.calculatorMemory.add(n+i,calculatorSequence.get(i));
        }
        for (int i=4;i>=0;i--){
            calculatorSequence.remove(i);
        }
        this.calculatorMemory.add(calculatorMemory.size(),", ");
        countNumbers=0;
    }

    private String getMessage(){
        int i=0;
        while(checkCalculatorSequence(i)) {
            System.out.print("calculatorSequence(" + i + ")=");
            System.out.println("'"+calculatorSequence.get(i)+"'");
            i++;
        }
        String x = "";
        for(String s : calculatorMemory){
            x = " " + x + " " + s;
            System.out.print(" " +s);
        }
        for (String s : calculatorSequence) {
            x = " " + x + " " + s;
            System.out.print(" " +s);
        }
        System.out.println();
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
        if(checkCalculatorSequence(countNumbers-1) && countNumbers>0) {
            if (NumberUtils.isNumber(calculatorSequence.get(countNumbers-1))){
                if (i==10)
                    s=".";
                setCalculatorSequence(countNumbers-1, calculatorSequence.get(countNumbers-1) + s);
            }
                else {
                setCalculatorSequence(countNumbers, s);
            }
        }
        else{
            setCalculatorSequence(countNumbers, s);
        }
        if (i==10)
            hasDot=true;
        return getMessage();
    }

    private void setCalculatorSequence(int i,String s){
        System.out.println("setCalculatorSequence  i="+i+" ; s="+s);
        if(checkCalculatorSequence(i)){
            this.calculatorSequence.set(i,s);
        }

        else{
            this.calculatorSequence.add(i,s);
            count();
        }

    }
    public String addOperator(String s){
        System.out.println("Operator: "+s+" ,countNumbers: "+countNumbers);
        if (countNumbers==0){
            return getMessage();
        }
        if (countNumbers==1){
            if(s.equals("="))
                return getMessage();
            setCalculatorSequence(countNumbers,s);
            return getMessage();
        }
        if (countNumbers==2){
            if(s.equals("=")){
                setCalculatorSequence(countNumbers,calculatorSequence.get(countNumbers-2));
                setCalculatorSequence(countNumbers,s);
                setCalculatorSequence(countNumbers,makeAnOperation(countNumbers -4,countNumbers-2));
                saveMemory();
                return getMessage();
            }
            else {
                setCalculatorSequence(countNumbers - 1, s);
                return getMessage();
            }
        }
        if (countNumbers==3)  {
            if(s.equals("=")){
                setCalculatorSequence(countNumbers,s);
                setCalculatorSequence(countNumbers, makeAnOperation(countNumbers - 4, countNumbers - 2));
                saveMemory();
            }
            else{
                setCalculatorSequence(countNumbers,"=");
                setCalculatorSequence(countNumbers, makeAnOperation(countNumbers - 4, countNumbers - 2));
                saveMemory();
                setCalculatorSequence(countNumbers,calculatorMemory.get(calculatorMemory.size()-2));
                setCalculatorSequence(countNumbers,s);
            }
            return getMessage();
        }
        if (countNumbers==4){
            setCalculatorSequence(countNumbers,s);
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
        this.countNumbers=0;
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
        if(countNumbers>=1){
            this.countNumbers=countNumbers-1;
            if(checkCalculatorSequence(countNumbers+2))
                this.calculatorSequence.remove(countNumbers+2);
            if(checkCalculatorSequence(countNumbers+1))
                this.calculatorSequence.remove(countNumbers+1);
            this.calculatorSequence.remove(countNumbers);
        }
        return getMessage();
    }
}
