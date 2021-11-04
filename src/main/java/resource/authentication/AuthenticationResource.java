package resource.authentication;

public interface AuthenticationResource
{
    void login(LoginVM credentials, String domain);
    void logout();

    boolean isAuthenticated();

    String[][] getRequestImportantHeaders();
    String getDomain();
}
