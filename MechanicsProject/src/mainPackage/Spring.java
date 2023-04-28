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
	
	
	public double[] move(double t, double dt, double x0, double v0) {
		return null;
		
	}
	
	public double[] move(double t, double dt, double x0) {
		return null;
	}
	
	public double[] move(double t0, double t1, double dt, double x0, double v0) {
		return null;
	}
	
	double[] move(double t0, double t1, double dt, double x0, double v0, double m) {
		return null;
	}
	
}
