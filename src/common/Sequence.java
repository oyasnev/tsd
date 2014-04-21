package common;

import common.interfaces.ISequence;

import java.awt.datatransfer.StringSelection;

/**
 * Author: Oleg Yasnev (oyasnev@gmail.com)
 * Date: 18.11.13
 */
public class Sequence implements ISequence {
    protected String description;
    protected String sequence;
    protected String revComplement = null;

    public Sequence(String description, String sequence) {
        setDescription(description);
        setSequence(sequence);
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getSequence() {
        return sequence;
    }

    @Override
    public void setSequence(String sequence) {
        this.sequence = sequence.toUpperCase();
    }

    public static String getReverseComplement(String s) {
        StringBuilder sb = new StringBuilder();
        char[] cstr = s.toCharArray();
        for (char c : cstr) {
            char complC;
            switch (c) {
                case 'A':
                    complC = 'T';
                    break;
                case 'C':
                    complC = 'G';
                    break;
                case 'G':
                    complC = 'C';
                    break;
                case 'T':
                    complC = 'A';
                    break;
                default:
                    complC = c;
            }
            sb.append(complC);
        }
        return sb.reverse().toString();
    }
}
