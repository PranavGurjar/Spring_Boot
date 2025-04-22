package com.debug;

public class Test {
    public static void main(String[] args) {
        System.out.println("going to debug our simple java application");
        int []myarr = {2, 5, 7, 81};
        int sum = getSum(myarr);
        System.out.println("Sum = "+sum);
    }

    public static int getSum(int arr[]){
        int s = 0;
        for (int i = 0; i < arr.length;i++){
            s = s + arr[i];
        }
        s = s + 3;
        return s;
    }
}
