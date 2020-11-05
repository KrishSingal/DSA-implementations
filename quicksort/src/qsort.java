import java.io.*;
import java.util.StringTokenizer;
import java.util.*;
import java.lang.*;

public class qsort {
    public static void main(String args[]) throws IOException{
        //BufferedReader f = new BufferedReader(new FileReader("feast.in"));
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("feast.out")));
        //PrintWriter out = new PrintWriter(System.out, true);
        StringTokenizer k = new StringTokenizer(f.readLine());

        int n = Integer.parseInt(k.nextToken());
        int list[] = new int[n];

        k = new StringTokenizer(f.readLine());

        for( int i=0; i< n; i++){
            list[i] = Integer.parseInt(k.nextToken());
        }

        sort(list, 0 , n-1);
        System.out.println(Arrays.toString(list));
    }
    public static void sort(int arr[], int lo, int hi){
        if(hi-lo !=0) {

            int mid = (lo + hi) / 2;


            if (arr[mid] < arr[lo]) {
                swap(arr, mid, lo);
            }
            if (arr[hi] < arr[mid]) {
                swap(arr, hi, mid);
            }
            if (arr[hi] < arr[lo]) {
                swap(arr, hi, lo);
            }

            int pivot = arr[mid];

            swap(arr, mid, hi);
            int i = lo;
            int j = hi - 1;

            for (int k = lo; k < hi; k++) {
                while (i < hi && arr[i] <= pivot) {
                    i++;
                }
                while (j > lo && arr[j] > pivot) {
                    j--;
                }
                if (i > j) {
                    break;
                }
                swap(arr, i, j);
            }

            swap(arr, hi, i);

            if(lo<= i-1) {
                sort(arr, lo, i - 1);
            }
            if(i+1<= hi) {
                sort(arr, i + 1, hi);
            }
        }

    }

    public static void swap(int arr[], int first, int second){
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }


}
