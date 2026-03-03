public class HealthChanges {

    protected double health;

    protected double maxHealth;

    protected int awakenBar;

    protected int domainBar;

    protected int cooldownMossa;

    boolean awakening;

    public HealthChanges(double health){
        this.health = health;
        this.maxHealth = health;
    }

    public void faiDanno(double danno){
        health -= danno;
        if(health < 0){
            health = 0;
        }
    }

    public int getAwakenbar() {
        return awakenBar;
    }

    public int getDomainbar() {
        return domainBar;
    }

    public void cura(double valore){
        health += valore;

        if(health > maxHealth){
            health = maxHealth;
        }
    }

    public double getHealth(){
        return health;
    }

    public boolean isDomainActive(){
        return false;
    }

    public void aggiornaCooldown() {
        if (cooldownMossa>0) {
            cooldownMossa--;
        }
    }

    public void stampaMoveset(){}

    public void moveset(int scelta, HealthChanges target){}

    public int mossaCPU(){
        return 1;
    }

    public void stampaBarre() {
        if (!awakening) {
            System.out.println("Awakening: " + getAwakenbar() + "%");
        } else {
            System.out.println("Domain: " + getDomainbar() + "%");
        }
    }
}