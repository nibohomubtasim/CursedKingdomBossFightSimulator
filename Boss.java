public class Boss extends Character {    //inherits from Character class
    private boolean phaseTwo;    //checks if boss has entered phase two; false = normal boss form, true = stronger second phase form

    public Boss(String name) {    //constructor for Boss class
        super(name, 200, 18);    //calls constructor of parent class
        this.phaseTwo = false;    //boss starts in normal form
    }

    public int attackPlayer(Player player) {    //creates a method called attackPlayer, in which the boss attacks the player
        checkPhaseTwo();    //before attacking, boss checks if it is in phase two. Mainly checks boss's current health

        int damage;    //stores damage amount

        if (phaseTwo) {
            // Boss does random damage between 25 and 40 in second phase
            damage = 25 + (int)(Math.random() * 16);    //Math.random() generates a decimal between 0.0 and 0.999
        } else {
            // Boss does random damage between 12 and 25 in first phase
            damage = 12 + (int)(Math.random() * 14);
        }

        if (player.isDefending()) {    //checks whether player used defensive attack
            damage = damage / 2;    //cuts boss damage in half
        }

        player.takeDamage(damage);    //damages the player
        player.stopDefending();   //turns defending off for player after attack

        return damage;
    }

    public void checkPhaseTwo() {    //creates method that checks whether boss should transform
        if (health <= maxHealth / 2 && !phaseTwo) {    //checks whether boss health is below 50% AND second phase is not active yet. If true, boss enters second phase
            phaseTwo = true;
        }
    }

    public void recoverHealth(int amount) {    //creates method for healing the boss
        health += amount;

        if (health > maxHealth) {
            health = maxHealth;
        }
    }

    public boolean isPhaseTwo() {    //getter method for phase two
        return phaseTwo;
    }

    public void setPhaseTwo(boolean phaseTwo) {    //setter method for phase two
        this.phaseTwo = phaseTwo;
    }
}
