import java.util.Random;

public class Gojo extends HealthChanges {

    Random random = new Random();

    boolean awakening=false;

    public int awakenbar=0;

    boolean usedBlue=false;
    boolean usedRed=false;

    public Gojo(double health){
        super(health);
    }

    public int getAwakenbar() {
        return awakenbar;
    }

    public void moveset(int scelta, HealthChanges target){

        int blackflash;

        if(!awakening) {
            switch (scelta) {

                case 1:
                    System.out.println("Lapse Blue");
                    target.faiDanno(50);
                    awakenbar += 15;

                    if(awakenbar>100){
                        awakenbar=100;
                    }

                    break;

                case 2:
                    System.out.println("Reversal Red");
                    target.faiDanno(150);
                    awakenbar += 10;

                    if(awakenbar>100){
                        awakenbar=100;
                    }

                    break;

                case 3:
                    blackflash = random.nextInt(10) + 1;

                    if (blackflash == 8) {
                        System.out.println("Black Flash!");
                        target.faiDanno(300);
                        awakenbar += 30;

                        if(awakenbar>100){
                            awakenbar=100;
                        }

                    } else {
                        System.out.println("Punch Combo");
                        target.faiDanno(75);
                        awakenbar += 5;
                    }
                    break;

                case 4:
                    if (awakenbar == 100) {
                        System.out.println("Awakening");
                        Utilities.pausa(1000);
                        System.out.println("\nSix eyes");
                        awakening = true;
                        cura(500);
                        awakenbar = 0;
                        Utilities.pausa(1000);

                    } else {
                        System.out.println("Awakening non pronto");
                    }
                    break;

                default:
                    System.out.println("Mossa non valida");
            }
        }else{
            switch(scelta){

                case 1:
                    System.out.println("Max Lapse Blue");
                    target.faiDanno(150);
                    usedBlue=true;
                    break;

                case 2:
                    System.out.println("Max Reversal Red");
                    target.faiDanno(300);
                    usedRed=true;
                    break;

                case 3:
                    blackflash = random.nextInt(5) + 1;

                    if (blackflash == 3) {
                        System.out.println("Black Flash!");
                        target.faiDanno(500);
                    } else {
                        System.out.println("Furious Beatdown");
                        target.faiDanno(150);
                    }
                    break;

                case 4:
                    if(usedBlue && usedRed){
                        System.out.println("Hollow Purple");
                        target.faiDanno(800);
                        usedBlue=false;
                        usedRed=false;
                        Utilities.pausa(1000);
                    }else{
                        System.out.println("Non ancora pronto...");
                    }
                    break;

            }
        }
    }

    public void stampaMoveset(){
        if(!awakening) {
            System.out.println("1) Lapse blue\n2) Reversal red\n3) Punch Combo\n4) Awakening: Six Eyes");
        }else{
            System.out.println("1) Max Lapse Blue\n2) Max Reversal Red\n3) Furious Beatdown\n4) Hollow Purple");
        }
    }
}