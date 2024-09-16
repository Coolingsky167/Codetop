package ks;

import java.util.*;

public class shopee {

    public static String mostFrequentSubstring(String s, int k) {
        // write code here
        Map<String,Integer> cnt = new TreeMap<String,Integer>();
        for (int i=0;i<s.length();i++){
            int end = i+k;
            if (end>s.length())
                break;
            String tmp = s.substring(i,end);
            if (cnt.containsKey(tmp)){
                Integer times  = cnt.get(tmp);
                cnt.put(tmp,times+1);
            }else {
                cnt.put(tmp,1);
            }
        }
        int max = Integer.MIN_VALUE;
        String ans = "";
        for(String key : cnt.keySet()){
            if (cnt.get(key)>max){
                ans=key;
                max=cnt.get(key);
            }
        }
        return ans;
    }

    public static int maxK(int[] nums, int k) {
        // write code here
        Arrays.sort(nums);
        int sum=0;
        for(int i=nums.length-1;i>=0;i--){
            if (k==0)
                break;
            sum+=nums[i];
            k--;
        }
        return sum;
    }

    public static boolean plantFlowers(int[] flowerbed, int n) {
        // write code here
        List<Integer> hasFlower =  new ArrayList<>();
        for (int i=0;i<flowerbed.length;i++){
            if (flowerbed[i]==1)
                hasFlower.add(i);
        }
        //全是0
        if (hasFlower.size()==0){
            int num = (flowerbed.length+1)/2;
            if (num>=n)
                return true;
            else
                return false;
        }

        if (hasFlower.get(0)>0){
            int first1 = hasFlower.get(0);
            n=n-(first1)/2;
        }
        if (n<=0)
            return true;

        if (hasFlower.get(hasFlower.size()-1)<flowerbed.length-1){
            int last1 = hasFlower.get(hasFlower.size()-1);
            int cnt0 = flowerbed.length-last1-1;
            n=n-(cnt0)/2;
        }
        if (n<=0)
            return true;
        for (int i=0;i<hasFlower.size()-1;i++){
            int left = hasFlower.get(i);
            int right = hasFlower.get(i+1);
//            System.out.println(left+","+right);
            int cnt0 = right-left-1;
//            System.out.println(cnt0);
            n=n-(cnt0-1)/2;
            if (n<=0)
                return true;
        }
//        System.out.println(n);
        return false;
    }

    public static void main(String[] args) {
        System.out.println(mostFrequentSubstring("b", 1));
//        System.out.println(maxK(new int[]{4, 1, 7, 3, 9, 2}, 3));
//        System.out.println(plantFlowers(new int[]{0,0,0,0,1,0,0,0,0,0,1,0,1,0,0,0,0}, 100));
    }
}
