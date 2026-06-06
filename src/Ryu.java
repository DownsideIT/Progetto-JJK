import java.util.Random;

public class Ryu extends HealthChanges{
    Random random=new Random();

    int awakenState=0;

    protected int awakenbar=0;
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
                        target.faiDannoDiretto(800+(charge*100));
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
            System.out.println("1) All-out Beatdown\n2) Satisfaction at last \n3) Furious exchange");
        }
    }

    @Override
    public void infoMosse(int sceltainfo) {
        if (awakenState==0) {
            switch (sceltainfo) {
                case 1:
                    System.out.println("Granite blast\n\nDanno: 100 | Awakening: +15 | Surriscaldamento: +20\n\nCondizioni: Surriscaldamento sotto il 100\n");
                    break;

                case 2:
                    System.out.println("Sudden exchange\n\nDanno: 75 | Awakening: +15\n");
                    break;

                case 3:
                    System.out.println("Granite barrage\n\nDanno: 250 | Awakening: +30 | Surriscaldamento: +50\n\nCondizioni: Surriscaldamento sotto il 100\n");
                    break;

                case 4:
                    System.out.println("Circling blast\n\nDanno: 125 | Awakening: +10 | Surriscaldamento: +25\n\nCondizioni: Surriscaldamento sotto il 100\n");
                    break;

                case 5:
                    System.out.println("Re-style\n\nEffetto: Spendi il turno per resettare la barra del surriscaldamento a 0, indipendentemente dal valore del surriscaldamento\n");
                    break;

                case 6:
                    System.out.println("Awakening: Every Last Drop\n\nCura: 500\n\nEntra in uno stato di carica di un colpo che utilizza fino all'ultima goccia della tua energia malefica\n\nCondizioni: Awaken bar al 100%\n");
                    break;
            }
        } else if (awakenState==1){
            switch (sceltainfo) {
                case 1:
                    System.out.println("Charge\n\nCariche: +1\n\nEffetto: Aumenta di 100 il danno di True Cannon per carica, arriva ad un massimo di 5 cariche\n");
                    break;

                case 2:
                    System.out.println("True Cannon\n\nDanno: Da 800 a 1300 | Cura: 300\n\nInschivabile\n\nRilascia il tuo colpo più potente, utilizzando tutta la tua energia malefica.\nDopo l'utilizzo, passi automaticamente ad uno stato di combattimento corpo a corpo\n");
                    break;
            }
        }else if(awakenState==2){
            switch (sceltainfo){
                case 1:
                    System.out.println("All-out Beatdown\n\nDanno: 350 | Contraccolpo: 125\n");
                    break;

                case 2:
                    System.out.println("Satisfaction at last\n\nDanno: 250 | Contraccolpo: 100\n");
                    break;

                case 3:
                    System.out.println("Furious exchange\n\nDanno: 200 | Contraccolpo: 75\n");
                    break;
            }
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
