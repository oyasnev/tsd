package repeat;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.Out;

import java.util.ArrayList;

/**
 * Author: Oleg Yasnev (oyasnev@gmail.com)
 * Date: 11.03.14
 */
public class MergeFilter {
    public MergeFilter(In input, Out output, int merge_threshold) {
        // skip header lines
        for (int i = 0; i < 3; i++) {
            input.readLine();
        }

        // read repeats
        ArrayList<RepeatMaskerLine> rmlArr = new ArrayList<RepeatMaskerLine>();
        RepeatMaskerLine rml = RepeatMaskerLine.read(input);
        while (rml != null) {
            rmlArr.add(rml);
            rml = RepeatMaskerLine.read(input);
        }

        // convert
        ArrayList<RepeatLine> rlArr = new ArrayList<RepeatLine>(rmlArr.size());
        for (RepeatMaskerLine repeat : rmlArr) {
            rlArr.add(new RepeatLine(repeat));
        }

        // write to output
        for (RepeatLine repeat : rlArr) {
            repeat.write(output);
        }

    }
}
