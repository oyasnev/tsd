package repeat;

import edu.princeton.cs.introcs.Out;

/**
 * Author: Oleg Yasnev (oyasnev@gmail.com)
 * Date: 11.03.14
 */
public class Repeat {
    public String querySeq;
    public int    posQBegin;
    public int    posQEnd;
    public String name;
    public String cls;
    public String complement;

    public Repeat() {

    }

    public Repeat(RepeatMaskerLine rml) {
        querySeq   = rml.querySeq;
        posQBegin  = rml.posQBegin;
        posQEnd    = rml.posQEnd;
        name       = rml.repeatName;
        cls        = rml.repeatClass;
        complement = rml.complement;
    }

    public void write(Out out) {
        out.printf("%s  %10d  %10d  %50s  %50s\n",
                querySeq, posQBegin, posQEnd, name, cls);
    }
}
