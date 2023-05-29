package Users;

public class User {
    private String name;
    private String password;
    private String email;
    private int id;
    private UserData userData;


    //constructors:
    public User(String name, String password, String email, int id, UserData userData) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.id = id;
        this.userData = userData;
    }

    public User() {
    }

    //Getters:


    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public UserData getUserData() {
        return userData;
    }

    //equals, hashcode, compareTo, toString


    @Override
    public String toString() {
        return "User{" + '\n' +
                "name: " + name + '\n' +
                "password: " + password + '\n' +
                "email: " + email + '\n' +
                "id: " + id + '\n' +
                "userData: " + userData.toString() +
                '}';
    }
}
