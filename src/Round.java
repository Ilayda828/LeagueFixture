import java.util.ArrayList;
import java.util.List;

public class Round {
    private final int roundNumber;
    private final List<Match> matches;
    private final String phase;

    public Round(int roundNumber, List<Match> matches, String phase) {
        this.roundNumber = roundNumber;
        this.matches = new ArrayList<>(matches);
        this.phase = phase;
    }

    public int getRoundNumber() { return roundNumber; }
    public List<Match> getMatches() { return new ArrayList<>(matches); }
    public String getPhase() { return phase; }

    public void displayRound() {
        System.out.println("\n=== Week " + roundNumber + 
                          (phase != null ? " (" + phase + ")" : "") + " ===");
        matches.forEach(System.out::println);
    }

    public int getMatchCount() {
        return (int) matches.stream().filter(match -> !match.isByeWeek()).count();
    }
}
