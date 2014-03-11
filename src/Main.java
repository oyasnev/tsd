import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.Out;
import repeat.MergeFilter;

public class Main {

    public static void main(String[] args) {
        In input = new In("merge_test.out");
        Out output = new Out("merge_test_output.out");
        MergeFilter mergeFilter = new MergeFilter(input, output, 20);
    }
}
