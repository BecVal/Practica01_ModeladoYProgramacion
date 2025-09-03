package com.unam.ciencias.practica1.model;

import com.unam.ciencias.practica1.patterns.observer.Observer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
 * User class that represents a user within
 * the system.
 * 
 * Each user can subscribe to different streaming
 * services, manage a balance of money, and
 * receive notifications from the services to
 * which they are subscribed (implements Observer).
 */
public class User implements Observer {
    private String name;
    private double balance;
    private Map<Service, Plan> subscriptions;

    /*
     * User class constructor.
     * 
     * @param name User name.
     * @param initialMoney Initial amount of money in
     * their balance.
     */
    public User(String name, double initialMoney){
        this.name = name;
        this.balance = initialMoney;
        this.subscriptions = new HashMap<>();
    }

    /*
     * {@inheritDoc}
     */
    @Override
    public void update (String service, String message, BufferedWriter out) throws IOException {
        out.write("[" + name + "] Notified " + service + ": " + message + "\n");
    }

    /*
     * Method that subscribes the user to a service with
     * a specific plan.
     * 
     * @param service Service to which the user wishes
     * to subscribe.
     * @param plan Plan that the user wishes to contract.
     * @param out BufferedWriter to write to file
     */
    public void subscribe(Service service, Plan plan, BufferedWriter out) throws IOException {
        subscriptions.put(service, plan);
        service.registerObserver(this);
        out.write(name + " subscribed to " + service.getName() + " with the plan: " + plan.getDescription() + "\n");
    }

    /*
     * Method that cancels the subscription to a service
     * 
     * @param service Service to be canceled.
     * @param out BufferedWriter to write to file
     */
    public void cancelSubscription(Service service, BufferedWriter out) throws IOException{
        if (subscriptions.containsKey(service)){
            service.removeObserver(this);
            out.write(name + " canceled their subscription to: " + service.name + "\n");
        } else{
            out.write(name + " was not subscribed to: " + service.name + "\n");
        }
    }

    /*
     * Method that attempts to make a payment using the
     * user's balance.
     * 
     * @param amount Amount to be paid.
     * @param out BufferedWriter to write to file
     * @return true if the payment was successful, false
     * if these is insufficient balance.
     */
    public boolean makePayment(double amount, BufferedWriter out) throws IOException{
        if (balance >= amount){
            balance -= amount;
            out.write(name + " paid $" + amount + ". Their remaining balance is: $" + balance + "\n");
            return true;
        } else {
            out.write(name + " does not have enough balance to pay$" + amount + "\n");
            return false;
        }
    }

    /*
     * Method that returns the plan contracted for a
     * given service.
     * 
     * @param service Service being queried.
     * @return The associated plan, or null if not
     * subscribed.
     */
    public Plan getPlanForService(Service service) {
        return subscriptions.get(service);
    }

    public String getName() {
        return name;
    }

    public double getBalance(){
        return balance;
    }
}