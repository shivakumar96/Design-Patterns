package org.example.designpatterns.structural;

// Converts one form to the other
// Example we have a class method that returns a xml format data,
// But the client accepts only JSON then an adaptor pattern cam be bused can be used

class XML{

}
class JSON{

}

class Fetcher{
    public XML fetch(String s){
        return new XML();
    }
}

class FetchJSONAdaptor{
    Fetcher fetcher;
    FetchJSONAdaptor(){
        fetcher = new Fetcher();
    }
    public JSON fetchInJSON(String s){
        XML xml = fetcher.fetch(s);
        //convert xml tp JSON
        JSON json = new JSON();
        return json;
    }
}

public class Adapter {
    public static void main(String[] args) {
        FetchJSONAdaptor jsonAdaptor = new FetchJSONAdaptor();
        jsonAdaptor.fetchInJSON("url");
    }
}
