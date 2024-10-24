package org.example.designpatterns.creational;

// this is used for the creating the clone of the objects

interface PrototypeI{
    PrototypeI clone();
}

class Student implements PrototypeI {
    private int id;
    private String name;
    Student(String name, int id){
        this.id = id;
        this.name = name;
    }

    public PrototypeI clone(){
        return new Student(this.name,this.id);
    }
}

public class Prototype {
    public static void main(String[] args) {
        Student s = new Student("name",1);
        Student sClone = (Student) s.clone();

    }
}
