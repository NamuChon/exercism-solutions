import java.util.*;
import java.util.stream.Collectors;
class Tournament {
    private static final String HEADER = "Team                           | MP |  W |  D |  L |  P\n";
    private String table = HEADER;
    String printTable() {
        return table;
    }

    void applyResults(String resultString) {
        Map<String, Team> teams = new HashMap<>();
        for (String line : resultString.split("\n")) {
            String[] parts = line.split(";");
            String subject = parts[0], opponent = parts[1], result = parts[2];
            if (!teams.containsKey(subject)) teams.put(subject, new Team());
            if (!teams.containsKey(opponent)) teams.put(opponent, new Team());
            Team subjectTeam = teams.get(subject), opponentTeam = teams.get(opponent);
            switch (result) {
                case "win":
                    subjectTeam.wins++;
                    opponentTeam.losses++;
                    break;
                case "draw":
                    subjectTeam.draws++;
                    opponentTeam.draws++;
                    break;
                case "loss":
                    subjectTeam.losses++;
                    opponentTeam.wins++;
            }
        }
        table = HEADER + teams.entrySet().stream()
            .peek(e -> e.getValue().update())
            .sorted(Comparator.comparingInt((Map.Entry<String, Team> e) -> e.getValue().points).reversed().thenComparing(Map.Entry::getKey))
            .map(e -> {
                String name = e.getKey();
                Team team = e.getValue();
                return String.format("%-30s" + " |%3d".repeat(5), name, team.matchesPlayed, team.wins, team.draws, team.losses, team.points);
            })
            .collect(Collectors.joining("\n")) + "\n";
    }
    
    private class Team {
        public int matchesPlayed = 0, wins = 0, draws = 0, losses = 0, points = 0;
        public void update() {
            matchesPlayed = wins + draws + losses;
            points = 3*wins + draws;
        }
    }
}
