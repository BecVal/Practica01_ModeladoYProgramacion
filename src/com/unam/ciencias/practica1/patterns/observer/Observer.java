package com.unam.ciencias.practica1.patterns.observer;

import java.io.BufferedWriter;
import java.io.IOException;

/*
 * Observer interface that defines the
 * contract that all observers within
 * the Observer pattern must follow.
 * 
 * Observers are notified by Subjects
 * when a relevant event occurs.
 */
public interface Observer {


    /*
     * Method called when the subject notifies
     * an event.
     * 
     * @param service Name of the service sending
     * the notification.
     * @param message Message sent by the service.
     */
    void update(String service, String message, BufferedWriter out) throws IOException;
}