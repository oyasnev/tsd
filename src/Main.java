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
import tsd.TSD;
import tsd.TSDFile;
import tsd.TSDFind;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        StdOut.println("Merge repeats...");
        In input = new In("merge_test.out");
        //In input = new In("D.melanogaster_chrX.fna.out");
        Out output = new Out("merge_test_output.out");
        MergeFilter mergeFilter = new MergeFilter(input, output, 20);
        RepeatLine[] rlArr = new RepeatLine[mergeFilter.rlArr.size()];
        mergeFilter.rlArr.toArray(rlArr);

        // read sequences
        StdOut.println("Read sequence...");
        SequenceFactory sequenceFactory = new SequenceFactory();
        ArrayList<Sequence> arList = (ArrayList<Sequence>) Fasta.readSequences("D.melanogaster_chrX.fna", sequenceFactory);

        StdOut.println("Find TSD...");
        TSDFind tsdFind = new TSDFind(rlArr, arList.get(0), 7, 3);
        StdOut.println("TSD found: " + tsdFind.getTSDList().size());
        TSDFile.write(tsdFind.getTSDList(), new Out("tsd.txt"));
    }




}
