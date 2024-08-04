package page1;

import java.util.HashSet;
import java.util.Set;

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

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.lengthOfLongestSubstring("pwwkew");
    }
}
