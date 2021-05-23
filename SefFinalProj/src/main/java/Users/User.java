package Users;

public class User
{
    private String username, password, fullName,role;

    /**
     * @param username
     * @param fullName
     * @param role
     */
    public User ( String username, String password, String fullName, String role )
    {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername()
    {
        return username;
    }

    public String getFullName()
    {
        return fullName;
    }


}
