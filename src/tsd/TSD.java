package tsd;

import repeat.Repeat;

/**
 * Author: Oleg Yasnev (oyasnev@gmail.com)
 * Date: 16.03.14
 */
public class TSD {
    public Repeat  repeat;
    public boolean hasTSD = false;
    public int     startPos;
    public int     endPos;
    public String  start;
    public String  end;
    public String  alignedStart;
    public String  alignedEnd;
    public int     score;
    public int     dist;

    public TSD() {

    }

    public TSD(String start, String end, int score, int dist) {
        this.start = start;
        this.end   = end;
        this.score = score;
        this.dist  = dist;
    }

    public int getHeuristicScore() {
        return score - (dist + 1) * (dist + 1);
    }
}
