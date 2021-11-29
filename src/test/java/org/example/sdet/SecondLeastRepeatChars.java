package org.example.sdet;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SecondLeastRepeatChars {

     /*
    Find the second least repeating character in a given string
Example:
Input: "tesla-service" Output: s
Solve using Map */

    public char secondLeastRepeatedChar(String s){
        HashMap<Character, Integer>map=new HashMap<>();
        for (int i=0;i<s.length();i++){
           map.put(s.charAt(i),map.getOrDefault(s.charAt(i),0)+1);
        }
        int min=Integer.MAX_VALUE;
        Set<Map.Entry<Character, Integer>> entries = map.entrySet();
       for (Map.Entry eachEntry:entries){
           Integer value=(Integer) eachEntry.getValue();
           Math.max(min,value);
       }
return ' ';
    }
}
