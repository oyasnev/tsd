package tsd;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.Out;
import repeat.Repeat;

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
                out.printf("length: %d, SW score: %d, edit distance: %d\n", tsd.length(), tsd.score, tsd.dist);
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
                        ,tsd.length()
                        ,tsd.dist
                );
            }
            out.println();
        }
    }

    public static ArrayList<TSD> readCSV(In in) {
        ArrayList<TSD> tsdList = new ArrayList<TSD>();
        while (in.hasNextLine()) {
            String s = in.readLine();
            String[] sArr = s.split(";");
            Repeat repeat = new Repeat();
            repeat.querySeq = sArr[0];
            repeat.cls = sArr[1];
            repeat.name = sArr[2];
            repeat.posQBegin = Integer.parseInt(sArr[3]);
            repeat.posQEnd = Integer.parseInt(sArr[4]);
            repeat.complement = sArr[5].equals("C");
            TSD tsd = new TSD();
            tsd.repeat = repeat;
            tsd.hasTSD = sArr[6].equals("1");
            if (tsd.hasTSD) {
                tsd.alignedStart = sArr[7];
                tsd.startPos = Integer.parseInt(sArr[8]);
                tsd.alignedEnd = sArr[9];
                tsd.endPos = Integer.parseInt(sArr[10]);
                // skip sArr[11], the length as it is accessible in TSD as method
                tsd.dist = Integer.parseInt(sArr[12]);
            }
            tsdList.add(tsd);
        }
        return tsdList;
    }
}
