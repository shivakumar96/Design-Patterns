package org.example.designpatterns.creational;

interface CustomObject {
    void work();
}

class CustomObjectA implements CustomObject{
    public void work(){
    }
}

class CustomObjectB implements  CustomObject{
    public void work(){
    }
}

abstract class Factory{
    public CustomObject generate(){
        CustomObject obj = null;
        obj = create();
        obj.work();
        return  obj;
    }
    public abstract CustomObject create();
}

class FactoryA extends Factory{
    public CustomObject create(){
        return  new CustomObjectA();
    }
}

class FactoryB extends Factory{
    public CustomObject create(){
        return  new CustomObjectB();
    }
}

public class SimpleFactory {
    public static void main(String[] args) {
        Factory factory = new FactoryA();
        CustomObject a =  factory.generate();
    }
}
