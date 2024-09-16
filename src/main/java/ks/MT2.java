package ks;
import java.util.*;

import java.util.*;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class MT2 {
    public static int calDistance(int m, int l, int r) {
        if (m >= l && m <= r)
            return 0;
        if (m < l)
            return r - l;
        if (m > r)
            return m - r;
        return -1;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int a = in.nextInt();
            int b = in.nextInt();
            System.out.println(a + b);
        }
        int arrlen = in.nextInt();
        int times = in.nextInt();
        int[] arr = new int[arrlen];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = in.nextInt();
        }
        //维护区间最大值
        int[][] dp = new int[arr.length][arr.length];
        int maxInArr = arr[0];
        List<Integer> maxIndex = new ArrayList<>();
        for (int a : arr) {
            if (a > maxInArr)
                maxInArr = a;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == maxInArr)
                maxIndex.add(i);
        }


        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = i; j < arr.length; j++) {
                if (i == j) {
                    dp[i][j] = i;
                    continue;
                }
                if (j == i + 1) {
                    if (arr[i] > arr[j])
                        dp[i][j] = i;
                    else
                        dp[i][j] = j;
                    continue;
                }
                int curMaxIndex = dp[i + 1][j - 1];
                dp[i][j] = curMaxIndex;
                int curMax = arr[curMaxIndex];
                if (arr[i] > curMax) {
                    dp[i][j] = i;
                    curMax = arr[i];
                }
                if (arr[j] > curMax) {
                    dp[i][j] = j;
                    curMax = arr[i];
                }
            }
        }
        // query
        for (int t = 0; t < times; t++) {
            int l = in.nextInt();
            int r = in.nextInt();
            int tuan = arr[dp[l][r]];
            // 首先判断是否已经是数组最大值
            if (tuan == maxInArr) {
                // 小美还能不能找到最大值的集合
                if (maxIndex.size() == 1) {
                    System.out.println("lose");
                    System.out.println(r - l + 1);
                    return;
                } else {
                    int minNotZero = Integer.MAX_VALUE;
                    int target = -1;
                    int zeroNum = 0;
                    for (int index : maxIndex) {
                        int min = calDistance(index, l, r);
                        if (min == 0) {
                            zeroNum += 1;
                        } else {
                            if (min < minNotZero) {
                                minNotZero = min;
                                target = index;
                            }
                        }
                    }
                    if (zeroNum >= 2) {
                        System.out.println("draw");
                        System.out.println(r - l + 1);
                        return;
                    } else {
                        System.out.println("draw");
                        System.out.println(r - l + 1 + minNotZero);
                        return;
                    }
                }
            }
        }
    }
}