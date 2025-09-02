package com.unam.ciencias.practica1.model;

import com.unam.ciencias.practica1.patterns.Observer;
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
    public void update (String service, String message) {
        System.out.println("[" + name + "] Notified " + service + ": " + message);
    }

    /*
     * Method that subscribes the user to a service with
     * a specific plan.
     * 
     * @param service Service to which the user wishes
     * to subscribe.
     * @param plan Plan that the user wishes to contract.
     */
    public void subscribe(Service service, Plan plan) {
        subscriptions.put(service, plan);
        service.registerObserver(service, plan);
        System.out.println(name + " subscribed to " + service.name + " with the plan: " + plan.getDescription());
    }

    /*
     * Method that cancels the subscription to a service
     * 
     * @param service Service to be canceled.
     */
    public void cancelSubscription(Service service){
        if (subscriptions.containsKey(service)){
            subscriptions.remove(service);
            service.removeObserver(this);
            System.out.println(name + " canceled their subscription to: " + service.name);
        } else{
            System.out.println(name + " was not subscribed to: " + service.name);
        }
    }

    /*
     * Method that attempts to make a payment using the
     * user's balance.
     * 
     * @param amount Amount to be paid.
     * @return true if the payment was successful, false
     * if these is insufficient balance.
     */
    public boolean makePayment(double amount){
        if (balance >= amount){
            balance -= amount;
            System.out.println(name + " paid $" + amount + ". Their remaining balance is: $" + balance);
            return true;
        } else {
            System.out.println(name + " does not have enough balance to pay$" + amount);
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