package tsd;

import edu.princeton.cs.introcs.Out;

import java.util.ArrayList;

/**
 * Author: Oleg Yasnev (oyasnev@gmail.com)
 * Date: 16.03.14
 */
public class TSDFile {
    public static void write(ArrayList<TSD> tsdList, Out out) {
        for (TSD tsd : tsdList) {
            out.println(tsd.alignedStart);
            out.println(tsd.alignedEnd);
            out.printf("SW score: %d, edit distance: %d\n", tsd.score, tsd.dist);
            out.println("\n");
        }
    }
}
