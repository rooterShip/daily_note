/*
 * @Author: Rooter
 * @Date: 2022-03-02 18:40:56
 * @LastEditors: Rooter
 * @LastEditTime: 2022-03-04 22:25:37
 */
/**
 * 给定单个链表的头 head ，使用 插入排序 对链表进行排序，并返回 排序后链表的头 。
 * 
 * 输入: head = [4,2,1,3]
 * 输出: [1,2,3,4]
 */
public class InsertionListSort {
    public static ListNode insertionSortList(ListNode head){
         if(head == null) return head;

         ListNode dummyHead = new ListNode(0);
         dummyHead.next = head;
         ListNode lastSorted = head;
         ListNode curr = head.next;

         while(curr != null){
             if(curr.val >= lastSorted.val){
                 lastSorted = lastSorted.next;
                 curr = curr.next;
             }
             else{
                 ListNode prev = dummyHead;
                 while(prev.next.val <= curr.val){
                     prev = prev.next;
                 }
                 lastSorted.next = curr.next;
                 curr.next = prev.next;
                 prev.next = curr;
                 curr = lastSorted.next;
             }
         }
         return dummyHead.next; //不能是head只能是dummyHead.next，待解决
    }
    public static void main(String[] args) {

    }
}

class ListNode{
    int val;
    ListNode next;
    ListNode(){}
    ListNode(int val){this.val = val;}
    ListNode(int val,ListNode next){
        this.val = val;
        this.next = next;
    }
}