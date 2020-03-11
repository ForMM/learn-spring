package com.example.demo;

public class ListNode {
    private int val;
    ListNode next;

    public ListNode(int i){
        this.val=i;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}
