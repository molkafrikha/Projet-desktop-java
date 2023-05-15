/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fithnitek.entities;

/**
 *
 * @author Nadhem
 */
public class CalculeDistance extends OffreCovoiturage {

    private static final double EARTH_RADIUS = 6371; // Rayon de la terre en kilomètres

    public static double distance(double latdepart, double londepart, double latarriver, double lonarriver) {
        double dLat = Math.toRadians(latarriver - latdepart);
        double dLon = Math.toRadians(lonarriver - londepart);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(latdepart)) * Math.cos(Math.toRadians(latarriver))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = EARTH_RADIUS * c;
        return distance;
    }
}
