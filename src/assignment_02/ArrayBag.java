
package assignment_02;

/**
 * This is a class of bags whose entries are stored in an array
 * 
 * @author Thien Ngo N. Le
 * 
 * @version 20160926
 */
public class ArrayBag<T> implements BagInterface<T> 
{
    
    private T[] bag;
    private int numberOfEntries ;
    private static final int DEFAULT_CAPACITY = 100;
    
    
    public ArrayBag () 
    {
        
        @SuppressWarnings("unchecked")
        Object bagObject = new Object[DEFAULT_CAPACITY];
        
        bag = (T[]) bagObject;
        numberOfEntries = 0;
        
    }
    
    /***************************************************************************
     * 
     * @param size size of the bag.
     */
    public ArrayBag (int size) 
    {
        
        @SuppressWarnings("unchecked")
                
        Object bagObject = new Object[size];
        bag = (T[]) bagObject;
        numberOfEntries = 0;
        
    } // End ArrayBag
    
    /***************************************************************************
     * This method checks that the array is full or not
     * 
     * @return true or false
     */
    private boolean isArrayFull () 
    {
        return (numberOfEntries < bag.length);
        
    } // End isArrayFull
    
    /***************************************************************************
     * This method finds an entry and returns the index.
     * 
     * @param anEntry the object to compare.
     * 
     * @return location
     */
    public int findMethod (T anEntry)
    {
        
        int location = -1;
        boolean result = false;
        int i = 0;
        
        while (!result && i < numberOfEntries)
        {
            
            if (anEntry.equals(bag[i]))
            {
                location = i;
                result = true;        
            }
            
            i++;
        }
        
        return location;
        
    } //End findMethod
    
    /***************************************************************************
     * This method adds a new entry to the bag.
     * 
     * @param newEntry the object to add as a new entry.
     * 
     * @return true or false.
     */
    public boolean addMethod(T newEntry) 
    {
        
        if (isArrayFull ()) 
        {
            bag[numberOfEntries] = newEntry;
            numberOfEntries++;
            return true;
        }
        
        return false;
        
    } //End addMethod
    
    /***************************************************************************
     * This method removes one occurrence of a given entry from this bag.
     * 
     * @param anEntry an object to remove
     * 
     * @return true or false
     */
    public boolean removeMethod (T anEntry) 
    {
        
        int location = findMethod (anEntry);
        boolean result = false;
        
        if (location != -1)
        {
            numberOfEntries--;
            bag[location] = bag[numberOfEntries];
            bag[numberOfEntries] = null;
            result = true;
        }
        
        return result;
        
    } //End removeMethod
    
    /**
     * This method retrieves all entries that are in this bag.
     *
     * @return a newly allocated array of all the entries in the bag.
     */
    public T[] toArray() 
    {
        
        Object newBagObject = new Object[numberOfEntries];
        T[] newBag = (T[]) newBagObject;
        
        for (int i = 0; i < numberOfEntries; i++)
        {
            newBag[i] = bag[i];
        }
        
        return newBag;
        
    } // End toArray method
  
} // End ArrayBag class
