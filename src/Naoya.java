import java.util.Random;

public class Naoya extends HealthChanges{

    Random random = new Random();

    boolean awakening=false;

    public int awakenbar=0;

    public int speed=0;

    double speedboost = speed * 0.5;

    public Naoya(double health){
        super(health);
    }

    public int getAwakenbar() {
        return awakenbar;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public int mossaCPU(){

        if(!awakening){
            if (awakenbar < 100) {
                return random.nextInt(4) + 1;
            } else {
                return 5;
            }
        }

        if (speed < 100) {
            return random.nextInt(4) + 1;
        }

        int chance = random.nextInt(100);

        if (chance < 60) {
            return 5;
        }

        return random.nextInt(4) + 1;
    }


    @Override
    public void moveset(int scelta, HealthChanges target){
        if(!awakening){
            switch(scelta){

                case 1:
                    System.out.println("Top speed beat up");
                    target.faiDanno(75);
                    awakenbar+=15;

                    if(awakenbar>=100){
                        awakenbar=100;
                    }
                    break;

                case 2:
                    System.out.println("Lighting fast punch");
                    target.faiDanno(100);
                    awakenbar+=10;

                    if(awakenbar>=100){
                        awakenbar=100;
                    }
                    break;

                case 3:
                    System.out.println("Shattering combo");
                    target.faiDanno(125);
                    awakenbar+=10;

                    if(awakenbar>=100){
                        awakenbar=100;
                    }
                    break;

                case 4:
                    System.out.println("Hidden dagger");
                    target.faiDanno(50);
                    target.applicaBleeding(3, 25);
                    awakenbar+=15;

                    if(awakenbar>=100){
                        awakenbar=100;
                    }
                    break;

                case 5:
                    if(awakenbar>=100){
                        System.out.println("\nAwakening");
                        Utilities.pausa(1000);
                        System.out.println("\nComandante dell'unità fulgore");
                        awakening=true;
                        cura(500);
                        awakenbar=0;
                        Utilities.pausa(1000);
                    }else {
                        System.out.println("Awakening non pronto");
                    }
                    break;

                default:
                    System.out.println("Mossa non valida");
            }
        }else{
            switch (scelta){
                case 1:
                    System.out.println("Shattering kick");
                    target.faiDanno(175+speedboost);
                    speed+=15;
                    if(speed>100){
                        speed=100;
                    }
                    break;

                case 2:
                    System.out.println("Frame perfect assault");
                    target.faiDanno(100+speedboost);
                    speed+=20;
                    if(speed>100){
                        speed=100;
                    }
                    break;

                case 3:
                    System.out.println("Ultrasonic shatter");
                    target.faiDanno(225+speedboost);
                    speed+=10;
                    if(speed>100){
                        speed=100;
                    }
                    break;

                case 4:
                    System.out.println("Limit breaking acceleration");
                    speed+=30;
                    if(speed>100){
                        speed=100;
                    }
                    break;

                case 5:
                    if(speed>=100) {
                        System.out.println("Final speed lunge");
                        target.faiDanno(600);
                        speed-=50;
                    }else{
                        System.out.println("Non sei abbastanza veloce");
                    }
                    break;

                default:
                    System.out.println("Mossa non valida");
            }
        }
    }

    @Override
    public void stampaMoveset() {
        if(!awakening){
            System.out.println("1) Top speed beat up\n2) Lighting fast punch\n3) Shatteing combo\n4) Hidden dagger\n5) Awakening: Comandante dell'unità fulgore");
        }else{
            System.out.println("1) Shattering kick\n2) Frame perfect assault\n3) Ultrasonic shatter\n4) Limit breaking acceleration\n5) Final speed lunge");
        }
    }

    @Override
    public void stampaBarre(){
        if(!awakening) {
            System.out.println("Awaken: "+getAwakenbar());
        }else{
            System.out.println("Velocità: "+getSpeed());
        }
    }

    @Override
    public boolean tryDodge() {
        int roll=random.nextInt(100);

        return roll<(speed*0.4);
    }
}
