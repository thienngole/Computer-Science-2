
package assignment_06;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This is the main class. This class includes a main method, 
 * processTeamData method, processInput method, and generateReport method.
 * 
 * @author Thien Ngo N. Le
 * @version 20161130
 */
public class TLeHW6 
{
    // Define the listOfTeams and the listOfGames
    private static SortedLinkedList<Team> listOfTeams;
    
    // Define the intput file's names
    private static final String INPUT_FILE_NAME = "input/hw6input.txt";
    
    // Define the output files' names
    private static final String OUTPUT_FILE_NAME = "output/hw6output.txt";

    /**
     * This is the main method. This method will:
     * 1. Instantiate the listOfTeams object 
     * 2. Call the processInput method 
     * 3. Call the generateReport(OUTPUT_FILE_NAME1) metho
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        // Instantiate the listOfTeams and the listOfGames 
        listOfTeams = new SortedLinkedList<Team>();
        
        // Call processInput method
        processInput();
        
        // Call generateReport method
        generateReport(OUTPUT_FILE_NAME);
        
    }// End main method
    
    /***************************************************************************
     * This is processTeamData method. This method will:
     * 1. Receive an array of type String with team data
     * 2. If the second field is ADD then it will:
     *      1. Create a Team object
     *      2. Will call the add method in the SortedList class to add the team 
     *      object to the list of teams.
     *      3. Check the return value from the add method and display a message 
     *      using err stream.
     * 3. If the second field is a DEL then it will:
     *      1. Call the remove method in the SortedList to remove the team from 
     *      the list of teams
     *      2. Check the return value from the call to remove method in step 1 
     *      and display a message using err stream
     * 
     * @param info the information of the team.
     */
    private static void processTeamData (String[] info)
    {
       if (info[1].contains("ADD"))
       {
           
           Team team = new Team (info[2], info[3], info[4], info[5], info[6],
                   info[7]);
           
           if (!listOfTeams.contains(team)) 
           {
               if (listOfTeams.add(team)) 
               {
                   System.err.println("Added to the list of teams "
                           + team.toString());
                   
               } 
               
           } 
           
           else 
           {
               System.err.println("This team is already in the list of teams " 
                       + team.toString() );
               
           } 
           
       } 
       
       else if (info[1].equals("DEL"))
       {
           Team team = new Team (info[2], null, null, null, null, null );
           
           //listOfTeams.removeMethod(team);
           
           if (listOfTeams.removeAnEntry(team))
           {
               System.err.println("The team with team ID #" + team.getTeamID()
                       + " was removed from the list of teams.");
               
           } 
           
       } 
       
    } 
    
    
    /***************************************************************************
     * This is processInput method. This method will:
     * 1. Open the input file – display an error message using err stream if 
     *      file is not found.
     * 2. Read the data line.
     * 3. Use split to parse the line
     * 4. Call the processTeamData method passing it the array of type String 
     * containing team data.
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

                if (data[0].equals("TEAM")) 
                {
                    processTeamData(data);
                    
                } 
                
            } // End while
            
        } // End try
        
        catch(FileNotFoundException ex)
        {
           System.err.println("\nERROR: The input file name " + inputFile 
                   + " was not found.\nPlease check your input file again.");
           
        } // End catch exception
        
    } // End processInput 
    
    /***************************************************************************
     * This is generateReport method. This method will:
     * 1. Call the toArray method in the SortedLinkedList to get a list of Team 
     * objects.
     * 2. Open the output file for writing (file name is passed to it as an
     * argument)
     * 3. Write the Team information in the output file.
     * 
     */
    private static void generateReport(String OutputFile) 
    {
        Object[] teamList = listOfTeams.toArray();
        
        File file = new File (OutputFile);
        
        try
        {
            //File file = new File (OutputFile);
            PrintWriter writer = new PrintWriter (file);
            
            // Print the header
            writer.printf("%-4s%-22s%-10s%-10s%-14s%-15s%n","#ID","Teams' Name"
                    , "FirstName", "LastName", "Phone Number", " Email Address");
            writer.println("---------------------------------------------------"
                    + "-----------------------------");            
        
            for (int i = 0; i < teamList.length; i++) 
            {
                Team team = (Team) teamList[i];
                
                writer.printf("%-4s%-22s%-10s%-10s0%-14s%-15s%n", team.getTeamID()
                        , team.getTeamName(), team.getFirstName(), team.getLastName()
                        , team.getPhoneNumber(), team.getEmailAddress());
                
            } // End for
            
            // Close the output file
            writer.flush();
            writer.close();
            
        } // End try
        
        catch(FileNotFoundException ex)
        {
            System.err.println ("Error: File " + OutputFile 
                    + " was not found.");
            
        } // End catch exception
       
    } // End generateReport
    
}// End main class