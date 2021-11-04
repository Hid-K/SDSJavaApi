package interceptor;

import resource.authentication.AuthenticationResource;
import resource.authentication.JWTAuthenticationResourceImpl;
import resource.authentication.LoginVM;

import javax.security.auth.login.LoginException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class JWTAuthInterceptorImpl implements Interceptor
{
    AuthenticationResource authenticationResource;
    LoginVM credentials;
    String domain;

    public JWTAuthInterceptorImpl(LoginVM credentials, String domain)
    {
        this.authenticationResource = new JWTAuthenticationResourceImpl();
        this.authenticationResource.login(credentials, domain);
    }

    @Override
    public HttpResponse doRequest(String endpoint, int requestType, String requestBody) throws LoginException
    {
        if(!this.authenticationResource.isAuthenticated())
        {
            this.authenticationResource.login(credentials, domain);
        }

        if(!this.authenticationResource.isAuthenticated())
        {
            throw new LoginException();
        }
        System.out.println("=================\nRequest:\n" + endpoint);
        System.out.println(requestBody+"\n");
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(this.authenticationResource.getDomain() + endpoint))
                .timeout(Duration.ofMinutes(10));

        for (String[] header:
            authenticationResource.getRequestImportantHeaders())
        {
            requestBuilder.header(header[0],header[1]);
        }

        HttpRequest request;

        if(requestType == REQUESTTYPE.REQUESTTYPE_POST)
        {
            request = requestBuilder.POST(HttpRequest.BodyPublishers.ofByteArray(requestBody.getBytes()))
                    .build();
        } else if(requestType == REQUESTTYPE.REQUESTTYPE_GET)
        {
            request = requestBuilder.GET()
                    .build();
        } else if(requestType == REQUESTTYPE.REQUESTTYPE_PUT)
        {
            request = requestBuilder.PUT(HttpRequest.BodyPublishers.ofByteArray(requestBody.getBytes()))
                    .build();
        } else if(requestType == REQUESTTYPE.REQUESTTYPE_DELETE)
        {
            request = requestBuilder.DELETE()
                    .build();
        } else return null;

        HttpResponse<String> response = null;

        try
        {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        System.out.println("Response:\n" + response.body() + "\n=================");

        return response;
    }
}
