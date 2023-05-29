package Users;

public class AdminUser extends User{


    //constructors:
    public AdminUser(String name, String password, String email, int id, UserData userData) {
        super(name, password, email, id, userData);
    }

    public AdminUser() {
    }

    //Methods:

    public User getSpecificUser(int id)
    {
        return aux;
    }

    public void showAllUsers()
    {

    }
}
