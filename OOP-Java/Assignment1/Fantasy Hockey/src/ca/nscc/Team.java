package ca.nscc;

public class Team {

    //Variables
    private String teamName;
    private int teamGoals;
    private int teamAssists;
    private int teamTotal;
    private double budget;
    private String rating;

        //Player array
    Players[] playerArray = new Players[3];

    public Team(String teamName, int teamGoals, int teamAssists, int teamTotal, double budget, String rating) {
        this.teamName = teamName;
        this.teamGoals = teamGoals;
        this.teamAssists = teamAssists;
        this.teamTotal = teamTotal;
        this.budget = budget;
        this.rating = rating;
    }

    //Team Report
    public void teamStats(){
        for (Players playerGA:playerArray) {
            int playerGoals = playerGA.getPlayerGoals();
            int playerAssists = playerGA.getPlayerAssists();
            teamAssists = teamAssists + playerAssists;
            teamGoals = teamGoals + playerGoals;
        }
        int teamTotal = teamAssists + teamGoals;

        if (teamTotal > 20){
                rating = "*** stars";
            }else if (teamTotal >= 10 && teamTotal < 20){
                rating = "** stars";
            }
            else if (teamTotal < 10 && teamTotal > 0){
                rating = "* star";
            }else{
                rating = "0 stars";
            }
        System.out.println(teamName + ": G - " + teamGoals + "   A - " + teamAssists + "   Total - " +
                (getTeamAssists() + getTeamGoals()) + "   Budget - $" + budget + "   Rating: " + rating);
    }

    //player Report
    public void playerRep(){
        for (Players playerReport:playerArray) {
            String playerName = playerReport.getPlayerName();
            int playerGoals = playerReport.getPlayerGoals();
            int playerAssists = playerReport.getPlayerAssists();
            int playerTotal = playerGoals + playerAssists;

            System.out.println(this.teamName);
            System.out.println(playerName + ": " + "G - " + playerGoals + " A - " + playerAssists + " Total - " + playerTotal);
        }
    }

    public String getTeamName() { return teamName; }

    public void setTeamName(String teamName) { this.teamName = teamName; }

    public int getTeamGoals() { return teamGoals; }

    public void setTeamGoals(int teamGoals) { this.teamGoals = teamGoals; }

    public void setTeamAssists(int teamAssists) { this.teamAssists = teamAssists; }

    public int getTeamAssists() {
            return teamAssists;
        }

    public void setBudget(double budget) { this.budget = budget; }
}