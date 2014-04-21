package repeat;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.Out;

/**
 * Author: Oleg Yasnev (oyasnev@gmail.com)
 * Date: 11.03.14
 */
public class RepeatMaskerLine {
    public int     swScore;
    public double  percDiv;
    public double  percDel;
    public double  percIns;
    public String  querySeq;
    public int     posQBegin;
    public int     posQEnd;
    public String  posQLeft;
    public Boolean complement;
    public String  repeatName;
    public String  repeatClass;
    public String  posRBegin;
    public String  posREnd;
    public String  posRLeft;
    public int     id;
    public boolean asteriks;

    public RepeatMaskerLine() {

    }

    public static RepeatMaskerLine read(In in) {
        if (in.hasNextLine()) {
            return parse(in.readLine());
        }
        return null;
    }

    public static RepeatMaskerLine parse(String line) {
        try {
            RepeatMaskerLine rml = new RepeatMaskerLine();
            line = line.trim();
            String[] fields = line.split("\\s+");
            rml.swScore = Integer.parseInt(fields[0]);
            rml.percDiv = Double.parseDouble(fields[1]);
            rml.percDel = Double.parseDouble(fields[2]);
            rml.percIns = Double.parseDouble(fields[3]);
            rml.querySeq = fields[4];
            rml.posQBegin = Integer.parseInt(fields[5]);
            rml.posQEnd = Integer.parseInt(fields[6]);
            rml.posQLeft = fields[7];
            rml.complement = fields[8].equals("C");
            rml.repeatName = fields[9];
            rml.repeatClass = fields[10];
            rml.posRBegin = fields[11];
            rml.posREnd = fields[12];
            rml.posRLeft = fields[13];
            rml.id = Integer.parseInt(fields[14]);
            rml.asteriks = (fields.length == 16 && fields[15].equals("*"));
            return rml;
        } catch (Exception e) {
            return null;
        }
    }
}
