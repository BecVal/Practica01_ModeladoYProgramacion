
package com.unam.ciencias.practica1.model;

import com.unam.ciencias.practica1.patterns.Observer;
import com.unam.ciencias.practica1.patterns.Subject;
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
public abstract class Service{

    protected String name;
    protected List<Observer> observer;
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
    public void notifyObserver(String message) {
        for (com.unam.ciencias.practica1.patterns.observer.Observer o : observers) {
            o.update(name, message);
        }
    }

    /*
     * Abstract method that each service must implement
     * to charge its subscribers monthly
     */
    public abstract void chargeSubscribers();

    /*
     * Abstract method that each service must implement
     * to recommend content to users.
     */
    public abstract String getRecommendationOfTheMonth();

    public String getName(){
        return name;
    }

}