package com.jesse.concurrent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testConcurrency();
    }


    public void testConcurrency() {
        final ProducerProblemByLock producerProblemByLock = new ProducerProblemByLock();
        for (int i = 0; i < 10; i++) {
            new Thread(null, new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        producerProblemByLock.consumer();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, "consumer" + i).start();
        }


        for (int i = 0; i < 11; i++) {
            new Thread(null, new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        producerProblemByLock.producer();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, "producer" + i).start();
        }
    }


}
