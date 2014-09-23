package testCalculator;

/**
 * Class instantiating the whole MVC.
 */
public class CalculatorStart{

    CalculatorStart(){
        //Create model
        Calculator calculator=new Calculator();

        //Create view
        CalculatorListener listener=new CalculatorListener();
        CalculatorView view=new CalculatorView(listener);

        //Create Controller
        CalculatorController controller= new CalculatorController(calculator,view,listener);

    }
}
