package com.jesse.concurrent;

import android.util.Log;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者模型的ReentrantLock版本实现
 *
 * @author wuzhao
 * @Date 2018/8/30
 * @mail： wuzhao@in66.com
 */
public class ProducerProblemByLock {


    public static final int BAR_CAPACITY = 4;
    private ReentrantLock lock = new ReentrantLock(true);
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();


    private Object[] bar = new Object[BAR_CAPACITY];
    private int count = 0;


    public Object consumer() {
        try {
            lock.lock();
            while (count == 0) {
                notEmpty.await();
            }
            Log.d("wuzhao test", "count:"+count+"thread:" + Thread.currentThread().getName() + "consuming");
            Object result = bar[0];
            count--;
            notFull.signal();
            return result;
        } catch (InterruptedException e) {


        } finally {
            lock.unlock();
        }
        return null;
    }


    public void producer() {
        try {
            lock.lock();
            while (count == BAR_CAPACITY) {
                notFull.await();
            }
            Log.d("wuzhao test", "count:"+count+"thread:" + Thread.currentThread().getName() + "producing");
            bar[0] = new Object();
            count++;
            notEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
