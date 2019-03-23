public class Actor extends ActorPoint implements EffortEstimation{

    protected double total = 0;

    public Actor(int actorType1, int actorType2, int actorType3){
        super(actorType1, actorType2, actorType3);
    }

    public double calculate(){
        total = (actorType1 * 1) + (actorType2 * 2) + (actorType3 * 3);
        return total; 
    }

}