package jz;

import java.util.ArrayList;
import java.util.List;

public class JZ_41 {
    class MovingAverage {

        private List<Integer> list = new ArrayList<>();
        private int size;
        private int removedMaxIndex = -1;
        private double total = 0;

        /**
         * Initialize your data structure here.
         */
        public MovingAverage(int size) {
            this.size = size;
        }

        public double next(int val) {
            list.add(val);
            total += val;

            if (list.size() > size) {
                int first = list.get(++removedMaxIndex);
                total -= first;
                return total / size;
            }

            return total / list.size();
        }
    }

}
