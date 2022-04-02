package main.java.org.hy.algorithm.patternsearching;

public class KMPPatternSearching {
    public static void search(String txt, String pat) {
        int[] lps = prep(pat);
        int tlen = txt.length();
        int plen = pat.length();
        int i = 0;
        int j = 0;
        while (i < tlen) {
            char a = txt.charAt(i);
            char b = pat.charAt(j);
            if (a == b) {
                j++;
                i++;
                if (j == plen - 1) {
                    System.out.println("Pattern found at index " + (i - j) + ".");
                    j = 0;
                }
            } else {
                if (j != 0) {
                    j = lps[j - 1];
                }
                i++;
            }
        }

    }

    public static int[] prep(String pat) {
        int len = pat.length();
        int[] lps = new int[len];
        for (int end = 2; end <= len; end++) {
            int longest = 1;
            while (longest < end) {
                String prefix = pat.substring(0, longest);
                String suffix = pat.substring(end - longest, end);
                if (prefix.equals(suffix)) {
                    lps[end - 1] = longest;
                }
                longest++;
            }
        }
        return lps;
    }

    /* Preprocess not using substring:
    public static int[] prep(String pat) {
        int len = pat.length();
        int[] lps = new int[len];
        lps[0] = 0;
        int longest = 0;
        int i = 1;
        while (i < len) {
            if (pat.charAt(i) == pat.charAt(longest)) {
                longest++;
                lps[i] = longest;
                i++;
            } else {
                if (longest == 0) {
                    lps[i] = 0;
                    i++;
                } else {
                    longest = lps[longest - 1];
                }
            }
        }
        return lps;
    }
*/

    public static void main(String[] args) {
        search("AAaAAABAAAAA", "AAA");
        System.out.println("\n");
        search("ABABDABACDABABCABAB", "ABABCABAB");
        System.out.println("\n");
        search("Java and JavaScript is not the same.", "Java");
    }
}
