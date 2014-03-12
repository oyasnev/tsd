package common;

import common.interfaces.ISequence;
import common.interfaces.ISequenceFactory;

/**
 * Author: Oleg Yasnev (oyasnev@gmail.com)
 * Date: 18.11.13
 */
public class SequenceFactory implements ISequenceFactory {
    @Override
    public ISequence createSequence(String description, String sequence) {
        return new Sequence(description, sequence);
    }
}