package org.example.sdet;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

public class Anagram {

    /*
    1) Write a function to check whether two given strings are anagram of each other or not.
 An anagram of a string is another string that contains the same characters,
 only the order of characters can be different. For example, “abcd” and “dabc” are an anagram of each other. */

    @Test
    public void test(){
        String s1="listen";
        String s2="silent";
        boolean b = anagramCheck(s1, s2);
        Assert.assertTrue(b);
    }
    public boolean anagramCheck(String s1, String s2){
        if (s1.length()!=s2.length())
            return false;
        else{
            s1.toLowerCase();
            s2.toLowerCase();
            ArrayList<Character>l1=new ArrayList<>();
            ArrayList<Character>l2=new ArrayList<>();
            for (int i=0;i<s1.length();i++){
                l1.add(s1.charAt(i));
                l2.add(s2.charAt(i));
            }
            Collections.sort(l1);
            Collections.sort(l2);
            if(l1.equals(l2)){
                return true;
            }else {
                return false;
            }
        }

    }

}
