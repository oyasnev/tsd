package tsd;

import alignment.AlignStartPos;
import alignment.LocalAlignment;
import common.interfaces.ISequence;
import edu.princeton.cs.introcs.StdOut;
import repeat.Repeat;

import java.util.ArrayList;

/**
 * Author: Oleg Yasnev (oyasnev@gmail.com)
 * Date: 12.03.14
 */
public class TSDFind {
    public int tsdCount;
    protected Repeat[] rlArr;
    protected ISequence sequence;
    protected ArrayList<TSD> tsdArr;
    protected int lenThreshold;
    protected int distThreshold;
    protected int distOutRepeat;
    protected int distInsideRepeat;

    public TSDFind(Repeat[] rlArr, ISequence sequence, int lenThreshold, int distThreshold, int distOutRepeat, int distInsideRepeat) {
        this.rlArr            = rlArr;
        this.sequence         = sequence;
        this.lenThreshold     = lenThreshold;
        this.distThreshold    = distThreshold;
        this.distOutRepeat    = distOutRepeat;
        this.distInsideRepeat = distInsideRepeat;
        findAll();
    }

    public ArrayList<TSD> getTSDList() {
        return tsdArr;
    }

    protected void findAll() {
        String seq = sequence.getSequence();
        tsdArr = new ArrayList<TSD>();
        tsdCount = 0;
        for (Repeat repeat : rlArr) {
            int startFirst = Math.max(0, repeat.posQBegin - distOutRepeat - 1);
            int endFirst = Math.min(seq.length(), repeat.posQBegin + distInsideRepeat - 1);
            String strStart = seq.substring(startFirst, endFirst);
            int startSecond = Math.max(0, repeat.posQEnd - distInsideRepeat);
            int endSecond = Math.min(seq.length(), repeat.posQEnd + distOutRepeat);
            String strEnd = seq.substring(startSecond, endSecond);

           // StdOut.printf("Repeat test %d\n", counter);

            // find tsd
            TSD tsd = findTSD(strStart, strEnd);
            if (tsd != null) {
                tsd.hasTSD = true;
                tsdCount++;
                //StdOut.printf("\nTSD startPos: %d, endPos: %d\n\n", tsd.startPos, tsd.endPos);
                tsd.startPos += startFirst;
                tsd.endPos += startSecond;
            } else {
                tsd = new TSD();
                //StdOut.printf("\nno TSD found\n\n");
            }
            tsd.repeat = repeat;
            tsdArr.add(tsd);
            //if (tsdArr.size() > 20) return;
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

        for (int i = m - 1; i > 0; i--) {
            for (int j = 1; j < n; j++) {
                int score = matrix[i][j];
                int dist = distMatrix[i][j];
                AlignStartPos start = startPosMatrix[i][j];
                if (checkTSD(i, j, start, dist)) {
                    String[] alignedStrs = alignment.backtracking(i, j);
                    TSD tsd = new TSD(strFirst.substring(start.i, i), strSecond.substring(start.j, j), score, dist);
                    tsd.startPos = start.i + 1;
                    tsd.endPos = start.j + 1;
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
