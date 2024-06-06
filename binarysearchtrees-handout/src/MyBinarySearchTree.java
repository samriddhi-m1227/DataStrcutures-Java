import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Function;

/**
 * Assignment for the Binary Search Tree module.
 * @param <T> the type of elements stored in the tree, must be comparable.
 * @author Samriddhi Matharu
 */
public class MyBinarySearchTree<T extends Comparable<T>> {
    /**
     * Each node in the linked list is represented by this class. It contains a forward and backward pointer
     * to allow traversal in both directions
     * @param <T> the type of data stored in the node.
     */
    static class TreeNode<T> {
        T data;
        TreeNode<T> left, right;

        /**
         * Creates a new node and set its prev/next pointers.
         * @param t  the data to be stored in the node.
         * @param left  the left child of the node.
         * @param right  the right child of the node.
         */
        public TreeNode(T t, TreeNode<T> left, TreeNode<T> right) {
            data = t;
            this.left = left;
            this.right = right;
        }
        
        /**
         * Returns a string representation of the node.
         * @return a string representation of the node.
         */
        public String toString() {
            return data + "(left->" + left + ", right->" + this.right + ")";
        }
    };

    /**
     * The root of the tree.
     */ 
    TreeNode<T> root;

    /**
     * Inserts the given element in the tree
     * @param t
     * @return true if the element was inserted, false if it wasn't (ie. there was a duplicate element)
     */
    public boolean insert(T t) {
    	 if (root == null) {
             root = new TreeNode<>(t, null, null);
             return true;
         }
         return insertHelper(root, t);
     }
    
    // Helper method for insertion
    private boolean insertHelper(TreeNode<T> node, T t) {
        if (t.compareTo(node.data) < 0) {
            if (node.left == null) {
                node.left = new TreeNode<>(t, null, null);
                return true;
            } else {
                return insertHelper(node.left, t);
            }
        } else if (t.compareTo(node.data) > 0) {
            if (node.right == null) {
                node.right = new TreeNode<>(t, null, null);
                return true;
            } else {
                return insertHelper(node.right, t);
            }
        } else {
            return false; // Duplicate element
        }
    }

    /**
     * Deletes the given element from the tree.
     * @param t
     * @return true if element existed (and was deleted), false otherwise.
     */
    public boolean delete(T t) {
    	 root = deleteNode(root, t);
         return true;
     }
    
    // Helper method for deletion
    private TreeNode<T> deleteNode(TreeNode<T> root, T key) {
        if (root == null) return null;
        if (key.compareTo(root.data) < 0) {
            root.left = deleteNode(root.left, key);
        } else if (key.compareTo(root.data) > 0) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;
            root.data = minValue(root.right);
            root.right = deleteNode(root.right, root.data);
        }
        return root;
    }
    
    // Helper method to find minimum value in a subtree
    private T minValue(TreeNode<T> node) {
        T minv = node.data;
        while (node.left != null) {
            minv = node.left.data;
            node = node.left;
        }
        return minv;
    }

    // Inorder traversal, postorder traversal, preorder traversal.
    /**
     * Inorder traversal of the tree.
     * @param fn The function to call when traversing a node.
     */
    public void inorder(Function<T, Void> fn) {
    	 inorderTraversal(root, fn);
    }
    
    // Helper method for inorder traversal
    private void inorderTraversal(TreeNode<T> node, Function<T, Void> fn) {
        if (node != null) {
            inorderTraversal(node.left, fn);
            fn.apply(node.data);
            inorderTraversal(node.right, fn);
        }
    }

    /**
     * Preorder traversal of the tree.
     * @param fn The function to call when traversing a node.
     */
    public void preorder(Function<T, Void> fn) {
    	  preorderTraversal(root, fn);
    }
    
    // Helper method for preorder traversal
    private void preorderTraversal(TreeNode<T> node, Function<T, Void> fn) {
        if (node != null) {
            fn.apply(node.data);
            preorderTraversal(node.left, fn);
            preorderTraversal(node.right, fn);
        }
    }

    /**
     * Postorder traversal of the tree. 
     * @param fn The function to call when traversing a node.
     */
    public void postorder(Function<T, Void> fn) {
    	 postorderTraversal(root, fn);
    }
    
    // Helper method for postorder traversal
    private void postorderTraversal(TreeNode<T> node, Function<T, Void> fn) {
        if (node != null) {
            postorderTraversal(node.left, fn);
            postorderTraversal(node.right, fn);
            fn.apply(node.data);
        }
    }


    /**
     * Search for the value in the tree.
     * @param t The value to search for.
     * @return true if the value is found in the tree, false otherwise.
     */
    public boolean search(T t) {
    	  return searchNode(root, t);
    }
    
    // Helper method for search
    private boolean searchNode(TreeNode<T> node, T t) {
        if (node == null) return false;
        if (node.data.equals(t)) return true;
        if (t.compareTo(node.data) < 0) return searchNode(node.left, t);
        else return searchNode(node.right, t);
    }
}
