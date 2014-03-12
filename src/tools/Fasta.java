package tools;

import common.interfaces.ISequence;
import common.interfaces.ISequenceFactory;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.Out;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Basic tool for working with FASTA file format
 *
 * Author: Oleg Yasnev
 */
public class Fasta {
    /**
     * Symbol opening a description line
     */
    public static final String DESCR_SYMBOL = ">";

    /**
     * Read sequences from the file and write them to the array list
     * @param filename file with sequences in FASTA format
     * @param sequenceFactory common factory
     */
    public static ArrayList<? extends ISequence> readSequences(String filename, ISequenceFactory sequenceFactory) {
        In in = new In(filename);
        ArrayList<ISequence> sequences = new ArrayList<ISequence>();
        String descr = "";                               // common description
        StringBuilder sb = new StringBuilder();          // string builder for multi-line strings
        boolean strFlag = false;                         // true if there is a string in the file
        String line;                                     // line read from the file
        while (!in.isEmpty()) {
            line = in.readLine();
            if (line.startsWith(DESCR_SYMBOL)) {
                // save previous string (if there is) and prepare for a new one
                if (strFlag) {
                    sequences.add(sequenceFactory.createSequence(descr, sb.toString()));
                }
                strFlag = true;
                descr = line.substring(DESCR_SYMBOL.length());
                sb = new StringBuilder();
            } else {
                // concat multi-line string
                sb.append(line);
            }
        }
        if (strFlag) {
            sequences.add(sequenceFactory.createSequence(descr, sb.toString()));
        }
        in.close();
        return sequences;
    }

    public static Map<String, ? extends ISequence> readMap(String filename, ISequenceFactory sequenceFactory) {
        ArrayList<? extends ISequence> arrayList = readSequences(filename, sequenceFactory);
        Map<String, ISequence> map = new HashMap<String, ISequence>(arrayList.size());
        for (ISequence sequence : arrayList) {
            map.put(sequence.getDescription(), sequence);
        }
        return map;
    }

    public static void writeSequences(String filename, ISequence[] sequences) {
        Out out = new Out(filename);
        for (ISequence sequence : sequences) {
            writeSequence(sequence, out);
        }
        out.close();
    }

    public static void writeSequences(String filename, ArrayList<? extends ISequence> sequences) {
        Out out = new Out(filename);
        for (ISequence sequence : sequences) {
            writeSequence(sequence, out);
        }
        out.close();
    }

    protected static void writeSequence(ISequence sequence, Out out) {
        out.print(DESCR_SYMBOL);
        out.println(sequence.getDescription());
        out.println(sequence.getSequence());
    }
}
