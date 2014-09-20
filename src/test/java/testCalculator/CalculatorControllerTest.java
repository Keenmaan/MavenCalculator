package testCalculator;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.*;

import static org.hamcrest.core.IsInstanceOf.instanceOf;


/**
 * Created by keen on 9/19/14.
 */
public class CalculatorControllerTest {
    CalculatorController controller;
    @Mock
    Calculator modelMock;
    @Mock
    CalculatorView viewMock;
    @Mock
    CalculatorListener listenerMock;

/*

    @Before
    public void CalculatorControllerInstance(){
        controller=new CalculatorController(modelMock,viewMock,listenerMock);
    }


    @Test
    public void CalculatorControllerConstructorCheck(){
        Assert.assertThat(controller, instanceOf(CalculatorController.class));
    }

    @Test
    public void CalculatorControllerAddTest(){
        Assert.assertEquals(1,1);
    }
    */
}
