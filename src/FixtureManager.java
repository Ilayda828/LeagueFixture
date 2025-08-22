import java.util.*;

class FixtureManager {
    private Scanner scanner;
    private FixtureBuilder fixtureBuilder;
    private List<String> currentTeams;
    private SportType selectedSport;

    public FixtureManager() {
        this.scanner = new Scanner(System.in);
        this.fixtureBuilder = new FixtureBuilder();
        this.currentTeams = new ArrayList<>();
    }

    public void startApplication() {
        displayWelcome();

        while (true) {
            try {
                selectedSport = selectSport();
                if (selectedSport == null) break;

                currentTeams.clear();
                manageTeams();

                if (!currentTeams.isEmpty()) {
                    generateAndDisplayFixture();
                }
                if (!askToContinue()) break;

            } catch (Exception e) {
                System.out.println("❌ An error occurred: " + e.getMessage());
                System.out.println("Please try again...\n");
                scanner.nextLine(); // Clear buffer after error
            }
        }

        System.out.println("🎉 Thank you for using the fixture generator!");
        scanner.close();
    }

    private boolean askToContinue() {
        System.out.print("\nWould you like to generate another fixture? (y/n): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return !response.equals("y") && !response.equals("yes");
    }

    private void displayWelcome() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("🏆  MULTI-SPORT FIXTURE GENERATOR  🏆");
        System.out.println("=".repeat(60));
        System.out.println("Create a professional double round-robin fixture");
        System.out.println("for Football, Volleyball, and Basketball!");
        System.out.println("=".repeat(60));
    }

