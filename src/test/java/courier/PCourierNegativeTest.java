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
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class PCourierNegativeTest {
   private CourierClient courierClient;
    private Courier courier;
    private int statusCode;
    private String message;
    private int id;

   public PCourierNegativeTest(Courier courier, int statusCode, String message){
       this.courier = courier;
       this.statusCode = statusCode;
       this.message = message;
  }

//test data
@Parameterized.Parameters
    public static Object[][] getTestData(){
        return new Object[][]{
                {CourierGenerator.getWithLoginOnly(),SC_BAD_REQUEST,"Недостаточно данных для создания учетной записи"},
                {CourierGenerator.getWithPasswordOnly(),SC_BAD_REQUEST,"Недостаточно данных для создания учетной записи"}
        };
    }

   @Before
    public void setUp() {
        courierClient = new CourierClient();
   }

    @After
    public void cleanUp(){
        courierClient.delete(id);
    }

    @Test
    @DisplayName("Check response  when Courier can't create with one null field ")
    public void createCourierWithOutOneParameterCheckStatusCode(){
        ValidatableResponse responseCreate = courierClient.create(courier);
        ValidatableResponse responseLogin = courierClient.login(Credentials.from(courier));
        id = responseLogin.extract().path("id");
        int actualStatusCode = responseCreate.extract().path("code");
        String actualMessage = responseCreate.extract().path("message" );
        assertEquals("Message incorrect",message,actualMessage);
        assertEquals("Status Code incorrect",statusCode,actualStatusCode);
   }
}
