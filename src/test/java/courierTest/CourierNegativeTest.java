package courierTest;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.courier.Courier;
import org.example.courier.CourierClient;
import org.example.courier.CourierGenerator;
import org.example.courier.Credentials;
import org.junit.Before;
import org.junit.Test;
import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.assertEquals;

public class CourierNegativeTest {
    private  CourierClient courierClient;
    private  Courier courier;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
        courier = CourierGenerator.getDefault();
        ValidatableResponse responseCreate = courierClient.create(courier);
    }

    @Test
    @DisplayName("Check response when courier can't created twice" )
    public void createTheSameCourierAndCheckStatusCode(){
        ValidatableResponse responseCreate = courierClient.create(courier);
        ValidatableResponse responseLogin = courierClient.login(Credentials.from(courier));
        int actualCode = responseCreate.extract().path("code" );
        String actualMessage = responseCreate.extract().path("message" );
        assertEquals("Expected 409",SC_CONFLICT, actualCode);
        assertEquals("Этот логин уже используется. Попробуйте другой.",actualMessage);
    }
}

