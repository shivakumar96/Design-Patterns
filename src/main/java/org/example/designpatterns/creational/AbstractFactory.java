package org.example.designpatterns.creational;
interface Monitor{
    void assemble();
}

interface GPU{
    void assemble();
}

class MonitorA implements Monitor{
    public void assemble(){}
}
class MonitorB implements Monitor{
    public void assemble(){}
}

class GPUA implements GPU{
    public void assemble(){}
}
class GPUB implements GPU{
    public void assemble(){}
}

abstract class Company{
    public abstract Monitor createMonitor();
    public abstract GPU CreateGPU();
}
class CompanyA extends Company{
    public Monitor createMonitor(){
        MonitorA m = new MonitorA();
        m.assemble();
        return m;
    }
    public GPU CreateGPU(){
        GPUA g = new GPUA();
        g.assemble();
        return g;
    }
}

class CompanyB extends Company{
    public Monitor createMonitor(){
        MonitorB m = new MonitorB();
        m.assemble();
        return m;
    }
    public GPU CreateGPU(){
        GPUB g = new GPUB();
        g.assemble();
        return g;
    }
}


public class AbstractFactory {
    public static void main(String[] args) {
        Company company = new CompanyA();
        Monitor monitorA = company.createMonitor();
    }
}
