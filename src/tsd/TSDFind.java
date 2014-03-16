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
    protected ArrayList<TSD> tsdArr;
    protected int lenThreshold;
    protected int distThreshold;

    public TSDFind(RepeatLine[] rlArr, ISequence sequence, int lenThreshold, int distThreshold) {
        this.rlArr = rlArr;
        this.sequence = sequence;
        this.lenThreshold = lenThreshold;
        this.distThreshold = distThreshold;
        findAll();
    }

    public ArrayList<TSD> getTSDList() {
        return tsdArr;
    }

    protected void findAll() {
        String seq = sequence.getSequence();
        tsdArr = new ArrayList<TSD>();
        for (RepeatLine repeat : rlArr) {
            int dist = 15;
            int startFirst = Math.max(0, repeat.posQBegin - dist - 1);
            int endFirst = Math.min(seq.length(), repeat.posQBegin + dist - 1);
            String strStart = seq.substring(startFirst, endFirst);
            int startSecond = Math.max(0, repeat.posQEnd - dist - 1);
            int endSecond = Math.min(seq.length(), repeat.posQEnd + dist);
            String strEnd = seq.substring(startSecond, endSecond);

            // find tsd
            TSD tsd = findTSD(strStart, strEnd);
            if (tsd != null) {
                tsd.repeatName = repeat.repeatName;
                tsd.repeatClass = repeat.repeatClass;
                tsd.startPos += startFirst;
                tsd.endPos += startSecond;
                tsdArr.add(tsd);
            }
        }
    }

    protected TSD findTSD(String strFirst, String strSecond) {
        LocalAlignment alignment = new LocalAlignment(strFirst, strSecond);
        int[][] matrix = alignment.getMatrix();
        int[][] distMatrix = alignment.getDistMatrix();
        AlignStartPos[][] startPosMatrix = alignment.getAlignStartPosMatrix();
        TSD bestTsd = null;

        int m = strFirst.length();
        int n = strSecond.length();

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int score = matrix[i][j];
                int dist = distMatrix[i][j];
                AlignStartPos start = startPosMatrix[i][j];
                if (checkTSD(i, j, start, dist)) {
                    String[] alignedStrs = alignment.backtracking(i, j);
                    TSD tsd = new TSD(strFirst.substring(start.i, i), strSecond.substring(start.j, j), score, dist);
                    tsd.startPos = start.i;
                    tsd.endPos = start.j;
                    tsd.alignedStart = alignedStrs[0];
                    tsd.alignedEnd = alignedStrs[1];
                    if (bestTsd == null && tsd.getHeuristicScore() > 0
                            || bestTsd != null && bestTsd.getHeuristicScore() < tsd.getHeuristicScore()) {
                        bestTsd = tsd;
                    }
                }
            }
        }
        return bestTsd;
    }

    protected boolean checkTSD(int i, int j, AlignStartPos startPos, int dist) {
        if (dist > distThreshold) {
            return false;
        }
        int lenFirst = i - startPos.i;
        int lenSecond = j - startPos.j;
        if (lenFirst < lenThreshold || lenSecond < lenThreshold) {
            return false;
        }
        return true;
    }
}
