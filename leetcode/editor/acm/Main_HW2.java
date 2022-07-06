package acm;

import java.util.*;


public class Main_HW2 {
    private static List<int[]> success = new ArrayList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int low = in.nextInt();
        int high = in.nextInt();
        boolean flag = false;
        for (int a = low; a <= high; a++) {
            for (int b = a + 1; b <= high; b++) {
                int c = (int) Math.sqrt(a * a + b * b);
                if (c < low || c > high) {
                    continue;
                }
//                for (int c = b + 1; c <= high; c++) {
                if (a * a + b * b == c * c) {
                    boolean ok = true;
                    for (int[] before : success) {
                        int x = before[0] / a;
                        if (before[1] * x == b && before[2] * x == c) {
                            ok = false;
                            break;
                        }
                    }

                    if (ok && isPrimary(a, b) && isPrimary(a, c) && isPrimary(b, c)) {
                        System.out.println(a + " " + b + " " + c);
                        success.add(new int[]{a, b, c});
                        if (!flag) {
                            flag = true;
                        }
                    }
                }
//                }
            }
        }

        if (!flag) {
            System.out.println("NA");
        }
    }

    /**
     * a和b互质，b和c互质，不能推到出a和c互质，例如 2，7，10
     */
    private static boolean isPrimary(int a, int b) {
        int min = Math.min(a, b);
        for (int i = 2; i <= min; i++) {
            if (a % i == 0 && b % i == 0) {
                return false;
            }
        }
        return true;
    }
}