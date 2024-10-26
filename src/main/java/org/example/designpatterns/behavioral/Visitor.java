package org.example.designpatterns.behavioral;

// This isolates particular behaviors from the objects on which they operate , and places them in a single class
// classes will be focused on their main Job hence enforcing the single responsibility principle

// This allows us to  add new operations to existing class without changing their structure
// It achieves this separating the algorithm from the objects on which they operate
// In order to do this is makes use of the double dispatch concept.

import java.util.ArrayList;
import java.util.List;

interface RoomElement{
    void accept(RoomVisitor visitor);
}

class SingleRoom implements RoomElement {
    int price;
    @Override
    public void accept(RoomVisitor visitor) {
        visitor.visit(this);
    }
}

class DoubleRoom implements RoomElement {
    int price;
    @Override
    public void accept(RoomVisitor visitor) {
        visitor.visit(this);
    }
}
interface RoomVisitor{
    void visit(SingleRoom singleRoom);
    void visit(DoubleRoom doubleRoom);

}

class PricingVisitor implements RoomVisitor{

    @Override
    public void visit(SingleRoom singleRoom) {
        System.out.println("Updating Single Room Price");
        singleRoom.price = 100;
    }

    @Override
    public void visit(DoubleRoom doubleRoom) {
        System.out.println("Updating Double Room Price");
        doubleRoom.price = 200;
    }
}

class MaintenanceVisitor implements RoomVisitor{

    @Override
    public void visit(SingleRoom singleRoom) {
        System.out.println("Sending one person for Single Room Maintenance");
    }

    @Override
    public void visit(DoubleRoom doubleRoom) {
        System.out.println("Sending two people for Double Room Maintenance");
    }
}

// In the above example I have removed the behaviour from room class,
// so that client doesn't have to worry about with type of the room it has to deal with.
// whenever there is a change in behavior it will be updated in one single class.

public class Visitor {
    public static void main(String[] args) {
        List<RoomElement> rooms = new ArrayList<>();
        RoomVisitor pricingVisitor = new PricingVisitor();
        RoomVisitor maintenanceVisitor = new MaintenanceVisitor();
        rooms.add(new DoubleRoom());
        rooms.add(new SingleRoom());

        for(RoomElement room : rooms){
            room.accept(pricingVisitor);
        }

        for(RoomElement room : rooms){
            room.accept(maintenanceVisitor);
        }

    }
}
