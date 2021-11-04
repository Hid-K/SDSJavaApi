package resource.authentication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

public class JWTAuthenticationResourceImpl implements AuthenticationResource
{
    String token;
    Date tokenExpiration;
    String domain = "http://127.0.0.1:8080";

    @Override
    public void login(LoginVM credentials, String domain)
    {
        if (domain != null)
        {
            this.domain = domain;
        }

        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> response = null;

        HttpRequest getTokenRequest = HttpRequest.newBuilder()
                .uri(URI.create(domain + "/api/authenticate"))
                .timeout(Duration.ofMinutes(1))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(
                        new JSONObject().put("username", credentials.getUsername())
                                        .put("password", credentials.getPassword())
                                        .put("rememberMe", credentials.isRememberMe()).toString()
                ))
                .build();

        try
        {
            response = client.send(getTokenRequest, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            token = new JSONObject(response.body()).getString("id_token");
            if(credentials.isRememberMe())
            {
                tokenExpiration = new Date(2592000L * 1000 + new Date().getTime());
            } else
            {
                tokenExpiration = new Date(86400L * 1000 + new Date().getTime());
            };

        } catch (JSONException e)
        {
            System.out.println("Invalid server response.");
            System.out.println(response.body());
        }

        if ( token!=null )
        System.out.println("Got token: " + token);
    }

    @Override
    public void logout()
    {
        // TODO: implement logout
    }

    @Override
    public boolean isAuthenticated()
    {
        return token != null && tokenExpiration.after(new Date(new Date().getTime()));
    }

    public String[][] getRequestImportantHeaders()
    {
        String[][] res = new String[2][2];
        res[0][0]= "Authorization";
        res[0][1]= "Bearer " + token;

        res[1][0]= "Content-Type";
        res[1][1]= "application/json";

        return res;
    }

    public String getDomain()
    {
        return domain;
    }
}
