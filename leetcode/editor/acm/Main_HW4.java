package acm;

import java.util.Scanner;

/**
 * 2022.7.11 华为正式编制考试
 * @author 17862
 */
public class Main_HW4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input1 = in.next();
        if (input1.startsWith("0")) {
            System.out.println(-1);
            return;
        }
        for (int i = 0; i < input1.length(); i++) {
            if (!Character.isDigit(input1.charAt(i))) {
                System.out.println(-1);
                return;
            }
        }
        int n = Integer.parseInt(input1);
        String input = in.next();
        if (input.length() != 1) {
            System.out.println(-1);
        } else {
            if (input.charAt(0) > '9' || input.charAt(0) < '0') {
                System.out.println(-1);
                return;
            }
            int ch = input.charAt(0) - '0';
            int sum = 0;
            for (int i = 1; i <= n; i++) {
                sum += count(i, ch);
            }
            System.out.println(sum);
        }
    }

    private static int count(int target, int ch) {
        int count = 0;
        while (target > 0) {
            if (target % 10 == ch) {
                count++;
            }
            target = target / 10;
        }
        return count;
    }
}