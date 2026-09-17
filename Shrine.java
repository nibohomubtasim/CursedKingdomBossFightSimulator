public class Shrine {    //creates the Shrine class, which acts as a support/progression system for the Player
    private int flasks;    //stores current number of healing flasks
    private int maxFlasks;    //stores maximum number of allowed flasks at once
    private int souls;    //stores the player's souls. Souls acts like currency in this game, which can be used to upgrade weapon
    private int deaths;    //stores player's total deaths

    public Shrine() {    //constructor for shrine class
        this.maxFlasks = 3;    //sets maximum flasks to 3
        this.flasks = maxFlasks;    //player starts with max flasks
        this.souls = 0;    //player starts with 0 souls
        this.deaths = 0;    //player starts with 0 death count
    }

    public boolean useFlask(Player player) {    //creates a method which heals the player and reduces the flask count
        if (flasks > 0) {    //checks if there are any flasks available for use
            player.heal(35);    //heals player by set amount
            flasks--;    //each use reduces flask count by 1
            return true;    //returns true if flask use succeeds
        }

        return false;   //returns false when there are no flasks
    }

    public void rest(Player player, Boss boss) {    //creates rest method for both Player and Boss
        // Resting restores Player's full health, restores flask number, and slightly heals Boss as well
        player.respawn();    //acts similar as if a player respawned
        flasks = maxFlasks;    //resting restores all flasks

        boss.recoverHealth(20);    //restores boss health by 20
    }

    public boolean upgradePlayerWeapon(Player player) {    //method for upgrading player weapon
        int upgradeCost = 100;    //sets weapon upgrade cost to 100 souls

        if (souls >= upgradeCost) {    //checks if player has enough souls for weapon uprade
            souls -= upgradeCost;    //subtracts souls
            player.upgradeWeapon();    //calls upgradeWeapon() method of Player class
            return true;
        }

        return false;    //if not enough souls
    }

    public void playerDied(Player player) {    //creates method for handling player death
        deaths++;    //increases death count by 1
        souls = souls / 2;    //player loses half his souls after dying
        player.respawn();    //player respawns
        flasks = maxFlasks;    //all flasks restored
    }

    public void addSouls(int amount) {    //method for adding souls
        souls += amount;
    }

    public int getFlasks() {    //getter for flask amount
        return flasks;
    }

    public int getMaxFlasks() {    //getter for max flask amount
        return maxFlasks;
    }

    public int getSouls() {    //getter for souls
        return souls;
    }

    public int getDeaths() {    //getter for death count
        return deaths;
    }

    public void setFlasks(int flasks) {    //setter for flask amount
        this.flasks = flasks;
    }

    public void setSouls(int souls) {    //setter for souls amount
        this.souls = souls;
    }

    public void setDeaths(int deaths) {     //setter for death count
        this.deaths = deaths;
    }
}
