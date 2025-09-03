package com.unam.ciencias.practica1.patterns.observer;

import java.io.BufferedWriter;
import java.io.IOException;

/*
 * Subject interface that defines the
 * operations that observable subjects
 * must implement within the Observer
 * pattern.
 * 
 * A subject is observed by multiple
 * objects (Observers) and is responsible
 * for notifying them when an important
 * event occurs.
 */
public interface Subject {

    /*
     * Method that registers a new observer in
     * the observer list.
     * 
     * @param observer The observer you want to
     * register.
     */
    void registerObserver(Observer observer);

    /*
     * Method that removes an observer from the 
     * list of observers.
     * 
     * @param observer The observer you want to
     * remove.
     */
    void removeObserver(Observer observer);

    /*
     * Method that notifies all registered observers 
     * with a message.
     * 
     * @param message The message to be sent to observers.
     * @param out BufferedWriter to write to file
     */
    void notifyObserver(String message, BufferedWriter out) throws IOException;

}