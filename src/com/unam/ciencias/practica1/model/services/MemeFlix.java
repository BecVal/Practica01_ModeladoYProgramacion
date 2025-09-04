package com.unam.ciencias.practica1.model.services;

import com.unam.ciencias.practica1.patterns.strategy.payment.*;
import com.unam.ciencias.practica1.model.Plan;
import com.unam.ciencias.practica1.model.Service;
import java.util.Random;
import java.util.ArrayList;


/**
 * Clase concreta que representa el servicio de streaming MemeFlix.
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
public class MemeFlix extends Service {
    private ArrayList<String> recommendations;

    /**
    * Constructor de la clase MemeFlix.
    * <p>
    * Inicializa el nombre del servicio, los planes disponibles y 
    * las 12 recomendaciones del año.
    * </p>
    */
    public MemeFlix() {
        super("MemeFlix");
        availablePlans.add(new Plan(new FixedPayment(120.0, "Plan de Un Dispositivo")));
        availablePlans.add(new Plan(new FixedPayment(170.0, "Plan de Dos dispositivos")));
        availablePlans.add(new Plan(new FixedPayment(200.0, "Plan de Cuatro Dispositivos")));

        recommendations = new ArrayList<String>();
        recommendations.add("Breaking God");
        recommendations.add("Miercoles");
        recommendations.add("Python Kai");
        recommendations.add("Stranger Objects");
        recommendations.add("No somos caballos");
        recommendations.add("Somos humanos");
        recommendations.add("White Mirror");
        recommendations.add("Angel lucifer = new Angel();");
        recommendations.add("Us");
        recommendations.add("El pensadero de canek");
        recommendations.add("The Java Academy");
        recommendations.add("Sex.py Education");
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
    return "Este mes te recomendamos la serie: ' " + recommendations.get(random.nextInt(recommendations.size())) + " '" ;
    }
}

