package repeat;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.Out;

import java.util.ArrayList;

/**
 * Author: Oleg Yasnev (oyasnev@gmail.com)
 * Date: 11.03.14
 */
public class MergeFilter {
    public ArrayList<Repeat> rlArr;

    public MergeFilter(In input, Out output, boolean merge, int merge_threshold) {
        // skip header lines
        for (int i = 0; i < 3; i++) {
            input.readLine();
        }

        // read repeats
        rlArr = new ArrayList<Repeat>();
        if (merge) {
            // read first repeat
            Repeat rl = new Repeat(RepeatMaskerLine.read(input));
            String rName = rl.name;
            // continue
            RepeatMaskerLine rml = RepeatMaskerLine.read(input);
            while (rml != null) {
                if (rml.posQBegin - rl.posQEnd <= merge_threshold && (rml.repeatName.equals(rName))) {
                    // merge repeats
                    rl.posQEnd = Math.max(rl.posQEnd, rml.posQEnd);
                    //rl.name += '|' + rml.repeatName;
                    //rl.cls += '|' + rml.repeatClass;
                } else {
                    rlArr.add(rl);
                    rl = new Repeat(rml);
                    rName = rl.name;
               }
               rml = RepeatMaskerLine.read(input);
            }
            rlArr.add(rl);
        } else {
            // not merge
            RepeatMaskerLine rml = RepeatMaskerLine.read(input);
            while (rml != null) {
                rlArr.add(new Repeat(rml));
                rml = RepeatMaskerLine.read(input);
            }
        }

        // write to output
        for (Repeat repeat : rlArr) {
            repeat.write(output);
        }
    }
}
