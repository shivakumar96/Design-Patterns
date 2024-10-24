package org.example.designpatterns.creational;


// used when multiple constructor fields are present
class ObjectA{
    String name;
    int id;
    boolean isAvailable;
    ObjectA(String name, int id, boolean isAvailable){
        this.name = name;
        this.id = id;
        this.isAvailable = isAvailable;
    }
}

class ObjectABuilder {
    private String name;
    private int id;
    private boolean isAvailable;
    public ObjectABuilder id(int id){
        this.id = id;
        return this;
    }
    public ObjectABuilder isAvailable(boolean isAvailable){
        this.isAvailable = isAvailable;
        return this;
    }
    public ObjectABuilder name(String name){
        this.name = name;
        return this;
    }

    public ObjectA build(){
        return new ObjectA(name,id,isAvailable);
    }
}

public class Builder {
    public static void main(String[] args) {
        ObjectABuilder builder = new ObjectABuilder();
        ObjectA a = builder.name("name").id(123).build();
    }
}
