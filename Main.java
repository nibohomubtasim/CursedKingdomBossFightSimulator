/*
    Kingdom Boss Fight Simulator

    This project draws inspiration from the Soulslike genre of
    action role-playing games, particularly Dark Souls and
    Elden Ring by FromSoftware. Features such as boss battles,
    character progression, resource management, and the cycle
    of death and respawn influenced the overall simulator design.

    These ideas were adapted into a simplified text-based,
    turn-based simulation for educational purposes. The project
    demonstrates key Object-Oriented Programming concepts,
    including inheritance, encapsulation, class interaction,
    file handling, and user-controlled simulation cycles.

    References:
    FromSoftware (2011). Dark Souls.
    FromSoftware (2022). Elden Ring.
*/

public class Main {
    static java.util.Scanner input = new java.util.Scanner(System.in);    //creates Scanner object called input.Takes input from user

    static Player player = new Player("Ashen Knight");   //creates player object and gives player name
    static Boss boss = new Boss ("Hollow King");    //creates boss object and gives boss name
    static Shrine shrine = new Shrine ();    //creates shrine object

    static int cycle = 1;    //stores current cycle number
    static final String SAVE_FILE = "boss_save.txt";    //stores the save file name. Final means value cannot change

    public static void main(String[] args) {    //main method
        boolean running = true;    //creates loop control variable, menu keeps running

        System.out.println("=== Kingdom Boss Fight Simulator ===");    //prints game title

        while (running) {    //creates loop
            System.out.println("\n1. Start New Game");    //menu interface
            System.out.println("2. Continue Game");
            System.out.println("3. Exit");
            System.out.println("Choose an option: ");    //ask user for menu choice

            int choice = input.nextInt();    //reads int input from user

            if (choice == 1) {    //runs appropriate method as selected
                startNewGame();
                runSimulation();
            } else if (choice == 2) {
                loadGame();
                runSimulation();
            } else if (choice == 3) {
                System.out.println("Exiting game...");
                running = false;
            } else {    //checks if user enters an invalid prompt
                System.out.println("Invalid choice.");
            }
        }
    }

    public static void startNewGame() {    //creates method to reset game. Static means method belongs to class itself
        player = new Player("Ashen Knight");
        boss = new Boss("Hollow King");
        shrine = new Shrine();
        cycle = 1;

        System.out.println("\nNew game started.");
    }

    public static void runSimulation() {    //creates main simulation loop where the gameplay happens
        boolean playing = true;

        while (playing) {
            if (!boss.isAlive()) {    //checks if boss is dead
                System.out.println("\nVICTORY ACHIEVED!");
                System.out.println("You defeated " + boss.getName() + ".");
                shrine.addSouls(500);    //rewards 500 souls
                displayStats();    //prints final stats
                saveGame();    //saves final game state
                break;
            }

            displayStats();

            System.out.println("\nChoose your action:");     //displays Player choices
            System.out.println("1. Aggressive Attack");
            System.out.println("2. Defensive Attack");
            System.out.println("3. Heal with Flask");
            System.out.println("4. Upgrade Weapon");
            System.out.println("5. Rest at Shrine");
            System.out.println("6. Save and Exit");
            System.out.print("Enter choice: ");

            int action = input.nextInt();

            if (action ==1) {    //checks user choice
                int damage = player.aggressiveAttack(boss);    //player attacks boss. damage amount is returned
                System.out.println("\nYou used Aggressive Attack and dealt " + damage + " damage.");

                if (boss.isAlive()) {    //checks if boss is still alive
                    int bossDamage = boss.attackPlayer(player);    //boss attacks player back
                    System.out.println(boss.getName() + " attacked you for " + bossDamage + " damage.");
                }
            } else if (action ==2) {
                int damage = player.defensiveAttack(boss);
                System.out.println("\nYou used Defensive Attack and dealt " + damage + " damage.");

                if (boss.isAlive()) {
                    int bossDamage = boss.attackPlayer(player);
                    System.out.println(boss.getName() + " attacked you for " + bossDamage + " damage");
                }
            } else if (action ==3) {
                boolean healed = shrine.useFlask(player);

                if (healed) {
                    System.out.println("\nYou used a flask and restored health.");
                } else {
                    System.out.println("\nNo flasks left.");
                }

                if (boss.isAlive()) {
                    int bossDamage = boss.attackPlayer(player);
                    System.out.println(boss.getName() + " attacked you for " + bossDamage + " damage");
                }
            } else if (action ==4) {
                boolean upgraded = shrine.upgradePlayerWeapon(player);

                if (upgraded) {
                    System.out.println("\nWeapon upgraded successfully!");
                } else {
                    System.out.println("\nNot enough souls.");
                }
            } else if (action == 5) {
                shrine.rest(player, boss);
                System.out.println("\nYou rested at the shrine.");
                System.out.println("Health and flasks have been restored, but the boss has also recovered some health.");

            } else if (action == 6) {
                saveGame();
                System.out.println("\nGame saved. Exiting to menu...");
                playing = false;
                continue;

            } else {
                System.out.println("\nInvalid choice.");
            }

            boss.checkPhaseTwo();    //checks whether boss should enter phase two

            if (boss.isPhaseTwo() && boss.isAlive()) {   //checks conditions for entering phase two
                System.out.println("The boss is now in Phase Two. It's attacks have grown stronger!");
            }

            if (!player.isAlive()) {    //checks if player died
                System.out.println("\nYOU DIED.");
                shrine.playerDied(player);    //player respawns at shrine
                System.out.println("You respawned at the shrine and lost half your souls.");
            } else {    //if player survives
                shrine.addSouls(25);     //awarded 25 souls for each cycle survived
                System.out.println("You gained 25 souls for surviving the cycle.");
            }

            saveGame();    //automatically saves game every cycle
            cycle++;    //increase cycle number by 1
        }
    }

