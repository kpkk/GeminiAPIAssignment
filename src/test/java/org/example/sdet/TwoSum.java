package org.example.sdet;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class TwoSum {
    /*
  Question:-
    Given an array of integers say nums and an integer say target, return indices of the two numbers such that they add up to target.
    You may assume that each input would have exactly one solution, and you may not use the same element twice. You can return the answer in any order.
            Example:
    Input: nums = [2,7,11,15], target = 9
    Output: [0,1]
    Explanation:
    nums[0] + nums[1] == 9, so we
    return [0, 1]. */

    @Test
    public void test(){
        int[] nums={2,7,11,15};
        int k=9;
        int[] ints = twoSum(nums, k);
        Assert.assertTrue(Arrays.equals(ints,new int[]{0,1}));
    }

    public int[] twoSum(int nums[], int target){
        int[] result=new int[2];
        for (int i=0;i<nums.length;i++){
            int diff=target-nums[i];
            for (int j=i+1;j<nums.length;j++){
                if(nums[j]==diff){
                    result[0]=i;
                    result[1]=j;
                }
            }
        }
        System.out.println(Arrays.toString(result));
        return result;
    }
}
