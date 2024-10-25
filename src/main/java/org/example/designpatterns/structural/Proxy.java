package org.example.designpatterns.structural;

// provides controlled access to objects
// can do pre-process or post-process (acts as middleware)
// can do caching id needed

interface DBOperations{
    void create();
    void delete();
    void update();

}

class DB implements DBOperations{

    @Override
    public void create() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void update() {

    }

}

class DBProxy implements DBOperations{
    private String username;
    private String password;
    private String role;
    DBOperations dbOperations ;
    DBProxy(String username, String Password){
        dbOperations = new DB();
        // role = DBConnect(user,password).getRole();

    }
    @Override
    public void create() {
        dbOperations.create();
    }

    @Override
    public void delete() {
        if(role.equals("ADMIN")){
            dbOperations.delete();
        }
    }

    @Override
    public void update() {
        if(role.equals("ADMIN")){
            dbOperations.update();
        }
    }

}

public class Proxy {
    public static void main(String[] args) {
        DBOperations db = new DBProxy("ADMIN","PASSWORD");
        //db operation controls the access.
    }

}
