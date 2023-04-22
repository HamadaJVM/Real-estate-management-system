package com.example.brocodedemo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Finance {
   private static float VALUE_OF_PROPERTIES =0;
    private static float TOTAL_CASH = 1000;
    private static float ALL_NET_WORTH ;
    static void AddOnTotalCash(float var){
        TOTAL_CASH += var;
        VALUE_OF_PROPERTIES -=var;
    }
    static void cutFromTotalCash(float var){
        TOTAL_CASH -= var;
        VALUE_OF_PROPERTIES +=var;
    }
    static void AddOnTotalCash(String var){
        if (!var.isEmpty())
           TOTAL_CASH += Float.parseFloat(var);

    }
    static void cutFromTotalCash(String var){
        if (!var.isEmpty())
            TOTAL_CASH -= Float.parseFloat(var);

    }

    public static void setValueOfProperties(float var){
        VALUE_OF_PROPERTIES = var;
        updateAllNetWorth();
    }
    public static void updateAllNetWorth(){
        ALL_NET_WORTH = TOTAL_CASH + VALUE_OF_PROPERTIES;

    }

    static public void displayFinance(){
        System.out.println("-------------------");
        System.out.println("Total Cash = " + TOTAL_CASH);
        System.out.println("value OF properties = " + VALUE_OF_PROPERTIES );
        System.out.println("All Net Worth  = " + ALL_NET_WORTH);
        System.out.println("-------------------");

    }

    public static float getValueOfProperties() {
        return VALUE_OF_PROPERTIES;
    }

    public static float getTotalCash() {
        return TOTAL_CASH;
    }

    public static float getAllNetWorth() {
        return ALL_NET_WORTH;
    }
}
