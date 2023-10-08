package com.example.assign2_dsa2_dbp_jc;

public class Utilities {
    public static double distance(Station station1, Station station2) {
        int earthRadius = 6371; // km
        double lat1, lat2, lon1, lon2;
        lat1 = station1.getLatitude();
        lon1 = station1.getLongitude();
        lat2 = station2.getLatitude();
        lon2 = station2.getLongitude();

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return (earthRadius * c)*1000;
    }
}
