package ks;

public class MT {



    public static boolean isOk(int[] arr,int m,int k){
        int[] trees = new int[arr.length+k+1];
        int cnt=0;
        for (int index : arr){
            for (int i=0;i<m;i++){
                int cur =index+i;
                if (trees[cur]==0){
                    trees[cur]=1;
                    cnt+=1;
                    if (cnt==k)
                        return true;
                }
            }
        }
        if (cnt>=k){
            return true;
        }
        return false;
    }



    public static void main(String[] args) {
        int n = 3;
        int k = 6;
        int[] arr = new int[]{1};
        int min = 0;
        int max = k;
        int ans = max;
        while (min<=max && min>=0 && max<=k){
            int mid  = (min+max)/2;
            if (isOk(arr,mid,k)){
                ans=mid;
                //尝试找更小的
                max = mid-1;
            }else {
                //尝试找更大的
                min=mid+1;
            }
        }
        System.out.println(ans);
    }
}
