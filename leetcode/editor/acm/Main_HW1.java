package acm;

import java.util.Scanner;

/**
 * 记录一下ACM模式提交题目基本模板；
 *
 * @author 17862
 */
public class Main_HW1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.next();
        String[] split = input.split(",");
        int[] score = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            score[i] = Integer.parseInt(split[i]);
        }
        // dp[i][0] 表示拿第i轮牌的得分
        // dp[i][1] 表示不拿第i轮牌的得分
        int[][] dp = new int[score.length][2];
        dp[0][0] = score[0];
        dp[0][1] = 0;
        for (int i = 1; i < score.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]) + score[i];
            if (i < 3) {
                dp[i][1] = 0;
            } else {
                dp[i][1] = Math.max(dp[i - 3][0], dp[i - 3][1]);
            }
        }
        System.out.println(Math.max(dp[score.length - 1][0], dp[score.length - 1][1]));
        // 1,-5,-6,4,3,6,-2
        // 1,-4,-6,4,7,13,11
        // 0,0,0,  0,0,0,4
    }
}