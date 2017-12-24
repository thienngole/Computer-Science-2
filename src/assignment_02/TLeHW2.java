
package assignment_02;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This is the main class. This class includes a main method, 
 * processTeamData method, processGameData method, processInput method, 
 * and generateReport method.
 * 
 * @author Thien Ngo N. Le
 * 
 * @version 20160926
 */
public class TLeHW2 
{
    // Define the listOfTeams and the listOfGames
    private static ArrayBag<Team> listOfTeams;
    private static ArrayBag<Game> listOfGames;
    
    // Define the files' names
    private static final String INPUT_FILE_NAME = "input/hw2input1.txt";
    private static final String OUTPUT_FILE_NAME = "output/hw2output1.txt";

    /**
     * This is the main method. This method instantiate the listOfTeams and 
     * the listOfGames, then calls processInput method and generateReport method.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        // Instantiate the listOfTeams and the listOfGames 
        listOfTeams = new ArrayBag<Team>(100);
        listOfGames = new ArrayBag<Game>(100);
        
        // Call processInput method
        processInput();
        
        // Call generateReport method
        generateReport();
        
    }// End main method
    
    /***************************************************************************
     * This is processTeamData method. This method receives an array of type 
     * String with team data, checks information of the team to add or remove 
     * information.
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
           
           if (listOfTeams.findMethod(team) == -1) 
           {
               if (listOfTeams.addMethod(team)) 
               {
                   System.err.println("Added to the list of teams "
                           + team.toString());
                   
               } // End if
               
           } // End if
           
           else 
           {
               System.err.println("This team " + team.toString() 
                       + " is already added to the list of teams");
               
           } // End else
           
       } // End if
       
       else if (info[1].equals("DEL"))
       {
           Team team = new Team (info[2], null, null, null, null, null );
           
           //listOfTeams.removeMethod(team);
           
           if (listOfTeams.removeMethod(team))
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
                       if (listOfGames.removeMethod(game)) 
                       {
                           System.err.println("The game with game ID #" 
                                   + game.getGameID() 
                                   + " was removed from the list of games.");
                       } // End if
                       
                   } // End if
                   
               } // End for
               
           } // End if
           
       } // End else if
       
    } // End processTeamData
    
    /***************************************************************************
     * This is processGameData method. This method receives an array of type 
     * String with game data, checks information of the game to add or remove 
     * information.
     * 
     * @param info the information of the game.
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
           
           if(listOfTeams.findMethod(homeTeam)!=-1 
                   && listOfTeams.findMethod(guestTeam)!=-1)
           {
               
                if (listOfGames.addMethod(game)) 
                {
                    System.err.println("Added to the list of games " 
                            + game.toString());
                    
                } // End if
                
            } // End if
            
       } // End if
        
       else if (info[1].equals("DEL"))
       {
           
           Game game = new Game (info[2], null, null, null, 0, 0);
           
           if (listOfGames.removeMethod(game))
           {
               System.err.println("The game with game ID #" + game.getGameID()
                       + " was removed from the list of games.");
               
           } // End if
           
       } // End else if
        
    } // End processGameData
    
    /***************************************************************************
     * This is processInput method. This method opens and checks the input file 
     * then calls the processTeamData and processGameData to process the 
     * information.
     * 
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
                    
                } // End else if
                
            } // End while
            
        } // End try
        
        catch(FileNotFoundException ex)
        {
           System.err.println("\nERROR: The input file name " + inputFile 
                   + " was not found.\nPlease check your input file again.");
           
        } // End catch exception
        
    } // End processInput 
    
    /***************************************************************************
     * This is generateReport method. This method calls the toArray method in 
     * the ArrayBag class to get a list of Team objects and Game objects, 
     * then generates a report and writes it to an output file
     * 
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
                            
                        } // End else
                        
                    } // End else if
                       
                } // End inter for
                
                writer.println("====================================="
                        + "===========================================");
                writer.printf("%5s%15d%3d%n%n","Total",teamScore
                        , opponentTeamScore );
                
            } // End for
            
            // Close the output file
            writer.close();
            
        } // End try
        
        catch(FileNotFoundException ex)
        {
            System.err.println ("Error: File " + OUTPUT_FILE_NAME 
                    + " was not found.");
            
        } // End catch exception
       
    } // End generateReport
    
}// End main class
