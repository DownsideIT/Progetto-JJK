import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.*;
import java.io.File;

public class Utilities {

    private static Clip musica;

    public static void playMusicLoop(String path) {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(path));
            musica = AudioSystem.getClip();
            musica.open(audioIn);

            FloatControl gainControl = (FloatControl) musica.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-20.0f);

            musica.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.out.println("Errore musica: " + e.getMessage());
        }
    }

    public static void stopMusic() {
        if (musica != null) {
            musica.stop();
        }
    }

    public static void pausa(int ms){
        try{
            Thread.sleep(ms);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    public static void playSound(String path) {
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(path));
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (Exception e) {
            System.out.println("Errore suono: " + e.getMessage());
        }
    }
}
