package mst;

public class MST_0109 {
    public static void main(String[] args) {
        String s1 = "waterbottle";
        String s2 = "erbottlewat";

    }

    public boolean isFlipedString(String s1, String s2) {
        return s1.length() == s2.length() && (s1 + s1).contains(s2);
    }

    public boolean isFlipedStringV1(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        } else {
            if (s1.equals(s2)) {
                return true;
            }

            int n = s1.length();
            StringBuilder builder = new StringBuilder();
            builder.append(s1);
            for (int i = 0; i < n; i++) {
                builder.append(builder.charAt(0)).deleteCharAt(0);
                if (builder.toString().equals(s2)) {
                    return true;
                }
            }
            return false;
        }
    }
}
