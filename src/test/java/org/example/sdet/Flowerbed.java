package org.example.sdet;

import org.junit.Assert;
import org.junit.Test;

public class Flowerbed {

    /*
    You have a long flowerbed in which some of the plots are planted, and some are not. However, flowers cannot be planted in adjacent plots.
    Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not empty, and an integer n, return if n new flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule.
Example 1:
Input: flowerbed = [1,0,0,0,1], n = 1
Output: true
Example 2:
Input: flowerbed = [1,0,0,0,1], n = 2
Output: false */
    @Test
    public void test(){
        int[] flowerBed={1,0,0,0,1};
        int n=1;
        boolean result=flowerBed(flowerBed,n);
        Assert.assertEquals(result,true);
    }

    @Test
    public void test1(){
        int[] flowerBed={1,0,0,0,1};
        int n=2;
        boolean result=flowerBed(flowerBed,n);
        Assert.assertEquals(result,false);
    }

    public boolean flowerBed(int[] nums, int n){
        int prev=0, next=0;
        for (int i=0;i<nums.length;i++){
            if(i>0){
                prev=nums[i-1];
            }
            if(i<nums.length-1){
                next=nums[i+1];
            }
            if(prev==0&& next==0 & nums[i]==0){
                nums[i]=1;
                n--;
            }
        }
        if(n==0) return true;

        return false;
    }

}
