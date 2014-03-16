package tsd;

/**
 * Author: Oleg Yasnev (oyasnev@gmail.com)
 * Date: 16.03.14
 */
public class TSD {
    public int startPos;
    public int endPos;
    public String start;
    public String end;
    public String alignedStart;
    public String alignedEnd;
    public int score;
    public int dist;
    public String repeatName;
    public String repeatClass;

    public TSD(String start, String end, int score, int dist) {
        this.start = start;
        this.end = end;
        this.score = score;
        this.dist = dist;
    }

    public int getHeuristicScore() {
        return score - (dist + 1) * (dist + 1);
    }
}
