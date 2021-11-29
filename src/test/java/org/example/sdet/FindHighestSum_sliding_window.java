package org.example.sdet;

import org.junit.Test;

public class FindHighestSum_sliding_window {
    @Test
    public void test(){
        int []nums={1,5,2,3,7,1};
        int window=3;
    }

    public int highestSumFromSubArray(int[] nums, int k){
        int sum=0;
        for (int i=0;i<nums.length-k+1;i++){
            sum+=nums[i];
        }
    }
}
