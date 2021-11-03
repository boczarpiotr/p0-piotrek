
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


@Slf4j
public class App {
    public static void main(String[] args){
        String choice = args[0];
        log.error("It is working =)");

        ObjectMapper objectMapper = new ObjectMapper();

        URL url = null;
        try {
            url = new URL("https://rickandmortyapi.com/api/character/" + choice);
        } catch (MalformedURLException e) {
           log.error("Not correct URL");
        }
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
           log.error("Connection was not made");
        }
        InputStream response = null;
        try {
            response = connection.getInputStream();
        } catch (IOException e) {
            log.error("Response was not received");
        }
        String body = null;
        try {
            body = new String(response.readAllBytes());
        } catch (IOException e) {
            log.error("Could not read response");
        }

        Character character = null;
        try {
            character = objectMapper.readValue(body, Character.class);
        } catch (JsonProcessingException e) {
           log.error("Could not parse response");
        }

        System.out.println(character);


    }
}
