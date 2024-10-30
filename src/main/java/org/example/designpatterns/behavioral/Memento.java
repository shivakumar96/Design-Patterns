package org.example.designpatterns.behavioral;

// provides an ability to revert the object to its previous state, i.e., UNDO capability
// It does not expose the object internal implementation
// It is also known as snapshot design pattern

// This pattern has three components
// 1. Originator
// 2. Memento
// 3. caretaker

// Originator: This is the object, for which state need to be saved and restored
//             - It exposes method Save and Restore its state using Memento Object
//
// Memento:  This is the object which holds the state of the Originator
//
// Caretaker: Manges the list of state (i.e list of Memento)

import java.util.ArrayList;
import java.util.List;

class ConfigurationMemento {
    private String fuel;
    private int speed;

    public String getFuel() {
        return fuel;
    }

    public int getSpeed() {
        return speed;
    }

    ConfigurationMemento(String fuel, int speed){
        this.speed =speed;
        this.fuel = fuel;
    }
}
class Configuration {
    private String fuel;
    private int speed;

    Configuration(String fuel, int speed){
        this.speed =speed;
        this.fuel = fuel;
    }

    public ConfigurationMemento createConfigurationMemento(){
        return new ConfigurationMemento(fuel,speed);
    }

    public void restoreFromConfigurationMemento(ConfigurationMemento memento){
        if(memento == null)
            return;
        this.speed = memento.getSpeed();
        this.fuel = memento.getFuel();
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "fuel='" + fuel + '\'' +
                ", speed=" + speed +
                '}';
    }
}

class ConfigurationMementoCareTaker{
    List<ConfigurationMemento> mementoList;
    ConfigurationMementoCareTaker(){
        mementoList = new ArrayList<>();
    }

    public void addMemento(ConfigurationMemento memento){
        if(memento == null)
            return;
        mementoList.add(memento);
    }

    public ConfigurationMemento undo(){
        if(mementoList.isEmpty())
            return  null;
        ConfigurationMemento memento = mementoList.get(mementoList.size()-1);
        mementoList.remove(mementoList.size()-1);
        return  memento;
    }
}


public class Memento {
    public static void main(String[] args) {
        Configuration configuration = new Configuration("Fuel-tank-1",35);
        ConfigurationMementoCareTaker careTaker = new ConfigurationMementoCareTaker();
        careTaker.addMemento(configuration.createConfigurationMemento());
        System.out.println(configuration);
        configuration.setSpeed(100);
        System.out.println(configuration);
        configuration.restoreFromConfigurationMemento(careTaker.undo());
        System.out.println(configuration);
    }
}
