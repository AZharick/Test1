package MitshyxLabs;

import java.util.ArrayList;
import java.util.List;

public class Main {
   public static void main(String[] args) {
   }

   //=================================================

   public static int multiply(int a, int b) {
      return a*b;
   }

   public static double divide(int i1, int i2) {
         return i1/i2;
   }

   public static List<String> cloneList(List<String> inputList) {
      List<String> outputList = new ArrayList<>(inputList);
      return outputList;
   }



}