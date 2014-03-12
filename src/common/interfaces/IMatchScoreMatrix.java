package common.interfaces;

/**
 * Interface for match scoring matrix
 *
 * Author: Oleg Yasnev
 */
public interface IMatchScoreMatrix {
    /**
     * Get a score of substitution for two nucleotides
     * @param ch1 nucleotide
     * @param ch2 nucleotide
     * @return a score
     */
    public int getScore(char ch1, char ch2);

    /**
     * Get a gap score
     * @return gap score
     */
    public int getGapScore();
}
