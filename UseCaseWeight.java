public class UseCaseWeight extends TotalUseCase implements EffortEstimation{

    double total = 0;

    public UseCaseWeight(int useCase1, int useCase2, int useCase3){
        super(useCase1, useCase2, useCase3);
    }

    public double calculate(){
        total = (useCase1 * 5) + (useCase2 * 10) + (useCase3 * 15);
        return total; 
    }

}