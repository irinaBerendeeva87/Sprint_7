package order;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.order.Order;
import org.example.order.OrderClient;
import org.example.order.OrderGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class POrderTest {
    private OrderClient orderClient;
    private Order order;
    private int statusCode;

    public POrderTest(Order order, int statusCode) {
        this.order = order;
        this.statusCode = statusCode;
    }

//test data
@Parameterized.Parameters
    public static Object[][] getTestData() {
    return new Object[][]{
            {OrderGenerator.getWithBlack(), SC_CREATED},
            {OrderGenerator.getWithGrey(), SC_CREATED},
            {OrderGenerator.getWithoutTwoColours(), SC_CREATED},
            {OrderGenerator.getWithTwoColours(), SC_CREATED}
        };
    }

    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    @Test
    @DisplayName("Check response when order can be created with track number")
    public void orderCanBeCreated(){
        ValidatableResponse responseCreate = orderClient.create(order);
        int actualStatusCode = responseCreate.extract().statusCode();
        int track = responseCreate.extract().path("track");
        assertThat("Expected track number",track, notNullValue());
        assertEquals("Status Code incorrect",statusCode,actualStatusCode);
    }
}
