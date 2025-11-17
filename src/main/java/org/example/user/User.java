package org.example.user;

import java.util.ArrayList;
import java.util.List;

public class User {

    private int id;
    private String name;
    private String email;

    User(int _id, String _name, String _email) {
        id = _id;
        name = _name;
        email = _email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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



    public static List<User> defaul_values()
    {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "Mario Treviño", "mario@aroundthelab.edu"));
        users.add(new User(2, "Lisa heiss", "lisa@aroundthelab.com"));
        users.add(new User(3, "Frida Sernas", "frida@aroundthelab.com"));

        return users;
    }

    public static int get_index_from_user_id(List<User> _users, int _id) {
        for (int i = 0; i < _users.size(); i++) {
            if (_users.get(i).getId() == _id)
            {
                return i;
            }
        }
        return -1;
    }

    public static boolean user_id_exists(List<User> _users, int _id){
        for (User user : _users) {
            if (user.getId() == _id) {
                return true;
            }
        }
        return false;
    }
}
