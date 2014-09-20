package testCalculator;

import java.util.Observer;

/**
 * Created by keen on 9/18/14.
 */
public class Calculator{

    //lastWasANumber is true if last was a number and not an operation
    private boolean lastWasANumber=true;
    private boolean previousNumber=false;
    private long calculationNumber;
    private long calculationValue;
    private String operator="";
    private String lastMessage="";
    private String fullLastMessage="";
    private long Add(long a, long b){
        return (a+b);
    }

    private long Subtract(long a,long b){
        return (a-b);
    }

    private long Multiply(long a,long b){
        return (a*b);
    }

    private long Divide(long a,long b){
        //if (b!=0){
            return (a/b);
        //}
       // else if (a>0)
            //calculationValue=Double.POSITIVE_INFINITY;
        //else
            //calculationValue=Double.NEGATIVE_INFINITY;
    }

    public String NumbersCalculation(int i) {
        if (lastWasANumber)
            this.calculationNumber=calculationNumber*10+i;
        else {
            this.lastWasANumber=true;
            this.calculationNumber=i;
        }
        fullLastMessage=lastMessage+String.valueOf(calculationNumber);
        return fullLastMessage;
    }
    public String getLastMessage(){
        return fullLastMessage;
    }
    public String makeAnOperation(String s){
        if (operator=="")
            operator=s;
        if (operator=="=")
            operator=s;
        if (lastWasANumber){
            if (previousNumber) {
                if (operator == "-") {
                    this.calculationValue = Subtract(calculationValue, calculationNumber);
                }
                if (operator == "+") {
                    this.calculationValue = Add(calculationValue, calculationNumber);
                }
                if (operator == "*") {
                    this.calculationValue = Multiply(calculationValue, calculationNumber);
                }
                if (operator == "/") {
                    this.calculationValue = Divide(calculationValue, calculationNumber);
                }
                if (s!="=")
                    operator=s;
                fullLastMessage=String.valueOf(calculationValue);
                return fullLastMessage;
            }
                this.previousNumber = true;
                this.lastWasANumber = false;
                this.calculationValue = calculationNumber;
                this.lastMessage = String.valueOf(calculationNumber) + ' ' + s + ' ';
                this.calculationNumber=0;
                return lastMessage;

        }
        else{
            return lastMessage;
        }
    }

    public String clearAll() {
        this.previousNumber=false;
        this.lastWasANumber=false;
        this.calculationValue=0;
        this.calculationNumber=0;
        this.lastMessage="";
        this.fullLastMessage="";
        this.operator="";
        return null;
    }
}
