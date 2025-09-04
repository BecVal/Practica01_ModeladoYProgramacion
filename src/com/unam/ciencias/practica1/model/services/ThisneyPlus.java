package com.unam.ciencias.practica1.model.services;

import com.unam.ciencias.practica1.patterns.strategy.payment.*;
import com.unam.ciencias.practica1.model.Plan;
import com.unam.ciencias.practica1.model.Service;
import java.util.Random;
import java.util.ArrayList;

/**
 * Clase concreta que representa el servicio de streaming ThisneyPlus.
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
public class ThisneyPlus extends Service {
  private ArrayList<String> recommendations;
  
  /**
  * Constructor de la clase ThisneyPlus.
  * <p>
  * Inicializa el nombre del servicio, los planes disponibles y 
  * las 12 recomendaciones del año.
  * </p>
  */
  public ThisneyPlus() {
    super("ThisneyPlus");
    availablePlans.add(new Plan(new PaymentPromotion(160.0, 130.0, 3, "Plan con Promoción")));

    recommendations = new ArrayList<>();
    recommendations.add("Lokillo");
    recommendations.add("WandaCiego");
    recommendations.add("Ms. Marvel");
    recommendations.add("Andor");
    recommendations.add("What for...?");
    recommendations.add("Sun Knight");
    recommendations.add("The book of Canek Fett");
    recommendations.add("Falcon and the Winter Femboy");
    recommendations.add("Obi-Wan Kenobi");
    recommendations.add("High School Programming: The Series");
    recommendations.add("Hawkeye");
    recommendations.add("Star Wars: A New Hope");

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
    return "Este mes te recomendamos la serie: ' " + recommendations.get(random.nextInt(recommendations.size())) + " '";
  }
}
