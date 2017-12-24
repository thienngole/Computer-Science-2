
 
package assignment_06;

import javax.xml.soap.Node;

/**
 * This is SortedLinkedList class. This class implements the following methods:
 * add – adds a new entry to the sorted list
 * removeAnEntry – removes an entry from the sorted list
 * removeByPosition – removes an entry from the given position
 * getEntry – returns the entry at given position
 * contains – test to see if an entry is included in the list
 * clear – deletes all entry from the list
 * getLength – returns the number of entries in the list
 * isEmpty – check to see if the list is empty
 * toArray – converts the list into an array
 * 
 * @author Thien Ngo N. Le
 * @version 20161130
 */
public class SortedLinkedList <T extends Comparable<T>> implements 
        SortedLinkedListInterface<T> {

    private Node first; 
    private int numberOfEntries;

    public SortedLinkedList() {
        first = null;
        numberOfEntries = 0;
    } // end default constructor
    
    /***************************************************************************
     * This is toArray method. This method will Converts the list into an array.
     * 
     * @return An array with all the items in the list.
     */
    public T[] toArray(){
        
        T[] list = (T[]) new Comparable[numberOfEntries];
        
        int index = 0;
        
        Node cn = first;
        
        while((index < numberOfEntries) && (cn != null)){
            
            list[index] = cn.getData();
            cn = cn.getNextNode();
            index++;
        }
        return list;
    }
    
    /***************************************************************************
     * This is isEmpty method. This method will Check to see if the list is empty
     * 
     * @return true if list is empty, otherwise returns false.
     */
    public boolean isEmpty() {
        
        boolean result;
        if(numberOfEntries == 0){
            assert first == null;
            result = true;
        }
        else{
            assert first != null;
            result = false;
        }
        return result;
    }
    
    /***************************************************************************
     * This is getLength method. This method will return the length of the list.
     * 
     * @return the length of the list.
     */
    public int getLength(){
        return numberOfEntries + 1;
    }
    
    /***************************************************************************
     * This is clear method. This method will remove all items from the list.
     */
    public void clear(){
        
        first = null;
        numberOfEntries = 0;
    }
    
    /***************************************************************************
     * This is Add method. This method will add an entry to an appropriate 
     * place in the list. It will make sure that list is maintained in sorted 
     * order. 
     * 
     * @param newEntry to be added to the list
     * 
     * @return It will return true if item is successfully added to the list,
     * otherwise it will return false 
     */
    public boolean add(T newEntry) {
        
        boolean result = false;
        
        Node newNode = new Node(newEntry);
        Node nodeBefore = getNodeBefore(newEntry);
        
        if (isEmpty() || (nodeBefore == null)) 
        {
            newNode.setNextNode(first);
            first = newNode;
            
            result = true;
        } 
        else 
        {
            Node nodeAfter = nodeBefore.getNextNode();
            newNode.setNextNode(nodeAfter);
            nodeBefore.setNextNode(newNode);
            
            result = true;
        } 
        numberOfEntries++;
        return result;
        
    } // end add
    
    /***************************************************************************
     * This is getNodeBefore method. This method will find a node before the 
     * position that we want to insert or remove a node.
     * 
     * @param anEntry a node to find on the list
     * 
     * @return a node
     */
    private Node getNodeBefore(T anEntry) {
        Node currentNode = first;
        Node nodeBefore = null;
        
        while ((currentNode != null) 
                && (anEntry.compareTo(currentNode.getData()) > 0)) 
        {
            nodeBefore = currentNode;
            currentNode = currentNode.getNextNode();
        } 
        return nodeBefore;
    } 
    
    /***************************************************************************
     * This is removeAnEntry method. This method will remove a given item from 
     * the list. It will make sure that list is maintained in sorted order after 
     * the removal of the given item. Minimum about of information needed to 
     * find an item in the list is dictated by the compareTo method in the items
     * class
     * 
     * @param anEntry to be removed from the list
     * 
     * @return true if the items is found in the list and is removed,
     * otherwise it will return false.
     */
    public boolean removeAnEntry(T anEntry){
        
        boolean result = false;
        
        if(numberOfEntries == 0){
            
            result = false;
        }
        
        Node currentNode = first;
        Node previousNode = first;
        
        while((currentNode != null) && !result){
            
            if(anEntry.equals(currentNode.getData())){
                
                if(previousNode == currentNode){
                    
                    first = previousNode.getNextNode();
                    result = true;
                    numberOfEntries--;
                }
                else{
                    
                    previousNode.setNextNode(currentNode.getNextNode());
                    result = true;
                    numberOfEntries--;
                }
                 
            }
            previousNode = currentNode;
            currentNode = currentNode.getNextNode();
            
        }
        return result;
    }
    
    /***************************************************************************
     * This is removeByPosition. This method remove an entry from the given 
     * position making sure that list if maintained in sorted order after the 
     * removal of the item.
     * 
     * @param position Index of the item to be removed.
     * 
     * @return true if the item can be successfully removed from the list.
     * It will return false if the index is out of bounds.
     */
    public T removeByPosition(int position){
        
        T result = null;
        
        if((position < 0) || (position >= numberOfEntries)){
            
            throw new IndexOutOfBoundsException("Illegal position given to "
                    + "remove operation");
        }
        else{
            
            assert !isEmpty();
            
            if(position == 1){
                
                result = first.getData();
                first = first.getNextNode();
            }
            else{
                
                Node nodeBefore = getNode(position - 1);
                Node nodeToRemove = nodeBefore.getNextNode();
                
                result = nodeToRemove.getData();
                
                Node nodeAfter = nodeToRemove.getNextNode();
                nodeBefore.setNextNode(nodeAfter);
            }
            numberOfEntries--;
            return result;
        }
    }
    
    /***************************************************************************
     * This is getNode method. This method will get a node by a given position.
     * 
     * @param position the position of the node
     * 
     * @return a node at given position.
     */
    private Node getNode(int position){
        
        assert(first != null) && (position >= 1) && (position <= numberOfEntries);
        
        Node currentNode = first;
        
        for(int i = 0; i < position; i++){
            
            currentNode = currentNode.getNextNode();
        }
        
        assert currentNode != null;
        return currentNode;
    }
    
    /***************************************************************************
     * This is getEntry method. This method will return an entry from the given 
     * position without removing it.
     * 
     * @param position Index of the item to be returned.
     * 
     * @return the item at the given index position. It will return null if the
     * index is out of bounds.
     */
    public T getEntry(int position){
        
        if((position > 0) && (position <= numberOfEntries)){
            
            assert !isEmpty();
            return getNode(position).getData();
        }
        else{
            
            throw new IndexOutOfBoundsException("Illigal position given to "
                    + "getEntry operation.");
            
        }
    }
    
    /***************************************************************************
     * This is contains method. This method will check to see if the item is in 
     * the list.
    
     * @param anEntry Item to be checked for existence in the list.
     * 
     * @return true if the item is in the list, otherwise it will return false
     */
    public boolean contains(T anEntry){
        
        boolean found = false;
        Node currentNode = first;
        
        while(!found && (currentNode != null)){
            
            if(anEntry.compareTo(currentNode.getData()) == 0){
                found = true;
            }
            //if(anEntry.equals(currentNode.getData())){
            //    found = true;
            //}
            else{
                currentNode = currentNode.getNextNode();
            }
        }
        return found;
        
    } // End contains method
    
    
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
        
        private Node getNextNode()
        {
            return next;
        }
        
        private void setNextNode(Node next)
        {
            this.next = next;
        }
    } 
} // end SortedLinkedList
