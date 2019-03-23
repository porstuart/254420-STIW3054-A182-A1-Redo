public class UnadjustedUCP implements EffortEstimation{

    Actor actor;
    UseCaseWeight ucWeight;
    double total = 0;
    
    public UnadjustedUCP(Actor actor, UseCaseWeight ucWeight){
        this.actor = actor;
        this.ucWeight = ucWeight;
    }

    public double calculate(){
        total = actor.calculate() + ucWeight.calculate();
        return total;
    }

}