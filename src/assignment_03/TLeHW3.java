
package assignment_03;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *This is a main class which includes main method, processInput method,
 * processTeamData method, processGameData method, and the generateReport method
 * 
 * @author ThienNgo N. Le
 * @version 20161014
 */
public class TLeHW3 
{
    
    // Define the listOfTeams and the listOfGames
    private static LinkedListBag<Team> listOfTeams;
    private static LinkedListBag<Game> listOfGames;
    
    // Define the files' names
    private static final String INPUT_FILE_NAME = "input/assignment_03/hw3input3.txt";
    private static final String OUTPUT_FILE_NAME = "output/hw3output3.txt";


    /**
     * This is the main method. this method will:
     * 1. Instantiate the listOfTeams and listOfGames object.
     * 2. Call the processInput method.
     * 3. Call the generateReport method.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        // Instantiate the listOfTeams and the listOfGames 
        listOfTeams = new LinkedListBag<Team>();
        listOfGames = new LinkedListBag<Game>();
        
        // Call processInput method
        processInput();
        
        // Call generateReport method
        generateReport();
        
    } // End main method
    
    /***************************************************************************
     * This is processTeamData method. This method will:
     * 1. Receive an array of type String with team data
     * 2. If the second field is ADD then it will:
     *      1. Create a Team object
     *      2. Validate the email address and display an error message using 
     *      err stream if the email address is invalid
     *      3. Will call the add method in the Bag class to add the team object
     *      to the list of teams.
     *      4. Check the return value from the add method and display a message
     *      using err stream.
     * 3. If the second field is a DEL then it will:
     *      1. Call the remove method to remove the team from the list of teams
     *      2. Check the return value from the call to remove method in step 1 
     *      and display a message using err stream
     *      3. If step 1 is a success then it will also call the remove method 
     *      to remove the corresponding games from the list of games
     *      4. Check the return value from the call to remove method in step 3 
     *      and display a message using err stream
     * 
     * @param info the information of the team.
     */
    private static void processTeamData (String[] info)
    {
        
       if (info[1].equals("ADD"))
       {
           
           Team team = new Team (info[2], info[3], info[4], info[5], info[6],
                   info[7]);
           
           if(!team.checkEmail())
           {
               
               System.err.println ("ERROR: This email address " + 
                       team.getEmailAddress() + " is an invalid email address.");
               
           } // End if
           
           if (listOfTeams.findItem(team) == null) 
           {
               
               if (listOfTeams.addNode(team)) 
               {
                   
                   System.err.println("Added to the list of teams "
                           + team.toString());
                   
               } // End if
               
           } // End if
           
           else 
           {
               
               System.err.println("Team " + info[2] + " is already added to "
                       + "the list of teams");
               
           } // End else
           
       } // End if
       
       else if (info[1].equals("DEL"))
       {
           
           Team team = new Team (info[2], null, null, null, null, null );
           
           //listOfTeams.removeMethod(team);
           
           if (listOfTeams.removeNode(team))
           {
               
               System.err.println("The team with team ID #" + team.getTeamID()
                       + " was removed from the list of teams.");
               
               Object[] list = listOfGames.toArray();
               for (int i = 0; i < list.length; i++)
               {
                   
                   Game game = (Game) list [i];
                   
                   if (game.getHomeTeamID().equals(team.getTeamID())
                           || game.getGuestTeamID().equals(team.getTeamID())) 
                   {
                       
                       if (listOfGames.removeNode(game)) 
                       {
                           
                           System.err.println("The game with game ID #" 
                                   + game.getGameID() 
                                   + " was removed from the list of games.");
                       } 
                       
                   } 
                   
               } 
               
           } 
           
       } 
       
    } 
    
    /***************************************************************************
     * This is processGameData method. This method will:
     * 1. Receive an array of type String with game data
     * 2. If the second field is ADD then it will:
     *      1. Create a Game object
     *      2. Validate the game date and display an error message using err 
     *      stream if the game date is invalid
     *      3. Validate that the home team and guest team (given by their 
     *      respective team ids) exist in the team list
     *              1. If the teams exist then call the add method in the Bag 
     *              class to add the game object to the list of games
     *              2. If any of the teams do not exist then display an error 
     *              message using the err stream
     *              3. Check the return value from the add method and display 
     *              a message using err stream.
     * 3. If the second field is a DEL then it will:
     *      1. Call the remove method to remove the game from the list of teams.
     *      2. Check the return value from the call to remove method in step 1 
     *      and display a message using err stream.
     * 
     * @param info the information of game
     */
    private static void processGameData (String[] info)
    {
        
        if (info[1].equals("ADD"))
        {
            
            int homeTeamScore = Integer.parseInt(info[6]);
            int guestTeamScore = Integer.parseInt(info[7]);
            
           Game game = new Game (info[2], info[3], info[4], info[5], 
                   homeTeamScore, guestTeamScore);
           
           if(!game.checkDateOfTheGame())
           {
               
               System.err.println ("ERROR: " + game.getDateOfTheGame() 
                       + " is an invalid date of game.");
               
           } // End if
           
           Team homeTeam = new Team (info[3], null, null, null, null, null );
           Team guestTeam = new Team (info[4], null, null, null, null, null );
           
           if(listOfTeams.findItem(homeTeam) != null 
                   && listOfTeams.findItem(guestTeam) != null)
           {
               
                if (listOfGames.addNode(game)) 
                {
                    
                    System.err.println("Added to the list of games " 
                            + game.toString());
                    
                } // End if
                
            } // End if
           
           else
           {
               
               System.err.println("Team with team Id " + info[3] 
                       + " or team with team Id " + info[4] 
                       + " was not found in the list of teams");
           }
            
       } // End if
        
       else if (info[1].equals("DEL"))
       {
           
           Game game = new Game (info[2], null, null, null, 0, 0);
           
           if (listOfGames.removeNode(game))
           {
               
               System.err.println("The game with game ID #" + game.getGameID()
                       + " was removed from the list of games.");
               
           } 
           
       } 
        
    } 
    
