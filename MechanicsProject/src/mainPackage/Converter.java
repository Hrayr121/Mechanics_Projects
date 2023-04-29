package mainPackage;


public abstract class Converter {

    public abstract Spring systemOfSprings(String bitSequence);

    public double[] computeOscillations(double t, double dt, double x0, double v0, Spring system) {
        return system.move(t, dt, x0, v0);
    }

    public double[] computeFrequencyAmplitudes(double[] oscillations) {
        FT fourierTransform = new FT(oscillations);
        return fourierTransform.transform();
    }

    public abstract int convertToDecimal(String bitSequence);

	public Spring[] bitSequenceToSprings(int[] bits) {
		// TODO Auto-generated method stub
		return null;
	}

	public int evaluateDecimalValue(double[] freqAmplitudes) {
		// TODO Auto-generated method stub
		return 0;
	}
}


