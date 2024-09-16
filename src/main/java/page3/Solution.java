package page3;

import common.ListNode;
import common.TreeNode;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Solution {


    public ListNode sortList(ListNode head) {
        return mergeListNodeSort(head,null);
    }
    public ListNode mergeListNodeSort(ListNode head,ListNode tail){
        if (head==null){
            return head;
        }
        if (head.next==tail){
            head.next=null; // 这里需要注意 tail是开区间 需要断开
            return head;
        }
        ListNode mid = middleNode(head,tail);
        ListNode l = mergeListNodeSort(head,mid);
        ListNode r = mergeListNodeSort(mid,tail);
        return mergeTwoLists(l,r);
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode fakeHead = new ListNode(-1);
        ListNode cur=fakeHead;
        ListNode lcur = list1;
        ListNode rcur = list2;
        while (lcur!=null && rcur!=null){
            if (lcur.val<=rcur.val){
                cur.next=lcur;
                cur=lcur;
                lcur=lcur.next;
            }else {
                cur.next=rcur;
                cur=rcur;
                rcur=rcur.next;
            }

        }
        if (lcur==null){
            cur.next=rcur;
        }
        if (rcur==null){
            cur.next=lcur;
        }
        return fakeHead.next;

    }

    public ListNode middleNode(ListNode head,ListNode tail) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next!=tail){
            slow = slow.next;
            fast=fast.next;
            if (fast.next!=tail)
                fast=fast.next;
        }
        return slow;
    }


    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();

        dfsgenerateParenthesis(ans,new StringBuilder(),0,0,n);
        return ans;
    }

    public void dfsgenerateParenthesis(List<String> ans,StringBuilder sb, int left,int right,int total){
        if (sb.length()==2*total){
            ans.add(sb.toString());
            return;
        }
        if (left<total){
            sb.append('(');
            dfsgenerateParenthesis(ans,sb,left+1,right,total);
            sb.deleteCharAt(sb.length()-1);
        }
        if (right<left){
            sb.append(')');
            dfsgenerateParenthesis(ans,sb,left,right+1,total);
            sb.deleteCharAt(sb.length()-1);
        }

    }

    public List<List<Integer>> permute(int[] nums) {
        // [1,2,3,4]
        int[] visited = new int[nums.length];
        List<Integer> output =new ArrayList<>();
        for (int i=0;i<nums.length;i++)
            output.add(nums[i]); // 构建一个输入的链表方便操作
        List<List<Integer>> ans =  new ArrayList<>();
        permuteDFS(0,output,ans);
        return  ans;
    }

    public void permuteDFS(int n,List<Integer> output,List<List<Integer>> ans){
        // n代表填数字填到了第几个
        if (n==output.size()){
            ans.add(new ArrayList<>(output));

        }
        // 把所有 [n,length-1]的数字都往n的位置写一次
        for (int index = n;index<output.size()-1;index++){
            Collections.swap(output,n,index);
            permuteDFS(n+1,output,ans);
            Collections.swap(output,n,index);
        }
    }


    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 写完考察一下是否代码有逻辑冗余 （四面挂的原因）
        int p1 = m-1;
        int p2 = n-1;
        int index = nums1.length-1;
        while(p2>=0){
            if (p1>=0 &&nums1[p1]>=nums2[p2]){
                nums1[index]=nums1[p1];
                p1-=1;
                index-=1;
            }else {
                nums1[index]=nums2[p2];
                p2-=1;
                index-=1;
            }
        }
        System.out.println(Arrays.toString(nums1));
    }

    public boolean hasCycle(ListNode head) {
        if(head==null)
            return false;
        ListNode p1 = head;
        ListNode p2 = head.next;
        while (p1!=p2){
            if(p2==null)
                return false;
            p1 = p1.next;
            if (p2.next!= null)
                p2 = p2.next;
            p2=p2.next;
        }
        if (p1==null)
            return false;
        return true;
    }

    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (true){
            if (slow==null || fast == null)
                return null;
            slow = slow.next;
            fast = fast.next;
            if (fast!=null)
                fast=fast.next;
            else
                return null;
            if (fast == slow)
                break;
        }
        fast = head;
        while (fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }



    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return lowestCommonAncestorDFS(root,p,q);
    }

//    {
//        if (root == null || root == p || root == q) return root;
//        TreeNode left = lowestCommonAncestor(root.left, p, q);
//        TreeNode right = lowestCommonAncestor(root.right, p, q);
//        if (left == null) return right;
//        if (right == null) return left;
//        return root;
//    }
    public TreeNode lowestCommonAncestorDFS(TreeNode node,TreeNode p, TreeNode q){
        if(node==null)
            return null;
        if (node == p || node == q)
            return node;
        TreeNode l = lowestCommonAncestorDFS(node.left,p,q);
        TreeNode r = lowestCommonAncestorDFS(node.right,p,q);
        if (l == null)
            return r;
        if (r == null)
            return l;
        return node;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<List<Integer>>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        boolean turn = true;
        while (!queue.isEmpty()){
            Deque<Integer> levellist = new ArrayDeque<>();
            for (int i=0;i<queue.size();i++){
                TreeNode cur = queue.poll();
                if (turn){
                    levellist.addLast(cur.val);
                }else {
                    levellist.addFirst(cur.val);
                }
                if (cur.left!=null)
                    queue.add(cur.left);
                if (cur.right!=null)
                    queue.add(cur.right);
            }
            turn = !turn;
            ans.add(new ArrayList<>(levellist));
        }
        return ans;
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode nHead = new ListNode(-1);
        nHead.next=head;
        ListNode pre = nHead;
        ListNode cur = head;
        ListNode next;
        for (int i=1;i<left;i++){
            cur = cur.next;
            pre=pre.next;
        }
        next = cur.next;
        for (int i=0;i<right-left;i++){
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
            next = cur.next;
        }
        return nHead.next;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        System.out.println(solution.zigzagLevelOrder(root));
    }
}
