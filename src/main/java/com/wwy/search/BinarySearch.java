package com.wwy.search;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
//        List<Integer> list = binarySearchList(arr, 0, arr.length - 1, 8);
//        System.out.println(list);
        int index = search(arr, 5);
        System.out.println(index);
    }

    /**
     * 二分查找入口
     *
     * @param arr 要查找的数组
     */
    public static int search(int[] arr, int findVal) {
        int left = 0;
        int right = arr.length - 1;
        int index = binarySearch(arr, left, right, findVal);
        return index;
    }

    /**
     * @param arr     要查找的数组
     * @param left    左边的下标
     * @param right   右边的下标
     * @param findVal 要查找的数
     * @return 查找数的下标
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        //如果右边的下标大于左边的下标，结束递归
        if (left > right) {
            return -1;
        }
        //中间索引
        int mid = (left + right) / 2;
        //如果查找数大于中间数
        if (findVal > arr[mid]) {
            //开始向右递归查找
            return binarySearch(arr, mid + 1, right, findVal);
            //如果查找数小于中间数
        } else if (findVal < arr[mid]) {
            //开始向左递归查找
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }

    /**
     * 查数据的同时查询是否有多个值，一起返回到集合
     *
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return
     */
    public static List<Integer> binarySearchList(int[] arr, int left, int right, int findVal) {
        //如果右边的下标大于左边的下标，结束递归
        if (left > right) {
            return new ArrayList<>();
        }
        //中间索引
        int mid = (left + right) / 2;
        if (findVal > arr[mid]) {
            return binarySearchList(arr, mid + 1, right, findVal);
        } else if (findVal < arr[mid]) {
            return binarySearchList(arr, left, mid - 1, findVal);
        } else {
            List<Integer> list = new ArrayList<>();
            int leftIndex = mid - 1;
            while (leftIndex > 0 && arr[leftIndex] == findVal) {
                list.add(leftIndex);
                leftIndex--;
            }
            int rightIndex = mid + 1;
            while (rightIndex < arr.length - 1 && arr[rightIndex] == findVal) {
                list.add(rightIndex);
                rightIndex++;
            }
            return list;
        }
    }

    /**
     * 循环实现
     * @param nums
     * @param size
     * @param target
     * @return
     */
    public  static int search(int nums[], int size, int target) //nums是数组，size是数组的大小，target是需要查找的值
    {
        int left = 0;
        int right = size - 1;    // 定义了target在左闭右闭的区间内，[left, right]
        while (left <= right) {    //当left == right时，区间[left, right]仍然有效
            int middle = left + ((right - left) / 2);//等同于 (left + right) / 2，防止溢出
            if (nums[middle] > target) {
                right = middle - 1;    //target在左区间，所以[left, middle - 1]
            } else if (nums[middle] < target) {
                left = middle + 1;    //target在右区间，所以[middle + 1, right]
            } else {    //既不在左边，也不在右边，那就是找到答案了
                return middle;
            }
        }
        //没有找到目标值
        return -1;
    }

}
