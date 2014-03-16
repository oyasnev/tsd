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
            out.printf("Repeat name: %s \n", tsd.repeatName);
            out.printf("Repeat class: %s \n", tsd.repeatClass);
            out.printf("First TSD pos: %d, second TSD pos: %d\n", tsd.startPos + 1, tsd.endPos + 1);
            out.printf("length: %d, SW score: %d, edit distance: %d\n", tsd.alignedStart.length(), tsd.score, tsd.dist);
            out.println("\n");
        }
    }
}
