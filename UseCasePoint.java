import java.util.InputMismatchException;
import java.util.Scanner;

public class UseCasePoint{

    Scanner scan = new Scanner(System.in);
    String [] actorType = new String[] {"Simple", "Average", "Complex"};
    int [] actorQty = new int[3];
    String [] useCaseType = new String[] {"Simple", "Average", "Complex"};
    int [] ucQty = new int[3];
    int [] technicalFactor = new int[13];
    int [] experienceFactor = new int[8];
    String [] reportType = new String[] {"Simple", "Average", "Complex"};
    int [] reportQty = new int[3];
    int [] reportWeight = new int[] {12, 20, 40};
    double totalTFactor, tcf, softwareSize, totalEFactor, expFactor, ucp, manHours;
    int pass = 0;
    int index;
    int count = 0;
    double riskCoeficient, reportManHrs = 0;

    double adjManHrs;

    public void calculateAP(){
        //Calculation of Actor Point
        System.out.println();
        System.out.println("1. Actors");
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("| Actor Type |               Description                | Weight Factor |");
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("|   Simple   |               Defined API                |       1       |");
        System.out.println("|   Average  | Interactive or Protocol driven interface |       2       |");
        System.out.println("|   Complex  |          Graphical User Interface        |       3       |");
        System.out.println("-------------------------------------------------------------------------");

        for(index = 0; index < actorType.length; index++){
            do{
                try{
                    System.out.print("Please input the quantity of " +actorType[index] +" Actor \t: ");
                    actorQty[index] = scan.nextInt();
                    pass = 1;
                }catch(InputMismatchException e){
                    System.out.println("Please input ONLY numbers!!!\n");
                    scan.next();
                    pass = 2;
                }
            }while(pass == 2);
        }
        System.out.println();

        ActorPoint actorPoint = new Actor(actorQty[0], actorQty[1], actorQty[2]);
        Actor actor = (Actor)actorPoint;
        System.out.println("Total Actor Point= " +actor.calculate()); 
        System.out.println(); 
    }

    public void calculateUC(){
        /*Calculation of Use Case*/
        System.out.println("2. Weighting Use Cases");
        System.out.println("---------------------------------------------------------------");
        System.out.println("| Use Case Type |         Description         | Weight Factor |");
        System.out.println("---------------------------------------------------------------");
        System.out.println("|    Simple     |   3 or fewer transactions   |        5      |");
        System.out.println("|    Average    |     4 to 7 transactions     |       10      |");
        System.out.println("|    Complex    | Greater than 7 transactions |       15      |");
        System.out.println("---------------------------------------------------------------");

        for(index = 0; index < useCaseType.length; index++){
            do{
                try{
                    System.out.print("Please input the quantity of " +useCaseType[index] +" Use Case \t: ");
                    ucQty[index] = scan.nextInt();
                    pass = 1;
                }catch(InputMismatchException e){
                    System.out.println("Please input ONLY numbers!!!\n");
                    scan.next();
                    pass = 2;
                }
            }while(pass == 2);
        }
        System.out.println();

        ActorPoint actorPoint = new Actor(actorQty[0], actorQty[1], actorQty[2]);
        Actor actor = (Actor)actorPoint;
        TotalUseCase totalUC = new UseCaseWeight(ucQty[0], ucQty[1], ucQty[2]);
        UseCaseWeight ucWeight = (UseCaseWeight)totalUC;
        UnadjustedUCP uucp = new UnadjustedUCP(actor, ucWeight);
        System.out.println("Total Use Cases= " +ucWeight.calculate());
        System.out.println("Unadjusted Use Case Point(UUCP)= " +uucp.calculate());
        System.out.println();
    }

    public void calculateTF(){
        /*Calculation of Technical Factors*/
        System.out.println("3. Weighting Technical Factors");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("| Techinal Factor |             Technical Description               | Weight Factor |");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("|        T1       |         Must have a distributed solution        |       2       |");
        System.out.println("|        T2       | Must respond to specific performance objectives |       1       |");
        System.out.println("|        T3       |       Must meet end-user efficiency desires     |       1       |");
        System.out.println("|        T4       |           Complex internal processing           |       1       |");
        System.out.println("|        T5       |              Code must be reusable              |       1       |");
        System.out.println("|        T6       |             Must be easy to install             |      .5       |");
        System.out.println("|        T7       |               Must be easy to use               |      .5       |");
        System.out.println("|        T8       |                Must be portable                 |       2       |");
        System.out.println("|        T9       |              Must be easy to change             |       1       |");
        System.out.println("|       T10       |           Must allow concurrent users           |       1       |");
        System.out.println("|       T11       |        Includes special security features       |       1       |");
        System.out.println("|       T12       |    Must provide direct access for 3rd parties   |       1       |");
        System.out.println("|       T13       |    Requires special user training  facilities   |       1       |");
        System.out.println("-------------------------------------------------------------------------------------");

        for(index = 0; index < technicalFactor.length; index++){
            do{
                try{
                    System.out.print("PLease input the rating of T" +(index+1) +" \t: ");
                    technicalFactor[index] = scan.nextInt();
                    pass = 1;
                }catch(InputMismatchException e){
                    System.out.println("Please input ONLY numbers!!!\n");
                    scan.next();
                    pass = 2;
                }
            }while(pass == 2);
        } 
        System.out.println();

        Technical technical = new TFactor(technicalFactor);
        TFactor tFactor = (TFactor)technical;
        ActorPoint actorPoint = new Actor(actorQty[0], actorQty[1], actorQty[2]);
        Actor actor = (Actor)actorPoint;
        TotalUseCase totalUC = new UseCaseWeight(ucQty[0], ucQty[1], ucQty[2]);
        UseCaseWeight ucWeight = (UseCaseWeight)totalUC;
        UnadjustedUCP uucp = new UnadjustedUCP(actor, ucWeight);
        totalTFactor = tFactor.calculate();
        tcf = (0.01*totalTFactor) + 0.6;
        softwareSize = uucp.calculate()*tcf;
        System.out.println("Total TFactor= " +totalTFactor);
        System.out.println("Technical Complexity Factor(TCF)= " +tcf);
        System.out.println("Size of Software(SzUC)= " +softwareSize);
        System.out.println();
    }

