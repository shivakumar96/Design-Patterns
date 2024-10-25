package org.example.designpatterns.structural;

// Decouples abstraction form the implementation so that they can vary independently
// incorporates strategy design pattern
// allows classes to grow independently

import java.awt.*;

// the below example allows to grow share and color independently
interface Color{
    String getColor();
}

class Red implements Color{
    public String getColor(){
        return "Red";
    }
}
class Blue implements Color{
    public String getColor(){
        return "Blue";
    }
}

abstract class Shape{
    Color color;
    Shape(Color color){
        this.color = color;
    }

    abstract public void describe();
}

class Square extends Shape {
    Square(Color color){
        super(color);
    }
    public void describe(){
        System.out.println("This is square and is "+this.color.getColor());
    }
}

class Circle extends Shape {
    Circle(Color color){
        super(color);
    }
    public void describe(){
        System.out.println("This is Circle and is "+this.color.getColor());
    }
}

public class Bridge {
    public static void main(String[] args) {
        Shape shape = new Circle(new Blue());
        shape.describe();
    }
}
