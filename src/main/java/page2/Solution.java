package page2;

import common.ListNode;
import common.TreeNode;

import java.util.*;

public class Solution {

    public List<Integer> spiralOrder(int[][] matrix) {
        int[][] movement = new int[][]{
                {0,1},
                {1,0},
                {0,-1},
                {-1,0}
        };
        int movementIndex = 0;
        List<Integer> ans = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        int x = 0;
        int y = -1;
        while (ans.size()!=m*n){
            int nextX = x + movement[movementIndex][0];
            int nextY = y + movement[movementIndex][1];
            if (nextX<0 || nextX>=m || nextY<0 || nextY>=n){
                movementIndex = (movementIndex+1)%4;
                continue;
            }
            if (matrix[nextX][nextY]==101){
                movementIndex = (movementIndex+1)%4;
                continue;
            }
            ans.add(matrix[nextX][nextY]);
            x = nextX;
            y = nextY;
        }
        return ans;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val-o2.val;
            }
        });
        ListNode ans = new ListNode(-1);
        ListNode cur =ans;
        for (int i=0;i<lists.length;i++){
            if (lists[i]!=null)
                queue.offer(lists[i]);
        }

        while (!queue.isEmpty()){
            ListNode first = queue.poll();
            cur.next = first;
            cur = cur.next;
            if (first.next!=null)
                queue.offer(first.next);
        }
        return ans.next;
    }

    public int lengthOfLIS(int[] nums) {
        int[] f = new int[nums.length];
        f[0]=1;
        int ans = 1;
        for (int i=1;i<nums.length;i++){
            f[i] = 1;
            for (int j=0;j<i;j++){
                if (nums[j]<nums[i]){
                    f[i]=Math.max(f[j]+1,f[i]);
                }
            }
            if (ans<f[i])
                ans=f[i];
        }
        return ans;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA;
        ListNode p2 = headB;
        int m =0;
        int n =0;
        int counter = 0;
        while (true){
            counter+=1;
            if (p1==p2)
                return p1;
            if (counter==m+n){
                return null;
            }
            if (p1!=null){
                p1=p1.next;
            }else {
                p1=headB;
                m = counter;
            }
            if (p2!=null){
                p2=p2.next;
            }else {
                p2=headB;
                n = counter;
            }

        }
    }

    public String addStrings(String num1, String num2) {
        // "11" "123"
        int n = num1.length()-1;
        int m = num2.length()-1;
        int len = Math.max(m,n);
        int c= 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0;i<=len;i++){
            int n1;
            int n2;
            if (n-i>=0){
                n1 = num1.charAt(n-i)-'0';
            }else {
                n1 =0;
            }
            if (m-i>=0){
                n2 = num2.charAt(m-i)-'0';
            }else {
                n2 = 0;
            }
            int sum = n1+n2+c;
            if (sum>=10){
                sum = sum-10;
                c=1;
            }else {
                c=0;
            }
            stringBuilder.insert(0,sum);
        }
        if (c!=0)
            stringBuilder.insert(0,c);
