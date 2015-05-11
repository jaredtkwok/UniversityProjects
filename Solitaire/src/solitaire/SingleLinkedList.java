//********************************************************************
//  SingleLinkedList.java      
//  Author: Jared Kwok      ID no. 1382534
//
//  It's named SingleLinkedList because I used the code from the lab
//  and didn't rename the class
//********************************************************************
package solitaire;

import java.util.NoSuchElementException;

public class SingleLinkedList<E> {
    
    protected transient Element<E> head;
    protected transient int size;
    
    public SingleLinkedList(){
        head = null;
        size = 0;
               
    }
    
    public int size(){
        return size;
    }
    
    public E removeFirst(){
        if (size ==0)
            throw new NoSuchElementException();
        Element<E> temp = head;
        head = head.next;
        size--;
        return temp.value;
    }
    
    public void addFirst(E value){
        head = new Element<E>(value, head);
        size++;
    }
    
    public E getFirst(){
        return head.value;
    } 
    
    public void addLast(E value){
        Element<E> temp = new Element<E>(value, null);
        if (head != null){
            Element<E> finger = head;
            while(finger.next != null){
                finger = finger.next;
            }
            finger.next = temp;
        }else{
            head = temp;
        }
        size++;
    }
    
    public E removeLast(){
        if (size ==0)
            throw new NoSuchElementException();
        
        Element<E> finger = head;
        Element<E> previous = null;
        
        while(finger.next != null){
                previous = finger;
                finger = finger.next;
        }
        
        if(previous == null){
            head = null;
        }else{
            previous.next = null;
        }
        size --;
        return finger.value;
    }
    
    public E getLast(){
        if (size ==0)
            throw new NoSuchElementException();
        
        Element<E> finger = head;

        while(finger.next != null){
                finger = finger.next;
        }

        return finger.value;
    }
    
    public boolean contains(E value){
        Element<E> finger = head;
        
        while(head.next != null && !finger.value.equals(value)){
            finger = finger.next;
        }
        return finger != null;
    }
    
    public E remove(E value){
        Element<E> finger = head;
        Element<E> previous = null;
        
        while(head.next != null && !finger.value.equals(value)){
            previous = finger;
            finger = finger.next;
        }
        
        if(finger != null){
            if(previous == null){
                head = finger.next;
            } else {
                previous = finger.next;
            }
            size--;
            return finger.value;
        }
        return null;
    }
    
    public E getValueAtIndex(int index){
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        
        if(index == 0)
            return getFirst();
        else if(index == size-1)
            return getLast();
        
        Element<E> finger = head;
        while(index > 0){
            finger = finger.next;
            index--;
        }
            
        return finger.value;
    
    }
    
    public void setValueAtIndex(int index, E value){
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        
        if(index == 0)
            head.value = value;
        
        Element<E> finger = head;
        while(index > 0){
            finger = finger.next;
            index--;
        }
            
        finger.value = value;
    
    }
    
    public void clear(){
        head = null;
        size = 0;
    }
    
    public String toString(){
        String string = "[ ";
        
        Element<E> finger = head;
        while(finger != null && finger.next != null){
            string += finger.value+", ";
            finger = finger.next;
        }
        
        if(head != null)
            string += finger.value+ " ]";
        else
            string += " ]";
        
        return string;
    }
    
    public void reverseOrder(){
        recursiveReverseOrder(head);
    }
    
    private void recursiveReverseOrder(Element<E> element){
        
        if(element == null)
            return;
        
        if(element.next == null){
            head = element;
            return;
        }
        
        recursiveReverseOrder(element.next);
        element.next.next = element;
        element.next = null;
        
        
    }
    
    protected static class Element<E>{
        
        E value;
        Element<E> next;
        
        Element(E value, Element<E> next){
            this.value = value;
            this.next = next;
        }
    }
}

