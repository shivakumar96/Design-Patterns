package org.example.designpatterns.behavioral;

// This pattern restricts direct communicate and hence reduce the dependencies
// In this pattern objects interact through a mediator.
// This Pattern is similar to Observer and proxy method combined.
// example: say if multiple planes need to do landing,
// They cannot participate communicate directly, instead they communicate
// to airport traffic control room which is a mediator and guides them.

// example of bidding system

import java.util.ArrayList;
import java.util.List;

interface Participant{
    void bidAmount(int amount);
    void receiveBidNotification(int amount);
    String getName();
}
interface MediatorI {
    void addBidder(Participant p);
    void placeBid(Participant p, int bidAMount);
}

class BidMediator implements MediatorI{
    List<Participant> participantList;
    BidMediator(){
        participantList = new ArrayList<>();
    }
    @Override
    public void addBidder(Participant p) {
        participantList.add(p);
    }

    @Override
    public void placeBid(Participant p, int bidAMount) {
        for(Participant participant : participantList){
            if(!p.getName().equals(participant.getName())){
                participant.receiveBidNotification(bidAMount);
            }
        }
    }
}

class Bidder implements Participant {
    MediatorI mediator;
    String name;
    Bidder(String name, MediatorI mediator){
        this.name = name;
        this.mediator = mediator;
        mediator.addBidder(this);
    }
    @Override
    public void bidAmount(int amount) {
        mediator.placeBid(this,amount);
    }

    @Override
    public void receiveBidNotification(int amount) {
        System.out.println(name + " has new bid notification of amount : "+amount);
    }

    @Override
    public String getName() {
        return name;
    }
}

public class Mediator {
    public static void main(String[] args) {
        MediatorI mediator = new BidMediator();
        Participant p1 = new Bidder("Bidder A",mediator);
        Participant p2 = new Bidder("Bidder B",mediator);
        Participant p3 = new Bidder("Bidder C",mediator);

        p1.bidAmount(200);
        p2.bidAmount(250);
    }
}
