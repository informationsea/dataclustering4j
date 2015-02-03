package info.informationsea.dataclustering4j.matrix.aggregate;

import java.util.Arrays;
import java.util.List;

/**
 * dataclustering4j
 * Copyright (C) 2015 Yasunobu OKAMURA
 * Created at 15/02/03
 */
public class Quantile<T extends Number> implements Aggregate<T, Double> {

    private double ratio;
    
    public Quantile(double ratio) {
        this.ratio = ratio;
    }
    
    @Override
    public Double process(List<T> array) {
        Number[] numbers = new Number[array.size()];
        array.toArray(numbers);
        Arrays.sort(numbers);
        
        double actualIndex = ratio*(numbers.length-1);
        if ((actualIndex - ((int)actualIndex)) == 0) {
            return numbers[(int)actualIndex].doubleValue();
        } else {
            int after = (int)Math.ceil(actualIndex);
            int before = (int)Math.floor(actualIndex);
            return (numbers[after].doubleValue()*(actualIndex - before) + numbers[before].doubleValue()*(after -actualIndex));
        }
    }
}
