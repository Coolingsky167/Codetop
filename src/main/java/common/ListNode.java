package common;

public class ListNode {
      public int val;
      public int key;
      public ListNode next;
      public ListNode pre;
      ListNode() {}
      public ListNode(int key,int val) { this.key=key;this.val = val; }
      public ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }

      @Override
      public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", key=" + key + "}";
      }
}