package org.example.sdet;

import org.apache.commons.lang3.builder.ToStringExclude;
import org.testng.annotations.Test;

import java.util.Arrays;

public class IntersectionOfArrays {

//    3) Given two arrays,find the intersection of 2 arrays.
//            Input: nums1 = [11,2,12,1], nums2 = [2,12]
//    Output: [2,12]
//    Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
//    Output: [9,4]

    @Test
    public void test(){
        int []n={11,2,12,1};
        int [] m={2,12};
        int[] interSection = findInterSection(n, m);
        System.out.println(Arrays.toString(interSection));
        Arrays.equals(interSection,new int[]{2,12});
    }



    public int[] findInterSection(int[] nums1, int[] nums2){

        int [] nums3=new int[nums2.length];
        int index=0;
        for (int i=0;i<nums1.length;i++){
            for (int j=0;j<nums2.length;j++){
                if(nums1[i]==nums2[j]){
                    nums3[index++]=nums2[j];
                }
            }
        }
return nums3;
    }
}
