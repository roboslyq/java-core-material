/**
 * Copyright (C), 2015-2019
 * FileName: TopK
 * Author:   luo.yongqian
 * Date:     2019/5/17 9:21
 * Description: TopK问题
 * History:
 * <author>                 <time>          <version>          <desc>
 * luo.yongqian         2019/5/17 9:21      1.0.0               创建
 */
package com.roboslyq.algorithm;

import java.util.Random;

/**
 * 〈TopK问题〉
 * 仅实现从源数据构造排序，未实现更新，删除，增加等操作。
 * @author luo.yongqian
 * @create 2019/5/17
 * @since 1.0.0
 */
public class TopK {
    /**
     *
     * @param userArray
     * @param k
     * @return
     */
    public User[] createHeapOther(User[] userArray, int k) {
        User[] result = new User[k];
        for (int i = 0; i < k; i++) {
            result[i] = userArray[i];
        }
        for (int i = 1; i < k; i++) {
            int child = i;
            int parent = (i - 1) / 2;
            User temp = userArray[i];
            while (parent >= 0 &&child!=0&& result[parent].getIntegral() >  temp.getIntegral()) {
                result[child] = result[parent];
                child = parent;
                parent = (parent - 1) / 2;
            }
            result[child] = temp;
        }
        return result;
    }

    //创建小根堆,复杂度最坏是nlgK
    public User[] createHeap(User input[], int K) {
        User heap[] = new User[K];
        for(int i=0;i<K;i++)
            heap[i] = input[i];
        for(int i = 1;i < heap.length;i++) {
            int child = i;
            int parent = (child-1) / 2;
            while(parent >= 0 && child!=0 && heap[parent].getIntegral() > heap[child].getIntegral()) {
                User temp = heap[child];
                heap[child] = heap[parent];
                heap[parent] = temp;
                child = parent;
                parent = (parent - 1) / 2;
            }
        }
        return heap;
    }
    public void insertHeap(User heap[], User value) {
        heap[0] = value;
        int parent = 0;
        while(parent<heap.length) {   //这个循环复杂度最坏是  logK
            int lchild = parent*2 + 1;
            int rchild = parent*2 + 2;
            int minIndex = parent;  //指向左右儿子中最小的
            if(lchild < heap.length && heap[lchild].getIntegral() < heap[parent].getIntegral())
                minIndex = lchild;
            if(rchild < heap.length && heap[rchild].getIntegral() < heap[minIndex].getIntegral())
                minIndex = rchild;
            if(minIndex == parent) {
                break;
            }
            else {
                User temp = heap[minIndex];
                heap[minIndex] = heap[parent];
                heap[parent] = temp;
                parent = minIndex;
            }
        }
    }

    public User[] getTopKByHeap(User input[], int K) {
        User result[] = createHeap(input, K);   //复杂度最坏是 O(nlgK)
        for(int i=K;i<input.length;i++) {
            if(input[i].getIntegral() > result[0].getIntegral())
                //复杂度最坏是O(nlgK)，而且内存消耗就K，不然海量数据排序，内存放不下，得用归并排序，最好最坏平均都是 O(nlgn)
                insertHeap(result, input[i]);
        }
        return result;
    }

    public static void main(String[] args) {
       User[] userArray = User.userHelper(10000000);//待找TOP K 的排海量数据N
        TopK topK = new TopK();
        long startTime = System.currentTimeMillis();
        System.out.println("start sort......." );
        User result[] = topK.getTopKByHeap(userArray, 10000000);
        long usedTime = System.currentTimeMillis() -  startTime;
        System.out.println("end sorted,TOP K is :" + usedTime);
//
//        for(User resultItem : result) {
//            System.out.println( resultItem.getUserId() + ":" + resultItem.getIntegral());
//        }
        System.out.println("---------神奇的分割线---------");
//        for(User user : userArray){
//            System.out.println( user.getUserId() + ":" + user.getIntegral());
//        }
    }

}

class User {
    //用户ID
    private Integer userId;
    //用户积分
    private Integer integral;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public static User[] userHelper(int userNum){
        User[] userArray = new User[userNum];
        Random random = new Random(System.currentTimeMillis());
        for(int i = 0 ; i< userNum ;i ++ ){
            User user = new User();
            user.setUserId(userNum);
            user.setIntegral(random.nextInt(userNum));
            userArray[i] = user;
        }
        return userArray;
    }
}