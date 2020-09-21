/*
  Author >>        Tyler Zoucha >> tzoucha@unomaha.edu
  Program Title >> Binary Search Tree (BST)
  Class >>         CSCI3320-820, Fall 2020
  Assignment >>    CSCI-3320-ZC-F20-PA2

  Objective >>  BinaryNode object to traverse through Binary Search Tree
 */

/**
 * Init BinaryNode object to traverse through Binary Search Tree
 * @param <AnyType>
 * @author Tyler Zoucha >> tzoucha@unomaha.edu
 */
public class BinaryNode<AnyType> {

    // Constructor to init BinaryNode<AnyType>
    BinaryNode(AnyType element, BinaryNode<AnyType> left, BinaryNode<AnyType> right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }
    AnyType element;                                // The data in the node
    BinaryNode<AnyType> left;                       // Left child
    BinaryNode<AnyType> right;                      // Right child
}
