import java.util.ArrayList;
import java.util.List;

public class FixtureStatistics {
    private final List<String> teams;
    private final List<Round> rounds;
    
    public FixtureStatistics(List<String> teams, List<Round> rounds) {
        this.teams = new ArrayList<>(teams);
        this.rounds = new ArrayList<>(rounds);
    }
    
    public void displayStatistics() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("📊 FIXTURE STATISTICS");
        System.out.println("=".repeat(50));
        
        int totalTeams = teams.size();
        int totalRounds = rounds.size();
        int totalMatches = rounds.stream().mapToInt(Round::getMatchCount).sum();
        boolean hasByeWeek = teams.size() % 2 != 0;
        
        System.out.println("🏆 Total Teams         : " + totalTeams);
        System.out.println("📅 Total Weeks         : " + totalRounds);
        System.out.println("⚽ Total Matches       : " + totalMatches);
        System.out.println("🗓️  League Duration    : " + totalRounds + " weeks");
        System.out.println("🎯 Bye Week           : " + (hasByeWeek ? "Yes" : "No"));
        
        if (hasByeWeek) {
            System.out.println("ℹ️  Each team will have " + (totalRounds / totalTeams) + " bye week(s)");
        }
        
        System.out.println("📈 Matches per Team    : " + (totalMatches * 2 / totalTeams));
        System.out.println("=".repeat(50));
    }
}
