package org.example.designpatterns.structural;
// adds more functionality to the existing data without changing it's base structure
// adding new notification system on top of the existing one.


interface BasePizza{
    int cost();
}
class ThickCrustPizza implements BasePizza{
    public int cost(){
        return 10;
    }
}

class ThinCrustPizza implements BasePizza{
    public int cost(){
        return 8;
    }
}

class CheezeToppings implements BasePizza{
    BasePizza base;
    CheezeToppings(BasePizza base){
        this.base = base;
    }
    public int cost(){
        return base.cost() + 2;
    }
}

class MushroomToppings implements BasePizza{
    BasePizza base;
    MushroomToppings(BasePizza base){
        this.base = base;
    }
    public int cost(){
        return base.cost() + 4;
    }
}

public class Decorator {
    public static void main(String[] args) {
        BasePizza pizza = new CheezeToppings(new MushroomToppings( new ThickCrustPizza()));
        System.out.println(pizza.cost());

    }
}
