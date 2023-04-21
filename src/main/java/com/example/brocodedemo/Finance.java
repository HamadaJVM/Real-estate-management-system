package com.example.brocodedemo;
import java.util.Scanner;
public class Finance {
   private static float VALUE_OF_PROPERTIES =0;
    private static float TOTAL_CASH = 1000;
    private static  float ALL_NET_WORTH;

//?how can I call it
    static void setTotalCash(){
        Scanner cin = new Scanner(System.in);
        System.out.println("Enter the Total Cash Or Edit");
        TOTAL_CASH = cin.nextFloat();
    }
    static void AddOnTotalCash(float var){
        TOTAL_CASH += var;
        VALUE_OF_PROPERTIES -=var;
    }
    static void CutFromTotalCash(float var){
        TOTAL_CASH -= var;
        VALUE_OF_PROPERTIES +=var;
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

}
