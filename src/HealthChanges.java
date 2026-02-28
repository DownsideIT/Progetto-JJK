public class HealthChanges {

    protected double health;

    protected double maxHealth;

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
}