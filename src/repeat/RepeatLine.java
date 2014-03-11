package repeat;

/**
 * Author: Oleg Yasnev (oyasnev@gmail.com)
 * Date: 11.03.14
 */
public class RepeatLine {
    public long id;
    public String querySeq;
    public long posQBegin;
    public long posQEnd;
    public String repeatName;
    public String repeatClass;

    public RepeatLine() {

    }

    public RepeatLine(RepeatMaskerLine rml) {
        id = rml.id;
        querySeq = rml.querySeq;
        posQBegin = rml.posQBegin;
        posQEnd = rml.posQEnd;
        repeatName = rml.repeatName;
        repeatClass = rml.repeatClass;
    }
}
