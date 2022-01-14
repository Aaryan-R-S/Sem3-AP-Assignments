import java.util.Scanner;
import java.util.concurrent.*;

public class PascalTriangle extends RecursiveTask<Integer> {
    private int n;
    private int k;
    private int result;
    private static int threshold_n = 10;
    private static int threshold_k = 5;
    private static int[][] arr = new int[1003][1003];

    PascalTriangle(int _n, int _k, int _r) {
        n=_n; k=_k; result=_r;
    }

    public int sequential(int n, int k){
        if(n==0 | k==0 | n==k) {
            return (this.result = 1);
        }
        if(arr[n][k]!=-1){
            return (this.result = arr[n][k]);
        }
        int left = sequential(n-1, k-1);
        int right = sequential(n-1, k);
        return arr[n][k]= (this.result = left + right);
    }

    public Integer compute() {
        if(n==0 | k==0 | n==k) {
            return (result = 1);
        }
        if(n<threshold_n || k<threshold_k){
            return (result = sequential(n, k));
        }
        PascalTriangle left = new PascalTriangle(n-1, k-1, 0);
        PascalTriangle right = new PascalTriangle(n-1, k, 0);
        left.fork();
        return (result = right.compute() + left.join());
    }

    public static void main(String args[]) {
        for (int i = 0; i < 1003; i++) {
            for (int j = 0; j < 1003; j++) {
                arr[i][j] = -1;
            }
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of threads: ");
        int no_of_threads = sc.nextInt();

        ForkJoinPool pool = new ForkJoinPool(no_of_threads);
        PascalTriangle task = new PascalTriangle(30, 10, 0);

        int result = pool.invoke(task);
        System.out.println(result);
        sc.close();
    }
}

