/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

/**
 *
 * @author guita
 */
public class Pilha {
    int elementos[];
    int topo;
    
    public Pilha(){
        elementos = new int[100];
        topo = -1;//posicao invalida ao vetor
    }
    
    public  void  push(int e){
        if(isFull()){
            throw  new RuntimeException("Stack Overflow");
        }
        topo++;
        elementos[topo] = e;
    }
    
    public int pop(){
        if(isEmpty()){
            throw  new RuntimeException("Empty Stack");
        }
        int e;
        e = elementos[topo];
        topo--;
        return e;
    }
    
    public boolean  isEmpty(){
        return  (topo == -1);
    }
    
    public boolean  isFull(){
        return (topo == 99);
    }
    
    public int top(){
        if(isEmpty()){
            throw  new RuntimeException("Empty Stack");
        }
        return elementos[topo];
    }
    
}
