package alignment;

import edu.princeton.cs.introcs.StdOut;

/**
 * Author: Oleg Yasnev (oyasnev@gmail.com)
 * Date: 12.03.14
 */
public class LocalAlignment {
    public static char GAP = '_';

    protected char[] first;
    protected char[] second;

    protected int maxScore;
    protected int[][] matrix;
    protected AlignStartPos[][] startPosMatrix;
    protected int m, n;

    public LocalAlignment(String strFirst, String strSecond) {
        // add empty string to the beginning of every string
        first = (GAP + strFirst).toCharArray();
        second = (GAP + strSecond).toCharArray();
        m = first.length;
        n = second.length;
        align();
    }

    public int getMaxScore() {
        return maxScore;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public AlignStartPos[][] getAlignStartPosMatrix() {
        return startPosMatrix;
    }

    protected void align() {
        maxScore = Integer.MIN_VALUE;
        matrix = new int[m][n];
        startPosMatrix = new AlignStartPos[m][n];

        // First, align empty string
        int dist = 0;
        for (int i = 0; i < m; i++) {
            matrix[i][0] = dist;
            startPosMatrix[i][0] = new AlignStartPos(i, 0);
            dist += 0;
        }
        dist = 0;
        for (int j = 1; j < n; j++) {
            matrix[0][j] = dist;
            startPosMatrix[0][j] = new AlignStartPos(0, j);
            dist += 0;
        }

        // align dinamycally
        for (int i = 1; i < m; i++) {
            char cFirst = first[i];
            for (int j = 1; j < n; j++) {
                int matchDist = matrix[i-1][j-1] + (second[j] == cFirst ? 1 : -1);
                int inDist    = matrix[ i ][j-1] - 1;
                int delDist   = matrix[i-1][ j ] - 1;
                // choose min dist
                int score = Math.max(0, Math.max(Math.max(matchDist, inDist), delDist));
                AlignStartPos startPos;
                if (score == 0) {
                    startPos = new AlignStartPos(i, j);
                } else if (score == matchDist) {
                    startPos = startPosMatrix[i-1][j-1];
                } else if (score == inDist) {
                    startPos = startPosMatrix[ i ][j-1];
                } else {
                    startPos = startPosMatrix[i-1][ j ];
                }
                matrix[i][j] = score;
                startPosMatrix[i][j] = startPos;
                maxScore = Math.max(maxScore, score);
            }
        }

        /*String tab = "%5s";
        String gap = "_";
        // Print score matrix
        StdOut.printf(tab, "");
        //StdOut.printf(tab, gap); // gap
        for (int j = 0; j < n; j++) {
            StdOut.printf(tab, second[j]);
        }
        StdOut.println();
        // gap
        StdOut.printf(tab, gap);
        for (int j = 0; j < n; j++) {
            StdOut.printf(tab, matrix[0][j]);
        }
        StdOut.println();
        for (int i = 1; i < m; i++) {
            StdOut.printf(tab, first[i]);
            for (int j = 0; j < n; j++) {
                StdOut.printf(tab, matrix[i][j]);
            }
            StdOut.println();
        }
        StdOut.println();*/
    }
}
