package acm;

import java.util.Scanner;

/**
 * 记录一下ACM模式提交题目基本模板；
 *
 * @author 17862
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        boolean[] isDeleted = new boolean[n];
        int count = 0;
        int index = 0;
        while (count < n) {
            for (int i = 1; i <= 3; i++) {
                while (isDeleted[index]) {
                    index = (index + 1) % n;
                }
                if (i == 3) {
                    isDeleted[index] = true;
                    count++;
                    if (count == n) {
                        System.out.println(index);
                        break;
                    }
                }
                index++;
            }
        }
    }
}