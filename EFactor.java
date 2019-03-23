public class EFactor extends Environmental implements EffortEstimation{

    protected double total = 0;
    private double [] eWeight = new double[] {1, 0.5, 1, 0.5, 0, 2, -1, -1};
    
    public EFactor(int eFactor[]){
        super(eFactor);
    }

    public double calculate(){
        for(int index = 0; index < 8; index++){
            total = total + (eFactor[index] * eWeight[index]);
        }
        return total;
    }

}