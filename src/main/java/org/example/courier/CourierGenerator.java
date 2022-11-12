package org.example.courier;

public class CourierGenerator {
    public static Courier getDefault(){
        return new Courier("joey23", "next1221", "Alex");
    }

    public static Courier getWithPasswordOnly (){
        return new Courier(null,"next1221","Alex" );
    }

    public static Courier getWithLoginOnly (){
        return new Courier("joey23", null, "Alex");
    }

    public static Courier getWithIncorrectCredentials(){
        return new Courier("joe", "next1221", "Alex");
    }
}