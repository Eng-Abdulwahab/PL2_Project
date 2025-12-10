package ClassesOfTheProject;

public class User {
    private String username;
    private String password;
    private String ID;
    private String role;

    User() {}

    User(String username, String pass, String ID, String role)
    {
        this.username = username;
        this.password = pass;
        this.ID = ID;
        this.role = role;
    }

    public String getUsername() {return this.username;}

    public String getPassword() {return this.password;}

    public String getID() {return this.ID;}

    public String getRole() {return this.role;}

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setID(String ID)
    {
        this.ID = ID;
    }

    public void setRole(String role)
    {
        this.role = role;
    }
}
