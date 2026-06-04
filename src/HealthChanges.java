public class HealthChanges {

    protected double health;

    protected double maxHealth;

    protected int awakenBar;

    protected int domainBar;

    protected int cooldownMossa;

    protected int bleedTurns = 0;

    protected double bleedDamage = 0;

    public HealthChanges(double health){
        this.health = health;
        this.maxHealth = health;
    }

    public void faiDanno(double danno){

        if(tryDodge()){
            System.out.println("Schivato!");
            return;
        }

        health -= danno;
        if(health < 0){
            health = 0;
        }
    }

    public void faiDannoDiretto(double danno){
        health -= danno;

        if(health < 0){
            health = 0;
        }
    }

    public int getAwakenbar() {
        return 0;
    }

    public int getDomainbar() {
        return 0;
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

    public void applicaBleeding(int turns, double damagePerTurn){
        bleedTurns = turns;
        bleedDamage = damagePerTurn;
    }

    public boolean tryDodge() {
        return false;
    }

    public boolean bloccaTurnoNemico() {
        return false;
    }

    public void stampaMoveset(){}

    public void moveset(int scelta, HealthChanges target){}

    public int mossaCPU(){
        return 1;
    }

    public void stampaBarre() {
        System.out.println("Awaken: " + getAwakenbar() + "%");
        System.out.println("Domain: " + getDomainbar() + "%");
    }
}