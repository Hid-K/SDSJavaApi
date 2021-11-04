package resource.authentication;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Date;

public class JavaSessionAuthenticationResourceImpl implements AuthenticationResource
{
    String token = "";
    String XSRFTOKEN = "";
    String domain = "http://127.0.0.1:8080";
    Date tokenExpiration;

    @Override
    public void login(LoginVM credentials, String domain)
    {
        this.domain = domain;

        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> response = null;

        HttpRequest getXSRFTokenRequest = HttpRequest.newBuilder()
                .uri(URI.create(domain + "/login"))
                .timeout(Duration.ofMinutes(1))
                .header("Accept", "*/*")
                .header("Accept-Encoding", "gzip, deflate, br")
                .GET()
                .build();

        try
        {
            response = client.send(getXSRFTokenRequest, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        System.out.println(response.body());

        XSRFTOKEN = response.headers().allValues("Set-Cookie").toString().replace("[XSRF-TOKEN=", "").replace("; path=/]", "");

        System.out.println("XSRF-TOKEN = " + XSRFTOKEN);

        HttpRequest getTokenRequest = HttpRequest.newBuilder()
                .uri(URI.create(domain + "/api/authentication?username=" + credentials.getUsername() + "&password=" + credentials.getPassword() + "&remember-me=" + credentials.isRememberMe()))
                .timeout(Duration.ofMinutes(1))
                .header("X-XSRF-TOKEN", XSRFTOKEN)
                .header("Cookie", "XSRF-TOKEN=" + XSRFTOKEN)
                .POST(HttpRequest.BodyPublishers.ofByteArray("".getBytes()))
                .build();

        try
        {
            response = client.send(getTokenRequest, HttpResponse.BodyHandlers.ofString());
            if(credentials.isRememberMe())
            {
                tokenExpiration = new Date(2592000L * 1000 + new Date().getTime());
            } else
            {
                tokenExpiration = new Date(86400L * 1000 + new Date().getTime());
            };
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        token = response.headers().allValues("Set-Cookie").toString().split(";")[0].replace("[JSESSIONID=", "");
        System.out.println("JSESSIONID = " + token);
    }

    @Override
    public void logout()
    {
        // TODO: Implement logout
    }

    @Override
    public boolean isAuthenticated()
    {
        return token != null && tokenExpiration.before(new Date(new Date().getTime()));
    }

    @Override
    public String[][] getRequestImportantHeaders()
    {
        return new String[0][];
    }

    @Override
    public String getDomain()
    {
        return domain;
    }
}
