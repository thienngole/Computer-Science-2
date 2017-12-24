
package assignment_05;

/**
 * This is a class of bags whose entries are stored in an array
 * 
 * @author Thien Ngo N. Le
 * 
 * @version 20161104
 */
public class ArrayBag<T extends Comparable<T>> implements BagInterface<T> 
{
    
    private T[] bag;
    private int numberOfEntries ;
    private static final int DEFAULT_CAPACITY = 100;
    private static final int MAX_CAPACITY = 1000;
    private boolean initialized;
    
    
    public ArrayBag () 
    {
        
        Comparable[] bagObject = new Comparable[DEFAULT_CAPACITY];
        
        bag = (T[]) bagObject;
        numberOfEntries = 0;
        initialized = true;
        
    }
    
    /***************************************************************************
     * 
     * @param size size of the bag.
     */
    public ArrayBag (int size) 
    {
        
        initialized = false;
       
        if (size < MAX_CAPACITY) 
        {
            
            Comparable[] bagObject = new Comparable[size];
            
            bag = (T[]) bagObject;
            numberOfEntries = 0;
            initialized = true;
        }
        else
        {
            throw new IllegalStateException ("Given size is larger than allowed size");
        }
        
    }
    
    /***************************************************************************
     * This is getCurrentSize method. This method Gets the current number of 
     * entries in this bag.
     *
     * @return the integer number of entries currently in the bag.
     */
    public int getCurrentSize() 
    {
        return numberOfEntries;
    }
    
    /***************************************************************************
     * This is isEmpty method. This method check whether this bag is empty.
     *
     * @return true if the bag is empty, or false if not.
     */
    public boolean isEmpty() 
    {
        
        return (getCurrentSize() == 0);
    }
    
    /***************************************************************************
     * This method checks that the array is full or not
     * 
     * @return true or false
     */
    private boolean isArrayFull () 
    {
        
        return (numberOfEntries > bag.length);
        
    } // End isArrayFull
    
    /***************************************************************************
     * This method finds an entry and returns the index.
     * 
     * @param anEntry the object to compare.
     * 
     * @return location
     */
    public int find (T anEntry)
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
    public boolean add(T newEntry) 
    {
        
        if (initialized)
        {

          if (!isArrayFull()) 
          {
            bag[numberOfEntries] = newEntry;
            numberOfEntries++;
            return true;
          }
        }
        
        else
        {
            throw new SecurityException ();
        }
            
        return false;
    }
    
    /***************************************************************************
     * This method removes one occurrence of a given entry from this bag.
     * 
     * @param anEntry an object to remove
     * 
     * @return true or false
     */
    public boolean remove (T anEntry) 
    {
        
        int location = find(anEntry);
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
    
    /***************************************************************************
     * This method retrieves all entries that are in this bag.
     *
     * @return a newly allocated array of all the entries in the bag.
     */
    public T[] toArray() 
    {
        
        Comparable[] newBagObject = new Comparable[numberOfEntries];
        T[] newBag = (T[]) newBagObject;
        
        for (int i = 0; i < numberOfEntries; i++)
        {
            newBag[i] = (T) bag[i];
        }
        
        return newBag;
        
    } // End toArray method
    
    
    /***************************************************************************
     * This is selectionSort method. This method will sort data using selection 
     * sort
     */
    public void selectionSort()
    {
        for (int i=0; i < numberOfEntries-1; i++)
        {
            int min=i;
            //Find the index for the smallest number
            for (int j=i+1; j < numberOfEntries; j++)
            {
                Team itemJ = (Team) bag[j]; // casting to team
                Team itemMin = (Team) bag[min];
                if (itemJ.compareTo(itemMin) < 0)
                {
                    min = j;
                }
            }
            
            T temp = bag[min];
            bag[min] = bag[i];
            bag[i] = temp;
            
        }
    }
    
    /***************************************************************************
     * This is a public type of quickSort method
     */
    
    public void quickSort() 
    {
        
        TeamComparatorByName ct = new TeamComparatorByName();
        
        quickSort(ct, 0, numberOfEntries - 1);
    }
    
    
    /***************************************************************************
     * This is quickSort method. This method will sort data using quick sort.
     * 
     * @param ct is the comparator
     * @param first the first element in the list
     * @param last the last element in the list
     */
    private void quickSort(TeamComparatorByName ct, int first, int last) 
    {
        
        if (first < last) 
        {
            
            int pivotIndex = partition(ct, first, last);
            quickSort(ct, first, pivotIndex - 1);
            quickSort(ct, pivotIndex + 1, last);
        }
    }
    
    
    /***************************************************************************
     * This is partition method. This method will return the partition when it 
     * is called
     * 
     * @param ct is the comparator
     * @param first is the first element in the list
     * @param last is the last element in the list
     * 
     * @return the partition
     */
    private int partition(TeamComparatorByName ct, int first, int last) 
    {
        
        int pivotIndex = last;
        boolean done = false;
        
        int leftIndex = first;
        int rightIndex = last - 1;

        while (!done) 
        {
            Team leftItem  = (Team) bag[leftIndex]; 
            Team rightItem = (Team) bag[rightIndex]; 
            Team pivotItem = (Team) bag[pivotIndex]; 
            
            while (ct.compare(leftItem,pivotItem) < 0) 
            {
                leftIndex++;
                leftItem = (Team) bag[leftIndex];
            }

            while (ct.compare(rightItem, pivotItem) >= 0) {
                rightIndex--;
                rightItem = (Team) bag[rightIndex];
            }
            
            if (leftIndex < rightIndex)
            {
                T temp1 = bag[leftIndex];
                bag[leftIndex] = bag[rightIndex];
                bag[rightIndex] = temp1;
            }
            
            else
            {
                done = true;
            }
        }
        
        T temp2 = bag[leftIndex];
            bag[leftIndex] = bag[pivotIndex];
            bag[pivotIndex] = temp2;
            
            pivotIndex = leftIndex;
            
            return pivotIndex;
    } // End pertition method
    
} // End ArrayBag class