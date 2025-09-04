package com.unam.ciencias.practica1.model.services;

import com.unam.ciencias.practica1.patterns.strategy.payment.*;
import com.unam.ciencias.practica1.model.Plan;
import com.unam.ciencias.practica1.model.Service;
import java.util.Random;
import java.util.ArrayList;

/**
 * Clase concreta que representa el servicio de streaming Momazon.
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
public class Momazon extends Service {
  private ArrayList<String> recommendations;

  /**
  * Constructor de la clase Momazon.
  * <p>
  * Inicializa el nombre del servicio, los planes disponibles y 
  * las 12 recomendaciones del año.
  * </p>
  */
  public Momazon() {
    super("Momazon");
    availablePlans.add(new Plan(new FixedPayment(110.0, "Plan Normal")));
    availablePlans.add(new Plan(new FixedPayment(150.0, "Plan Premium")));

    recommendations = new ArrayList<>();
    recommendations.add("The Boys Basados");
    recommendations.add("Digital Pomni Circus");
    recommendations.add("Good Omens");
    recommendations.add("Inservible");
    recommendations.add("DownLoad");
    recommendations.add("Reacher");
    recommendations.add("Fleabag");
    recommendations.add("Hunters");
    recommendations.add("Jack Ryan");
    recommendations.add("Carnival Go");
    recommendations.add("Bosch.java");
    recommendations.add("The Expanse");

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
