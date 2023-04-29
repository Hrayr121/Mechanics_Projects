package mainPackage;
import java.util.Arrays;

public class FT {
	
	    private int N; // number of samples
	    private double[] x; // input signal
	    private double[] re; // real part of DFT
	    private double[] im; // imaginary part of DFT

	    public FT(double[] x) {
	        this.N = x.length;
	        this.x = Arrays.copyOf(x, N);
	        this.re = new double[N];
	        this.im = new double[N];
	    }

	    public double[] transform() {
	        for (int k = 0; k < N; k++) {  // for each frequency
	            for (int n = 0; n < N; n++) {  // for each time sample
	                double theta = 2 * Math.PI * k * n / N;
	                re[k] += x[n] * Math.cos(theta);
	                im[k] -= x[n] * Math.sin(theta);
	            }
	        }

	        double[] amplitudes = new double[N/2];
	        for (int k = 0; k < N/2; k++) {
	            amplitudes[k] = Math.sqrt(re[k]*re[k] + im[k]*im[k]);
	        }
	        return amplitudes;
	    }

	    public double[] transform(int numHarmonics) {
	        transform();
	        double[] amplitudes = new double[numHarmonics];
	        for (int k = 0; k < numHarmonics; k++) {
	            amplitudes[k] = Math.sqrt(re[k]*re[k] + im[k]*im[k]);
	        }
	        return amplitudes;
	    }
	}


