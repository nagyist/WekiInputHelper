/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wekiinputhelper.Modifiers;

import wekiinputhelper.Modifiers.WindowedOperation.Operation;
import wekiinputhelper.UsesOnlyOriginalInputs;

/**
 *
 * @author rebecca
 */
public class WindowedAverage implements ModifiedInput, UsesOnlyOriginalInputs{
    WindowedOperation wo;
    Operation op;
    
    public WindowedAverage(String originalName, int index, int windowSize) {
        op = new Operation() {

            @Override
            public double doOperation(double[] vals) {
                double sum = 0;
                for (int i = 0; i < vals.length; i++) {
                    sum += vals[i];
                }
                return sum / vals.length;
            }

            @Override
            public String shortName() {
                return "Avg";
            }
        };
        wo = new WindowedOperation(originalName, op, index, windowSize);
    }
    
    @Override
    public String getName() {
        return wo.getName();
    }

    @Override
    public void updateForInputs(double[] inputs) {
        wo.updateForInputs(inputs);
    }

    @Override
    public int getSize() {
        return 1;
    }
    
    public int getWindowSize() {
        return wo.getWindowSize();
    }

    @Override
    public double getValue() {
        return wo.getValue();
    }
    
    
    public static void main(String[] args) {
        WindowedAverage bi = new WindowedAverage("feat1", 0, 3);
   
        for (int i = 0; i < 10; i++) {
            System.out.print(i + ": ");
            bi.updateForInputs(new double[] {i});
            double d = bi.getValue();
            System.out.println(d);
        }
    }
}
