import java.util.Scanner;
import java.util.Random;

public class Main {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int turno=0;

        HealthChanges player = null;
        HealthChanges enemy = null;

        System.out.println("Scegli il personaggio:");
        System.out.println("1) Gojo");
        System.out.println("2) Sukuna");

        int sceltaPlayer = sc.nextInt();

        switch(sceltaPlayer){
            case 1:
                player = new Gojo(2000);
                Utilities.pausa(200);
                break;
            case 2:
                player = new Sukuna(2000);
                Utilities.pausa(200);
                break;
        }

        System.out.println("Scegli il nemico:");
        System.out.println("1) Gojo");
        System.out.println("2) Sukuna");
        System.out.println("3) Dummy");

        int sceltaEnemy = sc.nextInt();

        switch(sceltaEnemy){
            case 1:
                enemy = new Gojo(2000);
                Utilities.pausa(200);
                break;
            case 2:
                enemy = new Sukuna(2000);
                Utilities.pausa(200);
                break;
            case 3:
                enemy = new Dummy(10000);
                Utilities.pausa(200);
                break;
        }

        while(player.getHealth() > 0 && enemy.getHealth() > 0){

            turno+=1;

            System.out.println("\nTurno "+turno);
            System.out.println("\nTurno del giocatore - scegli la tua mossa:");

            if(player instanceof Gojo){
                ((Gojo)player).stampaMoveset();
            }else{
                ((Sukuna)player).stampaMoveset();
            }

            int sceltaMossa = sc.nextInt();

            if(player instanceof Gojo){
                ((Gojo)player).moveset(sceltaMossa, enemy);
                Utilities.pausa(200);
            }
            else if(player instanceof Sukuna){
                ((Sukuna)player).moveset(sceltaMossa, enemy);
                Utilities.pausa(200);
            }

            if(enemy.getHealth() <= 0) break;

            System.out.println("\nTurno Nemico:");


            if(player instanceof Gojo && ((Gojo)player).unlimitedVoidStun > 0){
                System.out.println("Il nemico non può muoversi|");
                ((Gojo)player).unlimitedVoid();
            }else {
                if (enemy instanceof Gojo) {
                    int mossaBot = rand.nextInt(3) + 1;
                    ((Gojo) enemy).moveset(mossaBot, player);
                } else if (enemy instanceof Sukuna) {
                    ((Sukuna) enemy).moveset(player);
                }
            }

            System.out.println("Vita Player: " + player.getHealth());

            if(player instanceof Gojo){
                if(!((Gojo) player).awakening) {
                    System.out.println("Awakening: " + ((Gojo) player).getAwakenbar());
                }else if(((Gojo) player).awakening){
                    System.out.println("Domain: "+((Gojo)player).getDomainbar());
                }
            }else{
                System.out.println("Awakening: "+((Sukuna)player).getAwakenbar());
            }
            Utilities.pausa(500);

            System.out.println("\nVita Enemy: " + enemy.getHealth());
        }

        if(enemy.getHealth()<=0){
            System.out.println("Vince il giocatore 1");
        }else{
            System.out.println("Vince il giocatore 2");
        }

        System.out.println("Fine combattimento!");
    }
}