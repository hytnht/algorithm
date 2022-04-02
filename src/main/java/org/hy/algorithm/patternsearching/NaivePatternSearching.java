package main.java.org.hy.algorithm.patternsearching;

public class NaivePatternSearching {
    public static void search(String txt, String pat) {
        int plen = pat.length();
        int tlen = txt.length();
        for (int i = 0; i <= tlen - plen; i++) {
            String sub = txt.substring(i, i + plen);
            if (sub.equals(pat)) {
                System.out.println("Pattern found at index " + i + ".");
            }
        }
    }

    public static void main(String[] args) {
        search("AAaAAABAAAAA", "AAA");
        System.out.println("\n");
        search("ABABDABACDABABCABAB", "ABABCABAB");
        System.out.println("\n");
        search("Java and JavaScript is not the same.", "Java");
    }
}
