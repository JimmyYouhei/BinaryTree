package com.jimmyyouhei.binary_tree;

public class SortedBinaryTree {

    // root node
    Node root;

    // add method
    public void add(int value){
        // init a new node based on the value given
        Node nodeToBeAdded = new Node(value);

        // if there is no node , the new node will become
        if (root == null){
            root = nodeToBeAdded;
        } else {
            // make a pointer to point at the root
            Node pointer = root;
            // loop from the pointer until the end of the tree
            while(pointer != null){
                // check if value more or less
                // if less travel left and if the left is null , add the node to the left
                if (nodeToBeAdded.value < pointer.value ){
                    if (pointer.left == null){
                        pointer.left = nodeToBeAdded;
                        nodeToBeAdded.parent = pointer;
                        break;
                    } else {
                        pointer = pointer.left;
                    }
                    // same as above for the right when the value is more
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

    //find if the tree contain the value
    public boolean isContained(int value){

        // if the tree is empty
        if (root == null){
            return false;
        // if not empty
        } else {
            // make a pointer point at the root first
            Node pointer = root;

            // travel left or right based on the value until the value is found
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
        // if not return false
        return false;
    }

    // remove method
    public void remove(int value){

        // if tree is empty
        if (root == null){
            System.out.println("no such value");
        } else {

            // make a pointer point at root
            Node pointer = root;

            // loop to find and delete the node
            while (pointer != null){
                // if the value is found
                if (pointer.value == value){
                    //case 1 leaf node: simply delete it
                    if (pointer.left == null && pointer.right == null){

                        deleteTheNode(pointer , value);
                        return;
                    // case 2 : if the node have no right child , replace the node with its left child
                    } else if (pointer.right == null){
                        deleteTheNodeAndReplaceWithNewNode(pointer , value , pointer.left);
                        return;
                    // case 3: if the right child 's   left child is null . replace the node with its right child
                    } else if (pointer.right.left == null){
                        pointer.right.left = pointer.left;
                        pointer.left.parent = pointer.right.left;
                        deleteTheNodeAndReplaceWithNewNode(pointer , value , pointer.right);
                        return;

                    // case 4 if the right child 's left child is not null , replace its with the right child 's left child
                    } else if (pointer.right.left != null){
                        Node nodeToReplace = pointer.right.left;

                        pointer.right.left.left = pointer.left;
                        pointer.right.left.right = pointer.right;

                        deleteTheNodeAndReplaceWithNewNode(pointer , value , nodeToReplace);

                        nodeToReplace.right.left = null;
                        nodeToReplace.right.parent = nodeToReplace;

                        return;
                    }

                // travel right or left to find the pointer
                } else if (value < pointer.value){
                    pointer = pointer.left;
                } else {
                    pointer = pointer.right;
                }
            }

        }

        // if no value is found print out
        System.out.println("no such value");

    }

    // the traversals method that use Recursion
    // I referenced the code from here https://www.programiz.com/dsa/tree-traversal
    public void printInOrder(Node root){

        if (root!=null){
            printInOrder(root.left);
            System.out.println(root.value);
            printInOrder(root.right);
        }


    }

    // private node class for the tree
    private class Node {

        // I also make it a doubly linked . Could be make singly linked but for my convenience
        int value;
        Node left;
        Node right;
        Node parent;

        // Quick constructor
        Node(int value) {
            this.value = value;
        }

    }

    // method to quickly delete the node (case 1: leaf node)
    private void deleteTheNode (Node node , int value){
        // determine the node is right or left child of the parent then make all the connecttion to null
        if (value < node.parent.value){
            node.parent.left = null;
            node.parent = null;
        } else {
            node.parent.right = null;
            node.parent = null;
        }
    }

    // method for the other 3 case when we need to rplace the node with other node
    private void deleteTheNodeAndReplaceWithNewNode (Node nodeToBeDeleted , int value , Node newNodeToReplace) {
        /*
        determine whether node to be deleted is left or right child of its parent
        then replace all its connection with the new node and make all its connection to  be null
        For the purpose of garbage collection to avoid memory leak
         */
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

}
