import java.util.Random;

public class Gojo extends HealthChanges {

    Random random = new Random();

    boolean awakening=false;

    public int awakenbar=0;
    public int domainbar=0;
    public int unlimitedVoidStun=0;

    boolean usedBlue=false;
    boolean usedRed=false;

    public Gojo(double health){
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
            if(awakenbar<100) {
                return random.nextInt(3) + 1;
            }else{
                return 4;
            }
        } else {
            if(domainbar<100) {
                if(!usedRed || !usedBlue){
                    return random.nextInt(3) + 1;
                }else{
                    return 4;
                }
            }else{
                return 5;
            }
        }
    }

    @Override
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
                    } else {
                        System.out.println("Punch Combo");
                        target.faiDanno(75);
                        awakenbar += 5;
                    }
                    if(awakenbar>100){
                        awakenbar=100;
                    }
                    break;

                case 4:
                    if (awakenbar >= 100) {
                        System.out.println("\nAwakening");
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
                    if(unlimitedVoidStun>0){
                        target.faiDanno(150*1.5);
                    }else {
                        target.faiDanno(150);
                        domainbar+=40;
                    }
                    if (domainbar>100){
                        domainbar=100;
                    }
                    usedBlue=true;
                    break;

                case 2:
                    System.out.println("Max Reversal Red");
                    if(unlimitedVoidStun>0){
                        target.faiDanno(300*1.5);
                    }else {
                        target.faiDanno(300);
                        domainbar+=40;
                    }
                    if (domainbar>100){
                        domainbar=100;
                    }
                    usedRed=true;
                    break;

                case 3:
                    blackflash = random.nextInt(5) + 1;

                    if (blackflash == 3) {
                        System.out.println("Black Flash!");
                        if(unlimitedVoidStun>0){
                            target.faiDanno(500*1.5);
                        }else {
                            target.faiDanno(500);
                            domainbar+=100;
                        }
                    } else {
                        System.out.println("Furious Beatdown");
                        if(unlimitedVoidStun>0){
                            target.faiDanno(150*1.5);
                        }else {
                            target.faiDanno(150);
                            domainbar+=40;
                        }
                    }
                    if (domainbar>100){
                        domainbar=100;
                    }
                    break;

                case 4:
                    if(usedBlue && usedRed){
                        System.out.println("\nHollow Purple");
                        if(unlimitedVoidStun>0){
                            target.faiDanno(800*1.5);
                        }else {
                            target.faiDanno(800);
                            domainbar+=100;
                        }
                        if (domainbar>100){
                            domainbar=100;
                        }
                        usedBlue=false;
                        usedRed=false;
                        Utilities.pausa(1000);
                    }else{
                        System.out.println("Non ancora pronto...");
                    }
                    break;

                case 5:
                     if(domainbar >= 100 && !target.isDomainActive()){
                        System.out.println("\nDomain expansion");
                        Utilities.pausa(1000);
                        System.out.println("\nUnlimited Void");
                        Utilities.pausa(1000);
                        unlimitedVoidStun=3;
                        domainbar=0;
                    }else{
                        System.out.println("Non ancora");
                    }
                    break;

                default:
                    System.out.println("Mossa non valida");
                    break;

            }
        }
    }

    @Override
    public void stampaMoveset(){
        if(!awakening) {
            System.out.println("1) Lapse blue\n2) Reversal red\n3) Punch Combo\n4) Awakening: Six Eyes");
        }else{
            System.out.println("1) Max Lapse Blue\n2) Max Reversal Red\n3) Furious Beatdown\n4) Hollow Purple\n5) Domain Expansion: Unlimited Void");
        }
    }

    public void unlimitedVoid(){
        if (unlimitedVoidStun>0) {
            unlimitedVoidStun -= 1;
        }
    }

    @Override
    public boolean bloccaTurnoNemico() {
        if (unlimitedVoidStun > 0) {
            System.out.println("\nIl nemico non può muoversi");
            unlimitedVoid();
            return true;
        }
        return false;
    }

    @Override
    public boolean isDomainActive(){
        return unlimitedVoidStun > 0;
    }

    @Override
    public void stampaBarre(){
        if(!awakening) {
            System.out.println("Awaken: "+getAwakenbar());
        }else{
            System.out.println("Domain: "+getDomainbar());
        }
    }
}