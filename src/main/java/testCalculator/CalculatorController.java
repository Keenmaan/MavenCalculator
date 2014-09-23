package testCalculator;

import java.util.Observable;
import java.util.Observer;

/**
 * Controller for MavenCalculator project.
 */
public class CalculatorController implements Observer {


    Calculator model;
    CalculatorView view;
    CalculatorListener listener;

    CalculatorController(Calculator model,CalculatorView view, CalculatorListener listener){
        this.model=model;
        this.view=view;
        this.listener=listener;

        listener.addObserver(this);
    }

    /**
     * Calls updateModel method when user input from GUI, gets a String message
     * and updates the view through view.setCalcResult method.
     * @param obj observable object (listener)
     * @param arg observer object (controller)
     */
    public void update(Observable obj, Object arg){
        //String x=String.valueOf(arg);
        view.setCalcResult(updateModel(String.valueOf(arg)));
    }

    /**
     * Returns String message from theModel after it was updated and has done its processing.
     * @param s User input String command value. A number or operator.
     * @return returns a String message containing calculation numbers, operators and results
     */
    private String updateModel(String s){
        for (int i=0;i<10;i++) {
            if (s.equals(String.valueOf(i))) {
                return model.NumbersCalculation(i);
            }
        }
        if (s.equals("."))
            return model.addDot();
        if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("="))
            return model.addOperator(s);
        else
        if (s.equals("AC"))
            return model.clearAll();
        else
        if (s.equals("C"))
            return model.clear();
        else
            return null;
    }
}
