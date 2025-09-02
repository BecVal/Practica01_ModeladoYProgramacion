package com.unam.ciencias.practica1.model;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class MemeFlix extends Service {

    public MemeFlix() {
        super("MemeFlix");
        // Ejemplo: agregar algunos planes
        availablePlans.add(new Plan("Plan de Un Dispositivo", 120.0));
        availablePlans.add(new Plan("Plan de Dos dispositivos", 170.0));
        availablePlans.add(new Plan("Plan de 4 Dispositivos", 200.0));

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

