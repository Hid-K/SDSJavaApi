package interceptor;

import resource.authentication.AuthenticationResource;

import javax.security.auth.login.LoginException;
import java.net.http.HttpResponse;

public interface Interceptor
{
    HttpResponse doRequest(String endpoint, int requestType, String requestBody) throws LoginException;
}
