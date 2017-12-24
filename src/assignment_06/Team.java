package assignment_06;

import java.util.Objects;

/**
 * This is a Team class. This class is used for instantiating the Team objects. 
 * This class contains a constructor, a get method, a toString method, an equals
 * method, and a method name emailCheck to make sure that the email address is 
 * in a correct form.
 * 
 * @author Thien Ngo N. Le
 * @version 20161130
 */
public class Team implements Comparable<Team>
{
    private String teamID;
    private String teamName;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;

   /**
    * This constructor initialize all the fields in the class.
    * 
    * @param teamID the ID number of the team.
    * @param teamName the name of the team.
    * @param firstName team's member first name.
    * @param lastName team's member last name.
    * @param phoneNumber team's member phone number.
    * @param emailAddress team's member email address.
    */
    public Team(String teamID, String teamName, String firstName, 
            String lastName, String phoneNumber, String emailAddress) 
    {
        this.teamID = teamID;
        this.teamName = teamName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    /***************************************************************************
     * This method gets the ID number of the team.
     * 
     * @return team's ID number
     */
    public String getTeamID() 
    {
        return teamID;
        
    }//End getTeamID

    /***************************************************************************
     * This method gets the name of the team.
     * 
     * @return team's name
     */
    public String getTeamName() 
    {
        return teamName;
        
    }//End teamName

    /***************************************************************************
     * This method gets the first name of the team's members.
     * 
     * @return team member's first name
     */
    public String getFirstName() 
    {
        return firstName;
        
    }//End getFirstName

    /***************************************************************************
     * This method gets the last name of the team's members.
     * 
     * @return team member's last name
     */
    public String getLastName() 
    {
        return lastName;
        
    }//End getLastName

    /***************************************************************************
     * This method gets the phone number of the team's members.
     * 
     * @return phone number
     */
    public String getPhoneNumber() 
    {
        return phoneNumber;
        
    }//End getPhoneNumber

    /***************************************************************************
     * This method gets the email address of the team's members.
     * 
     * @return email address
     */
    public String getEmailAddress() 
    {
        return emailAddress;
        
    }//End getEmailAddress

    /***************************************************************************
     * This method displays all object of the Team class.
     * 
     * @return teamID, teamName, firstName, lastName, phoneNumber, 
     * and emailAdress
     */
    public String toString() 
    {
        
        return "Team{" + "teamID: " + teamID + ", teamName: " + teamName + 
                ", firstName: " + firstName + ", lastName: " + lastName + 
                ", phoneNumber: " + phoneNumber + ", emailAddress: " + 
                emailAddress + '}';
        
    }// End toString
    
    /***************************************************************************
     * This method checks the teamID objects in the Team class to make sure that 
     * they are correct.
     * 
     * @return true or false
     */
    public boolean equals(Object obj) 
    {
        
        if (this == obj) 
        {
            return true;
        }
        
        if (obj == null) 
        {
            return false;
        }
        
        if (getClass() != obj.getClass()) 
        {
            return false;
        }
        
        final Team other = (Team) obj;
        
        if (!Objects.equals(this.teamID, other.teamID)) 
        {
            return false;
        }
        
        return true;
        
    } // End equals method

    /***************************************************************************
     * This method checks the email address to make sure that the email address 
     * contains a "@".
     * 
     * @return true or false
     */
    public boolean checkEmail()
    {
       return emailAddress.contains("@");
       
    }// End emailCheck
    
    /***************************************************************************
     * This is compareTo method. This method will compare student using id number. 
     * 
     * @param other other student object
     * @return the integer number
     */
    public int compareTo (Team other)
    {
        int result = 0;
        
        result = this.teamID.compareTo(other.teamID);
        
        
        return result;
    } // End compareTo
    
} // End Team class