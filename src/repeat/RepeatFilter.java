package repeat;

import java.util.ArrayList;

/**
 * Author: Oleg Yasnev (oyasnev@gmail.com)
 * Date: 16.03.14
 */
public class RepeatFilter {
    public static ArrayList<RepeatLine> filter(ArrayList<RepeatLine> sourceList) {
        ArrayList<RepeatLine> resList = new ArrayList<RepeatLine>();
        for (RepeatLine repeat : sourceList) {
            String cls = repeat.repeatClass;
            if (cls.contains("LINE") || cls.contains("LTR") || cls.contains("RC") || cls.contains("DNA")
                    || cls.contains("SINE") || cls.contains("Alu")) {
                resList.add(repeat);
            }
        }
        return resList;
    }
}
