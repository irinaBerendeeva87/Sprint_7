import courier.CourierNegativeTest;
import courier.CourierPositiveTest;
import courier.PCourierNegativeLoginTest;
import courier.PCourierNegativeTest;
import order.OrderListTest;
import order.POrderTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        CourierPositiveTest.class,
        CourierNegativeTest.class,
        PCourierNegativeTest.class,
        PCourierNegativeLoginTest.class,
        POrderTest.class,
        OrderListTest.class
})
public class AllTest {
}
