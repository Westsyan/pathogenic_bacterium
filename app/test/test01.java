package test;

import java.util.concurrent.ConcurrentLinkedDeque;

public class test01 {


    public static void main(String[]args){
        ConcurrentLinkedDeque queue = new ConcurrentLinkedDeque();
        queue.offer("哈哈哈");
        System.out.println("offer后，队列是否有空？" + queue.isEmpty());
        System.out.println("从队列中poll：" + queue.poll());
        System.out.println("poll后，队列是否有空：" + queue.isEmpty());
        

    }
}