//        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }


    public int trap(int[] height) {
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];
        leftMax[0] = height[0];
        for (int i = 1; i <height.length; i++){
            leftMax[i] = Math.max(leftMax[i-1],height[i]);
        }
        rightMax[height.length-1] = height[height.length-1];
        for (int i = height.length-2;i>=0;i--){
            rightMax[i]=Math.max(rightMax[i+1],height[i]);
        }
        int ans =0 ;
        for (int i=0;i<height.length;i++){
            int cap = Math.min(leftMax[i],rightMax[i])-height[i];
            if (cap>0)
                ans+=cap;
        }
        return ans;
    }

    public void reorderList(ListNode head) {
        // 找中点
        ListNode slow = head;
        ListNode fast = head;
        while (fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next;
            if (fast!=null)
                fast=fast.next;
        }
        ListNode mid = slow;
            // 切断链表
        ListNode head2 = mid.next;
        mid.next = null;
        // 反转链表
        ListNode cur = head2;
        ListNode pre = null;
        while (cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            if (next!=null)
                cur = next;
            else
                break;
        }
        // 合并链表 cur 和 head
        ListNode p1 = head;
        ListNode p2 = cur;
        while (p1!=null && p2 != null){
            ListNode cur2 = p2;
            p2 = p2.next;
            ListNode p1next = p1.next;
            p1.next = cur2;
            cur2.next = p1next;
            p1 = p1next;
        }
        return;
    }

    public boolean isMeragable(int x1,int y1,int x2){
        if (x2>=x1 && x2<=y1){
            return true;
        }
        return false;
    }
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        int r =0;
        int l =0;
        int curL = intervals[0][0];
        int curR = intervals[0][1];
        List<int[]> ans =new ArrayList<>();
        while (l<intervals.length){
            curL=intervals[l][0];
            curR=intervals[l][1];
            while (r<intervals.length && isMeragable(curL,curR,intervals[r][0])){
                curR = Math.max(curR,intervals[r][1]);
                r++;
            }
            ans.add(new int[]{curL,curR});
            l=r;
        }
        int[][] ansArray = new int[ans.size()][2];
        for (int i=0;i<ans.size();i++){
            ansArray[i][0] = ans.get(i)[0];
            ansArray[i][1] = ans.get(i)[1];
        }
        return ansArray;

    }

    int maxPathSumAns = 0;
    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxPathSumAns;
    }

    public int maxGain(TreeNode node){
        if (node==null)
            return 0;
        int leftSum = Math.max(maxGain(node.left),0);
        int rightSum = Math.max(maxGain(node.right),0);
        maxPathSumAns = Math.max(maxPathSumAns,leftSum+rightSum+node.val);
        return Math.max(leftSum,rightSum) + node.val;
    }

    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        if (n*m==0)
            return n+m;

        int[][] dp = new int[n+1][m+1];

        for (int i=0;i<n+1;i++){
            dp[i][0]=i;
        }
        for (int j = 0; j < m + 1; j++) {
            dp[0][j] = j;
        }
        for (int i=1;i<n+1;i++){
            for (int j = 1;j<m+1;j++){
                int ans = dp[i-1][j-1];
                if (word1.charAt(i)!=word2.charAt(j)){
                    ans = ans+1;
                }
                int ansTmp = Math.min(dp[i-1][j],dp[i][j-1])+1;
                dp[i][j] = Math.min(ans,ansTmp);
            }
        }
        return dp[n][m];
    }

    // "25525511135"
    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();
        if (s.length()<4 || s.length()>12)
            return ans;
        restoreIpAddressesDFS(s,new ArrayList<>(),ans);
        System.out.println(ans);
        return ans;
    }

    //"25525511135"
    public void restoreIpAddressesDFS(String s,List<String> stringBuilder,List<String> ans) {
        if (stringBuilder.size()==4 && !s.isEmpty())
            return;

        if (stringBuilder.size()==4){
//            System.out.println(stringBuilder);
            String tmp ="";
            for (String p : stringBuilder){
                tmp+=p+".";
            }
            ans.add(tmp.substring(0,tmp.length()-1));
            return;
        }
        for (int i=1;i<Math.min(4,s.length()+1);i++){
            String ip = s.substring(0,i);
            if (checkLegal(ip)){
                stringBuilder.add(ip);
                restoreIpAddressesDFS(s.substring(i),stringBuilder,ans);
                stringBuilder.remove(stringBuilder.size()-1);
            }
        }
    }

    public boolean checkLegal(String ip){
        if (ip.charAt(0)=='0' && ip.length()>1)
            return false;
        if (Integer.valueOf(ip)<=255 && Integer.valueOf(ip)>=0){
            return true;
        }
        return false;
    }


    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode newHead =  new ListNode(-1);
        newHead.next = head;
        ListNode slow = newHead;
        ListNode fast = newHead;
        for (int i=0;i<n-1;i++){
            fast=fast.next;
        }
        ListNode pre = newHead;
        while (fast.next!=null){
            pre = slow;
            slow=slow.next;
            fast=fast.next;
        }
        pre.next = slow.next;
        return newHead.next;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode n1 = new ListNode(1);
        n1.next = new ListNode(2);
        n1.next.next = new ListNode(3);
        n1.next.next.next = new ListNode(4);
        n1.next.next.next.next = new ListNode(5);
        solution.removeNthFromEnd(n1,2);
    }
}
