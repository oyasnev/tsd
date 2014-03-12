package tsd;

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
        find();
    }

    protected void find() {
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
            StdOut.println("-------------------------------");
        }
    }
}
