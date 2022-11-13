package org.example.courier;

import java.util.Random;

public class CourierGenerator {
    private static final Random rnd = new Random();
    private static int getRnd(){
        return rnd.nextInt(1000);
    }

    public static final Courier defaultCourier =  new Courier("joey"+getRnd(), "next"+getRnd(), "Alex"+getRnd());

    public static Courier getWithPasswordOnly (){
        return new Courier(null,"next"+getRnd(),"Alex"+getRnd());
    }

    public static Courier getWithLoginOnly (){
        return new Courier("joey"+getRnd(), null, "Alex"+getRnd());
    }

    public static Courier getWithIncorrectCredentials(){
        return new Courier("joe", "next1221", "Alex");
    }


}