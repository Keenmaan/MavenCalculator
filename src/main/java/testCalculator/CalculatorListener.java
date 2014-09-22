package testCalculator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

/**
 * Created by keen on 9/20/14.
 */
public class CalculatorListener extends Observable implements ActionListener {
    private String watchedValue;

    @Override
    public void actionPerformed(ActionEvent e){
        String z= e.getActionCommand();

        watchedValue=z;
        setChanged();
        notifyObservers(watchedValue);
    }
}
