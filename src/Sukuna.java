import java.util.Random;

public class Sukuna extends HealthChanges {

    Random rand = new Random();

    int blackflash;
    int chant=0;

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
        if(awakenbar>100){
            awakenbar=100;
        }
        return awakenbar;
    }

    public void moveset(int scelta, HealthChanges target){
        eseguiMossa(scelta, target);
    }

    public void moveset(HealthChanges target){
        int scelta = rand.nextInt(4) + 1;
        eseguiMossa(scelta, target);
    }

    private void eseguiMossa(int scelta, HealthChanges target){

        if(!awakening) {
            switch (scelta) {
                case 1:
                    System.out.println("Dismantle\n");
                    target.faiDanno(100);
                    awakenbar += 15;
                    break;

                case 2:
                    System.out.println("Cleave\n");
                    target.faiDanno(125);
                    awakenbar += 10;
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
                    break;

                case 4:
                    if (awakenbar == 100) {
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
                    addChant();
                    break;

                case 2:
                    blackflash = rand.nextInt(5) + 1;

                    if (blackflash == 3) {
                        System.out.println("Black Flash!");
                        target.faiDanno(400);
                        addChant();
                    } else {
                        System.out.println("Heian Combination");
                        target.faiDanno(125);
                        addChant();
                    }
                    break;

                case 3:
                    if(!usedFurnace) {
                        System.out.println("Open Furnace");
                        target.faiDanno(600);
                        addChant();
                        usedFurnace=true;
                    }else{
                        System.out.println("Fiamme esaurite");
                    }
                    break;

                case 4:
                    if(chant==3){
                        System.out.println("World Cutting Slash");
                        target.faiDanno(850);
                        chant=0;
                        Utilities.pausa(1000);
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
            System.out.println("1) Cleave Rush\n2) Heian Combination\n3) Open Furnace\n4) World Cutting Slash");
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

    public void addChant() {
        if (chant < 3) {
            chant++;
            stampaChant();
            Utilities.pausa(800);
        }
    }
}