package tsd;

import edu.princeton.cs.introcs.Out;
import edu.princeton.cs.introcs.StdOut;

import java.util.ArrayList;

/**
 * Author: Oleg Yasnev (oyasnev@gmail.com)
 * Date: 16.03.14
 */
public class TSDFile {
    public static void writeAlignment(ArrayList<TSD> tsdList, Out out) {
        for (TSD tsd : tsdList) {
            if (tsd.hasTSD) {
                out.println(tsd.alignedStart);
                out.println(tsd.alignedEnd);
                out.printf("Sequence: %s \n", tsd.repeat.querySeq);
                out.printf("Repeat name: %s \n", tsd.repeat.name);
                out.printf("Repeat class: %s \n", tsd.repeat.cls);
                out.printf("First TSD pos: %d, second TSD pos: %d\n", tsd.startPos + 1, tsd.endPos + 1);
                out.printf("length: %d, SW score: %d, edit distance: %d\n", tsd.alignedStart.length(), tsd.score, tsd.dist);
                out.println("\n");
            }
        }
    }

    public static void writeCSV(ArrayList<TSD> tsdList, Out out) {
        for (TSD tsd : tsdList) {
            out.printf("%s;%s;%s;%d;%d;%s;%d"
                    ,tsd.repeat.querySeq
                    ,tsd.repeat.cls
                    ,tsd.repeat.name
                    ,tsd.repeat.posQBegin
                    ,tsd.repeat.posQEnd
                    ,tsd.repeat.complement ? "C" : "+"
                    ,(tsd.hasTSD ? 1 : 0)
            );
            if (tsd.hasTSD) {
                out.printf(";%s;%d;%s;%d;%d;%d"
                        ,tsd.alignedStart
                        ,tsd.startPos
                        ,tsd.alignedEnd
                        ,tsd.endPos
                        ,tsd.alignedStart.length()
                        ,tsd.dist
                );
            }
            out.println();
        }
    }
}
