package org.example.designpatterns.creational;


public class SingleTon {
    private static SingleTon obj;

    //Disables from create a new instance outside of the class.
    private SingleTon(){
    }

    public  static SingleTon getInstance(){
        //Double-checking to prevent object corruption when multiple threads are calling ethe same function.
        if(obj == null){
            synchronized (SingleTon.class){
                if(obj == null){
                    obj = new SingleTon();
                }
            }
        }
        return obj;
    }

}
