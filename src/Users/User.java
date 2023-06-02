package Users;

import Interfaces.IToFile;
import Interfaces.IToJSON;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.*;

public class User implements IToJSON, IToFile, Comparable {
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

    //Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }


    //equals, hashcode, compareTo, toString

    @Override
    public boolean equals(Object obj)
    {
        boolean rta=false;
        if(obj!=null)
        {
            if(obj instanceof User)
            {
                User aux=(User) obj;
                if(getId()==aux.getId())
                {
                    rta=true;
                }
            }
        }
        return rta;
    }

    @Override
    public int compareTo(Object obj)
    {
        int rta=0;
        if(obj instanceof User)
        {
            User aux=(User) obj;
            if(getUserData().getAge()>aux.getUserData().getAge())
            {
                rta=1;
            }else{
                rta=-1;
            }
        }
        return rta;
    }

    @Override
    public int hashCode() {
        return 1;
    }

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

    @Override
    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("password", password);
        json.put("email", email);
        json.put("id", id);
        json.put("userData", userData.toJSON());
        return json;
    }

    @Override
    public void toFile() throws JSONException, IOException {

    }
}
