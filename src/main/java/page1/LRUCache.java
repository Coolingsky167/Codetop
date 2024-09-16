package page1;

import common.ListNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class LRUCache {
    int capacity;
    ListNode head;
    ListNode tail;
    Map<Integer,ListNode> map;
    int size;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.head = new ListNode(-1);
        this.tail = new ListNode(-1);
        this.head.next=tail;
        tail.pre=head;
        this.map = new HashMap<>();
        int size = 0;
    }

    public int get(int key) {
        if (this.map.containsKey(key)){
            ListNode node = map.get(key);
            if (node!=head.next){
                node.pre.next=node.next;
                node.next.pre=node.pre;
                node.pre=head;
                node.next=head.next;
                head.next=node;
                node.next.pre=node;
            }
            return this.map.get(key).val;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (this.map.containsKey(key)){
            ListNode node =map.get(key);
            node.val = value;
            //
            node.pre.next = node.next;
            node.next.pre = node.pre;
            node.pre=head;
            node.next=head.next;
            head.next=node;
            node.next.pre=node;
        }else{
            ListNode newNode = new ListNode(key,value);
            newNode.pre=head;
            newNode.next = head.next;
            head.next= newNode;
            newNode.next.pre=newNode;
            size+=1;
            this.map.put(key,newNode);
            if (this.size > this.capacity){
                ListNode rem = tail.pre;
                rem.pre.next = tail;
                tail.pre = rem.pre;
                size-=1;
                map.remove(rem.key);
            }
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1,0);
        lruCache.put(2,2);
        System.out.println(lruCache.get(1));
        lruCache.put(3,3);
        System.out.println(lruCache.get(2));
        lruCache.put(4,4);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
