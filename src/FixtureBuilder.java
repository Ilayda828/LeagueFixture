import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FixtureBuilder {
    
    public List<Round> generateDoubleRoundRobin(List<String> teams) {
        List<String> workingTeams = new ArrayList<>(teams);
        
        // If odd number of teams, add BYE
        if (workingTeams.size() % 2 != 0) {
            workingTeams.add("Bay");
        }
        
        List<Round> allRounds = new ArrayList<>();
        
        // First half
        List<Round> firstHalf = generateSingleRoundRobin(workingTeams, "First Half");
        allRounds.addAll(firstHalf);
        
        // Second half (swap home and away)
        List<Round> secondHalf = generateSecondHalf(firstHalf, "Second Half");
        allRounds.addAll(secondHalf);
        
        return allRounds;
    }
    
    private List<Round> generateSingleRoundRobin(List<String> teams, String phase) {
        List<Round> rounds = new ArrayList<>();
        List<String> workingTeams = new ArrayList<>(teams);
        int n = workingTeams.size();
        
        for (int round = 0; round < n - 1; round++) {
            List<Match> matches = new ArrayList<>();
            
            // Create matches for each round
            for (int i = 0; i < n / 2; i++) {
                String homeTeam = workingTeams.get(i);
                String awayTeam = workingTeams.get(n - 1 - i);
                matches.add(new Match(homeTeam, awayTeam));
            }
            
            rounds.add(new Round(round + 1, matches, phase));
            
            // Rotate teams (keep the first one fixed)
            if (n > 2) {
                String temp = workingTeams.get(1);
                for (int i = 1; i < n - 1; i++) {
                    workingTeams.set(i, workingTeams.get(i + 1));
                }
                workingTeams.set(n - 1, temp);
            }
        }
        
        return rounds;
    }
    
    private List<Round> generateSecondHalf(List<Round> firstHalf, String phase) {
        List<Round> secondHalf = new ArrayList<>();
        
        for (Round round : firstHalf) {
            List<Match> reversedMatches = round.getMatches().stream()
                .map(match -> new Match(match.getAwayTeam(), match.getHomeTeam()))
                .collect(Collectors.toList());
            
            int newRoundNumber = round.getRoundNumber() + firstHalf.size();
            secondHalf.add(new Round(newRoundNumber, reversedMatches, phase));
        }
        
        return secondHalf;
    }
}