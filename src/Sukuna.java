import java.util.Random;

public class Sukuna extends HealthChanges {

    Random rand=new Random();
    public int mossaSukuna;

    mossaSukuna=rand.nextInt(3)+1;

    public Sukuna(double health){
        this.health=health;
    }

    public void moveset(){
        switch(mossaSukuna){

            case 1:
                break;
        }
    }
}
