package repeat;

import java.util.ArrayList;

/**
 * Author: Oleg Yasnev (oyasnev@gmail.com)
 * Date: 16.03.14
 */
public class RepeatFilter {
    public static ArrayList<Repeat> filter(ArrayList<Repeat> sourceList, int repeatLength) {
        ArrayList<Repeat> resList = new ArrayList<Repeat>();
        for (Repeat repeat : sourceList) {
            /*String cls = repeat.cls;
            if (cls.contains("LINE") || cls.contains("LTR") || cls.contains("RC") || cls.contains("DNA")
                    || cls.contains("SINE") || cls.contains("Alu")) {
                resList.add(repeat);
            } */
            String cls = repeat.cls;
            if (cls.contains("L1") && (repeat.posQEnd - repeat.posQBegin >= repeatLength)) {
                resList.add(repeat);
            }
        }
        return resList;
    }
}
