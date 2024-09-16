package ks;

public class DJ {

    public int getDirection(int x){
        return (x+1)%4;
    }
    public int numberOfPatrolBlocks(int[][] block) {
        // up r d l
        int[][] directions = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};
        int counter = 0;
        int[][] visited = new int[block.length][block[0].length];
        int curDirection = 1;
        int curX = 0;
        int curY = -1;
        int cc = block.length*block[0].length*5;
        while (cc>=0){
            cc--;
            // 上下左右都尝试一遍
            int ready = 4;
            int nextX;
            int nextY;
            while (ready>0){
                ready-=1;
                nextX = curX+directions[curDirection][0];
                nextY = curY+directions[curDirection][1];
                if (nextX<0 || nextX >=block.length || nextY<0 || nextY>=block[0].length){
                    //超出边界
                    curDirection=getDirection(curDirection);
                    continue;
                } else if ( block[nextX][nextY] ==1) {
                    // 访问过或者有障碍
                    curDirection=getDirection(curDirection);
                    continue;
                } else {
                    // ok
                    break;
                }
            }
            if (ready==0)
                return counter;
            curX = curX+directions[curDirection][0];
            curY = curY+directions[curDirection][1];
            if (visited[curX][curY]==0){
                visited[curX][curY]=1;
                counter+=1;
            }
        }
        return counter;
    }

    public static void main(String[] args) {
        DJ dj = new DJ();
        System.out.println(dj.numberOfPatrolBlocks(new int[][]{
                {0, 0, 0,0,0},
                {0, 0, 0,0,0},
                {0, 0, 1,0,1},
                {0, 0, 0,0,0}
        }));

    }
}
