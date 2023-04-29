package mainPackage;

public class ConverterInt extends Converter {

    private final double k = 1.0;

    @Override
    public Spring systemOfSprings(String bitSequence) {
        int numBits = bitSequence.length();
        Spring[] springs = new Spring[numBits];
        for (int i = 0; i < numBits; i++) {
            if (bitSequence.charAt(i) == '1') {
                springs[i] = new Spring(k);
            } else {
                springs[i] = new Spring(0);
            }
        }
        new SpringArray();
		return SpringArray.equivalentSpring("{}");
    }

    @Override
    public int convertToDecimal(String bitSequence) {
        Spring spring = systemOfSprings(bitSequence);
        double[] oscillations = computeOscillations(1.0, 0.01, 0.0, 0.0, spring);
        double[] freqAmps = computeFrequencyAmplitudes(oscillations);

        double maxFreqAmp = 0.0;
        int maxFreqIndex = 0;
        for (int i = 0; i < freqAmps.length; i++) {
            if (freqAmps[i] > maxFreqAmp) {
                maxFreqAmp = freqAmps[i];
                maxFreqIndex = i;
            }
        }

        return maxFreqIndex;
    }
}


