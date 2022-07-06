package acm;

import java.util.*;

public class Main_HW3 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        boolean[] notFinish = new boolean[n];
        Map<Integer, Integer> map = new HashMap<>(n);
        // 如何让控制台停止输入
        while (in.hasNext()) {
            int before = in.nextInt();
            int after = in.nextInt();
            map.put(after, before);
            notFinish[after] = true;
        }

        for (int i = 0; i < n; i++) {
            if (notFinish[i]) {
                Set<Integer> needSet = new HashSet<>();
                int need = map.get(i);
                while (notFinish[need]) {
                    if (needSet.add(need)) {
                        need = map.get(need);
                    } else {
                        System.out.println("no");
                        return;
                    }
                }
                for (int ok : needSet) {
                    notFinish[ok] = false;
                }
            }
        }
        System.out.println("yes");
    }

}
