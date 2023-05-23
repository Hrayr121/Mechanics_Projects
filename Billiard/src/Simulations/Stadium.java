package Simulations;

import java.util.ArrayList;
import java.util.List;

public class Stadium {
	public static void main(String[] args) {
        double r = 1.0;  // Radius of the circle
        double xcLeft = 0.0;  // x coordinate of the center of the left semicircle
        double xcRight = 2.0;  // x coordinate of the center of the right semicircle
        double L = 2.0;  // Length of the line connecting the semicircles
        double delta = 1e-6;  // Small delta for deviation check
        int reflections = 5; 
        
     // Perform simulation for different values of L
        for (double currentL = L; currentL <= 3 * L; currentL += L) {
            List<double[]> reflectionPoints = new ArrayList<>();
            double x = Math.random() * (currentL - 2 * r) + r;  // Random initial x coordinate inside the circle
            double y = Math.random() * (2 * r) - r;  // Random initial y coordinate inside the circle
            double px = Math.random() * 2 - 1;  // Random initial x component of momentum
            double py = Math.sqrt(1 - px * px);  // Calculate y component of momentum based on |p|

            for (int i = 0; i < reflections; i++) {
                // Check which side the particle will hit
                if (y >= 0 && y <= r && x >= xcLeft && x <= xcRight) {
                    // Particle hits the top line segment
                    double t = (r - y) / py;  // Calculate intersection time
                    double xIntersect = x + px * t;  // Calculate x coordinate at intersection
                    if (xIntersect >= xcLeft && xIntersect <= xcRight) {
                        // Reflection off top line segment
                        x = xIntersect;
                        y = r;
                        py = -py;
                        reflectionPoints.add(new double[]{x, y});
                        continue;
                    }
                } else if (y >= -r && y <= 0 && x >= xcLeft && x <= xcRight) {
                    // Particle hits the bottom line segment
                    double t = (-r - y) / py;  // Calculate intersection time
                    double xIntersect = x + px * t;  // Calculate x coordinate at intersection
                    if (xIntersect >= xcLeft && xIntersect <= xcRight) {
                     // Reflection off bottom line segment
                      x = xIntersect;
                      y = -r;
                      py = -py;
                      reflectionPoints.add(new double[]{x, y});
                        continue;
                    }
                } else if (y >= 0 && y <= r && x >= xcLeft - r && x <= xcLeft) {
                    // Particle hits the left semicircle
                    double t = (-Math.sqrt(r * r - (x - xcLeft) * (x - xcLeft)) - y) / py;  // Calculate intersection time
                    double xIntersect = x + px * t;  // Calculate x coordinate at intersection
                    if (xIntersect >= xcLeft - r && xIntersect <= xcLeft) {
                        // Reflection off left semicircle
                     x = xIntersect;
                       y = -Math.sqrt(r * r - (x - xcLeft) * (x - xcLeft));
                     double xMinusXc = x - xcLeft;
                      double temp = px;
                     px = (y * y - xMinusXc * xMinusXc) * px - 2 * xMinusXc * y * py;
                     py = -2 * xMinusXc * y * temp + (xMinusXc * xMinusXc - y * y) * py;
                      reflectionPoints.add(new double[]{x, y});
                        continue;
                    }
                } else if (y >= 0 && y <= r && x >= xcRight && x <= xcRight + r) {
                    // Particle hits the right semicircle
                    double t = (-Math.sqrt(r * r - (x - xcRight) * (x - xcRight)) - y) / py;  // Calculate intersection time
                    double xIntersect = x + px * t;  // Calculate x coordinate at intersection
                    if (xIntersect >= xcRight && xIntersect <= xcRight + r) {
                        // Reflection off right semicircle
                          x = xIntersect;
                          y = -Math.sqrt(r * r - (x - xcRight) * (x - xcRight));
                          double xMinusXc = x - xcRight;
                        double temp = px;
                         px = (y * y - xMinusXc * xMinusXc) * px - 2 * xMinusXc * y * py;
                        py = -2 * xMinusXc * y * temp + (xMinusXc * xMinusXc - y * y) * py;
                       reflectionPoints.add(new double[]{x, y});
                        continue;
                    }
                }

                break;  // No intersection, stop simulation
            }

            // Print the reflection points
            System.out.println("Reflection points for L = " + currentL + ":");
            for (double[] point : reflectionPoints) {
                System.out.println("(" + point[0] + ", " + point[1] + ")");
            }
            System.out.println();

            // Reverse the momentum and check for deviation
            List<double[]> reversedReflectionPoints = new ArrayList<>(reflectionPoints);
            int reversedReflections = reflectionPoints.size();
            for (int i = 0; i < reversedReflections; i++) {
                double[] point = reversedReflectionPoints.get(i);
                double temp = point[1];
                point[1] = -point[1];
                double xMinusXc = point[0] - xcRight;
                double tempPx = px;
                px = (point[1] * point[1] - xMinusXc * xMinusXc) * px - 2 * xMinusXc * point[1] * py;
                py = -2 * xMinusXc * point[1] * tempPx + (xMinusXc * xMinusXc - point[1] * point[1]) * py;
            }

            // Check if the reversed path deviates more than the specified delta
            int deviationPoint = -1;
            for (int i = 0; i < Math.min(reflections, reversedReflections); i++) {
                 double[] point = reflectionPoints.get(i);
                double[] reversedPoint = reversedReflectionPoints.get(i);
                double deviation = Math.sqrt((point[0] - reversedPoint[0]) * (point[0] - reversedPoint[0]) +
                        (point[1] - reversedPoint[1]) * (point[1] - reversedPoint[1]));
                if (deviation > delta) {
                    deviationPoint = i;
                    break;
                }
            }

            if (deviationPoint == -1) {
                System.out.println("Reversed path coincides with the straight path.");
            } else {
                System.out.println("Reversed path deviates from the straight path after " +
                        deviationPoint + " reflections.");
            }
            System.out.println();
        }
    }
}

       
