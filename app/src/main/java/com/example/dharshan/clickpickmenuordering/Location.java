package com.example.dharshan.clickpickmenuordering;

/**
 * Created by Dharshan on 8/15/2017.
 */

public class Location {

    private int id;
    private String name;
    private double price;

    public Location(){

    }

    public Location(int id,String name,double price){
        this.id = id;
        this.name = name;
        this.price = price;

    }

    @Override
    public String toString(){
        return this.id +" "+this.name +""+this.price;

    }
}
