import java.util.Scanner;
import java.util.Random;

public class Main {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        Utilities.playMusicLoop("src/audio/AIZO.wav");

        int turno=0;

        Random random=new Random();

        int musiceasteregg=0;

        boolean sceltaValida = false;

        HealthChanges player = null;
        HealthChanges enemy = null;

        System.out.println("\n\nMade by Tony because he had nothing to do\n\n");
        Utilities.pausa(1500);

        System.out.println("Jujutsu Kaisen PvP game\n\n");
        Utilities.pausa(1000);

        System.out.println("Scegli il personaggio:");
        System.out.println("1) Gojo");
        System.out.println("2) Sukuna");
        System.out.println("3) Megumi (WIP)");
        System.out.println("4) Ryu");
        System.out.println("5) Naoya");

        String sceltaPlayer = sc.next();

        while(!sceltaValida) {
            switch (sceltaPlayer) {
                case "1":
                    player = new Gojo(2000);
                    Utilities.pausa(200);
                    sceltaValida = true;
                    break;
                case "2":
                    player = new Sukuna(2000);
                    Utilities.pausa(200);
                    sceltaValida = true;
                    break;
                case "3":
                    player = new Megumi(2000);
                    Utilities.pausa(200);
                    sceltaValida = true;
                    break;
                case "4":
                    player=new Ryu(2000);
                    Utilities.pausa(200);
                    sceltaValida=true;
                    break;
                case "5":
                    player=new Naoya(2000);
                    Utilities.pausa(200);
                    sceltaValida=true;
                    break;
                default:
                    System.out.println("Personaggio non esistente");
                    sceltaPlayer = sc.next();
            }
        }

        sceltaValida=false;

        System.out.println("Scegli il nemico:");
        System.out.println("1) Gojo");
        System.out.println("2) Sukuna");
        System.out.println("3) Megumi");
        System.out.println("4) Ryu");
        System.out.println("5) Naoya");
        System.out.println("6) Dummy");

        String sceltaEnemy = sc.next();

        while(!sceltaValida){
            switch (sceltaEnemy) {
                case "1":
                    enemy = new Gojo(2000);
                    sceltaValida = true;
                    Utilities.pausa(200);
                    break;
                case "2":
                    enemy = new Sukuna(2000);
                    sceltaValida = true;
                    Utilities.pausa(200);
                    break;
                case "3":
                    enemy = new Megumi(2000);
                    sceltaValida = true;
                    Utilities.pausa(200);
                    break;
                case "4":
                    enemy=new Ryu(2000);
                    sceltaValida=true;
                    Utilities.pausa(200);
                    break;
                case "5":
                    enemy=new Naoya(2000);
                    Utilities.pausa(200);
                    sceltaValida=true;
                    break;
                case "6":
                    enemy = new Dummy(10000);
                    sceltaValida = true;
                    Utilities.pausa(200);
                    break;
                default:
                    System.out.println("Personaggio non esistente");
                    sceltaEnemy = sc.next();
            }
        }

        if(player instanceof Ryu || enemy instanceof Ryu){
            musiceasteregg=random.nextInt(10)+1;
            if(musiceasteregg==8){
                Utilities.stopMusic();
                Utilities.playMusicLoop("src/audio/jane.wav");
                System.out.println("Ryu sembra molto più gasato del solito...");
                Utilities.pausa(1000);
            }
        }

        while(player.getHealth() > 0 && enemy.getHealth() > 0) {

            boolean domainAttivo = player.isDomainActive() || enemy.isDomainActive();

            boolean playerBloccato = enemy.bloccaTurnoNemico();
            boolean enemyBloccato = player.bloccaTurnoNemico();

            turno += 1;

            System.out.println("\nTurno " + turno);

            if (playerBloccato) {
                System.out.println("\nNon riesci muoverti...");
            } else {
                while(true){
                    System.out.println("\nTurno del giocatore - scegli la tua mossa:");
                    player.stampaMoveset();
                    System.out.println("0) Info mosse");

                    int sceltaMossa = sc.nextInt();

                    if(sceltaMossa == 0){

                        System.out.println("Quale mossa vuoi esaminare?");
                        Utilities.pausa(500);

                        int info = sc.nextInt();

                        player.infoMosse(info);
                        Utilities.pausa(500);

                        continue;
                    }

                    player.moveset(sceltaMossa, enemy);
                    Utilities.pausa(500);

                    break;
                }
            }

            if (enemy.getHealth() <= 0) break;

            System.out.println("\nTurno Nemico:");

            if (enemyBloccato) {
                System.out.println("Il nemico non riesce a muoversi...");
            }else{
                int sceltaBot = enemy.mossaCPU();
                enemy.moveset(sceltaBot, player);
                Utilities.pausa(500);
            }

            player.aggiornaCooldown();
            enemy.aggiornaCooldown();

            if(player.bleedTurns > 0){
                System.out.println("\nStai sanguinando...");
                Utilities.pausa(500);
                player.faiDanno(player.bleedDamage);
                player.bleedTurns--;
            }else{
                System.out.println("\n");
            }

            System.out.println("Vita Player: " + player.getHealth());
            player.stampaBarre();

            Utilities.pausa(1000);

            if(enemy.bleedTurns > 0){
                System.out.println("\nStai sanguinando...");
                Utilities.pausa(500);
                enemy.faiDanno(enemy.bleedDamage);
                enemy.bleedTurns--;
            }else{
                System.out.println("\n");
            }

            System.out.println("Vita Enemy: " + enemy.getHealth());
            Utilities.pausa(1000);
        }

        if(enemy.getHealth()<=0){
            System.out.println("\nVince il giocatore 1");
        }else{
            System.out.println("\nVince il giocatore 2");
        }

        System.out.println("\nFine combattimento!");
    }
}