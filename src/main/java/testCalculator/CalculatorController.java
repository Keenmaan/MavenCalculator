package testCalculator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by keen on 9/19/14.
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

    public void update(Observable obj, Object arg){
        //String x=String.valueOf(arg);
        view.setCalcResult(updateModel(String.valueOf(arg)));
    }

    private String updateModel(String s){
        for (int i=0;i<10;i++) {
            if (s.equals(String.valueOf(i))) {
                return model.NumbersCalculation(i);
            }
        }
        if (s==".")
            return model.addDot(s);
        if (s=="+" || s=="-" || s=="*" || s=="/" || s=="=" )
            return model.addOperator(s);
        else
        if (s=="AC")
            return model.clearAll();
        else
        if (s=="C")
            return model.clear();
        else
            return null;
    }
}
