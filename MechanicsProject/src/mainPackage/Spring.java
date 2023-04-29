package mainPackage;

public class Spring {

	private double k;
	
	public static final int defaultStiff = 1;
	
	public Spring() {
		this(defaultStiff);
	}
	
	public Spring(double stiff) {
		setK(stiff);
	}

	public double getK() {
		return k;
	}

	private void setK(double k) {
		this.k = k;
	}
	
	
//	public double[] move(double t, double dt, double x0, double v0) {
//		return null;
//		
//	}
//	
//	public double[] move(double t, double dt, double x0) {
//		return null;
//	}
//	
//	public double[] move(double t0, double t1, double dt, double x0, double v0) {
//		return null;
//	}
//	
//	double[] move(double t0, double t1, double dt, double x0, double v0, double m) {
//		return null;
//	}
	
	public double[] move(double t, double dt, double x0, double v0) {
        int n = (int) (t / dt) + 1; // number of time steps
        double[] x = new double[n]; // array to store coordinates
        double omega = Math.sqrt(k); // natural frequency
        double A = x0; // amplitude
        double phi = Math.atan(v0 / (omega * x0)); // phase angle

        for (int i = 0; i < n; i++) {
            x[i] = A * Math.cos(omega * i * dt + phi); // compute coordinate
        }

        return x;
    }

    public double[] move(double t, double dt, double x0) {
        return move(t, dt, x0, 0.0);
    }

    public double[] move(double t0, double t1, double dt, double x0, double v0) {
        int n = (int) ((t1 - t0) / dt) + 1; // number of time steps
        double[] x = new double[n]; // array to store coordinates
        double omega = Math.sqrt(k); // natural frequency
        double A = x0; // amplitude
        double phi = Math.atan(v0 / (omega * x0)); // phase angle

        for (int i = 0; i < n; i++) {
            double t = t0 + i * dt;
            x[i] = A * Math.cos(omega * t + phi); // compute coordinate
        }

        return x;
    }

    public double[] move(double t0, double t1, double dt, double x0, double v0, double m) {
        int n = (int) ((t1 - t0) / dt) + 1; // number of time steps
        double[] x = new double[n]; // array to store coordinates
        double omega = Math.sqrt(k / m); // natural frequency
        double A = x0; // amplitude
        double phi = Math.atan(v0 / (omega * x0)); // phase angle

        for (int i = 0; i < n; i++) {
            double t = t0 + i * dt;
            x[i] = A * Math.cos(omega * t + phi); // compute coordinate
        }

        return x;
    }
    
    
    public Spring inSeries(Spring that) {
        double k = this.k + that.getK();
        return new Spring(k);
    }

    public Spring inParallel(Spring that) {
        double k = (this.k * that.getK()) / (this.k + that.getK());
        return new Spring(k);
    }
	
}
