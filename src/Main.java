import alignment.AlignStartPos;
import alignment.LocalAlignment;
import common.Sequence;
import common.SequenceFactory;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.Out;
import edu.princeton.cs.introcs.StdOut;
import repeat.MergeFilter;
import repeat.RepeatLine;
import tools.Fasta;
import tsd.TSDFind;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        StdOut.println("Merging repeats...");
        In input = new In("merge_test.out");
        Out output = new Out("merge_test_output.out");
        MergeFilter mergeFilter = new MergeFilter(input, output, 20);
        RepeatLine[] rlArr = new RepeatLine[mergeFilter.rlArr.size()];
        mergeFilter.rlArr.toArray(rlArr);

        // read sequences
        /*StdOut.println("Read sequence...");
        SequenceFactory sequenceFactory = new SequenceFactory();
        ArrayList<Sequence> arList = (ArrayList<Sequence>) Fasta.readSequences("D.melanogaster_chrX.fna", sequenceFactory);

        StdOut.println("Find TSD...");
        TSDFind tsdFind = new TSDFind(rlArr, arList.get(0));*/

        String strFirst  = "CGACTACATACACGATGTACATATATATAT";
        String strSecond = "GACATGTACATACATACACGAGACAATAAAA";

        LocalAlignment alignment = new LocalAlignment(strFirst, strSecond);
        int[][] matrix = alignment.getMatrix();
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
                if (checkTSD(i, j, start, score)) {
                    StdOut.println(score);
                    StdOut.println(strFirst.substring(start.i, i));
                    StdOut.println(strSecond.substring(start.j, j));
                    StdOut.println();
                }
            }
        }
    }

    protected static boolean checkTSD(int i, int j, AlignStartPos startPos, int score) {
        int lenFirst = i - startPos.i;
        int lenSecond = j - startPos.j;
        if (lenFirst < 4 || lenSecond < 4) {
            return false;
        }
        return (lenFirst - score) / 2 < 4 && (lenSecond - score) / 2 < 4;
    }


}
