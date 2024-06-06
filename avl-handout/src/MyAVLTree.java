import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Function;

/**
 * Implements an AVL Tree, a self-balancing binary search tree.
 * @author Samriddhi Matharu
 * 
 * 
 * @param <T> The type of elements in the tree, which must implement Comparable.
 */
public class MyAVLTree<T extends Comparable<T>> {
    /**
     * Each node in the linked list is represented by this class. It contains a forward and backward pointer
     * to allow traversal in both directions
     *  @param <T> The type of data stored in the node.
     */
    static class TreeNode<T> {
        T data;
        TreeNode<T> left, right;

        /**
         * Creates a new node and set its prev/next pointers.
         * @param t The data to store in the node.
         * @param left The left child of the node.
         * @param right The right child of the node.
         */
        public TreeNode(T t, TreeNode<T> left, TreeNode<T> right) {
            data = t;
            this.left = left;
            this.right = right;
        }

        public String toString() {
            return data + "(left->" + left + ", right->" + this.right + ")";
        }
    };

    /**
     * The root of the tree.
     */ 
    TreeNode<T> root;
    
    
    /**
     * Inserts the given element in the tree. Balances the tree after insertion
     * @param t
     * @return true if the element was inserted, false if it wasn't (ie. there was a duplicate element)
     */
    
    // Method to insert an element into the AVL tree
    public boolean insert(T t) {
    	 if (root == null) {
             root = new TreeNode<>(t, null, null);
             return true;
         }
         // Perform BST insertion
         boolean inserted = insertHelper(root, t);
         // Balance the tree after insertion
         root = balanceTree(root); 
         return inserted;
     }
    
    
    // Helper method for BST insertion
    private boolean insertHelper(TreeNode<T> node, T t) {
        int cmp = t.compareTo(node.data);
        if (cmp < 0) {
            if (node.left == null) {
                node.left = new TreeNode<>(t, null, null);
                return true;
            }
            return insertHelper(node.left, t);
        } else if (cmp > 0) {
            if (node.right == null) {
                node.right = new TreeNode<>(t, null, null);
                return true;
            }
            return insertHelper(node.right, t);
        } else {
            return false; // Element already exists
        }
    }

 
    /**
     * Deletes the given element from the tree. Balances the tree after deletion.
     * @param t  element to delete 
     * @return true if element existed (and was deleted), false otherwise.
     */
    public boolean delete(T t) {
    	if (root == null) {
            return false; // Tree is empty
        }
        // Perform BST deletion
        boolean deleted = deleteHelper(root, null, t);
        // Balance the tree after deletion
        balanceTree(root);
        return deleted;
    }

    // Helper method for BST deletion
    private boolean deleteHelper(TreeNode<T> node, TreeNode<T> parent, T t) {
        if (node == null) {
            return false; // Element not found
        }
        int cmp = t.compareTo(node.data);
        if (cmp < 0) {
            return deleteHelper(node.left, node, t);
        } else if (cmp > 0) {
            return deleteHelper(node.right, node, t);
        } else {
            // Node found, perform deletion
            if (node.left == null && node.right == null) {
                // Case 1: Node has no children
                if (parent == null) {
                    root = null; // Node is root
                } else if (parent.left == node) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            } else if (node.left != null && node.right != null) {
                // Case 2: Node has two children
                // Find the smallest node in the right subtree
                TreeNode<T> successor = node.right;
                TreeNode<T> successorParent = node;
                while (successor.left != null) {
                    successorParent = successor;
                    successor = successor.left;
                }
                // Replace the data of the node to be deleted with the data of the successor
                node.data = successor.data;
                // Delete the successor node
                deleteHelper(successor, successorParent, successor.data);
            } else {
                // Case 3: Node has one child
                TreeNode<T> child = (node.left != null) ? node.left : node.right;
                if (parent == null) {
                    root = child; // Node is root
                } else if (parent.left == node) {
                    parent.left = child;
                } else {
                    parent.right = child;
                }
            }
            return true;
        }
    }

    /**
     * Search for the value in the tree.
     * @param t The value to search for.
     * @return true if the value is found in the tree, false otherwise.
     */
    public boolean search(T t) {
    	 return searchHelper(root, t);
    }
    
    // Helper method for searching an element
    private boolean searchHelper(TreeNode<T> node, T t) {
        if (node == null) {     	
            return false; // Element not found
        }
        int cmp = t.compareTo(node.data);
        if (cmp < 0) {       	
            return searchHelper(node.left, t);
        } else if (cmp > 0) {     	
            return searchHelper(node.right, t);
        } else {     	
            return true; // Element found
        }
    }
    
 // Method to balance the AVL tree
    private TreeNode<T> balanceTree(TreeNode<T> node) {
        if (node == null) {
            return null;
        }

        int balanceFactor = getBalanceFactor(node);      
        if (balanceFactor > 1) {       	
            if (getBalanceFactor(node.left) >= 0) {          	
                return rightRotate(node);
            } else {           	  
                node.left = leftRotate(node.left);             
                return rightRotate(node);
            }
        } else if (balanceFactor < -1) {    	
            if (getBalanceFactor(node.right) <= 0) {           	 
                return leftRotate(node);
            } else {           	
                node.right = rightRotate(node.right);               
                return leftRotate(node);
            }
        }

        node.left = balanceTree(node.left);
        node.right = balanceTree(node.right);
        return node;
    }
    
    // Method to perform a right rotation
    private TreeNode<T> rightRotate(TreeNode<T> y) {
        TreeNode<T> x = y.left;
        TreeNode<T> T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        return x;
    }

    // Method to perform a left rotation
    private TreeNode<T> leftRotate(TreeNode<T> x) {
        TreeNode<T> y = x.right;
        TreeNode<T> T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;
      
        return y;
    }

    // Method to calculate the height of a node
    private int height(TreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    private int getBalanceFactor(TreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }
}
 
