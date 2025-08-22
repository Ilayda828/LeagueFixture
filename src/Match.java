public class Match {
    private final String homeTeam;
    private final String awayTeam;
    private final boolean isByeWeek;

    public Match(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.isByeWeek = "Bay".equals(homeTeam) || "Bay".equals(awayTeam);
    }

    public String getHomeTeam() { return homeTeam; }
    public String getAwayTeam() { return awayTeam; }
    public boolean isByeWeek() { return isByeWeek; }

    @Override
    public String toString() {
        if (isByeWeek) {
            String activeTeam = "Bay".equals(homeTeam) ? awayTeam : homeTeam;
            return String.format("   %s - BYE (No Match)", activeTeam);
        }
        return String.format("   %s vs %s", homeTeam, awayTeam);
    }
}
