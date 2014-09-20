package testCalculator;

import org.junit.*;
import org.mockito.Mock;

import static org.hamcrest.core.IsInstanceOf.instanceOf;

/**
 * Created by keen on 9/19/14.
 */
public class CalculatorViewTest {
    CalculatorView calculatorView;
    @Mock
    CalculatorListener listenerMock;

    @Before
    public void ViewInstance(){
        //CalculatorListener listener=new CalculatorListener();
        calculatorView=new CalculatorView(listenerMock);
    }

    @Test
    public void ViewInstanceTest(){
        Assert.assertThat(calculatorView, instanceOf(CalculatorView.class));
    }

}
