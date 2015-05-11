//********************************************************************
//  Stack.java      
//  Author: Jared Kwok      ID no. 1382534
//
//  Code was from lab
//
//********************************************************************
package solitaire;

public class Stack<E> extends SingleLinkedList<E>{
    
    public void push(E value){
        addFirst(value);
    }
    
    public E pop(){
        return removeFirst();
    }
}
    
