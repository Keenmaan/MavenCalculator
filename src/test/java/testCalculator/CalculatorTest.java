package testCalculator;

import org.junit.*;
import static org.hamcrest.core.IsInstanceOf.instanceOf;



/**
 * Created by keen on 9/18/14.
 */
public class CalculatorTest {
    Calculator calculatorModel;

    @Before
    public void CalculatorInstanceTest(){
        calculatorModel=new Calculator();
    }

/*
    @Test
    public void AddTest1(){

        Assert.assertEquals(14,calculatorModel.Add(5,9));
        calculatorModel.Add(9,5);
        Assert.assertEquals(14,calculatorModel.getCalculationValue(),0.0001);
    }

    @Test
    public void SubtractTest(){
        calculatorModel.Subtract(9, 2);
        Assert.assertEquals(7,calculatorModel.getCalculationValue(),0.0001);
    }

    @Test
    public void MultiplyTest(){
        calculatorModel.Multiply(5, 2);
        Assert.assertEquals(10,calculatorModel.getCalculationValue(),0.0001);
    }

    @Test
    public void DivideTest(){
        calculatorModel.Divide(10, 2);
        Assert.assertEquals(5,calculatorModel.getCalculationValue(),0.0001);
    }*/

}
