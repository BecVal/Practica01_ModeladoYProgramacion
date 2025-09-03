
package com.unam.ciencias.practica1.model;

import com.unam.ciencias.practica1.patterns.observer.Observer;
import com.unam.ciencias.practica1.patterns.observer.Subject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * Abstract class Service representing a streaming
 * service (example: MemeFlix, Spootify, Momazon).
 * 
 * Implements the Subject interface, allowing it
 * tp register users as observers and send them
 * notifications.
 */
public abstract class Service implements Subject{

    protected String name;
    protected List<Observer> observers;
    protected List<Plan> availablePlans;

    /*
     * Constructor class Service.
     * 
     * @param name Service name
     */
    public Service(String name){
        this.name = name;
        this.observers = new ArrayList<>();
        this.availablePlans = new ArrayList<>();
    }

    /*
     * {@inheritDoc}
     */
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }


    /*
     * {@inheritDoc}
     */
    @Override
    public void removeObserver(Observer observer){
        observers.remove(observer);
    }


    /*
     * {@inheritDoc}
     */
    @Override
    public void notifyObserver(String message, BufferedWriter out) throws IOException {
        for (Observer o : observers) {
            o.update(name, message, out);
        }
    }

    /*
     * Abstract method that each service must implement
     * to charge its subscribers monthly
     */
    public void chargeSubscribers(BufferedWriter out) throws IOException {
        List<Observer> observersToRemove = new ArrayList<>();

        for (Observer observer : observers) {
            if(observer instanceof User) {
                User user = (User) observer;
                Plan plan = user.getPlanForService(this);
                if (plan != null) {
                    double amount = plan.getCost();
                    boolean paymentSuccess = user.makePayment(amount, out);

                    if (paymentSuccess) {
                        plan.increaseMonth();
                        int months = plan.getMonthsSubscribed();
                        user.update(name, "The monthly payment of $" + amount +" has been collected. \n", out);
                        user.update(name, user.getName() + ", you have been subscribed for " + months + " months!! \n", out);
                    } else {
                        observersToRemove.add(observer);
                        user.update(name, "We are sorry, " + name + ", you do not have sufficient funds to pay this service. \n", out);
                    }
                }
            }
        }
        for (Observer observer : observersToRemove) {
            removeObserver(observer);
        }
    }

    /*
     * Abstract method that each service must implement
     * to recommend content to users.
     */
    public abstract String getRecommendationOfTheMonth();

    public String getName(){
        return name;
    }

}