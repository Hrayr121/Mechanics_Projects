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
            double x = Math.random() * (currentL - 2 * r) + r;  
            double y = Math.random() * (2 * r) - r;  
            double px = Math.random() * 2 - 1; 
            double py = Math.sqrt(1 - px * px);  
            for (int i = 0; i < reflections; i++) {
                // Check which side the particle will hit
                if (y >= 0 && y <= r && x >= xcLeft && x <= xcRight) {
                    // Particle hits the top line segment
                    double t = (r - y) / py;  // intersection time
                    double xIntersect = x + px * t;  // x coordinate at intersection
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
        
        
}
            }}}}

       
