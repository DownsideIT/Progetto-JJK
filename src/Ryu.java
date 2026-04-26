import java.util.Random;

public class Ryu extends HealthChanges{
    Random random=new Random();

    int awakenState=0;

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

    public int getOverheat(){
        return overheat;
    }

    public int getCharge(){
        return charge;
    }

    @Override
    public int mossaCPU(){
        if(awakenState==0){
            return random.nextInt(4) + 1;
        } else {
            return random.nextInt(5) + 1;
        }
    }

    @Override
    public void moveset(int scelta, HealthChanges target){
        if(awakenState==0){
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
                    System.out.println("\nPrego");
                    Utilities.pausa(1500);
                    System.out.println("\nSiediti pure al tavolo, Okkotsu");
                    Utilities.pausa(1500);
                    awakenState=1;
                    cura(500);
                    break;
                }

            }else if(awakenState==1){
                switch(scelta){

                    case 1:
                        if(charge<5) {
                            System.out.println("Charge");
                            charge++;
                        }else{
                            System.out.println("Carico al massimo");
                        }
                        break;

                    case 2:
                        System.out.println("\nDiamoci dentro");
                        Utilities.pausa(1500);
                        target.faiDanno(800+(charge*100));
                        awakenState=2;
                        cura(300);
                        break;
            }
        }else if(awakenState==2){
            switch(scelta){

                case 1:
                    Utilities.playSound("src/audio/Ryu_allout.wav");
                    System.out.println("\nChi si aspettava...");
                    Utilities.pausa(2450);
                    System.out.println("\nChe si potesse continuare anche dopo aver dato fondo a tutto!");
                    Utilities.pausa(3000);
                    target.faiDanno(350);
                    faiDanno(125);
                    break;

                case 2:
                    Utilities.playSound("src/audio/Ryu_dessert.wav");
                    System.out.println("\nAllora è questo...");
                    Utilities.pausa(1700);
                    System.out.println("\nIl dessert...");
                    Utilities.pausa(1500);
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

    @Override
    public void stampaMoveset(){
        if(awakenState==0){
            System.out.println("1) Granite blast\n2) Sudden exchange\n3) Granite barrage\n4) Circling blast\n5) Re-style\n6) Awakening: Every Last Drop");
        }else if(awakenState==1){
            System.out.println("1) Charge\n2) True cannon");
        }else if(awakenState==2){
            System.out.println("1) All-out beat down\n2) Satisfaction at last \n3) Furious exchange");
        }
    }

    @Override
    public void stampaBarre(){
        if(awakenState==0) {
            System.out.println("Surriscaldamento: "+getOverheat());
            System.out.println("Awaken: "+getAwakenbar());
        }else if(awakenState==1){
            System.out.println("Cariche: "+getCharge());
        }
    }
}
