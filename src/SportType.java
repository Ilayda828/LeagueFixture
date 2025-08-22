import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
enum SportType {
    FOOTBALL("⚽ Football", "Super League", Arrays.asList(
        "Galatasaray", "Fenerbahçe", "Beşiktaş", 
        "Trabzonspor", "Başakşehir", "Bursaspor"
    )),
    VOLLEYBALL("🏐 Women's Volleyball", "Sultanlar League", Arrays.asList(
        "FENERBAHÇE MEDICANA", "ECZACIBAŞI DYNAVİT", "VAKIFBANK",
        "TÜRK HAVA YOLLARI", "GALATASARAY DAIKIN", "KUZEYBORU"
    )),
    BASKETBALL("🏀 Basketball", "BSL", Arrays.asList(
        "Anadolu Efes", "Bandırma", "Beşiktaş",
        "Darüşşafaka", "Fenerbahçe", "Galatasaray"
    ));

    private final String displayName;
    private final String leagueName;
    private final List<String> defaultTeams;

    SportType(String displayName, String leagueName, List<String> defaultTeams) {
        this.displayName = displayName;
        this.leagueName = leagueName;
        this.defaultTeams = new ArrayList<>(defaultTeams);
    }

    public String getDisplayName() { return displayName; }
    public String getLeagueName() { return leagueName; }
    public List<String> getDefaultTeams() { return new ArrayList<>(defaultTeams); }
}

