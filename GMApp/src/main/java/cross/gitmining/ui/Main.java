package cross.gitmining.ui;

import java.util.Scanner;

/**
 * Created by raychen on 16/4/20.
 */
public class Main {
    static int nn,mm;

    static class point{
        int x;
        int y;
    }

    public static void sort(point[] points, int len, int tag){
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                point p1 = points[i];
                point p2 = points[j];
                if (p2.x<p1.x || (p2.x==p1.x && p2.y<p1.y)){
                    int tmp = p1.x;p1.x=p2.x;p2.x=tmp;
                    tmp = p1.y;p1.y=p2.y;p2.y=tmp;
                }
            }
        }
        for (int i=1;i<len;i++){
            if (points[i].x==points[i-1].x && points[i].y==points[i-1].y){
                for (int j = i; j < len-1; j++) {
                    points[j].x = points[j+1].x;
                    points[j].y = points[j+1].y;
                }
                i--;
                len--;
            }
        }
        if (tag==1) nn=len;else mm=len;
    }

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        int t = reader.nextInt();
//        boolean[][] matched = new boolean[10002][10002];
        for (int k=0;k<t;k++){
            int n = reader.nextInt();
            int m = reader.nextInt();
            point[] b = new point[n];
            point[] w = new point[m];
//            for (int i = 0; i < 10002; i++) {
//                for (int j = 0; j < 10002; j++) {
//                    matched[i][j] = false;
//                }
//            }
            for (int i = 0; i < n; i++) {
                b[i] = new point();
                b[i].x = reader.nextInt();
                b[i].y = reader.nextInt();
            }
            for (int i = 0; i < m; i++) {
                w[i] = new point();
                w[i].x = reader.nextInt();
                w[i].y = reader.nextInt();
            }
            sort(b, n, 1);
            sort(w, m, 0);
//            for (int i = 0; i < nn; i++) {
//                System.out.println(b[i].x+" "+b[i].y);
//            }
            boolean[] matched = new boolean[mm];
            for (int i = 0; i < mm; i++) {
                matched[i] = false;
            }
            int ans = 0;
            for (int i = 0; i < nn; i++) {
                for (int j = 0; j < mm; j++) {
                    if (!matched[j] && b[i].x<=w[j].x && b[i].y<=w[j].y){
                        ans ++;
                        matched[j] = true;
                        break;
                    }
                }
            }
            System.out.println(ans);
        }
    }
}
