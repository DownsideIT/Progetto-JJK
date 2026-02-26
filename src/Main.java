import java.util.Random;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {

        int scelta=0;
        int blackflash;

        Scanner sceltaMossa=new Scanner(System.in);

        Random random=new Random();

        Gojo gojo=new Gojo(1000);
        Dummy dummy=new Dummy(10000);

        while(gojo.health>0){

            scelta=sceltaMossa.nextInt();
            switch(scelta) {

                case 1:
                    System.out.println("Lapse blue");
                    gojo.faiDanno(50);
                    break;

                case 2:
                    System.out.println("Reversal red");
                    gojo.faiDanno(150);
                    break;

                case 3:
                    blackflash=random.nextInt(10) + 1;
                    if (blackflash == 8) {
                        System.out.println("Black Flash");
                        gojo.faiDanno(300);
                    } else {
                        System.out.println("Punch combo");
                        gojo.faiDanno(75);
                    }
                    break;

                default:
                    break;
            }
            System.out.println("Vita Gojo:"+gojo.health);
        }
    }
}
