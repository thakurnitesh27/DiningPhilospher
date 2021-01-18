package com.thakurnitesh27.diningphilosopher.elements;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chopsticks {
    private  final String id;
   // private State state;
    private final Object object;
    private final Lock lock;
    private Philosopher owner;
    public Chopsticks(String id) {
        this.id = id;
        this.object=new Object();
        lock=new ReentrantLock();

    }

    public String getId() {
        return id;
    }

    public void tryUsing(Philosopher philosopher) throws InterruptedException {

        System.out.println(philosopher.getId()+ " is attempting to use chopstick "+ this.id+" . Used by "+ owner!=null?null:owner.getId());
        lock.tryLock(10,TimeUnit.MILLISECONDS);
        this.owner=philosopher;
    }

    public void releaseChopstick(){
        if(owner!=null){
            System.out.println(owner.getId()+ " is attempting to release chopstick "+ this.id);
            this.owner=null;
            lock.unlock();
        }

    }



//    public State getState() {
//        return state;
//    }
//
//    public void setState(State state) {
//        this.state = state;
//    }
}