    public void calculateEF(){
        /*Calculation of Experience Factor*/
        System.out.println("4. Weighting Experience Factors");
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("| Experience Factor |         Factor Description         | Weight Factor |");
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("|         E1        | Familiar with FPT software process |       1       |");
        System.out.println("|         E2        |       Application experience       |      .5       |");
        System.out.println("|         E3        |      Paradigm experience (OO)      |       1       |");
        System.out.println("|         E4        |       Lead analyst capability      |      .5       |");
        System.out.println("|         E5        |             Motivation             |       0       |");
        System.out.println("|         E6        |         Stable requirements        |       2       |");
        System.out.println("|         E7        |          Part-time workers         |      -1       |");
        System.out.println("|         E8        | Difficulty of programming language |      -1       |");
        System.out.println("--------------------------------------------------------------------------");

        for(index = 0; index < experienceFactor.length; index++){
            do{
                try{
                    System.out.print("PLease input the rating of E" +(index+1) +" \t: ");
                    experienceFactor[index] = scan.nextInt();
                    pass = 1;
                }catch(InputMismatchException e){
                    System.out.println("Please input ONLY numbers!!!\n");
                    scan.next();
                    pass = 2;
                }
            }while(pass == 2);
        }
        System.out.println();

        Environmental environmental = new EFactor(experienceFactor);
        EFactor eFactor = (EFactor)environmental;
        totalEFactor = eFactor.calculate();
        expFactor = (-0.03*totalEFactor) + 1.4;
        ucp = softwareSize*expFactor;
        System.out.println("Total EFactor= " +totalEFactor);
        System.out.println("Experience Factor= " +expFactor);
        System.out.println("Use Case Point= " +calculateUCP());
        System.out.println();
    }

    public double calculateUCP(){
        ucp = softwareSize*expFactor;
        return ucp;
    }  

    public void calculateManHrs(){
        /*Calculation of Man-Hours*/
        manHours = 28*calculateUCP();
        System.out.println("5. Calculating Man-Hours from UCP");
        System.out.println("Total Man-Hours= " +manHours);
        System.out.println();
        calculateRisk(manHours);
    }

    public void calculateRisk(double manHours){
        System.out.println("6. Adjusting Man-Hours for Risk");
        do{
            try{
                System.out.print("Please enter Risk Coefficients: ");
                riskCoeficient = scan.nextInt();
                pass = 1;
            }catch(InputMismatchException e){
                System.out.println("Please enter ONLY numbers!!!");
                System.out.println();
                scan.next();
                pass = 2;
            }
        }while(pass == 2);
        riskCoeficient = riskCoeficient/100;
        adjManHrs = (1.0 + riskCoeficient) * manHours;
        System.out.printf("Adjusted Man-Hours= %.2f\n\n", adjManHrs);
        calculateTotalMH(adjManHrs);
    }

    public void calculateTotalMH(double adjManHrs){
        /*Calculation of Total Man-Hours*/
        System.out.println("7. Estimating for Reports");
        System.out.println("-----------------------------------");
        System.out.println("| Report Type | Average Man-Hours |");
        System.out.println("-----------------------------------");
        System.out.println("|   Simple    |         12        |");
        System.out.println("|   Average   |         20        |");
        System.out.println("|   Complex   |         40        |");
        System.out.println("-----------------------------------");

        for(index = 0; index < reportType.length; index++){
            do{
                try{
                    System.out.print("PLease input the quantity of " +reportType[index] +" \t: ");
                    reportQty[index] = scan.nextInt();
                    reportManHrs += (reportQty[index]*reportWeight[index]);
                    pass = 1;
                }catch(InputMismatchException e){
                    System.out.println("Please input ONLY numbers!!!\n");
                    scan.next();
                    pass = 2;
                }
            }while(pass == 2);
        }
        System.out.println();
        double totalManHrs = reportManHrs + adjManHrs;
        System.out.printf("Total Man-Hours= %.2f", totalManHrs);
    }
    
}