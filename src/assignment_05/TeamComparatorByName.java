
package assignment_05;

import java.util.Comparator;

/**
 * This is an implementation of Comparator interface. It implements equals
 * and compare methods. It is comparing teams by team's names.
 * 
 * @author Thien Ngo N. Le
 * @version 20161104
 */
public class TeamComparatorByName implements Comparator<Team>
{
    /**
     * This is equals method which test two Teams objects for equality.
     * Equality is defined as equality of student name.
     * 
     * @param left Left team object
     * @param right Right team object
     * @return true if both teams have same team's name, else returns false.
     */
    public boolean equals (Team left, Team right)
    {
        return left.getTeamName().equals(right.getTeamName());
    }
    
    /**
     * This is compare method which will compare two teams objects using their 
     * team's name.
     * 
     * @param left Left team object
     * @param right Right team objects
     * @return It will return 0 if both teams objects are equal, it will return
     * a negative number if left is less than right, and it will return a 
     * positive number if left is greater then right.
     */
    
    public int compare (Team left, Team right)
    {
        return left.getTeamName().compareTo(right.getTeamName());
        
    }
}
