package hello.JsonIgnoreProperties;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.google.gson.JsonObject;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class UserIgnoreUnknownTest {

    @Test
    public void jsonIgnoreProperties_WhenIgnoreUnknown() throws IOException {
        UserIgnoreUnknown userIgnoreUnknown = new UserIgnoreUnknown("first", "last");

        JsonObject userJsonObject = new JsonObject();
        userJsonObject.addProperty("first_name", "first");
        userJsonObject.addProperty("last_name", "last");
        userJsonObject.addProperty("not_exist_key", "somethx");
        String json = userJsonObject.toString();

        // deserialization
        ObjectMapper mapper = new ObjectMapper();
        UserIgnoreUnknown actualUserIgnoreUnknown = mapper.readValue(json, UserIgnoreUnknown.class);
        System.out.println(actualUserIgnoreUnknown);
        assertThat(actualUserIgnoreUnknown, equalTo(userIgnoreUnknown));
    }

    @Test
    public void jsonIgnoreProperties_WhenIgnoreFirstName() throws IOException {
        UserIgnoreFirstName userIgnoreFirstName = new UserIgnoreFirstName(null, "last");

        JsonObject userJsonObject = new JsonObject();
        userJsonObject.addProperty("first_name", "first");
        userJsonObject.addProperty("last_name", "last");
        String json = userJsonObject.toString();

        // deserialization
        ObjectMapper mapper = new ObjectMapper();
        UserIgnoreFirstName actualUserIgnoreFirstName = mapper.readValue(json, UserIgnoreFirstName.class);
        System.out.println(actualUserIgnoreFirstName); // UserIgnoreUnknown{firstName='null', lastName='last'}
        assertThat(actualUserIgnoreFirstName, equalTo(userIgnoreFirstName));
    }

    @Test(expected = UnrecognizedPropertyException.class)
    public void deserialization_WhenNothingIgnore() throws IOException {
        User user = new User("first", "last");

        JsonObject userJsonObject = new JsonObject();
        userJsonObject.addProperty("first_name", "first");
        userJsonObject.addProperty("last_name", "last");
        userJsonObject.addProperty("not_exist_key", "somethx");
        String json = userJsonObject.toString();

        // deserialization
        ObjectMapper mapper = new ObjectMapper();
        User actualUserIgnoreUnknown = mapper.readValue(json, User.class);
        System.out.println(actualUserIgnoreUnknown);
        assertThat(actualUserIgnoreUnknown, equalTo(user));
    }
}