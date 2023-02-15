import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // call the method here
        Scanner scanner = new Scanner(System.in);
        int base = scanner.nextInt();
        int height = scanner.nextInt();
        countAreaOfParallelogram(base, height);
    }

    // Do not change code below   
    public static void countAreaOfParallelogram(int b, int h) {
        System.out.println(b * h);
    }
}