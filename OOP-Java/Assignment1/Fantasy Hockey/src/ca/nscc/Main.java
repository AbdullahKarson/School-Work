package ca.nscc;

import java.util.Scanner;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        //Variables
        String teamName = "";

            //Team Array
        Team[] teamArray = new Team[3];
//        Team team1 = new Team();
//        Team team2 = new Team();
//        Team team3 = new Team();
//        teamArray[0] = team1;
//        teamArray[1] = team2;
//        teamArray[2] = team3;
            //

        int maxBudget = 1000000;
        float randomBudget;
        String playerName;
        int playerGoals;
        int playerAssist;

        //Scanner
        Scanner sc = new Scanner(System.in);

        //Welcome message to the user
        System.out.println("\nFantasy Hockey Application");
        System.out.println("Team Entry");
        System.out.println("===========================\n");

        //Input of Team Names
        for (int i = 0; i < teamArray.length; i++) {
            //Team Name Validation
            do{
                System.out.print("Enter name for team #" + (i + 1) + ": ");
                teamName = sc.nextLine();
            }while (teamName.length() < 3 && !sc.hasNextLine());
            //Random Budget Calculation
            Random rand = new Random();
            randomBudget = rand.nextInt(maxBudget);
            teamArray[i] = new Team(teamName, 0,0 , 0,randomBudget, "");
        }

        System.out.println("\nPlayer Entry");
        System.out.println("===========================\n");

        //Player Name Entry and Their Goals/Assists
        for (Team playersInTeam:teamArray) {
            teamName = playersInTeam.getTeamName();
            System.out.print("\nEnter players for " + teamName + ":");
            for (int i = 0; i < playersInTeam.playerArray.length; i++) {
                sc = new Scanner(System.in);
                //Player Name Validation
                do {
                    System.out.print("\nEnter name for player #" + (i + 1) + ": ");
                    playerName = sc.nextLine();
                }while (playerName.length() < 3 && !sc.hasNextLine());
                playersInTeam.playerArray[i] = new Players(playerName,0,0);
                //Player Goals Validation
                do {
                    System.out.print("\nEnter number of Goals for " + playersInTeam.playerArray[i].getPlayerName() + ": ");
                    playerGoals = sc.nextInt();
                }while (playerGoals < 0 && !sc.hasNextInt());
                playersInTeam.playerArray[i] = new Players(playerName,playerGoals,0);
                //Player Assists Validation
                do {
                    System.out.print("\nEnter number of assists for " + playersInTeam.playerArray[i].getPlayerName() + ": ");
                    playerAssist = sc.nextInt();
                }while (playerAssist < 0 && !sc.hasNextInt());
                playersInTeam.playerArray[i] = new Players(playerName,playerGoals,playerAssist);
                //Total team Assists and Goals
                playersInTeam.setTeamAssists(playersInTeam.getTeamAssists() + playerAssist);
                playersInTeam.setTeamGoals(playersInTeam.getTeamGoals() + playerGoals);
            }
        }

        System.out.println("\nREPORT: Stats per Team");
        System.out.println("===========================\n");

        //The team report from the Team Class
        for (Team teamReports:teamArray) {
            teamReports.teamStats();
        }

        System.out.println("\n");

        //The Player Report retrieved from the Player Class
        for (Team playerReport:teamArray){
            playerReport.playerRep();
        }
    }
}
