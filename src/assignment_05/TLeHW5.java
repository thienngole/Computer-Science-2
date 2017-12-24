
package assignment_05;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This is the main class. This class includes a main method, 
 * processTeamData method, processInput method, and generateReport method.
 * 
 * @author Thien Ngo N. Le
 * @version 20161104
 */
public class TLeHW5 
{
    // Define the listOfTeams and the listOfGames
    private static ArrayBag<Team> listOfTeams;
    
    // Define the intput file's names
    private static final String INPUT_FILE_NAME = "input/assignment_05/hw5input.txt";
    
    // Define the output files' names
    private static final String OUTPUT_FILE_NAME1 = "output/hw5output1.txt";
    private static final String OUTPUT_FILE_NAME2 = "output/hw5output2.txt";

    /**
     * This is the main method. This method will:
     * 1. Instantiate the listOfTeams object 
     * 2. Call the processInput method 
     * 3. Use the selection sort method in your bag class to sort the teams by 
     * Team ID
     * 4. Call the generateReport(OUTPUT_FILE_NAME1) method
     * 5. Use quick sort method in your bag class to sort the teams by Team Name
     * 6. Call the generateReport(OUTPUT_FILE_NAME2) method
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        // Instantiate the listOfTeams and the listOfGames 
        listOfTeams = new ArrayBag<Team>(100);
        
        // Call processInput method
        processInput();
        
        listOfTeams.selectionSort();
        
        // Call generateReport method
        generateReport(OUTPUT_FILE_NAME1);
        
        listOfTeams.quickSort();
        
        generateReport(OUTPUT_FILE_NAME2);
        
    }// End main method
    
    /***************************************************************************
     * This is processTeamData method. This method will:
     * 1. Receive an array of type String with team data
     * 2. Create a Team object
     * 3. Call the add method in the Bag class to add the team object to the 
     *      list of teams.
     * 
     * @param info the information of the team.
     */
    private static void processTeamData (String[] info)
    {
       if (info[1].contains("ADD"))
       {
           
           Team team = new Team (info[2], info[3], info[4], info[5], info[6],
                   info[7]);
           
           if(!team.checkEmail())
           {
               System.err.println ("ERROR: This email address " + 
                       team.getEmailAddress() + " is an invalid email address.");
               
           } // End if
           
           if (listOfTeams.find(team) == -1) 
           {
               if (listOfTeams.add(team)) 
               {
                   System.err.println("Added to the list of teams "
                           + team.toString());
                   
               } // End if
               
           } // End if
           
           else 
           {
               System.err.println("This team " + team.toString() 
                       + " is already added to the list of teams");
               
           } 
           
       } 
       
       else if (info[1].equals("DEL"))
       {
           Team team = new Team (info[2], null, null, null, null, null );
           
           //listOfTeams.removeMethod(team);
           
           if (listOfTeams.remove(team))
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
     *      containing team data.
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
     * 1. Call the toArray method in the Bag class to get a list of Team objects.
     * 2. Open the output file for writing (file name is passed to it as an
     *      argument)
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