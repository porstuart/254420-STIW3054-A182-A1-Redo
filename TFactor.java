public class TFactor extends Technical implements EffortEstimation{

    protected double total = 0;
    private double [] tWeight = new double[] {2, 1, 1, 1, 1, 0.5, 0.5, 2, 1, 1, 1, 1, 1};
    
    public TFactor(int tFactor[]){
        super(tFactor);
    }

    public double calculate(){
        for(int index = 0; index < 13; index++){
            total = total + (tFactor[index] * tWeight[index]);
        }
        return total;
    }

}