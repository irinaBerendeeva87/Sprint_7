package courierTest;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.courier.Courier;
import org.example.courier.CourierClient;
import org.example.courier.CourierGenerator;
import org.example.courier.Credentials;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class PCourierNegativeLoginTest {
    private CourierClient courierClient;
    private Courier courier;
    private int statusCode;
    private String message;

    public PCourierNegativeLoginTest(Courier courier, int statusCode, String message) {
        this.courier = courier;
        this.statusCode = statusCode;
        this.message = message;
    }

//test data
    @Parameterized.Parameters(name = "#{index}, {0}, {1}, {2}")
    public static Object[][] getTestData() {
        return new Object[][]{
                {CourierGenerator.getWithLoginOnly(), SC_BAD_REQUEST, "Недостаточно данных для входа"},
                {CourierGenerator.getWithPasswordOnly(), SC_BAD_REQUEST, "Недостаточно данных для входа"},
                {CourierGenerator.getWithIncorrectCredentials(), SC_NOT_FOUND, "Учетная запись не найдена"}
        };
    }

    @Before
    public void setUp() {
        courierClient = new CourierClient();}

    @Test
    @DisplayName("Check response  when Courier can't logged in with one null field ")
    public void loginCourierAndCheckStatusCode(){
        ValidatableResponse responseLogin = courierClient.login(Credentials.from(courier));
        int actualStatusCode = responseLogin.extract().statusCode();
        String actualMessage = responseLogin.extract().path("message" );
        assertEquals("Message incorrect",message,actualMessage);
        assertEquals("Status Code incorrect",statusCode,actualStatusCode);
    }
}

