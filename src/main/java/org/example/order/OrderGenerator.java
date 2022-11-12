package org.example.order;

public class OrderGenerator {
    public static Order getWithGrey(){
        return new Order("Ирина","Иванова","Konoha, 142 apt.","5", "+78003553535",  "5","2022-11-15","Saske, come back to Konoha", new String[]{"GREY"});
    }

    public static Order getWithBlack(){
        return new Order("Ирина","Иванова","Konoha, 142 apt.","5","+78003553535","5","2022-11-15","Saske, come back to Konoha",new String[]{"BLACK"});
    }

    public static Order getWithoutTwoColours(){
        return new Order("Ирина","Иванова","Konoha, 142 apt.","5","+78003553535","5","2022-11-15","Saske, come back to Konoha",null);
    }

    public static Order getWithTwoColours(){
        return new Order("Ирина","Иванова","Konoha, 142 apt.","5","+78003553535","5","2022-11-15","Saske, come back to Konoha",new String[]{"GREY","BLACK"});
    }
}
