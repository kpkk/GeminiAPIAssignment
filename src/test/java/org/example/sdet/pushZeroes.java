package org.example.sdet;

import org.junit.Test;

import java.util.Arrays;

public class pushZeroes {
   /* 1) Given an array of random numbers, Push all the zero’s of a given array to the end of the array. The order of all other elements should be same.
            Example:
    Input :  arr = [1, 2, 0, 4, 3, 0, 5, 0]
    Output : arr = [1, 2, 4, 3, 5, 0, 0]
    Input : arr = [1, 2, 0, 0, 0, 3, 6]
    Output : arr = [1, 2, 3, 6, 0, 0, 0]*/

    @Test
    public void test(){
        int[] nums={1, 2, 0, 4, 3, 0, 5, 0};
        int[] result={1,2, 4, 3, 5, 0, 0,0};
        int[] ints = pushZeroes(nums);
        Arrays.equals(result,ints);
    }

    public int[] pushZeroes(int nums[]){
        int index=0;
        for (int i=0;i<nums.length;i++){
            if(nums[i]!=0){
                nums[index++]=nums[i];
            }
        }
        while (index<nums.length){
            nums[index++]=0;
        }
        System.out.println(Arrays.toString(nums));
        return nums;

    }
}
