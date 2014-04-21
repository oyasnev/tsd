package alignment;

import edu.princeton.cs.introcs.StdOut;

enum Direction {
    NULL,
    AB,
    _B, A_
}

/**
 * Author: Oleg Yasnev (oyasnev@gmail.com)
 * Date: 12.03.14
 */
public class LocalAlignment {
    public static char GAP = '-';

    protected char[] first;
    protected char[] second;

    protected int maxScore;
    protected int[][] matrix;
    protected int[][] distMatrix;
    protected Direction[][] dirMatrix;
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

    public int[][] getDistMatrix() {
        return distMatrix;
    }

    public AlignStartPos[][] getAlignStartPosMatrix() {
        return startPosMatrix;
    }

    protected void align() {
        maxScore = Integer.MIN_VALUE;
        matrix = new int[m][n];
        distMatrix = new int[m][n];
        dirMatrix = new Direction[m][n];
        startPosMatrix = new AlignStartPos[m][n];

        // First, align empty string
        int dist = 0;
        for (int i = 0; i < m; i++) {
            matrix[i][0] = dist;
            startPosMatrix[i][0] = new AlignStartPos(i, 0);
            dirMatrix[i][0] = Direction.NULL;
            distMatrix[i][0] = 0;
            //dist += 0;
        }
        dist = 0;
        for (int j = 1; j < n; j++) {
            matrix[0][j] = dist;
            startPosMatrix[0][j] = new AlignStartPos(0, j);
            dirMatrix[0][j] = Direction.NULL;
            distMatrix[0][j] = 0;
            //dist += 0;
        }

        // align dinamycally
        for (int i = 1; i < m; i++) {
            char cFirst = first[i];
            for (int j = 1; j < n; j++) {
                int AB = matrix[i-1][j-1] + (second[j] == cFirst ? 1 : -1);
                int A_ = matrix[ i ][j-1] - 1;
                int _B = matrix[i-1][ j ] - 1;
                // choose min dist
                int score = Math.max(0, Math.max(Math.max(AB, A_), _B));
                AlignStartPos startPos;
                Direction dir;
                if (score == 0) {
                    startPos = new AlignStartPos(i, j);
                    dir = Direction.NULL;
                    dist = 0;
                } else if (score == AB) {
                    startPos = startPosMatrix[i-1][j-1];
                    dir = Direction.AB;
                    dist = distMatrix[i-1][j-1] + (second[j] == cFirst ? 0 : 1);
                } else if (score == A_) {
                    startPos = startPosMatrix[ i ][j-1];
                    dir = Direction.A_;
                    dist = distMatrix[i][j-1] + 1;
                } else {
                    startPos = startPosMatrix[i-1][ j ];
                    dir = Direction._B;
                    dist = distMatrix[i-1][j] + 1;
                }
                matrix[i][j] = score;
                startPosMatrix[i][j] = startPos;
                dirMatrix[i][j] = dir;
                distMatrix[i][j] = dist;
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
        StdOut.println(); */
    }

    public String[] backtracking(int i, int j) {
        // init
        StringBuilder sbFirst  = new StringBuilder();
        StringBuilder sbSecond = new StringBuilder();

        // while not in a cell with score = 0
        while (dirMatrix[i][j] != Direction.NULL) {
            switch (dirMatrix[i][j]) {
                case AB:
                    sbFirst .append(first [i--]);
                    sbSecond.append(second[j--]);
                    break;
                case _B:
                    sbFirst .append(first[i--] );
                    sbSecond.append(GAP        );
                    break;
                case A_:
                    sbFirst .append(GAP        );
                    sbSecond.append(second[j--]);
                    break;
                default:
                    StdOut.printf("ERROR while backtracking at (%d, %d)", i, j);
                    return null;
            }
        }

        // reverse as we went from the end to beginning
        sbFirst .reverse();
        sbSecond.reverse();
        String[] strs = new String[2];
        strs[0] = sbFirst .toString();
        strs[1] = sbSecond.toString();
        return strs;
    }
}
