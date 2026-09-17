public class Character {     //initializing the character class, from which the player and boss class will inherit attributes
    protected String name;    //Protected meaning subclasses can access, but no access for unrelated class
    protected int health;    //stores current health of character
    protected int maxHealth;    //stores max health of character
    protected int attackPower;    //stores attack damage of character

    public Character(String name, int maxHealth, int attackPower) {    //Constructor class for Character
        this.name = name;    //keyword "this" refers to the current variable being dealt with
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.attackPower = attackPower;
    }

    public void takeDamage(int damage) {    //method that reduces the health of the character
        health -= damage;    //new health is health - damage

        if (health < 0) {   //checks if health became negative
            health = 0;    //health cannot be negative, so sets health to 0
        }
    }

    public boolean isAlive() {    //returns true if health is more than 0
        return health > 0;
    }

    public String getName() {    //getter method which allows classes to READ private/protected data safely
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setHealth(int health) {    //setter method which allows the program to change health safely
        this.health = health;

        if (this.health > maxHealth) {    //checks if character health is higher than max health
            this.health = maxHealth;    //health cannot be more than max health
        }

        if (this.health < 0) {    //checks if character health is lower than 0
            this.health = 0;    //health cannot be less than 0
        }
    }
}
