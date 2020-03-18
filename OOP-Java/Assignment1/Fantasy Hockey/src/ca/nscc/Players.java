package ca.nscc;

public class Players {
    private String playerName;
    private int playerGoals;
    private int playerAssists;


    public Players(String playerName, int playerGoals, int playerAssists) {
        this.playerName = playerName;
        this.playerGoals = playerGoals;
        this.playerAssists = playerAssists;
    }

    public String getPlayerName() { return playerName; }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerGoals() {
        return playerGoals;
    }

    public void setPlayerGoals(int playerGoals) {
        this.playerGoals = playerGoals;
    }

    public int getPlayerAssists() {
        return playerAssists;
    }

    public void setPlayerAssists(int playerAssists) {
        this.playerAssists = playerAssists;
    }

}
