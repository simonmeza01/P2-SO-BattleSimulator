
package components;


public class Queue {
    private Personaje head; 
    private Personaje tail; 
    public int size; 
    
    public Queue(){
        this.head = null; 
        this.tail = null; 
        this.size = 0; 
    }
    
    public boolean isEmpty(){
        return this.head == null;
    }
    
    public void empty(){
        this.head = this.tail = null;
        this.size = 0;
    }
    
    public void enqueue(Personaje nuevo){
        if(this.isEmpty()){
            head = tail = nuevo;
        }else{
            tail.setNext(nuevo);
            tail = nuevo;
        }
        size++;
    }
    
    public void dequeue(){
        if(this.isEmpty()){
        }else if(size == 1){
            
            this.empty();
        }else{
            head = head.getNext();
            size--;
        }
    }
    
    public Personaje getHead() {
        return head;
    }

    public void setHead(Personaje head) {
        this.head = head;
    }

    public Personaje getTail() {
        return tail;
    }

    public void setTail(Personaje tail) {
        this.tail = tail;
    }
    
}
