package com.jimmyyouhei.binary_tree;

public class SortedBinaryTree {

    Node root;

    public void add(int value){
        Node nodeToBeAdded = new Node(value);
        if (root == null){
            root = nodeToBeAdded;
        } else {

            Node pointer = root;
            // loop
            while(pointer != null){
                // check if value more or less
                // if less travel left
                if (nodeToBeAdded.value < pointer.value ){
                    if (pointer.left == null){
                        pointer.left = nodeToBeAdded;
                        nodeToBeAdded.parent = pointer;
                        break;
                    } else {
                        pointer = pointer.left;
                    }
                } else {
                    if (pointer.right == null){
                        pointer.right = nodeToBeAdded;
                        nodeToBeAdded.parent = pointer;
                        break;
                    } else{
                        pointer = pointer.right;
                    }
                }
            }
        }
    }

    public boolean isContained(int value){

        if (root == null){
            return false;
        } else {

            Node pointer = root;

            while (pointer != null){

                if (pointer.value == value){
                    return true;
                } else if (value < pointer.value){
                    pointer = pointer.left;
                } else {
                    pointer = pointer.right;
                }
            }
        }
        return false;
    }

    public void remove(int value){

        if (root == null){
            System.out.println("no such value");
        } else {

            Node pointer = root;

            while (pointer != null){
                if (pointer.value == value){
                    if (pointer.left == null && pointer.right == null){

                        deleteTheNode(pointer , value);
                        return;
                    } else if (pointer.right == null){
                        deleteTheNodeAndReplaceWithNewNode(pointer , value , pointer.left);
                        return;
                    } else if (pointer.right.left == null){
                        pointer.right.left = pointer.left;
                        pointer.left.parent = pointer.right.left;
                        deleteTheNodeAndReplaceWithNewNode(pointer , value , pointer.right);
                        return;
                    } else if (pointer.right.left != null){
                        Node nodeToReplace = pointer.right.left;

                        pointer.right.left.left = pointer.left;
                        pointer.right.left.right = pointer.right;

                        deleteTheNodeAndReplaceWithNewNode(pointer , value , nodeToReplace);

                        nodeToReplace.right.left = null;
                        nodeToReplace.right.parent = nodeToReplace;

                        return;
                    }
                } else if (value < pointer.value){
                    pointer = pointer.left;
                } else {
                    pointer = pointer.right;
                }
            }

        }

        System.out.println("no such value");

    }


    private class Node {

        int value;
        Node left;
        Node right;
        Node parent;

        Node(int value) {
            this.value = value;
        }



    }


    private void deleteTheNode (Node node , int value){
        if (value < node.parent.value){
            node.parent.left = null;
            node.parent = null;
        } else {
            node.parent.right = null;
            node.parent = null;
        }
    }

    private void deleteTheNodeAndReplaceWithNewNode (Node nodeToBeDeleted , int value , Node newNodeToReplace) {

        if (value < nodeToBeDeleted.parent.value){
            nodeToBeDeleted.parent.left = newNodeToReplace;
            newNodeToReplace.parent = nodeToBeDeleted.parent;
            nodeToBeDeleted.parent = null;
            nodeToBeDeleted.right = null;
            nodeToBeDeleted.left = null;
        } else {
            nodeToBeDeleted.parent.right = newNodeToReplace;
            newNodeToReplace.parent = nodeToBeDeleted.parent;
            nodeToBeDeleted.parent = null;
            nodeToBeDeleted.right = null;
            nodeToBeDeleted.left = null;
        }


    }

    private void exchangeTwoNode (Node original , Node toReplaceOriginal){


    }


}
