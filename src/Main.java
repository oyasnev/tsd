import alignment.AlignStartPos;
import alignment.LocalAlignment;
import common.Sequence;
import common.SequenceFactory;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.Out;
import edu.princeton.cs.introcs.StdOut;
import repeat.MergeFilter;
import repeat.RepeatFilter;
import repeat.RepeatLine;
import tools.Fasta;
import tsd.TSD;
import tsd.TSDFile;
import tsd.TSDFind;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        StdOut.println("Merge repeats...");
        //In input = new In("merge_test.out");
        //In input = new In("D.melanogaster_chrX.fna.out");
        In input = new In("chrX.fa.out");
        Out output = new Out("merge_test_output.out");
        MergeFilter mergeFilter = new MergeFilter(input, output, 20);
        ArrayList<RepeatLine> filteredRepeats = RepeatFilter.filter(mergeFilter.rlArr);
        RepeatLine[] rlArr =  new RepeatLine[filteredRepeats.size()];
        filteredRepeats.toArray(rlArr);

        // read sequences
        StdOut.println("Read sequence...");
        SequenceFactory sequenceFactory = new SequenceFactory();
        //ArrayList<Sequence> arList = (ArrayList<Sequence>) Fasta.readSequences("D.melanogaster_chrX.fna", sequenceFactory);
        ArrayList<Sequence> arList = (ArrayList<Sequence>) Fasta.readSequences("chrX.fa", sequenceFactory);

        StdOut.println("Find TSD...");
        TSDFind tsdFind = new TSDFind(rlArr, arList.get(0), 7, 3);
        StdOut.println("TSD found: " + tsdFind.getTSDList().size());

        StdOut.println("Write TSD to file...");
        TSDFile.write(tsdFind.getTSDList(), new Out("tsd.txt"));
    }




}
