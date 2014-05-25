import common.Sequence;
import common.SequenceFactory;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.Out;
import edu.princeton.cs.introcs.StdOut;
import repeat.MergeFilter;
import repeat.Repeat;
import repeat.RepeatFilter;
import tools.Fasta;
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
        //Out output = new Out("~" + params.inputSeqFilename + "_merge_repeats.out");
        MergeFilter mergeFilter = new MergeFilter(input, params.merge, params.mergeThreshold);
        input.close();
        //output.close();


        // filter
        ArrayList<Repeat> filteredRepeats = RepeatFilter.filter(mergeFilter.rlArr, params.repeatLength);
        Repeat[] rlArr =  new Repeat[filteredRepeats.size()];
        filteredRepeats.toArray(rlArr);
        StdOut.println("Total repeats: " + rlArr.length);

        StdOut.println("Read sequence...");
        SequenceFactory sequenceFactory = new SequenceFactory();
        ArrayList<Sequence> arList = (ArrayList<Sequence>) Fasta.readSequences(params.inputSeqFile, sequenceFactory);

        StdOut.println("Find TSD...");
        TSDFind tsdFind = new TSDFind(rlArr, arList.get(0), params.tsdLength, params.tsdEditDistance, params.distOutRepeat, params.distInsideRepeat);
        StdOut.println("TSD found: " + tsdFind.tsdCount);

        StdOut.println("Write TSD to file...");
        Out output = new Out(params.outputFile + "_alignment.txt");
        TSDFile.writeAlignment(tsdFind.getTSDList(), output);
        output.close();
        output = new Out(params.outputFile + ".csv");
        TSDFile.writeCSV(tsdFind.getTSDList(), output);
        output.close();
    }




}
