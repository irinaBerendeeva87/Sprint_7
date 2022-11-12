package order;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.order.OrderClient;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;

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
        int actualStatusCode = responseOrderList.extract().statusCode();
        assertEquals("Status Code incorrect",SC_OK, actualStatusCode);
        assertThat("Expected order list",actualList, notNullValue());
    }
}
