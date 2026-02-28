import java.util.Random;

public class Sukuna extends HealthChanges {

    Random rand = new Random();

    int blackflash;
    int chant=0;
    int domainCounter=0;

    public int domainbar=0;

    boolean awakening=false;
    boolean usedFurnace=false;

    public Sukuna(double health){
        super(health);
    }

    public int mossaSukuna(){
        return rand.nextInt(4) + 1;
    }

    public int awakenbar;

    public int getAwakenbar() {
        return awakenbar;
    }

    public int getDomainbar() {
        return domainbar;
    }

    public void moveset(int scelta, HealthChanges target){
        eseguiMossa(scelta, target);
    }

    public void moveset(HealthChanges target){
        int scelta = rand.nextInt(5) + 1;
        eseguiMossa(scelta, target);
    }

    private void eseguiMossa(int scelta, HealthChanges target){

        if(!awakening) {
            switch (scelta) {
                case 1:
                    System.out.println("Dismantle\n");
                    target.faiDanno(100);
                    awakenbar += 15;
                    if(awakenbar>100){
                        awakenbar=100;
                    }
                    break;

                case 2:
                    System.out.println("Cleave\n");
                    target.faiDanno(125);
                    awakenbar += 10;
                    if(awakenbar>100){
                        awakenbar=100;
                    }
                    break;

                case 3:
                    blackflash = rand.nextInt(10) + 1;

                    if (blackflash == 8) {
                        System.out.println("Black Flash!");
                        target.faiDanno(200);
                        awakenbar += 35;
                    } else {
                        System.out.println("Cursed Combo\n");
                        target.faiDanno(50);
                        awakenbar += 5;
                    }
                    if(awakenbar>100){
                        awakenbar=100;
                    }
                    break;

                case 4:
                    if (awakenbar >= 100) {
                        System.out.println("Awakening");
                        Utilities.pausa(1000);
                        System.out.println("\nKing of Curses");
                        awakening = true;
                        cura(500);
                        awakenbar = 0;
                        Utilities.pausa(1000);

                    } else {
                        System.out.println("Awakening non pronto");
                    }
                    break;
            }
        }else{
            switch (scelta){
                case 1:
                    System.out.println("Cleave Rush");
                    target.faiDanno(200);
                    malevolentShrine(target);
                    addChant();
                    if (domainCounter == 0) {
                        domainbar+=40;
                    }
                    if(domainbar>100){
                        domainbar=100;
                    }
                    break;

                case 2:
                    blackflash = rand.nextInt(5) + 1;

                    if (blackflash == 3) {
                        System.out.println("Black Flash!");
                        target.faiDanno(400);
                        if (domainCounter == 0) {
                            domainbar+=100;
                        }
                        addChant();
                    } else {
                        System.out.println("Heian Combination");
                        target.faiDanno(125);
                        if (domainCounter == 0) {
                            domainbar+=40;
                        }
                        addChant();
                    }
                    malevolentShrine(target);
                    if(domainbar>100){
                        domainbar=100;
                    }
                    break;

                case 3:
                    if(!usedFurnace) {
                        System.out.println("Open Furnace");
                        target.faiDanno(600);
                        if (domainCounter == 0) {
                            domainbar+=70;
                        }
                        addChant();
                        usedFurnace=true;
                    }else{
                        System.out.println("Fiamme esaurite");
                    }
                    malevolentShrine(target);
                    if(domainbar>100){
                        domainbar=100;
                    }
                    break;

                case 4:
                    if(chant==3){
                        System.out.println("World Cutting Slash");
                        target.faiDanno(850);
                        chant=0;
                        if (domainCounter == 0) {
                            domainbar+=100;
                        }
                        Utilities.pausa(1000);
                    }else{
                        System.out.println("Non ancora");
                    }
                    malevolentShrine(target);
                    if(domainbar>100){
                        domainbar=100;
                    }
                    break;

                case 5:
                    if(domainbar >= 100 && !target.isDomainActive()) {
                        System.out.println("Domain expansion");
                        Utilities.pausa(1000);
                        System.out.println("\nMalevolent Shrine");
                        Utilities.pausa(1000);
                        domainCounter+=3;
                        domainbar=0;
                    }else{
                        System.out.println("Non ancora");
                    }
                    break;
            }
        }
    }
    public void stampaMoveset(){
        if(!awakening) {
            System.out.println("1) Dismantle\n2) Cleave\n3) Cursed Combo\n4) King of Curses");
        }else{
            System.out.println("1) Cleave Rush\n2) Heian Combination\n3) Open Furnace\n4) World Cutting Slash\n5) Domain Expansion: Malevolent Shrine");
        }
    }

    public void stampaChant(){
        if(chant==1){
            System.out.println("Scales of the Dragon");
        } else if (chant==2) {
            System.out.println("Recoil");
        } else if (chant==3) {
            System.out.println("Twin Meteors");
        }
    }

    private void malevolentShrine(HealthChanges target){
        if(domainCounter > 0){
            System.out.println("\nMalevolent Shrine squarcia lo spazio");
            Utilities.pausa(1000);
            for(int i=0;i<10;i++){
                target.faiDanno(17.5);
            }
            domainCounter--;
        }
    }

    public void addChant() {
        if (chant < 3) {
            chant++;
            stampaChant();
            Utilities.pausa(800);
        }
    }

    @Override
    public boolean isDomainActive(){
        return domainCounter > 0;
    }
}