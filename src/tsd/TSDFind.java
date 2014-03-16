package tsd;

import alignment.AlignStartPos;
import alignment.LocalAlignment;
import common.interfaces.ISequence;
import edu.princeton.cs.introcs.StdOut;
import repeat.RepeatLine;

import java.util.ArrayList;

/**
 * Author: Oleg Yasnev (oyasnev@gmail.com)
 * Date: 12.03.14
 */
public class TSDFind {
    protected RepeatLine[] rlArr;
    protected ISequence sequence;

    public TSDFind(RepeatLine[] rlArr, ISequence sequence) {
        this.rlArr = rlArr;
        this.sequence = sequence;
        findAll();
    }

    protected void findAll() {
        String seq = sequence.getSequence();
        for (RepeatLine repeat : rlArr) {
            int dist = 15;
            int start = Math.max(0, repeat.posQBegin - dist - 1);
            //int middle = repeat.posQBegin - 1;
            int end = Math.min(seq.length(), repeat.posQBegin + dist - 1);
            //StdOut.println(seq.substring(start, middle) + "|" + seq.substring(middle, end));
            String strStart = seq.substring(start, end);
            StdOut.println(strStart);
            start = Math.max(0, repeat.posQEnd - dist - 1);
            //middle = repeat.posQEnd;
            end = Math.min(seq.length(), repeat.posQEnd + dist);
            //StdOut.println(seq.substring(start, middle) + "|" + seq.substring(middle, end));
            String strEnd = seq.substring(start, end);
            StdOut.println(strEnd);

            // find tsd
            findTSD(strStart, strEnd);
            StdOut.println("-------------------------------");
        }
    }

    protected void findTSD(String strFirst, String strSecond) {
        LocalAlignment alignment = new LocalAlignment(strFirst, strSecond);
        int[][] matrix = alignment.getMatrix();
        int[][] distMatrix = alignment.getDistMatrix();
        AlignStartPos[][] startPosMatrix = alignment.getAlignStartPosMatrix();
        int maxScore = alignment.getMaxScore();

        int m = strFirst.length();
        int n = strSecond.length();

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int score = matrix[i][j];
                if (score != maxScore) {
                    continue;
                }
                AlignStartPos start = startPosMatrix[i][j];
                if (checkTSD(i, j, start, score, distMatrix)) {
                    StdOut.printf("Score: %d, dist: %d, pos: (%d, %d)\n", score, distMatrix[i][j], i, j);
                    //StdOut.println(strFirst.substring(start.i, i));
                    //StdOut.println(strSecond.substring(start.j, j));
                    String[] strs = alignment.backtracking(i, j);
                    StdOut.println(strs[0]);
                    StdOut.println(strs[1]);
                    StdOut.println();
                }
            }
        }
    }

    protected boolean checkTSD(int i, int j, AlignStartPos startPos, int score, int[][] distMatrix) {
        int lenFirst = i - startPos.i;
        int lenSecond = j - startPos.j;
        if (lenFirst < 4 || lenSecond < 4) {
            return false;
        }
        return distMatrix[i][j] < 4;
    }
}
