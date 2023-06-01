package Users;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserTest {
    private String name;
    private String email;
    private String password;

    public UserTest(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    //getters, setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserTest() {
    }



    @Override
    public String toString() {
        return "UserTest{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
