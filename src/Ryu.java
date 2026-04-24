import java.util.Random;

public class Ryu extends HealthChanges{
    Random random=new Random();

    boolean awakening=false;

    boolean criticOverheat =false;

    protected int awakenbar=0;
    protected int awakenbar2=0;
    protected int domainbar=0;
    protected int domainCounter=0;
    protected int overheat=0;
    protected int charge=0;

    public Ryu(double health){
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
            switch(scelta) {

                case 1:
                    if (overheat >= 100) {
                        System.out.println("Surriscaldato");
                        break;
                    }

                    System.out.println("Granite blast");
                    target.faiDanno(100);
                    overheat+=20;
                    awakenbar+=15;

                    if(awakenbar>=100){
                        awakenbar=100;
                    }

                    if (overheat>100) {
                        overheat=100;
                    }
                    break;

                case 2:
                    System.out.println("Sudden exchange");
                    target.faiDanno(75);
                    awakenbar+=15;

                    if(awakenbar>=100){
                        awakenbar=100;
                    }

                    break;

                case 3:
                    if (overheat>=100) {
                        System.out.println("Surriscaldato");
                        break;
                    }

                    System.out.println("Granite barrage");
                    target.faiDanno(250);
                    overheat+=50;
                    awakenbar+=30;

                    if(awakenbar>=100){
                        awakenbar=100;
                    }

                    if (overheat>100) {
                        overheat=100;
                    }
                    break;

                case 4:
                    if (overheat>=100) {
                        System.out.println("Surriscaldato");
                        break;
                    }

                    System.out.println("Circling blast");
                    target.faiDanno(125);
                    overheat+=25;
                    awakenbar+=10;

                    if(awakenbar>=100){
                        awakenbar=100;
                    }

                    if (overheat > 100) {
                        overheat = 100;
                    }
                    break;

                case 5:
                    System.out.println("Re-style");
                    overheat=0;
                    break;

                case 6:
                    System.out.println("Awakening");
                    Utilities.pausa(1000);
                    System.out.println("Prego");
                    Utilities.pausa(1000);
                    System.out.println("Siediti pure al tavolo, Okkotsu");
                    Utilities.pausa(1500);
                    awakening=true;
                    cura(500);
                    break;
                }

            }else if(awakening){
                switch(scelta){

                    case 1:
                        System.out.println("Charge");
                        charge++;
                        break;

                    case 2:
                        System.out.println("\nDiamoci dentro");
                        Utilities.pausa(1500);
                        target.faiDanno(800+(charge*100));
                        criticOverheat=true;
                        cura(300);
                        break;
            }
        }else if(awakening && criticOverheat){
            switch(scelta){

                case 1:
                    System.out.println("Chi si aspettava...");
                    Utilities.pausa(1000);
                    System.out.println("Che si potesse continuare anche dopo aver dato fondo a tutto!");
                    Utilities.pausa(3000);
                    target.faiDanno(350);
                    faiDanno(125);
                    break;

                case 2:
                    System.out.println("Allora è questo...");
                    Utilities.pausa(1000);
                    System.out.println("Il dessert...");
                    Utilities.pausa(1000);
                    target.faiDanno(250);
                    faiDanno(100);
                    break;

                case 3:
                    System.out.println("Furious exchange");
                    target.faiDanno(200);
                    faiDanno(75);
                    break;
            }
        }
    }
}
