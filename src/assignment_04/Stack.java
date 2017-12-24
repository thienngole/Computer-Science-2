
package assignment_04;

import java.util.EmptyStackException;

/**
 * This program uses a stack to test whether an input string is a palindrome
 * In this stack class will include a constructor, push, pop, peek, isEmpty, 
 * and clear methods
 * 
 * @author ThienNgo N. Le
 * @version 20161024
 */
public class Stack 
{
    
    private char[] stack;
    private int topItem;
    
    /**
     * This constructor initialize a stack type char and the top item in the stack.
     * 
     * @param size the size of the stack
     */
    public Stack (int size)
    {
        
        stack = new char[size];
        topItem = -1;
    }
    
    /***************************************************************************
     * This is push method. 
     * This method will add an new entry to the top of the stack
     * 
     * @param newEntry the item to add to the top of the stack
     */
    public void push (char newEntry)
    {
        stack[topItem + 1] = newEntry;
        topItem ++;
        
    }
    
    /***************************************************************************
     * This is pop method.
     * This method will removed a top item from the stack and return that item
     * 
     * @return Item removed from the stack
     */
    public char pop()
    {
        if (isEmpty())
        {
            
            throw new EmptyStackException();
            
        }
        
        else 
        {
            
            char top = stack[topItem];
            stack[topItem] = 0;
            topItem --;
            return top;
        }   
    }
    
    /***************************************************************************
     * This is peek method.
     * This method will return top item on the stack but not remove it from 
     * the stack
     * 
     * @return top item on the stack
     */
    public char peek()
    {
        
        if (isEmpty())
        {
            
            throw new EmptyStackException(); 
        }
        
        return stack[topItem];
    }
    
    /***************************************************************************
     * This is isEmpty method.
     * This method will check if the stack is empty.
     * 
     * @return true if stack is empty, otherwise it returns false.
     */
    public boolean isEmpty()
    {
        
        return (topItem < 0);
    }
    
    /***************************************************************************
     * This is clear method.
     * This method will remove all the item in the stack.
     * 
     * @param anEntry the items to remove
     */
    public void clear(char anEntry)
    {
        
        while(!isEmpty())
        {
            pop();
        }
    }
     
}
