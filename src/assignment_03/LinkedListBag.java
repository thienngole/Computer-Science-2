
package assignment_03;

/**
 * This is a class of bags whose entries are stored in an linked list
 * 
 * @author Thien Ngo N. Le
 * @version 20161014
 */
public class LinkedListBag<T> implements BagInterface <T>
{
    
    private Node firstNode;
    private int numberOfEntries;
    
    public LinkedListBag()
    {
        
        firstNode = null;
        numberOfEntries = 0;
        
    } // End default constructor
    
    /***************************************************************************
     * This method finds an entry and returns the location.
     * 
     * @param anEntry the object to compare.
     * 
     * @return location
     */
    public Node findItem(T anEntry)
    {
        
        Node result = null;
        Node currentNode = firstNode;
        boolean found = false;
        
        while (!found && currentNode != null)
        {
            
            if (currentNode.getData().equals(anEntry))
            {
                
                result = currentNode;
                found = true;
            }
            
            currentNode = currentNode.getNext();
        }
        
        return result;
    }
    
    /***************************************************************************
     * This method adds a new entry to the bag.
     * 
     * @param newEntry the object to add as a new entry.
     * 
     * @return true or false.
     */
    public boolean addNode(T newEntry){
        Node newNode = new Node(newEntry);
        newNode.next = firstNode;
        firstNode  = newNode;
        numberOfEntries ++;
        
        return true;
        
    } // End addNode
    
    /***************************************************************************
     * This method removes one occurrence of a given entry from this bag.
     * 
     * @param anEntry an object to remove
     * 
     * @return true or false
     */
    public boolean removeNode(T anEntry)
    {
        
        boolean result = false;
        Node nodeToRemove = findItem(anEntry);
        
        if (nodeToRemove != null)
        {
            
            nodeToRemove.data = firstNode.data;
            
            firstNode = firstNode.next;
            numberOfEntries --;
            result = true;
            
        } // End if
        
        return result;
        
    } // End removenode
    
    /***************************************************************************
     * This method retrieves all entries that are in this bag.
     *
     * @return a newly allocated array of all the entries in the linked list.
     */
    public T[] toArray()
    {
        
        T[] result = (T[])new Object[numberOfEntries];
        
        int index = 0;
        Node currentNode = firstNode;
        
        while ((index < numberOfEntries) && (currentNode != null))
        {
            
            result[index] = currentNode.data;
            index ++;
            currentNode = currentNode.next;
            
        } // End while
        
        return result;
        
    } // End toArray
    
    
    /***************************************************************************
     * This is a constructor
     */
    private class Node
    {
        
        private T data;
        private Node next;
        
        private Node (T newEntry)
        {
            
            data = newEntry;
            next = null;
            
        } // End constructor
        
        private T getData()
        {
            return data;
        }
        
        private void setData(T data)
        {
            this.data = data;
        }
        
        private Node getNext()
        {
            return next;
        }
        
        private void setNext(Node next)
        {
            this.next = next;
        }
    } 
    
} //End of LinkedListBag class

