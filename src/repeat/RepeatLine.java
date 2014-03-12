package repeat;

import edu.princeton.cs.introcs.Out;

/**
 * Author: Oleg Yasnev (oyasnev@gmail.com)
 * Date: 11.03.14
 */
public class RepeatLine {
    public String querySeq;
    public int posQBegin;
    public int posQEnd;
    public String repeatName;
    public String repeatClass;

    public RepeatLine() {

    }

    public RepeatLine(RepeatMaskerLine rml) {
        querySeq = rml.querySeq;
        posQBegin = rml.posQBegin;
        posQEnd = rml.posQEnd;
        repeatName = rml.repeatName;
        repeatClass = rml.repeatClass;
    }

    public void write(Out out) {
        out.printf("%s  %10d  %10d  %50s  %50s\n",
                querySeq, posQBegin, posQEnd, repeatName, repeatClass);
    }
}
