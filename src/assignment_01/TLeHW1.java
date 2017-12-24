package assignment_01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This is the Main class. This class will be used to test the assignment_01.Team and
 * assignment_01.Game classes
 * 
 * @author Thien Ngo N. Le
 * @version 20160907
 */
public class TLeHW1 
{

    /**
     * This is the main method. This method will run three sets of tests.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        
        //Define the input file's name
        final String INPUT_FILENAME = "input/hw1input.txt";
        
         //Test Set 1: this first set of tests will test the constructors with 
         //parameters for each attribute and the toString() method for each class.
         
         //Dispaly the message to the user.
        System.out.println("\nThis is Test Set #1.\n" 
                + "This first set of tests will test the constructors with\n" 
                + "parameters for each attribute and the toString() method\n" 
                + "for each class.");
        
        // Test 1a
        System.out.println("\nRunning Test 1a");
        Team team1 = new Team("1","Road Runner", "David", "Brown", 
                "303-123-4567", "roadrunner@gmail.com");
        System.out.println(team1.toString());
        
        // Test 1b
        System.out.println("\nRunning Test 1b");
        Game game1 = new Game("101", "1", "2", "20160105", 4, 3);
        System.out.println(game1.toString());
        
        //Test Set 2: this second set of tests will test the constructors 
        //& getters,and will also test reading data from the input data file.
        
        //Display the message to the user.
        System.out.println("\nThis is Test Set #2.\n" 
                + "This second set of tests will test the constructors and\n" 
                + "getters. This test will also test reading data from the\n" 
                + "input data file.");
        
        // Access the input file
        File inputFile = new File(INPUT_FILENAME);
        
        try
        {
            // Test 2a
            System.out.println("\nRunning Test 2a");
            Scanner scnr = new Scanner (inputFile);
            String line = scnr.nextLine();
            String data[] = line.split(",");
            Team team2 = setTeamAttribute(data);
            if (data[0].equals("TEAM"))
            {
                boolean result = team2.emailCheck ();
                if (result == false)
                {
                    System.err.println(team2.getEmailAddress() + " is an "
                            + "invalide email address.");
                }
                
                getTeamAttribute (team2);
            }
            
            // Test 2b
            System.out.println("\nRunning Test 2b");
            String line2 = scnr.nextLine();
            String info[] = line2.split(",");
            
            Game game2 = setGameAttribute(info);
            if (info[0].equals("GAME"))
            {
                boolean result = game2.dateOfGameCheck();
                if (result == false)
                {
                    System.err.println(game2.getDateOfTheGame() + " is an "
                            + "invalid game date");
                }
                
                getGameAttribute (game2);
            }
            
            //Test Set 3: this third set of tests will test the equals() methods 
            //for both classes.
            
            //Dispaly the message to the user.
            System.out.println("\nThis is Test Set #3\n" 
                    + "This third set of tests will test the equals() methods\n" 
                    + "for both classes assignment_01.Team and assignment_01.Game");
            
            // Test 3a
            System.out.println("\nRunning Test 3a");
            
            if(team2.equals(team1))
            {    
                System.out.println("\nThe two assignment_01.Team objects are equal");
            }
            
            else 
            {  
                System.out.println("\nThe two assignment_01.Team objects are not equal");
            }
            
            // Test 3b
            System.out.println("\nRunning Test 3b");
            
            if(game2.equals(game1))
            {   
                System.out.println("\nThe two assignment_01.Game objects are equal");
            }
            
            else 
            {
                System.out.println("\nThe two assignment_01.Game objects are not equal");
            }
            
        }// End try
        
        catch(FileNotFoundException ex)
        {
            System.err.println("\nThe input file: " + inputFile + " was not found."
                    + "\nPlease try with another input file.");
        }// End catch
        
    }// End main method
    
    /***************************************************************************
     * This is setTeamAttribute method. This method will create the assignment_01.Team object
     * using the data given in the array and will return a assignment_01.Team object.
     * 
     * @param data the information of objects in assignment_01.Team class
     * 
     * @return team
     */
    private static Team setTeamAttribute (String[] data)
    {
        String teamID = data[1];
        String teamName = data[2];
        String firstName = data[3];
        String lastName = data[4];
        String phoneNumber = data[5];
        String emailAddress = data[6];
        
        Team team2 = new Team(teamID, teamName,  firstName, 
            lastName,  phoneNumber,  emailAddress);
        return team2;
        
    }// End setTeamAttribute
    
    /***************************************************************************
     * This is getTeamAttribute method. This method will use getters to display 
     * each attribute of the assignment_01.Team.
     * 
     * @param team the objects in assignment_01.Team class
     */
    private static void getTeamAttribute(Team team )
    {  
        System.out.println ("assignment_01.Team ID: " + team.getTeamID());
        System.out.println ("assignment_01.Team name: " + team.getTeamName());
        System.out.println ("assignment_01.Team member's first name: " + team.getFirstName());
        System.out.println ("assignment_01.Team member's last name: " + team.getLastName());
        System.out.println ("assignment_01.Team phone number: " + team.getPhoneNumber());
        System.out.println ("assignment_01.Team email address: " + team.getEmailAddress());
        
    }// End getTeamAttribute
    
    /***************************************************************************
     * This is setGameAttribute method. This method will create the assignment_01.Game object
     * using the data given in the array and will return a assignment_01.Game object.
     * 
     * @param info the in formation of objects in assignment_01.Game class
     * 
     * @return game
     */
    private static Game setGameAttribute (String[] info)
    {
        String gameID = info[1];
        String homeTeamID = info[2];
        String guestTeamID = info[3];
        String dateOfTheGame = info[4];
        String homeTeamScore = info[5];
        String guestTeamScore = info[6];
        
        // Convert String to integer
        int homeTeamScoreInt = Integer.parseInt(homeTeamScore);
        int guestTeamScoreInt = Integer.parseInt(guestTeamScore);
        
        Game game2 = new Game(gameID, homeTeamID,  guestTeamID, 
            dateOfTheGame,  homeTeamScoreInt,  guestTeamScoreInt);
        return game2;  
        
    }// End setGameAttribute
    
    /***************************************************************************
     * This is getGameAttribute method. This method will use getters to display 
     * each attribute of the assignment_01.Game.
     * 
     * @param game the objects in assignment_01.Game class
     */
    private static void getGameAttribute(Game game )
    {  
        System.out.println ("assignment_01.Game ID: " + game.getGameID());
        System.out.println ("Home team ID: " + game.getHomeTeamID());
        System.out.println ("Guest team ID: " + game.getGuestTeamID());
        System.out.println ("Date of the game: " + game.getDateOfTheGame());
        System.out.println ("Home team score: " + game.getHomeTeamScore());
        System.out.println ("Guest team score: " + game.getGuestTeamScore());   
        
    }// End getGameAttribute
    
}// End main class 

