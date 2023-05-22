package Simulations;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VerticalSimulation {

    public static void main(String[] args) {
        Random random = new Random();

        double x = random.nextDouble() * 2 - 1;  // Random x coordinate between -1 and 1
        double y = random.nextDouble() * 2 - 1;  // Random y coordinate between -1 and 1
        double px = random.nextDouble() * 5 + 5;  // Random x component of momentum between 5 and 10
        double py = random.nextDouble() * 5 + 5;  // Random y component of momentum between 5 and 10

        int reflections = 0;
        List<double[]> reflectionPoints = new ArrayList<>();
        double delta = 0.001;  
        while (reflections <= 30) {
            double t = (-1 - x) / px;  // Calculate intersection time with left boundary
            double yIntersect = y + py * t - 5 * t * t;  // Calculate y coordinate at intersection

         // Reflection off left boundary
            if (yIntersect >= -1 && yIntersect <= 1 && t > 0) {               
                x = -1;
                y = yIntersect;
                 px = -(y * y - x * x) * px - 2 * x * y * py;
                 py = -2 * x * y * px + (x * x - y * y) * py;
                reflectionPoints.add(new double[]{x, y});
                reflections++;
                
                continue;
            }

            t = (1 - x) / px;  // Calculate intersection time with right boundary
            yIntersect = y + py * t - 5 * t * t;  // Calculate y coordinate at intersection

            if (yIntersect >= -1 && yIntersect <= 1 && t > 0) {
                // Reflection off right boundary
                x = 1;
                y = yIntersect;
                px = -(y * y - x * x) * px - 2 * x * y * py;
                py = -2 * x * y * px + (x * x - y * y) * py;
                reflectionPoints.add(new double[]{x, y});
                reflections++;
                continue;
            }

            t = (-1 - y) / py;  // Calculate intersection time with bottom boundary
            double xIntersect = x + px * t;  // Calculate x coordinate at intersection

            if (xIntersect >= -1 && xIntersect <= 1 && t > 0) {
                // Reflection off bottom boundary
                x = xIntersect;
                y = -1;
                px = -(y * y - x * x) * px - 2 * x * y * py;
                py = -2 * x * y * px + (x * x - y * y) * py;
                reflectionPoints.add(new double[]{x, y});
                reflections++;
                continue;
            }

            t = (1 - y) / py;  // Calculate intersection time with top boundary
            xIntersect = x + px * t;  // Calculate x coordinate at intersection

            if (xIntersect >= -1 && xIntersect <= 1 && t > 0) {
                // Reflection off top boundary
                x = xIntersect;
                y = 1;
                px = -(y * y - x * x) * px - 2 * x * y * py;
                py = -2 * x * y * px + (x * x - y * y) * py;
                reflectionPoints.add(new double[]{x, y});
                reflections++;
                continue;
            }

            break;  // No intersection, stop simulation
        }

        
    }
}

