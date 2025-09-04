package com.unam.ciencias.practica1.model.services;

import com.unam.ciencias.practica1.patterns.strategy.payment.*;
import com.unam.ciencias.practica1.model.Plan;
import com.unam.ciencias.practica1.model.Service;
import java.util.Random;
import java.util.ArrayList;

/**
 * Clase concreta que representa el servicio de streaming Hvo Max.
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
public class HvoMax extends Service {
  private ArrayList<String> recommendations;


  /**
  * Constructor de la clase HvoMax.
  * <p>
  * Inicializa el nombre del servicio, los planes disponibles y 
  * las 12 recomendaciones del año.
  * </p>
  */
  public HvoMax() {
    super("HvoMax");
    availablePlans.add(new Plan(new PaymentPromotion(140, 0.0, 3, "Plan con Promoción")));
    availablePlans.add(new Plan(new FixedPayment(140.0, "Plan Básico")));

    recommendations = new ArrayList<>();
    recommendations.add("Game of Thrones");
    recommendations.add("Euphoria");
    recommendations.add("Succession");
    recommendations.add("The Last of Us");
    recommendations.add("Westworld");
    recommendations.add("Chernobyl");
    recommendations.add("Barry");
    recommendations.add("Watchmen");
    recommendations.add("The flight attendant");
    recommendations.add("Lovecraft Country");
    recommendations.add("Raised by Wolves");
    recommendations.add("True Detective");
    
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
