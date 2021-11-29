package org.example.sdet;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class Pickingtickets {

    @Test
    public void test(){
        int [] arr={8,5,4,8,4};
        pick(arr);
      //  System.out.println();

    }


    public void pick(int[] arr){

        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));

    }
}
