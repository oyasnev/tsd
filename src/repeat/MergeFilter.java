package repeat;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.Out;

import java.util.ArrayList;

/**
 * Author: Oleg Yasnev (oyasnev@gmail.com)
 * Date: 11.03.14
 */
public class MergeFilter {
    public ArrayList<RepeatLine> rlArr;

    public MergeFilter(In input, Out output, boolean merge, int merge_threshold) {
        // skip header lines
        for (int i = 0; i < 3; i++) {
            input.readLine();
        }

        // read repeats
        rlArr = new ArrayList<RepeatLine>();
        if (merge) {
            // read first repeat
            RepeatLine rl = new RepeatLine(RepeatMaskerLine.read(input));
            // continue
            RepeatMaskerLine rml = RepeatMaskerLine.read(input);
            while (rml != null) {
                if (rml.posQBegin - rl.posQEnd <= merge_threshold) {
                    // merge repeats
                    rl.posQEnd = Math.max(rl.posQEnd, rml.posQEnd);
                    rl.repeatName += '|' + rml.repeatName;
                    rl.repeatClass += '|' + rml.repeatClass;
                } else {
                    rlArr.add(rl);
                    rl = new RepeatLine(rml);
               }
                rml = RepeatMaskerLine.read(input);
            }
            rlArr.add(rl);
        } else {
            // not merge
            RepeatMaskerLine rml = RepeatMaskerLine.read(input);
            while (rml != null) {
                rlArr.add(new RepeatLine(rml));
                rml = RepeatMaskerLine.read(input);
            }
        }

        // write to output
        for (RepeatLine repeat : rlArr) {
            repeat.write(output);
        }
    }
}
