package com.wwy.recursion;

public class RecursionTest {
    public static void main(String[] args) {
//        print(4);
factirial(4);
    }

    public static void print(int n) {
        if (n > 2) {
            print(n - 1);
        } else {
            System.out.println("*-*");
        }
        System.out.print(n);
    }

    public static int factirial(int n) {
        if (n == 1) {
            return 1;
        } else {
            int res = factirial(n - 1) * n;
            System.out.println(res);
            return res;
        }
    }
}
