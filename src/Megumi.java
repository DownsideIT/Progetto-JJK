import java.util.Random;

public class Megumi extends HealthChanges{

    Random random=new Random();

    boolean awakening=false;

    public int awakenbar=0;
    public int awakenbar2=0;
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

    public int getAwakenbar2() {
        return awakenbar2;
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
                    awakenbar+=20;

                    if(awakenbar>100){
                        awakenbar=100;
                    }
                    break;

                case 2:
                    System.out.println("Nue");
                    target.faiDanno(100);
                    awakenbar+=15;

                    if(awakenbar>100){
                        awakenbar=100;
                    }
                    break;

                case 3:
                    System.out.println("Toad");
                    target.faiDanno(75);
                    awakenbar+=20;

                    if(awakenbar>100){
                        awakenbar=100;
                    }
                    break;

                case 4:
                    if(awakenbar>=100) {
                        System.out.println("\nAwakening");
                        Utilities.pausa(1000);
                        System.out.println("\nInsanity");
                        Utilities.pausa(1000);
                        awakening = true;
                        cura(500);
                        awakenbar = 0;
                        Utilities.pausa(1000);
                    }else{
                        System.out.println("Awakening non pronto");
                    }
                    break;

                default:
                    System.out.println("Mossa non valida");
                    break;
            }
        }else{
            switch (scelta){
                case 1:
                    System.out.println("Rabbit flood");
                    target.faiDanno(100);
                    domainbar+=40;
                    if(domainbar>100){
                        domainbar=100;
                    }
                    awakenbar2+=20;
                    if(awakenbar2>100){
                        awakenbar2=100;
                    }
                    break;

                case 2:
                    System.out.println("Orochi");
                    target.faiDanno(125);
                    domainbar+=40;
                    if(domainbar>100){
                        domainbar=100;
                    }
                    awakenbar2+=15;
                    if(awakenbar2>100){
                        awakenbar2=100;
                    }
                    break;

                case 3:
                    if(cooldownMossa==0) {
                        System.out.println("Max Elephant");
                        target.faiDanno(175);
                        domainbar+=40;
                        if(domainbar>100){
                            domainbar=100;
                        }
                        awakenbar2+=20;
                        if(awakenbar2>100){
                            awakenbar2=100;
                        }
                        cooldownMossa=2;
                    }else{
                        System.out.println("In ricarica...");
                    }
                    break;

                case 4:
                    System.out.println("Divine Dogs Totality");
                    target.faiDanno(150);
                    domainbar+=40;
                    if(domainbar>100){
                        domainbar=100;
                    }
                    awakenbar2+=10;
                    if(awakenbar2>100){
                        awakenbar2=100;
                    }
                    break;

                default:
                    System.out.println("Mossa non valida");
                    break;
            }
        }
    }

    @Override
    public void stampaBarre(){
        if (!awakening) {
            System.out.println("Awakening: " + getAwakenbar() + "%");
        } else {
            System.out.println("Domain: " + getDomainbar() + "%");
            System.out.println("...: " + getAwakenbar2() + "%");
        }
    }

    @Override
    public void stampaMoveset(){
        if(!awakening){
            System.out.println("1) Rabbit swarm\n2) Nue\n3) Toad\n4) Awakening: Insanity");
        }else{
            System.out.println("2) Rabbit flood\n2) Orochi\n3) Max Elephant\n4) Divine Dogs Totality");
        }
    }
}
