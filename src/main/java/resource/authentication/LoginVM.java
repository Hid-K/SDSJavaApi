package resource.authentication;

public class LoginVM
{
    private String username;
    private String password;

    private boolean rememberMe;

    public LoginVM(String username, String password, boolean rememberMe)
    {
        this.username = username;
        this.password = password;
        this.rememberMe = rememberMe;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public boolean isRememberMe()
    {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe)
    {
        this.rememberMe = rememberMe;
    }
}
