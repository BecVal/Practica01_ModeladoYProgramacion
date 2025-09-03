package com.unam.ciencias.practica1.model.services;

import com.unam.ciencias.practica1.patterns.strategy.payment.*;
import com.unam.ciencias.practica1.model.Plan;
import com.unam.ciencias.practica1.model.Service;
import java.util.Random;
import java.util.ArrayList;

public class MemeFlix extends Service {
    private ArrayList<String> recommendations;

    public MemeFlix() {
        super("MemeFlix");
        availablePlans.add(new Plan(new FixedPayment(120.0, "Plan de Un Dispositivo")));
        availablePlans.add(new Plan(new FixedPayment(170.0, "Plan de Dos dispositivos")));
        availablePlans.add(new Plan(new FixedPayment(200.0, "Plan de Cuatro Dispositivos")));

        recommendations = new ArrayList<String>();
        recommendations.add("");
        recommendations.add("");
        recommendations.add("");
        recommendations.add("");
        recommendations.add("");
        recommendations.add("");
        recommendations.add("");
        recommendations.add("");
        recommendations.add("");
        recommendations.add("");
        recommendations.add("");
        recommendations.add("");
  
    }

    @Override
    public void chargeSubscribers() {
        notifyObserver("¡Se ha realizado el cobro mensual de tu suscripción en MemeFlix!");
    }



    @Override
    public String getRecommendationOfTheMonth() {
      Random random = new Random();

      return "Este mes te recomendamos la serie: ' " + recommendations.get(random.nextInt(recommendations.size())) + " '" ;
    }
}