    public static void displayStats() {    //this method prints stats
        System.out.println("\n=== Cycle " + cycle + " ===");
        System.out.println("Player: " + player.getName());
        System.out.println("Player HP: " + player.getHealth() + "/" + player.getMaxHealth());
        System.out.println("Boss: " + boss.getName());
        System.out.println("Boss HP: " + boss.getHealth() + "/" + boss.getMaxHealth());
        System.out.println("Boss Phase Two: " + boss.isPhaseTwo());
        System.out.println("Flasks: " + shrine.getFlasks() + "/" + shrine.getMaxFlasks());
        System.out.println("Souls: " + shrine.getSouls());
        System.out.println("Weapon Level: " + player.getWeaponLevel());
        System.out.println("Deaths: " + shrine.getDeaths());
    }

    public static void saveGame() {
        try {
            java.io.FileWriter writer = new java.io.FileWriter(SAVE_FILE);

            writer.write("cycle=" + cycle + "\n");
            writer.write("playerHealth=" + player.getHealth() + "\n");
            writer.write("weaponLevel=" + player.getWeaponLevel() + "\n");
            writer.write("bossHealth=" + boss.getHealth() + "\n");
            writer.write("bossPhaseTwo=" + boss.isPhaseTwo() + "\n");
            writer.write("flasks=" + shrine.getFlasks() + "\n");
            writer.write("souls=" + shrine.getSouls() + "\n");
            writer.write("deaths=" + shrine.getDeaths() + "\n");

            writer.close();

        } catch (java.io.IOException e) {
            System.out.println("Error saving game.");
        }
    }

    public static void loadGame() {
        try {
            java.io.File file =
                    new java.io.File(SAVE_FILE);

            java.util.Scanner fileReader =
                    new java.util.Scanner(file);

            cycle =
                    Integer.parseInt(fileReader.nextLine().split("=")[1]);

            player = new Player("Ashen Knight");

            player.setHealth(
                    Integer.parseInt(fileReader.nextLine().split("=")[1])
            );

            player.setWeaponLevel(
                    Integer.parseInt(fileReader.nextLine().split("=")[1])
            );

            boss = new Boss("Hollow King");

            boss.setHealth(
                    Integer.parseInt(fileReader.nextLine().split("=")[1])
            );

            boss.setPhaseTwo(
                    Boolean.parseBoolean(fileReader.nextLine().split("=")[1])
            );

            shrine = new Shrine();

            shrine.setFlasks(
                    Integer.parseInt(fileReader.nextLine().split("=")[1])
            );

            shrine.setSouls(
                    Integer.parseInt(fileReader.nextLine().split("=")[1])
            );

            shrine.setDeaths(
                    Integer.parseInt(fileReader.nextLine().split("=")[1])
            );

            fileReader.close();

            System.out.println("\nSave loaded successfully.");

        } catch (Exception e) {
            System.out.println("\nNo valid save file found.");
            startNewGame();
        }
    }
}
