package com.unam.ciencias.practica1.model;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class Spootify extends Service {
  public Spootify() {
    super("Spootify");
    availablePlans.add(new Plan("Plan Free", 0.0));
    availablePlans.add(new Plan("Plan Premium",80.0));

    ArrayList recommendations = new ArrayList<>();
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
  public void getRecommendationOfTheMonth() {
    Random random = new Random();
    return "Este mes te recomendamos escuchar la playlist: ' " + recommendations.get(random.nextInt(recommendations.size())) + " '";
  }
