import java.util.Random;

public class Megumi extends HealthChanges{

    Random random=new Random();

    boolean awakening=false;

    public int awakenbar=0;
    public int domainbar=0;

    public Megumi(double health){
        super(health);
    }

    public int getAwakenbar() {
        return awakenbar;
    }

    public int getDomainbar() {
        return domainbar;
    }

    @Override
    public int mossaCPU(){
        if(!awakening){
            return random.nextInt(4) + 1;
        } else {
            return random.nextInt(5) + 1;
        }
    }

    @Override
    public void moveset(int scelta, HealthChanges target){
        if(!awakening){
            switch(scelta){

                case 1:
                    System.out.println("Rabbit swarm");
                    target.faiDanno(50);
                    break;

                case 2:
                    System.out.println("Divine Dogs Totality");
                    target.faiDanno(125);
                    break;

                case 3:
                    System.out.println("Nue");
                    target.faiDanno(75);
                    break;

                default:
                    System.out.println("Scelta non valida");
                    break;
            }
        }
    }
}
