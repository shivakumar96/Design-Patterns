package org.example.designpatterns.behavioral;

// This pattern is similar to publish subscriber architecture.


import java.util.ArrayList;
import java.util.List;

class News{
    String news;
    News(String news){
        this.news = news;
    }

    @Override
    public String toString() {
        return news;
    }
}
interface ObserverI{
    void update(News news);
}

interface NewsObservable {

    void subscribe(ObserverI observer);
    void unsubscribe(ObserverI observer);
    void notifyObservers();
    void publish(String news);
}

class TechNewsLetter implements NewsObservable {
    List<ObserverI> observers;
    News latestNews;
    TechNewsLetter(){
        observers = new ArrayList<>();
    }

    @Override
    public void subscribe(ObserverI observer) {
        observers.add(observer);
    }

    @Override
    public void unsubscribe(ObserverI observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for( ObserverI observer : observers){
            observer.update(latestNews);
        }
    }

    @Override
    public void publish(String news) {
        latestNews = new News(news);
        notifyObservers();
    }
}

class EnterpriseNewsObserver implements ObserverI {
    String enterpriseName;
    EnterpriseNewsObserver(String enterpriseName){
        this.enterpriseName = enterpriseName;
    }
    @Override
    public void update(News news) {
        System.out.println("Enterprise : "+enterpriseName+" News Board :"+news);
    }
}

class ConsumerNewsObserver implements ObserverI {
    String consumerName;
    ConsumerNewsObserver(String enterpriseName){
        this.consumerName = enterpriseName;
    }
    @Override
    public void update(News news) {
        System.out.println("Consumer : "+consumerName+" News Board :"+news);
    }
}

public class Observer {
    public static void main(String[] args) {
        NewsObservable techNewsPublisher = new TechNewsLetter();
        ObserverI enterprise1 = new EnterpriseNewsObserver("Enterprise-1");
        ObserverI enterprise2 = new EnterpriseNewsObserver("Enterprise-2");
        ObserverI consumer1 = new ConsumerNewsObserver("consumer-1");
        techNewsPublisher.subscribe(enterprise1);
        techNewsPublisher.subscribe(enterprise2);
        techNewsPublisher.subscribe(consumer1);
        techNewsPublisher.publish("iphone 16 has released");
    }
}
