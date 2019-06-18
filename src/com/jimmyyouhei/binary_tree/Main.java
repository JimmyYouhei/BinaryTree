package com.jimmyyouhei.binary_tree;

public class Main {

    public static void main(String[] args) {


        BinarySearchTree test = new BinarySearchTree();

        test.add(52);
        test.add(33);
        test.add(65);
        test.add(25);
        test.add(39);
        test.add(60);
        test.add(78);
        test.add(12);
        test.add(27);
        test.add(34);
        test.add(48);
        test.add(72);
        test.add(90);
        test.add(70);

        test.printInOrder(test.root);

        System.out.println("test");
        System.out.println("test2");



    }
}
