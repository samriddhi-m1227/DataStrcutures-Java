import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.function.Function;

public class Driver extends DriverBase {

   private static void addToTree(MyBinarySearchTree<Integer> tree, int ... values) {
      for (int value : values) {
         tree.insert(value);
      }
   }

   private static void checkOrder(String testCase, List<Integer> list, int ...values) {
      int index = 0;
      for (int val : list) {
         if (val != values[index]) {
            testOutput.put(testCase, "Expected " + values[index] + ", Got " + val);  
            return;
         }
         index++;
      }
      testOutput.put(testCase, null);
   }

   private static void checkSearch(String testCase, MyBinarySearchTree<Integer> tree, int ...values) {
      for (int value : values) {
         if (!tree.search(value)) {
            testOutput.put(testCase, "Could not find " + value);
            return;
         }
      }

      if (tree.search(100)) {
         testOutput.put(testCase, "search for 100 returned true.");
      } else {
         testOutput.put(testCase, null);
      }
   }

   public static void main(String[] args) {
      List<Integer> list = new ArrayList<>();
      Function<Integer, Void> addToList = (a) -> {
         list.add(a);
         return null;
      };

      MyBinarySearchTree<Integer> tree = new MyBinarySearchTree<>();
      // Insert elements in order. Check inorder, preorder, postorder, search.
      addToTree(tree, 1, 2, 3, 4, 5);
      list.clear();
      tree.inorder(addToList);
      checkOrder("Adding in order, inorder traversal", list, 1, 2, 3, 4, 5);
      list.clear();
      tree.preorder(addToList);
      checkOrder("Adding in order, preorder traversal", list, 1, 2, 3, 4, 5);
      list.clear();
      tree.postorder(addToList);
      checkOrder("Adding in order, postorder traversal", list, 5, 4, 3, 2, 1);
      checkSearch("Adding in order, search", tree, 1, 2, 3, 4, 5);

      // Insert element in reverse order. Check inorder, preorder, post order, search
      tree = new MyBinarySearchTree<>();
      addToTree(tree, 5, 4, 3, 2, 1);
      list.clear();
      tree.inorder(addToList);
      checkOrder("Adding reverse order, inorder traversal", list, 1, 2, 3, 4, 5);
      list.clear();
      tree.preorder(addToList);
      checkOrder("Adding reverse order, preorder traversal", list, 5, 4, 3, 2, 1);
      list.clear();
      tree.postorder(addToList);
      checkOrder("Adding reverse order, postorder traversal", list, 1, 2, 3, 4, 5);
      checkSearch("Adding reverse order, search", tree, 1, 2, 3, 4, 5);


      // Insert elements in random order. Check inorder, preorder, postorder, search
      tree = new MyBinarySearchTree<>();
      tree.insert(1);
      tree.insert(4);
      tree.insert(3);
      tree.insert(2);
      tree.insert(5);

      list.clear();
      tree.inorder(addToList);
      checkOrder("Adding random order, inorder traversal", list, 1, 2, 3, 4, 5);
      list.clear();
      tree.preorder(addToList);
      checkOrder("Adding random order, preorder traversal", list, 1, 4, 3, 2, 5);
      list.clear();
      tree.postorder(addToList);
      checkOrder("Adding random order, postorder traversal", list, 2, 3, 5, 4, 1);
      checkSearch("Adding random order, search", tree, 1, 2, 3, 4, 5);

      // Delete nodes and check.
      tree.delete(3);
      tree.delete(2);
      if (tree.search(2) || tree.search(3) || !tree.search(1) || !tree.search(4) || !tree.search(5)) {
            testOutput.put("Searching after deleting", "Did not find right values after deleting");
      }

      printJsonOutput();
   }
}
