import java.io.File;
import java.util.*;

/**
 * Created by Woj on 2016-11-01.
 */
public class Test {
    public static void main(String[] args) {
      // mostFrequentNumber(new int[]{1,3,11,7,3,17,7,3,7});

        System.out.println("I have frightened you- sit down and tell me all the news.\"                  ".compareToIgnoreCase("him,\" she added in a tone admitting of no rejoinder and raising her         \n"));

    }

    // Czyli algorytm który znajduje najczęściej powtarzająca sie wartość w tablicy o długości n sadzących sie z liczb naturalnym.
//    public static int findMostFrequent(int[] table) {
//        HashMap<Integer, Integer> mapOfOccurs = new HashMap<>();
//        for (int i = 0; i < table.length; i++) {
//            int key = table[i];
//            if (mapOfOccurs.containsKey(key)) mapOfOccurs.put(key, mapOfOccurs.get(key) + 1);
//            else mapOfOccurs.put(key, new Integer(1));
//        }
//        int count = 1, value = 0;
//        //dodac jesli sa takie same
//        //dodac wersjer na tablicach
//
//        for (Map.Entry<Integer, Integer> entry : mapOfOccurs.entrySet()) {
//            if (entry.getValue() > count) {
//                count = entry.getValue();
//                value = entry.getKey();
//            }
//
//        }
//        return value;
//    }



    public static void mostFrequentNumber(int[] table) {
        HashMap<Integer, Integer> mapOfOccurs = new HashMap<>();
        for (int i = 0; i < table.length; i++) {
            int key = table[i];
            if (mapOfOccurs.containsKey(key)) mapOfOccurs.put(key, mapOfOccurs.get(key) + 1);
            else mapOfOccurs.put(key, new Integer(1));
        }

        int max=0;
        for (int i = 0; i < mapOfOccurs.size(); i++) {
            int value =mapOfOccurs.get(table[i]);
            if (value>max) max=value;
        }
        String values ="";
        for (Map.Entry<Integer, Integer> entry : mapOfOccurs.entrySet()) {
            if (entry.getValue() == max) values+=" "+entry.getKey();
        }
        System.out.println("most frequent values:"+values);
    }
}
