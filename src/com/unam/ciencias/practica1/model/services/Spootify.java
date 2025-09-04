package com.unam.ciencias.practica1.model.services;

import com.unam.ciencias.practica1.patterns.strategy.payment.*;
import com.unam.ciencias.practica1.model.Plan;
import com.unam.ciencias.practica1.model.Service;
import java.util.Random;
import java.util.ArrayList;

/**
 * Clase concreta que representa el servicio de streaming Spootify. 
 * <p>
 * Hereda de {@link Service} e implementa sus métodos abstractos:
 * {@code chargeSubscribers()} para simular el cobro mensual
 * y {@code getRecommendationOfTheMonth()} para ofrecer
 * una recomendación de contenido entre 12 opciones (una por mes).
 * </p>
 * 
 * Ejemplo de uso:
 * <pre>
 *     Service hvoMax = new HvoMax();
 *     hvoMax.chargeSubscribers();
 *     System.out.println(hvoMax.getRecommendationOfTheMonth());
 * </pre>
 * 
 * @author César
 * @version 1.0
 */
public class Spootify extends Service {
  private ArrayList<String> recommendations;

  /**
  * Constructor de la clase Spootify.
  * <p>
  * Inicializa el nombre del servicio, los planes disponibles y 
  * las 12 recomendaciones del año.
  * </p>
  */
  public Spootify() {
    super("Spootify");
    availablePlans.add(new Plan(new FreePayment()));
    availablePlans.add(new Plan(new FixedPayment(80.0, "Plan Premium")));
    
    recommendations = new ArrayList<>();
    recommendations.add("Cumbia para ligar en el tianguis");
    recommendations.add("La peda de los viernes");
    recommendations.add("Corridos para la peda");
    recommendations.add("Cumbias Aimp3");
    recommendations.add("Cumbias rebajadas underground");
    recommendations.add("Loco mia y sus exitos");
    recommendations.add("Luismi clasico");
    recommendations.add("Chalino Sanchez mix");
    recommendations.add("Dani flow exitos nenas de telesecundaria");
    recommendations.add("Hasta que amanezca");
    recommendations.add("Molotov remix");
    recommendations.add("Sonido Pirata con Medio Metro");
  }

  /**
  * {@inheritDoc}
  * <p>
  * Devuelve una recomendación aleatoria de entre las 12 disponibles.
  * </p>
  * 
  * @return una cadena con la recomendación del mes
  */
  @Override
  public String getRecommendationOfTheMonth() {
    Random random = new Random();
    return "Este mes te recomendamos escuchar la playlist: ' " + recommendations.get(random.nextInt(recommendations.size())) + " '";
  }
}
