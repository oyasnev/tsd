package repeat;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.Out;

/**
 * Author: Oleg Yasnev (oyasnev@gmail.com)
 * Date: 11.03.14
 */
public class RepeatMaskerLine {
    public long swScore;
    public double percDiv;
    public double percDel;
    public double percIns;
    public String querySeq;
    public long posQBegin;
    public long posQEnd;
    public String posQLeft;
    public String complement;
    public String repeatName;
    public String repeatClass;
    public String posRBegin;
    public String posREnd;
    public String posRLeft;
    public long id;
    public boolean asteriks;

    public RepeatMaskerLine() {

    }

    public boolean read(In in) {
        return in.hasNextLine() && parse(in.readLine());
    }

    public boolean parse(String line) {
        try {
            line = line.trim();
            String[] fields = line.split("\\s+");
            swScore = Long.parseLong(fields[0]);
            percDiv = Double.parseDouble(fields[1]);
            percDel = Double.parseDouble(fields[2]);
            percIns = Double.parseDouble(fields[3]);
            querySeq = fields[4];
            posQBegin = Long.parseLong(fields[5]);
            posQEnd = Long.parseLong(fields[6]);
            posQLeft = fields[7];
            complement = fields[8];
            repeatName = fields[9];
            repeatClass = fields[10];
            posRBegin = fields[11];
            posREnd = fields[12];
            posRLeft = fields[13];
            id = Long.parseLong(fields[14]);
            asteriks = (fields.length == 16 && fields[15].equals("*"));
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
