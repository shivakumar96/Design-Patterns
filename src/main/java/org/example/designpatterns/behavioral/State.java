package org.example.designpatterns.behavioral;

// This pattern allows the object to alter it's
// behaviour when the internal state of the object changes.
// This pattern also makes uses of strategy design pattern.
// Finite state Machine

/*
*                        |--(Action -2)--|
*                        V               |
*   stateA -(Action-1)> stateB ----------|----(Action-3)---> StateC
*               ^                                               |
*               |----------(Action -4)--------------------------|
*
* Here Action will be the methods
* State will be the concrete implementation
* If there are unused actions by the state can throw exception or chose to do nothing
*
* */

/*
*    OffState ( on LockButtonClick , HomeButtonClick ) -> LockedState
*    LockedState (on LockButtonClick) -> OffState.
*    LockedState (on HomeButtonClick) -> ReadyState.
*    ReadyState  (on LockButtonClick) -> OffState.
*    ReadyState (on HomeButtonClick) -> does nothing
* */

class Phone{
    PhoneState state;
    Phone(){
        state = new OffState();
    }
    public void setPhoneState(PhoneState state){
        this.state = state;
    }
}

// you cna also make an abstract class and store the Phone reference
interface PhoneState {
    void onLockButtonClick(Phone phone);
    void onHomeScreenButtonClick(Phone phone);
}

class LockedState implements  PhoneState{

    @Override
    public void onLockButtonClick(Phone phone) {
        System.out.println("LockedSate -> clicked (Lock) Button change state to -> OffSate");
        phone.setPhoneState(new OffState());
    }

    @Override
    public void onHomeScreenButtonClick(Phone phone) {
        System.out.println("LockedSate -> clicked (Home) Button change state to -> ReadyState");
        phone.setPhoneState(new ReadyState());
    }
}

class ReadyState implements  PhoneState{

    @Override
    public void onLockButtonClick(Phone phone) {
        System.out.println("ReadyState -> clicked (Lock) Button change state to -> OffSate");
        phone.setPhoneState(new OffState());
    }

    @Override
    public void onHomeScreenButtonClick(Phone phone) {
        System.out.println("ReadyState -> clicked (Home) Button change state to -> ReadyState");
        // does nothing
    }
}

class OffState implements  PhoneState{

    @Override
    public void onLockButtonClick(Phone phone) {
        System.out.println("OffState -> clicked (Lock) Button change state to -> LockedState");
        phone.setPhoneState(new LockedState());
    }

    @Override
    public void onHomeScreenButtonClick(Phone phone) {
        System.out.println("OffState -> clicked (Home) Button change state to -> LockedState");
        phone.setPhoneState(new LockedState());
    }
}

public class State {
    public static void main(String[] args) {

        // phone state changes internally.
        Phone phone = new Phone();
        phone.state.onLockButtonClick(phone);
        phone.state.onHomeScreenButtonClick(phone);
        phone.state.onHomeScreenButtonClick(phone);
        phone.state.onLockButtonClick(phone);
        phone.state.onLockButtonClick(phone);
    }
}
