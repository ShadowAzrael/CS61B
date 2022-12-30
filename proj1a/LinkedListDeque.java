public class LinkedListDeque<T> {
    private int size;
    private DequeNode sentinel;
    private class DequeNode{
        private T item;
        private DequeNode prev;
        private DequeNode next;
        private DequeNode(T i,DequeNode p,DequeNode n){
            item=i;
            prev=p;
            next=n;
        }
        private DequeNode(){
            this(null,null,null);
        }
    }
    public LinkedListDeque(){
        size = 0;
        sentinel = new DequeNode();
        sentinel.prev=sentinel;
        sentinel.next=sentinel;
    }
    public LinkedListDeque(LinkedListDeque other){
        this();
        for(int i=0;i<other.size();i++){
            addLast((T) other.get(i));
        }
    }
    public void addFirst(T item){
        sentinel.next.prev=new DequeNode(item,sentinel,sentinel.next);
        sentinel.next=sentinel.next.prev;
        size+=1;
    }
    public void addLast(T item){
        sentinel.prev.next=new DequeNode(item,sentinel.prev,sentinel);
        sentinel.prev=sentinel.prev.next;
        size+=1;
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return (size==0);
    }
    public void printDeque(){
        DequeNode first=sentinel.next;
        while(first!=sentinel){
            System.out.println(first.item+" ");
            first=first.next;
        }
        System.out.println();
    }
    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        DequeNode first=sentinel.next;
        sentinel.next=first.next;
        first.next.prev=sentinel.next;
        size-=1;
        return first.item;
    }
    public T removeLast(){
        if(isEmpty()){
            return null;
        }
        DequeNode Last=sentinel.prev;
        sentinel.prev=Last.prev;
        Last.prev.next=sentinel;
        size-=1;
        return Last.item;
    }
    public T get(int index){
        if(index>size){
            return null;
        }
        DequeNode current=sentinel.next;
        for(int i=0;i<index;i++){
            current=current.next;
        }
        return current.item;
    }
    public T getRecursive(int index){
        if(index>size) {
            return null;
        }
        return getRecursive(index-1,sentinel.next);
    }
    private T getRecursive(int index,DequeNode node){
        if(index==0){
            return node.item;
        }else{
            return getRecursive(index-1,node.prev);
        }
    }
}
