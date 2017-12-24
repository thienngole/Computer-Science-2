package assignment_01;

import java.util.Objects;

/**
 * This is a assignment_01.Team class. This class is used for instantiating the assignment_01.Game objects.
 * This class does not contain a main method or any I/O code. This class contains
 * a constructor, a get method, a toString method, an equals method, and a method
 * name emailCheck to make sure that the email address is in a correct form.
 *
 * @author Thien Ngo N. Le
 * @version 20160907
 */
public class Team {
    private String teamID;
    private String teamName;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;

    /**
     * This constructor initialize all the fields in the class.
     *
     * @param teamID       the ID number of the team.
     * @param teamName     the name of the team.
     * @param firstName    team's member first name.
     * @param lastName     team's member last name.
     * @param phoneNumber  team's member phone number.
     * @param emailAddress team's member email address.
     */

    public Team(String teamID, String teamName, String firstName,
                String lastName, String phoneNumber, String emailAddress) {
        this.teamID = teamID;
        this.teamName = teamName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    /**************************************************************************
     * This method gets the ID number of the team.
     *
     * @return team's ID number
     */
    public String getTeamID() {
        return teamID;
    }//End getTeamID

    /**************************************************************************
     * This method gets the name of the team.
     *
     * @return team's name
     */
    public String getTeamName() {
        return teamName;
    }//End teamName

    /**************************************************************************
     * This method gets the first name of the team's members.
     *
     * @return team member's first name
     */
    public String getFirstName() {
        return firstName;
    }//End getFirstName

    /**************************************************************************
     * This method gets the last name of the team's members.
     *
     * @return team member's last name
     */
    public String getLastName() {
        return lastName;
    }//End getLastName

    /**************************************************************************
     * This method gets the phone number of the team's members.
     *
     * @return phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }//End getPhoneNumber

    /**************************************************************************
     * This method gets the email address of the team's members.
     *
     * @return email address
     */
    public String getEmailAddress() {
        return emailAddress;
    }//End getEmailAddress

    @Override
    /**************************************************************************
     * This method displays all object of the assignment_01.Team class.
     *
     * @return teamID, teamName, firstName, lastName, phoneNumber, 
     * and emailAdress
     */
    public String toString() {
        return "assignment_01.Team{" + "teamID: " + teamID + ", teamName: " + teamName +
                ", firstName: " + firstName + ", lastName: " + lastName +
                ", phoneNumber: " + phoneNumber + ", emailAddress: " +
                emailAddress + '}';
    }// End toString

    @Override
    /**************************************************************************
     * This method checks all objects in the assignment_01.Team class to make sure that they
     * are correct.
     *
     * @return true or false
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final Team other = (Team) obj;

        if (!Objects.equals(this.teamID, other.teamID)) {
            return false;
        }
        if (!Objects.equals(this.teamName, other.teamName)) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.phoneNumber, other.phoneNumber)) {
            return false;
        }
        if (!Objects.equals(this.emailAddress, other.emailAddress)) {
            return false;
        }
        return true;
    }//End equals

    /**************************************************************************
     * This method checks the email address to make sure that the email address 
     * contains a "@".
     *
     * @return true or false
     */
    public boolean emailCheck() {
        return emailAddress.contains("@");
    }// End emailCheck

}// End assignment_01.Team class
