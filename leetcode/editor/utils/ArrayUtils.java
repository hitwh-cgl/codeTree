package utils;

public class ArrayUtils {

    public static void main(String[] args) {
        String source = "[[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,1,0,0],[1,0,0,0,0,0,0,0,0,0],[0,0,1,0,0,0,1,0,0,0],[0,0,0,0,0,0,1,0,0,0],[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,1,0],[0,0,0,0,1,0,1,0,0,0],[0,0,0,0,0,0,0,0,0,0]]";
        parse(source);
    }

    public static int[][] parse(String source) {
        source = source.replace("[[", "");
        source = source.replace("]]", "");
        String[] bigSplit = source.split("],\\[");
        int[][] result = new int[bigSplit.length][];
        for (int i = 0; i < bigSplit.length; i++) {
            String cur = bigSplit[i];
            String[] smallSplit = cur.split(",");
            result[i] = new int[smallSplit.length];
            for (int j = 0; j < smallSplit.length; j++) {
                result[i][j] = Integer.parseInt(smallSplit[j]);
            }
        }
        return result;
    }
}