    /***************************************************************************
     * This is processInput method. This method will:
     * 1. Open the input file – display an error message using err stream if 
     * file is not found.
     * 2. Read the data line.
     * 3. Use split to parse the line
     * 4. If first field is TEAM then call the processTeamData method passing 
     * it the array of type String containing team data. If the first field is 
     * GAME then call the processGameData method passing it the array of type 
     * String containing game data.
     */
    public static void processInput()
    {
        
        File inputFile = new File(INPUT_FILE_NAME);
        
        try
        {
            
            Scanner scnr = new Scanner (inputFile);
            
            while (scnr.hasNext()) 
            {

                String line = scnr.nextLine();
                String[] data = line.split(",");

                if (data[0].contains("TEAM")) 
                {
                    processTeamData(data);
                    
                } // End if
                
                else if (data[0].contains("GAME")) 
                {
                    processGameData(data);
                    
                } 
                
            } 
            
        } 
        
        catch(FileNotFoundException ex)
        {
            
           System.err.println("\nERROR: The input file name " + inputFile 
                   + " was not found.\nPlease check your input file again.");
           
        } 
        
    } 
    
    /***************************************************************************
     * This is generateReport method. This method will:
     * 1. Call the toArray method in the Bag class to get a list of Team objects.
     * 2. Call the toArray method in the Bag class to get a list of Game objects.
     * 3. It will generate a report which will be written to an output file 
     */
    private static void generateReport() 
    {
        Object[] teamList = listOfTeams.toArray();
        Object[] gameList = listOfGames.toArray();
        
        try
        {
            
            File file = new File (OUTPUT_FILE_NAME);
            PrintWriter writer = new PrintWriter (file);
        
            for (int i = 0; i < teamList.length; i++) 
            {
                
                Team team = (Team) teamList[i];
                
                writer.println(team.getTeamID() + "   " + team.getTeamName() 
                        + "   " + team.getFirstName() + "   " 
                        + team.getLastName() + "   " + team.getPhoneNumber() 
                        + "   " + team.getEmailAddress() + "\nGames");
                
                String teamID = team.getTeamID();
                
                int teamScore = 0;
                int opponentTeamScore = 0;
                 
                for (int j = 0; j < gameList.length; j++) 
                {
         
                    Game game = (Game) gameList [j];
                    
                    String homeTeamID = game.getHomeTeamID();
                    String guestTeamID = game.getGuestTeamID();
                    
                    if (teamID.equals(homeTeamID))
                    {
                        
                        teamScore = teamScore + game.getHomeTeamScore();
                        opponentTeamScore = opponentTeamScore 
                                + game.getGuestTeamScore();
                        
                        if (game.getHomeTeamScore() > game.getGuestTeamScore())
                        {
                            writer.printf("%3s%1S%3S%10s%3s%3s%n", 
                                    game.getGameID(),"*", game.getGuestTeamID()
                                    ,game.getDateOfTheGame()
                                    , game.getHomeTeamScore()
                                    , game.getGuestTeamScore());
                            
                        } // End if
                        
                        else
                        {
                            
                            writer.printf("%3s%1S%3S%10s%3s%3s%n", 
                                    game.getGameID(),"", game.getGuestTeamID()
                                    , game.getDateOfTheGame()
                                    , game.getHomeTeamScore()
                                    , game.getGuestTeamScore());
                           
                        } // End else
                        
                    } // End if
                    
                    else if (teamID.equals(guestTeamID)) 
                    {
                        
                        teamScore = teamScore + game.getGuestTeamScore();
                        opponentTeamScore = opponentTeamScore 
                                + game.getHomeTeamScore();
                        
                        if (game.getGuestTeamScore() > game.getHomeTeamScore())
                        {
                            
                            writer.printf("%3s%1S%3S%10s%3s%3s%n"
                                    , game.getGameID()
                                    ,"*", game.getGuestTeamID()
                                    , game.getDateOfTheGame()
                                    , game.getGuestTeamScore()
                                    , game.getHomeTeamScore());
                           
                        } // End if
                        
                        else
                        {
                            
                            writer.printf("%3s%1S%3S%10s%3s%3s%n"
                                    , game.getGameID()
                                    ,"", game.getGuestTeamID()
                                    , game.getDateOfTheGame()
                                    , game.getGuestTeamScore()
                                    , game.getHomeTeamScore());
                            
                        } 
                        
                    } 
                       
                } 
                
                writer.println("====================================="
                        + "===========================================");
                writer.printf("%5s%15d%3d%n%n","Total",teamScore
                        , opponentTeamScore );
                
            } 
            
            // Close the output file
            writer.close();
            
        } 
        
        catch(FileNotFoundException ex)
        {
            
            System.err.println ("Error: File " + OUTPUT_FILE_NAME 
                    + " was not found.");
            
        } 
       
    } 
    
}
