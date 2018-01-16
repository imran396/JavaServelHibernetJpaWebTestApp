package custom;


import java.util.TreeMap;

public class Constant {

    public TreeMap<Integer, String> roles = new TreeMap<Integer, String>();


    public TreeMap<Integer, String> getRoles() {
        roles.put(1, "User");
        roles.put(2, "Admin");
        roles.put(3, "Super Admin");
        return roles;
    }
}
