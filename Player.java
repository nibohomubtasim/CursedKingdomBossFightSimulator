public class Player extends Character {    //inherits from Character class
    private int weaponLevel;     //stores player's current weapon level. Higher level weapon = more attack power
    private boolean defending;    //checks if player is defending

    public Player(String name) {    //constructor for Player class
        super(name, 100, 20);    //calls constructor of parent class
        this.weaponLevel = 1;    //player starts with level 1 weapon
        this.defending = false;    //players start NOT defending
    }

    public int aggressiveAttack(Boss boss) {    //method in which player uses a powerful attack. "Boss boss" indicates that the player attacks a Boss object
        defending = false;    //no defending when aggressive attack

        int damage = attackPower + (weaponLevel * 8);    //calculated damage output
        boss.takeDamage(damage);    //calls the boss's inherited takeDamage() method

        return damage;
    }

    public int defensiveAttack(Boss boss) {    //method in which player uses a less powerful attack
        defending = true;    //defending when defensive attack

        int damage = attackPower / 2 + (weaponLevel * 5);    //calculates lowered damage
        boss.takeDamage(damage);

        return damage;
    }

    public void heal(int amount) {    //creates healing method for player
        health += amount;    //updated health is health + amount

        if (health > maxHealth) {
            health = maxHealth;
        }
    }

    public void upgradeWeapon() {    //creates weapon upgrade method
        weaponLevel++;    //increases weapon level by 1
        attackPower += 5;    //increases attack power by 5 for each level of weapon upgrade
    }

    public void respawn() {    //creates respawn method. Used after player dies
        health = maxHealth;    //restores full health
        defending = false;
    }

    public boolean isDefending() {    //checks if player is defending
        return defending;
    }

    public void stopDefending() {    //turns off defensive mode
        defending = false;
    }

    public int getWeaponLevel() {    //getter for weapon level
        return weaponLevel;
    }

    public void setWeaponLevel(int weaponLevel) {    //setter for weapon level. Mainly used when loading save files
        this.weaponLevel = weaponLevel;
        this.attackPower = 20 + ((weaponLevel -1) * 5);    //recalculates attack power based on weapon level
    }
}
