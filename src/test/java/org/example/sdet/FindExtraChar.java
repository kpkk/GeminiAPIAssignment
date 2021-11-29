package org.example.sdet;

import org.junit.Assert;
import org.junit.Test;

public class FindExtraChar {
    /* Given 2 Strings with length n and (n+1) respectively. The second String contains all the characters of first but with an extra char. Write the code to find the extra char.
    Example 1:
    Input:
    A1 = "Test"
    A2 = "Test$"
    Output: '$'
    Example 2: */

    @Test
    public void test(){
        String s="tesla";
        String s1="tesla1";
        char extra = findExtra(s, s1);
        Assert.assertEquals(extra,'1');
    }

    public char findExtra(String s1, String s2){
        char[] chars = s1.toCharArray();
        char[] chars1 = s2.toCharArray();
       int  p1=0,p2=0;
        while (p1<chars.length && p2<chars1.length){
            if(chars[p1]!=chars1[p2])
                return chars1[p2];
            p1++;
            p2++;

        }
        System.out.println(chars1[p2]);
        return chars1[p2];
    }
}
