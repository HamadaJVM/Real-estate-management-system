package com.example.brocodedemo;
import java.util.Scanner;

public class Finance {
   private static float VALUE_OF_PROPERTIES =0;
    private static float TOTAL_CASH = 1000;
    private static float allNetWroth ;

//?how can I call it
    static void setTotalCash(){
        Scanner cin = new Scanner(System.in);
        System.out.println("Enter the Total Cash Or Edit");
        TOTAL_CASH = cin.nextFloat();
    }
     void updateTotalCash(float p){
        TOTAL_CASH = TOTAL_CASH - p;
    }
    void updateValueOfProperties(float p){
        VALUE_OF_PROPERTIES = VALUE_OF_PROPERTIES + p;

    }

    public static float getAllNetWorth(){
        return TOTAL_CASH + VALUE_OF_PROPERTIES;
    }


    static public void displayFinance(){
        System.out.println("-------------------");
        System.out.println("Total Cash = " + TOTAL_CASH);
        System.out.println("value OF properties = " + VALUE_OF_PROPERTIES );
        System.out.println("All Net Worth  = " + getAllNetWorth());
        System.out.println("-------------------");

    }

}
