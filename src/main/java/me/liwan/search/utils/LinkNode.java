package me.liwan.search.utils;

public class LinkNode<E> {

    public E item;
    public LinkNode<E> next;

    public LinkNode(E item){
        this.item = item;
        this.next = null;
    }

    public LinkNode(E item, LinkNode<E> next){
        this.item = item;
        this.next = next;
    }

    public void setNext(LinkNode<E> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "{ \"node\"： " + this.item.toString() + " }";
    }

}
