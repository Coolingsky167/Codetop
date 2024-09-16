package page1;

import common.ListNode;
import common.TreeNode;
import org.w3c.dom.ls.LSInput;

import java.util.*;

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        char[] input =  s.toCharArray();
        Set<Character> visit = new HashSet<>();
        int ans = 1;
        // ababc
        int end =0;
        for (int i=0;i<input.length;i++){
            if (i!=0){
                visit.remove(input[i-1]);
            }
            while (end<input.length){
                if (visit.contains(input[end])){
                    break;
                }else {
                    visit.add(input[end]);
                    end+=1;
                }
            }
            ans = Math.max(ans,end-i);
        }
        return ans;
    }

    public ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        while (cur!=null){
            ListNode next = cur.next;
            cur.next=pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
    // 如何递归做
    public ListNode reverseList1(ListNode node) {
        if (node==null || node.next == null)
            return node;
        ListNode newHead  = reverseList1(node.next);
        node.next.next=node;
        node.next=null;
        return newHead;
    }

    public static int heapSortArrLen;
    public int findKthLargest(int[] nums, int k) {
        // 堆排序
        heapSortArrLen = nums.length;
        buildHeap(nums);
        for (int i=0;i<nums.length;i++){
            swap(nums,0,heapSortArrLen-1);
            heapSortArrLen-=1;
            heapify(nums,0);
        }
        return nums[nums.length-k];
    }
    public  void buildHeap(int[] nums){
        for (int i = nums.length/2-1;i>=0;i--){
            heapify(nums,i);
        }
    }
    public void heapify(int[] nums,int index){
        int left = 2*index+1;
        int right = 2*index+2;
        int largest = index;
        if (left<heapSortArrLen && nums[left]>nums[index]){
            swap(nums,left,index);
            largest=left;
        }
        if (right<heapSortArrLen && nums[right]>nums[index]){
            swap(nums,right,index);
            largest=right;
        }
        if (largest!=index){
            heapify(nums,largest);
        }
    }

    public void swap(int[] nums,int i,int k){
        int tmp =nums[i];
        nums[i]=nums[k];
        nums[k]=tmp;
    }

    // 快速排序

    public void quickSort1(int[] nums,int l,int r){
        if (l<r){
            int pos = quickSort(nums, l, r);
            quickSort(nums, l, pos - 1);
            quickSort(nums, pos + 1, r);
        }
    }
    public int quickSort(int[] nums,int left, int right){
        Random random = new Random();
        int randomNumber = left + random.nextInt(right - left + 1);
        int pivot = nums[randomNumber];
        swap(nums,left,randomNumber);
        int smallerIndex=left;
        for (int i=left;i<=right;i++){
            if (nums[i]<=pivot){
                swap(nums,smallerIndex,i);
                smallerIndex++;
            }
        }
        swap(nums,left,smallerIndex);
        return smallerIndex;
    }


    // mergeTwoLists
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


    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode hair = new ListNode(0);
        hair.next = head;
        ListNode pre = hair;
        while (head!=null){
            ListNode tail = pre;
            // 查看剩余部分长度是否大于等于 k
            for (int i = 0; i < k; ++i) {
                tail = tail.next;
                if (tail == null) {
                    return hair.next;
                }
            }
            ListNode nex = tail.next;
            ListNode[] reverse = reverseK(head, tail);
            head = reverse[0];
            tail = reverse[1];
            // 把子链表重新接回原链表
            pre.next = head;
            tail.next = nex;
            pre = tail;
            head = tail.next;
        }
        return null;
    }

    public ListNode[] reverseK(ListNode head,ListNode tail){
        // return new ListNode{head,tail}
        ListNode cur = head;
        ListNode pre = tail.next;
        while (pre!=tail){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return new ListNode[]{tail, head};
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        for (int i=0;i<nums.length;i++){
            if (i>0 && nums[i]==nums[i-1]){
                continue;
            }
            int target = 0-nums[i];
            int end = nums.length-1;
            for (int j = i+1;j<nums.length;j++){
                if (j>i+1 && nums[j]==nums[j-1]){
                    continue;
                }
                while (j<end && nums[j] + nums[end] > target){
                    end--;
                }
                if (j==end)
                    break;
                if (nums[end] + nums[j] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[end]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    public int maxSubArray(int[] nums) {
        //
        if (nums.length==1)
            return nums[0];
        int[] dp = new int[nums.length];
        dp[0]=nums[0];
        int ansm=nums[0];
        for (int i=1;i<nums.length;i++){
            dp[i]=Math.max(dp[i-1]+nums[i],nums[i]);
            ansm=Math.max(ansm,dp[i]);
        }
        return ansm;
    }
    public int[] twoSum(int[] nums, int target) {

        Map<Integer,Integer> hashmap = new HashMap<>();
        for (int i=0;i<nums.length;i++){
            if (hashmap.containsKey(target-nums[i])){
                return new int[]{hashmap.get(target-nums[i]),i};
            }
            hashmap.put(nums[i],i);

        }
        return new int[0];
    }

    //5. 最长回文子串
    public String longestPalindrome(String s) {
        if (s.length()<2)
            return s;
        char[] input = s.toCharArray();
        int[][] dp = new int[s.length()][s.length()];
        int l=0;
        int r=0;
        for (int i=input.length-1;i>=0;i--){
            for (int j=0;j<input.length;j++){
                if (input[i]!=input[j])
                    continue;
                if (i==j){
                    dp[i][j]=1;
                    if (j-i>r-l){
                        l=i;
                        r=j;
                    }
                    continue;
                }
                if (i>j){
                    dp[i][j]=0;
                    continue;
                }
                if (i+1==j && input[i]==input[j]){
                    dp[i][j]=1;
                    if (j-i>r-l){
                        l=i;
                        r=j;
                    }
                    continue;
                }
                if (input[i]==input[j] && dp[i+1][j-1]==1){
                    dp[i][j]=1;
                    if (j-i>r-l){
                        l=i;
                        r=j;
                    }
                }
            }
        }
        return s.substring(r,l+1);
    }

    //102. 二叉树的层序遍历
    public List<List<Integer>> levelOrder(TreeNode root) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        List<List<Integer>> ans = new ArrayList<>();
        if(root==null)
            return ans;
        queue.offer(root);
        while (true){
            int len  = queue.size();
            if (len==0)
                break;
            List<Integer> cur = new ArrayList<>();
            for (int i=0;i<len;i++){
                TreeNode curNode = queue.poll();
                cur.add(curNode.val);
                if (curNode.left!=null)
                    queue.offer(curNode.left);
                if (curNode.right!=null)
                    queue.offer(curNode.right);
            }
            ans.add(cur);
        }
        return ans;
    }

    //33. 搜索旋转排序数组
    public int search(int[] nums, int target) {
        return search_binary(nums,0,nums.length-1,target);
    }
    public int search_binary(int[] nums,int left,int right,int target){
        int mid = (left+right)/2;
        int left_v = nums[left];
        int right_v = nums[right];
        int mid_v=nums[mid];
        if (mid_v==target)
            return mid;
        if (left==right && target!=nums[left])
            return -1;
        if (left_v<mid_v){
            if (target<=mid_v && target>=left_v){
                return search_binary(nums,left,mid,target);
            }else {
                return search_binary(nums,mid+1,right,target);
            }
        }else {
            if (target<=right_v && target>=mid_v){
                return search_binary(nums,mid+1,right,target);
            }else {
                return search_binary(nums,left,mid,target);
            }
        }
    }

    //200. 岛屿数量
    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int counter = 0;
        for (int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                counter+=numIslandsDFS(grid,i,j);
            }
        }
        return counter;
    }

    public int numIslandsDFS(char[][] grid,int x,int y) {
        if (x<0 || x>=grid.length || y<0 || y>= grid[0].length){
            return 0;
        }
        if (grid[x][y]=='1'){
            grid[x][y]='2';
            numIslandsDFS(grid,x-1,y);
            numIslandsDFS(grid,x+1,y);
            numIslandsDFS(grid,x,y-1);
            numIslandsDFS(grid,x,y+1);
        }else {
            return 0;
        }
        return 0;
    }

    //121. 买卖股票的最佳时机
    public int maxProfit(int[] prices) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i=0;i<prices.length;i++){
            if (prices[i] < minprice) {
                minprice = prices[i];
            } else if (prices[i] - minprice > maxprofit) {
                maxprofit = prices[i] - minprice;
            }
        }
        return maxprofit;
    }
    //20. 有效的括号
    public boolean isValid(String s) {
        //"()[]{}"
        HashMap<Character,Character> map = new HashMap<>();
        map.put(')','(');
        map.put('}','{');
        map.put(']','[');
        char[] input = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i=0;i<input.length;i++){
            if (input[i]=='('||input[i]=='{'||input[i]=='['){
                stack.push(input[i]);
            }else {
                if (stack.isEmpty())
                    return false;
                Character character = stack.pop();
                if (map.get(input[i])!=character){
                    return false;
                }
            }
        }
        if (!stack.isEmpty())
            return false;
        return true;
    }
//    //46. 全排列
//    public List<List<Integer>> permute(int[] nums) {
//        for (int i=0;i<nums.length;i++){
//
//        }
//    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longestPalindrome("ab"));
    }
}
