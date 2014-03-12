package common;

import common.interfaces.ISequence;

/**
 * Author: Oleg Yasnev (oyasnev@gmail.com)
 * Date: 18.11.13
 */
public class Sequence implements ISequence {
    protected String description;
    protected String sequence;

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
}
