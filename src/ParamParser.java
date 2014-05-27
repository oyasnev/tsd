import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdOut;
import org.apache.commons.io.FilenameUtils;


/**
 * Author: Oleg Yasnev (oyasnev@gmail.com)
 * Date: 25.11.13
 */

public class ParamParser {
    public static final String INPUT_FILE_KEY           = "-i";
    public static final String REPEAT_FILE_KEY          = "-r";
    public static final String OUTPUT_FILE_KEY          = "-o";
    public static final String MERGE_KEY                = "-m";
    public static final String MERGE_THRESHOLD_KEY      = "-mt";
    public static final String TSD_LENGTH_KEY           = "-tl";
    public static final String TSD_EDIT_DISTANCE_KEY    = "-ted";
    public static final String REPEAT_LENGTH_KEY        = "-rl";
    public static final String DIST_OUT_REPEAT_KEY      = "-dor";
    public static final String DIST_INSIDE_REPEAT_KEY   = "-dir";

    public static final String README  = "README.txt";
    public static final String VERSION = "VERSION";

    public boolean state = false;

    public String  inputSeqFile      = "";
    public String  inputSeqFilename  = "";
    public String  repeatFile        = "";
    public String  outputFile        = "";
    public boolean merge             = false;
    public int     mergeThreshold    = 20;
    public int     tsdLength         = 7;
    public int     tsdEditDistance   = 3;
    public int     repeatLength      = 1000;
    public int     distOutRepeat     = 200;
    public int     distInsideRepeat  = 0;

    public ParamParser(String[] args) {
        if (args.length == 0 || args[0].equals("--help") || args[0].equals("?") || args[0].equals("-h")) {
            printHelp();
            return;
        } else if (args[0].equals("-v") || args[0].equals("--version")) {
            printVersion();
            return;
        }

        for (int i = 0; i < args.length; i++) {
            String key = args[i];
            if      (key.equals(INPUT_FILE_KEY)) {
                i++;
                inputSeqFile = args[i];
                inputSeqFilename = FilenameUtils.getBaseName(inputSeqFile);
            }
            else if (key.equals(OUTPUT_FILE_KEY)) {
                i++;
                outputFile = args[i];
            }
            else if (key.equals(REPEAT_FILE_KEY)) {
                i++;
                repeatFile = args[i];
            }
            else if (key.equals(MERGE_KEY)) {
                merge = true;
            }
            else if (key.equals(MERGE_THRESHOLD_KEY)) {
                i++;
                mergeThreshold = Integer.parseInt(args[i]);
                merge = true;  // turn on merging automatically
            }
            else if (key.equals(TSD_LENGTH_KEY)) {
                i++;
                tsdLength = Integer.parseInt(args[i]);
            }
            else if (key.equals(TSD_EDIT_DISTANCE_KEY)) {
                i++;
                tsdEditDistance = Integer.parseInt(args[i]);
            }
            else if (key.equals(REPEAT_LENGTH_KEY)) {
                i++;
                repeatLength = Integer.parseInt(args[i]);
            } else if (key.equals(DIST_OUT_REPEAT_KEY)) {
                i++;
                distOutRepeat = Integer.parseInt(args[i]);
            } else if (key.equals(DIST_INSIDE_REPEAT_KEY)) {
                i++;
                distInsideRepeat = Integer.parseInt(args[i]);
            }
        }

        // check state
        state = true;
        if (inputSeqFile.isEmpty()) {
            state = false;
            System.err.println("ERROR: Input sequence file must be specified");
        } else {
            verifyInputSeqFile();
        }
        if (repeatFile.isEmpty()) {
            state = false;
            System.err.println("ERROR: Input repeat file must be specified");
        } else {
            verifyInputRepeatFile();
        }
        if (outputFile.isEmpty()) {
            outputFile = inputSeqFilename + "_tsd";
        }
        if (mergeThreshold < 0) {
            state = false;
            System.err.println("ERROR: Merge threshold must be >= 0");
        }
        if (tsdLength <= 0) {
            state = false;
            System.err.println("ERROR: TSD minimum length must be > 0");
        }
        if (tsdEditDistance < 0) {
            state = false;
            System.err.println("ERROR: TSD maximum edit distance must be >= 0");
        }
        if (repeatLength <= 0) {
            state = false;
            System.err.println("ERROR: Repeat minimum length must be > 0");
        }

        if (!state) {
            System.err.println("For more information run the program with the key '--help'");
        }
    }

    protected static void printHelp() {
        In in = new In(README);
        while (!in.isEmpty()) {
            StdOut.println(in.readLine());
        }
    }

    protected static void printVersion() {
        In in = new In(VERSION);
        while (!in.isEmpty()) {
            StdOut.println(in.readLine());
        }
    }

    protected void verifyInputSeqFile() {
        In in = new In(inputSeqFile);
        if (!in.exists()) {
            state = false;
            return;
        }
        in.close();
        String ext = FilenameUtils.getExtension(inputSeqFile);
        if (!".fasta .fna .fa".contains('.' + ext)) {
            System.err.println("ERROR: Unsupported input sequence file extension: " + ext);
            state = false;
        }
    }

    protected void verifyInputRepeatFile() {
        In in = new In(repeatFile);
        if (!in.exists()) {
            state = false;
            return;
        }
        in.close();
        String ext = FilenameUtils.getExtension(repeatFile);
        if (!".out".contains('.' + ext)) {
            System.err.println("ERROR: Unsupported input repeat file extension: " + ext);
            state = false;
        }
    }
}
