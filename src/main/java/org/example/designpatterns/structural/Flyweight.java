package org.example.designpatterns.structural;

// This pattern is used to reduce the memory consumption
// this segregates rarely modified data

import java.util.HashMap;
import java.util.Map;

class Image{
    String name;
    Image(String name){
        this.name = name;
    }
     void display(){
        System.out.println(name);
     }
}

enum AvatarType{
    TYPE_A,
    TYPE_B
}
//predefined set of avatar. Immutable. This is Flyweight object
class Avatar{
    private AvatarType type;
    private Image image;
    Avatar(AvatarType type, Image image){
        this.type = type;
        this.image = image;
    }
    // avatar image
    public Image render(){
        return image;
    }
}

class AvatarFactory{
    Map<AvatarType,Avatar> avatarCache ;
    AvatarFactory(){
        avatarCache = new HashMap<>();
    }
    public Avatar create(AvatarType type){
        if(avatarCache.containsKey(type))
            return avatarCache.get(type);

        Image img = switch (type){
            case TYPE_A -> { yield new Image("type1");}
            case TYPE_B -> { yield new Image("type2");}
        };

        avatarCache.put(type,new Avatar(type,img));
        return avatarCache.get(type);

    }
}

class User{
    private static AvatarFactory avatarFactory;
    String name;
    Avatar avatar;

    static {
        avatarFactory = new AvatarFactory();
    }

    User(String name, AvatarType avatarType){
        this.name = name;
        avatar = avatarFactory.create(avatarType);
    }

}

public class Flyweight {
    public static void main(String[] args) {
        User userA = new User("User-A",AvatarType.TYPE_A);
        User userB = new User("User-B",AvatarType.TYPE_B);
        User userC = new User("User-C",AvatarType.TYPE_A);

        userA.avatar.render().display();
        userB.avatar.render().display();
        userC.avatar.render().display();

        System.out.println(userA.avatar == userB.avatar);
        System.out.println(userA.avatar == userC.avatar);
    }
}
