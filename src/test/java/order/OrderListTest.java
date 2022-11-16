package order;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.order.OrderClient;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OrderListTest {
    private OrderClient orderClient;

    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    @Test
    @DisplayName("Check response when order can return order list")
    public void getOrderReturnedOrderList(){
        ValidatableResponse responseOrderList= orderClient.returnOrderList();
        ArrayList actualList = responseOrderList.extract().path("orders");
        int ordersSize = actualList.size();
        boolean actual = ordersSize > 0;
        int actualStatusCode = responseOrderList.extract().statusCode();
        assertEquals("Status Code incorrect",SC_OK, actualStatusCode);
        assertTrue( "Expected order list size more than 0", actual);
    }
}
