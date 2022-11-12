package courierTest;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.courier.Courier;
import org.example.courier.CourierClient;
import org.example.courier.CourierGenerator;
import org.example.courier.Credentials;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;

public class CourierPositiveTest {
    private CourierClient courierClient;
    private Courier courier;
    private int id;

   @Before
    public void setUp() {
       courierClient = new CourierClient();
       courier = CourierGenerator.getDefault();
    }

    @After
    public void cleanUp(){
        courierClient.delete(id);
    }

    @Test
    @DisplayName("Check response when courier is created" )
    public void courierCanBeCreated(){
       ValidatableResponse responseCreate = courierClient.create(courier);
       ValidatableResponse responseLogin = courierClient.login(Credentials.from(courier));
       id = responseLogin.extract().path("id");
       int actualStatusCode = responseCreate.extract().statusCode();
       boolean isCourierCreated = responseCreate.extract().path("ok");
       assertEquals("Status Code incorrect",actualStatusCode, SC_CREATED);
       assertEquals("Expected true",true, isCourierCreated);
    }

    @Test
    @DisplayName("Check response when courier is logged in" )
    public void courierCanBeLoginAndCheckResponse(){
        ValidatableResponse responseCreate = courierClient.create(courier);
        ValidatableResponse responseLogin = courierClient.login(Credentials.from(courier));
        id = responseLogin.extract().path("id");
        int actualStatusCode = responseLogin.extract().statusCode();
        assertEquals("Status Code incorrect",actualStatusCode,SC_OK);
        assertThat("Expected courier is logged In",id, notNullValue());
    }
}