    private SportType selectSport() {
        System.out.println("\n🎯 SELECT SPORT TYPE");
        System.out.println("-".repeat(30));

        SportType[] sports = SportType.values();
        for (int i = 0; i < sports.length; i++) {
            SportType sport = sports[i];
            System.out.printf("%d. %s (%s) - %d teams available\n",
                    i + 1, sport.getDisplayName(), sport.getLeagueName(),
                    sport.getDefaultTeams().size());
        }
        System.out.println("0. Exit");

        System.out.print("\nMake your selection (0-" + sports.length + "): ");

        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            if (choice == 0) return null;
            if (choice >= 1 && choice <= sports.length) {
                SportType selected = sports[choice - 1];
                System.out.println("\n✅ " + selected.getDisplayName() + " selected!");
                return selected;
            } else {
                System.out.println("❌ Invalid selection! Please try again.");
                return selectSport();
            }
        } catch (InputMismatchException e) {
            System.out.println("❌ Please enter a valid number!");
            scanner.nextLine(); // Clear buffer
            return selectSport();
        }
    }
    private void manageTeams() {
        System.out.println("\n⚙️  TEAM MANAGEMENT - " + selectedSport.getDisplayName());
        System.out.println("-".repeat(40));

        while (true) {
            displayTeamMenu();
            int choice = getMenuChoice(6);

            switch (choice) {
                case 1:
                    loadDefaultTeams();
                    break;
                case 2:
                    addCustomTeam();
                    break;
                case 3:
                    removeTeam();
                    break;
                case 4:
                    shuffleTeams();
                    break;
                case 5:
                    displayCurrentTeams();
                    break;
                case 6:
                    if (currentTeams.size() >= 2) {
                        return;
                    } else {
                        System.out.println("❌ At least 2 teams are required!");
                    }
                    break;
                default:
                    System.out.println("❌ Invalid choice!");
            }
        }
    }

    private int getMenuChoice(int maxChoice) {
        System.out.print("\nEnter your choice (1-" + maxChoice + "): ");
        
        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer
            
            if (choice >= 1 && choice <= maxChoice) {
                return choice;
            } else {
                System.out.println("❌ Please enter a number between 1 and " + maxChoice);
                return getMenuChoice(maxChoice);
            }
        } catch (InputMismatchException e) {
            System.out.println("❌ Please enter a valid number!");
            scanner.nextLine(); // Clear buffer
            return getMenuChoice(maxChoice);
        }
    }

    private void displayTeamMenu() {
        System.out.println("\n📋 Team Management Menu:");
        System.out.println("1. Load default teams (" + selectedSport.getDefaultTeams().size() + " teams)");
        System.out.println("2. Add a new team");
        System.out.println("3. Remove a team");
        System.out.println("4. Shuffle teams");
        System.out.println("5. Show current teams");
        System.out.println("6. Generate fixture");
        System.out.println("\n📊 Current number of teams: " + currentTeams.size());
    }

    private void loadDefaultTeams() {
        currentTeams.clear();
        currentTeams.addAll(selectedSport.getDefaultTeams());
        System.out.println("✅ " + selectedSport.getDisplayName() + " - " +
                currentTeams.size() + " default teams loaded!");
        displayCurrentTeams();
    }

    private void addCustomTeam() {
        System.out.print("Enter the name of the new team: ");
        String teamName = scanner.nextLine().trim();

        if (teamName.isEmpty()) {
            System.out.println("❌ Team name cannot be empty!");
            return;
        }

        if (currentTeams.contains(teamName)) {
            System.out.println("❌ This team is already in the list!");
            return;
        }

        currentTeams.add(teamName);
        System.out.println("✅ '" + teamName + "' has been added!");
    }

    private void removeTeam() {
        if (currentTeams.isEmpty()) {
            System.out.println("❌ No teams to remove!");
            return;
        }

        displayCurrentTeams();
        System.out.print("Enter the number of the team to remove (1-" + currentTeams.size() + "): ");

        try {
            int index = scanner.nextInt() - 1;
            scanner.nextLine();

            if (index >= 0 && index < currentTeams.size()) {
                String removedTeam = currentTeams.remove(index);
                System.out.println("✅ '" + removedTeam + "' has been removed!");
            } else {
                System.out.println("❌ Invalid number!");
            }
        } catch (InputMismatchException e) {
            System.out.println("❌ Please enter a valid number!");
            scanner.nextLine();
        }
    }

    private void shuffleTeams() {
        if (currentTeams.size() < 2) {
            System.out.println("❌ At least 2 teams are required to shuffle!");
            return;
        }

        Collections.shuffle(currentTeams);
        System.out.println("🔄 Teams shuffled!");
        displayCurrentTeams();
    }

    private void displayCurrentTeams() {
        if (currentTeams.isEmpty()) {
            System.out.println("\n📝 No teams have been added yet.");
            return;
        }

        System.out.println("\n📝 Current Teams:");
        System.out.println("-".repeat(25));
        for (int i = 0; i < currentTeams.size(); i++) {
            System.out.printf("%2d. %s\n", i + 1, currentTeams.get(i));
        }
        System.out.println("-".repeat(25));
    }

    private void generateAndDisplayFixture() {
        System.out.println("\n🎲 Generating fixture...");

        List<Round> fixture = fixtureBuilder.generateDoubleRoundRobin(currentTeams);

        FixtureStatistics stats = new FixtureStatistics(currentTeams, fixture);
        stats.displayStatistics();

        displayFixture(fixture);

        offerSaveToFile(fixture);
    }

    private void displayFixture(List<Round> fixture) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("📅 " + selectedSport.getDisplayName().toUpperCase() + " FIXTURE");
        System.out.println("=".repeat(60));

        for (Round round : fixture) {
            round.displayRound();
        }

        System.out.println("\n" + "=".repeat(60));
    }

    private void offerSaveToFile(List<Round> fixture) {
        System.out.print("\nWould you like to save the fixture to a file? (y/n): ");
        String response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("y") || response.equals("yes")) {
            saveFixtureToFile(fixture);
        }
    }

	private void saveFixtureToFile(List<Round> fixture) {
		// TODO Auto-generated method stub
		
	}
}