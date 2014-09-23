package testCalculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

/**
 * Listener for MavenCalculator project.
 */
public class CalculatorListener extends Observable implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e){
        String watchedValue = e.getActionCommand();
        setChanged();
        notifyObservers(watchedValue);
    }
}
