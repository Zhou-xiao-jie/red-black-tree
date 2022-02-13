package com.atxiaojie.redblacktree.BinarySearch;

/**
 * @ClassName: BinarySearchTest
 * @Description: 二叉搜索树二分查找Test
 * @author: zhouxiaojie
 * @date: 2021/11/8 18:49
 * @Version: V1.0.0
 */
public class BinarySearchTest {

    public static void main(String[] args) {
        int[] arr = {1,3,5,7,9};
        int i = binarySearch(arr, 7);
        System.out.println(i);
        System.out.println(arr[i]);
    }

    /**
     * @MethodsName: binarySearch
     * @Description arr有序数组，查找的数据，返回下标
     * @Author zhouxiaojie
     * @Date 19:04 2021/11/8
     * @Param [arr, data]
     * @return int
     **/
    public static int binarySearch(int[] arr, int data){
        int low = 0;
        int height = arr.length - 1;
        while (low <= height){
            int mid = low + (height - low) / 2;
            if(arr[mid] < data){
                System.out.println("左边舍弃");
                low = mid + 1;
            }else if(arr[mid] == data){
                return mid;
            }else {
                System.out.println("右边舍弃");
                height = mid - 1;
            }
        }
        return -1;
    }
}
