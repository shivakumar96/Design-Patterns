package org.example.designpatterns.structural;


// Provides a layer of abstraction and hides unwanted methods and complexities

class ObjectA{
    private String name;
    private int id;

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
}


class ObjectADAO{
    void insert(){

    }
    void update(){

    }
    void delete(){

    }
}


class ObjectAFacade{
    private ObjectADAO dao;
    ObjectAFacade(){
        dao = new ObjectADAO();
    }
    void insert(){
        dao.insert();
    }
}


public class Facade {
    public static void main(String[] args) {
        ObjectAFacade facade = new ObjectAFacade();
        facade.insert();
    }
}
