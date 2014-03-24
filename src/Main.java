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

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        ParamParser params = new ParamParser(args);
        if (!params.state) {
            return;
        }

        StdOut.println("Merge repeats...");
        In input = new In(params.repeatFile);
        Out output = new Out("~merge_" + params.repeatFile);
        MergeFilter mergeFilter = new MergeFilter(input, output, params.mergeThreshold);
        input.close();
        output.close();

        // filter
        ArrayList<RepeatLine> filteredRepeats = RepeatFilter.filter(mergeFilter.rlArr);
        RepeatLine[] rlArr =  new RepeatLine[filteredRepeats.size()];
        filteredRepeats.toArray(rlArr);

        StdOut.println("Read sequence...");
        SequenceFactory sequenceFactory = new SequenceFactory();
        ArrayList<Sequence> arList = (ArrayList<Sequence>) Fasta.readSequences(params.inputSeqFile, sequenceFactory);

        StdOut.println("Find TSD...");
        TSDFind tsdFind = new TSDFind(rlArr, arList.get(0), params.tsdLength, params.tsdEditDistance);
        StdOut.println("TSD found: " + tsdFind.getTSDList().size());

        StdOut.println("Write TSD to file...");
        output = new Out(params.outputFile);
        TSDFile.write(tsdFind.getTSDList(), output);
        output.close();
    }




}
