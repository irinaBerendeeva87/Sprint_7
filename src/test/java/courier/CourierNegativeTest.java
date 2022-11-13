package courier;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.courier.Courier;
import org.example.courier.CourierClient;
import org.example.courier.CourierGenerator;
import org.example.courier.Credentials;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.assertEquals;

public class CourierNegativeTest {
    private  CourierClient courierClient;
    private  Courier courier;
    private int id;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
        courier = CourierGenerator.defaultCourier;
        ValidatableResponse responseCreate = courierClient.create(courier);
    }

    @After
    public void cleanUp(){
        courierClient.delete(id);
    }

    @Test
    @DisplayName("Check response when courier can't created twice" )
    public void createTheSameCourierAndCheckStatusCode(){
        ValidatableResponse responseCreate = courierClient.create(courier);
        ValidatableResponse responseLogin = courierClient.login(Credentials.from(courier));
        id = responseLogin.extract().path("id");
        int actualCode = responseCreate.extract().path("code" );
        String actualMessage = responseCreate.extract().path("message" );
        assertEquals("Expected 409",SC_CONFLICT, actualCode);
        assertEquals("Этот логин уже используется. Попробуйте другой.",actualMessage);
    }
}

