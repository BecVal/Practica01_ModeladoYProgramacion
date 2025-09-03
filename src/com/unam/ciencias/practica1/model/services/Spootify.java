package com.unam.ciencias.practica1.model.services;

import com.unam.ciencias.practica1.patterns.strategy.payment.*;
import com.unam.ciencias.practica1.model.Plan;
import com.unam.ciencias.practica1.model.Service;
import java.util.Random;
import java.util.ArrayList;

public class Spootify extends Service {
  private ArrayList<String> recommendations;

  public Spootify() {
    super("Spootify");
    availablePlans.add(new Plan(new FreePayment()));
    availablePlans.add(new Plan(new FixedPayment(80.0, "Plan Premium")));
    
    recommendations = new ArrayList<>();
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
    notifyObserver("¡Se ha realizado el cobro mensual de tu suscripción en HvoMax!");
  }

  @Override
  public String getRecommendationOfTheMonth() {
    Random random = new Random();
    return "Este mes te recomendamos escuchar la playlist: ' " + recommendations.get(random.nextInt(recommendations.size())) + " '";
  }
}
