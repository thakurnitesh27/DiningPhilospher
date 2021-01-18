package com.thakurnitesh27.diningphilosopher.elements;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.thakurnitesh27.diningphilosopher.elements.Constants.EATING_TIME;

public class Philosopher {

   private final String Id;
    private Action action;
    private Chopsticks leftChopstick;
    private Chopsticks rightChopstick;
    private AtomicBoolean isEating;

    public Philosopher(String id) {
        Id = id;
        action=Action.THINK;
        isEating=new AtomicBoolean(false);
    }
    public  void changeAction(Action action){
        this.action=action;
    }
    public Action getAction(){
        return this.action;
    }

    public String getId() {
        return Id;
    }

    public void attemptToEat(){
        while (!isEating.get()) {
            System.out.println(Id + " Attempting to eat...");
            try {
                leftChopstick.tryUsing(this);
                rightChopstick.tryUsing(this);
                System.out.println(Id +" is eating");
                action=Action.EAT;
                Thread.sleep(EATING_TIME);
                leftChopstick.releaseChopstick();
                rightChopstick.releaseChopstick();
                isEating.set(true);
                action=Action.EATEN;

            } catch (Exception e) {
                System.out.println("ERROR "+e.getCause()+"...Re-attempting");
                leftChopstick.releaseChopstick();
                rightChopstick.releaseChopstick();


            }
            System.out.println(Id+" is done eating.");
        }


    }

    public Chopsticks getLeftChopstick() {
        return leftChopstick;
    }

    public void setLeftChopstick(Chopsticks leftChopstick) {
        this.leftChopstick = leftChopstick;
    }

    public Chopsticks getRightChopstick() {
        return rightChopstick;
    }

    public void setRightChopstick(Chopsticks rightChopstick) {
        this.rightChopstick = rightChopstick;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
