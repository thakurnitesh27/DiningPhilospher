package com.thakurnitesh27.diningphilosopher.elements;

import com.thakurnitesh27.diningphilosopher.enhance.CompletableFutureCollection;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.thakurnitesh27.diningphilosopher.elements.Constants.CONCURRENCY_LEVEL;

public class Manager {

    List<Philosopher> philosophers;
    List<Chopsticks> chopsticks;

    ExecutorService executorService= Executors.newFixedThreadPool(5);
    CompletableFuture completableFuture=new CompletableFuture();
    private void createElements(){
        philosophers=new ArrayList<Philosopher>();
        chopsticks=new ArrayList<>();

        for(int i=0;i<Constants.NUMBER_OF_PHILOSOPHER;i++){
            philosophers.add(new Philosopher("Philosopher_"+i));
        }
        for(int i=0;i<Constants.NUMBER_OF_CHOPSTICKS;i++){
            chopsticks.add(new Chopsticks("Chopsticks_"+i));
        }
    }

    public  void initiate(){
        createElements();

        for(int i=0;i<philosophers.size();i++){

            Philosopher philosopher=philosophers.get(i);
            int leftIndex=i;
            int rightIndex=i+1;

            if(leftIndex>chopsticks.size()-1){
                leftIndex=leftIndex-chopsticks.size();
            }
            if(rightIndex>chopsticks.size()-1){
                rightIndex=rightIndex-chopsticks.size();
            }
            System.out.println("Assigning "+ leftIndex +" and "+ rightIndex + " as chopstick to "+ philosopher.getId());
            Chopsticks leftChopstick=chopsticks.get(leftIndex);
            Chopsticks rightChopstick=chopsticks.get(rightIndex);

            philosopher.setLeftChopstick(leftChopstick);
            philosopher.setRightChopstick(rightChopstick);

            philosopher.setAction(Action.THINK);
        }
        attemptEating();




    }
    public void attemptEating(){
        CountDownLatch latch=new CountDownLatch(1); //just to ensure that all threads execute at once.

        ExecutorService executorService=Executors.newFixedThreadPool(CONCURRENCY_LEVEL);
        philosophers.stream().forEach(p-> executorService.submit(()->{
            try {
                latch.await();
                p.attemptToEat();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
        latch.countDown();
    }
}
