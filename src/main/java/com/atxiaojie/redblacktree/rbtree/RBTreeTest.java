package com.atxiaojie.redblacktree.rbtree;

import java.util.Scanner;

/**
 * @ClassName: RBTreeTest
 * @Description: 红黑树测试类
 * @author: zhouxiaojie
 * @date: 2021/11/15 19:49
 * @Version: V1.0.0
 */
public class RBTreeTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RBTree<String, Object> rbt = new RBTree<String, Object>();
        while (true){
            System.out.println("请输入Key:");
            String key = scanner.next();
            System.out.println();
            rbt.insert(key, null);

            TreeOperation.show(rbt.getRoot());
        }
    }
}
