package ks;
import java.util.*;

public class Main {
    static class Edge{
        int to;
        int cost;
        Edge(int to,int cost){
            this.to = to;
            this.cost = cost;
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int n = in.nextInt();
        int m = in.nextInt();
        int a = in.nextInt();
        List<Edge>[] graph = new ArrayList[n+1];
        for(int i=0;i<=n;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0;i<m;i++){
            int from = in.nextInt();
            int to = in.nextInt();
            int cost = in.nextInt();
            graph[from].add(new Edge(to,cost));
        }

        //
        int[][] dp = new int[n+1][a+1];
        dp[0][0]=1;
        for(int k=1;k<=a;k++){
            // k = cost
            // dp[v][k] = dp[u][k-xx]
            // start from each node
            for(int node = 1;node<=n;node++){
                for(Edge edge : graph[node]){
                    if(k>=edge.cost){
                        dp[edge.to][k] += dp[node][k-edge.cost];
                    }
                }
            }
        }
        System.out.println(dp[n-1][a]);
    }
}