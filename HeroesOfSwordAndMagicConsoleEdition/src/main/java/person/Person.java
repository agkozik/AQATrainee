package person;

import engine.GameEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import races.ERaceType;

import java.util.ArrayList;

public abstract class Person {
    private static final Logger logger = LoggerFactory.getLogger(Person.class);

    private double health = 100;
    private boolean readyToFight = true;
    private double damage;
    private double upgraded = 1;
    private String name;
    private ERaceType race;
    private String side;
    private String attackFirstTypeMessage;
    private String attackSecondTypeMessage;

    /**
     * Атака Привилегированной группы
     * @param gameEngine
     * @param privilegedGroup
     */
    void attackPrivilegedGroup(GameEngine gameEngine,
                               ArrayList<Person> privilegedGroup, String attackMessage) {
        //Атака Привилегированной группы
        int damagedHeroIndex;
        damagedHeroIndex = gameEngine.figuresRangeRandomGenerator(0, privilegedGroup);
        if (this.getUpgraded() == gameEngine.up) {
            logger.debug("+Привилегированная группа. Наносимый урон увеличен на 50%+");
        } else if (this.getUpgraded() == 0.5) {
            logger.debug("-Наносимый урон уменьшен на 50%-");
        }
        logger.debug(this.getName() + " атакует " + privilegedGroup.get(damagedHeroIndex).getName()
                + ((char) 27 + "[35m" + attackMessage + (char) 27 + "[0m")
                + " нанося ущерб " + this.getDamage());
        privilegedGroup.get(damagedHeroIndex)
                .setHealth((privilegedGroup.get(damagedHeroIndex).getHealth()) - this.getDamage());
        //Проверка здоровья после атаки (удаление из списка если здоровье меньше или равно нулю)
        if (privilegedGroup.get(damagedHeroIndex).getHealth() <= 0) {
            logger.debug
                    ("______________________________________________________________________________________________________________________________________________");
            logger.debug(privilegedGroup.get(damagedHeroIndex).getName()
                    + ((char) 27 + "[34m" + " выносят на щите с поля боя. Здоровье = " + (char) 27 + "[0m")
                    + privilegedGroup.get(damagedHeroIndex).getHealth());
            privilegedGroup.remove(damagedHeroIndex);
        } else {
            logger.debug("Здоровье " + privilegedGroup.get(damagedHeroIndex).getName()
                    + " уменьшилось и равно " + privilegedGroup.get(damagedHeroIndex).getHealth());
            logger.debug(privilegedGroup.get(damagedHeroIndex).toString());
        }
        logger.debug
                ("______________________________________________________________________________________________________________________________________________");
    }

    /**
     * Атака Обычной группы
     * @param gameEngine
     * @param attackRegularGroup
     */
    void attackRegularGroup(GameEngine gameEngine, ArrayList<Person> attackRegularGroup, String attackMessage) {
        int damagedHeroIndex;
        damagedHeroIndex = gameEngine.figuresRangeRandomGenerator(0, attackRegularGroup);
        if (this.getUpgraded() == gameEngine.up) {
            logger.debug("+Привилегированная группа. Наносимый урон увеличен на 50%+");
        } else if (this.getUpgraded() == 0.5) {
            logger.debug("-Наносимый урон уменьшен на 50%-");
        }
        logger.debug(this.getName() + " атакует " + attackRegularGroup.get(damagedHeroIndex).getName()
                + ((char) 27 + "[31m" + attackMessage + (char) 27 + "[0m")
                + " нанося ущерб " + this.getDamage());
        attackRegularGroup.get(damagedHeroIndex)
                .setHealth((attackRegularGroup.get(damagedHeroIndex).getHealth()) - this.getDamage());
        //Проверка здоровья после атаки (удаление из списка если здоровье меньше или равно нулю)
        if (attackRegularGroup.get(damagedHeroIndex).getHealth() <= 0) {
            logger.debug
                    ("______________________________________________________________________________________________________________________________________________");
            logger.debug(attackRegularGroup.get(damagedHeroIndex).getName()
                    + ((char) 27 + "[34m" + " выносят на щите с поля боя. Здоровье = " + (char) 27 + "[0m")
                    + attackRegularGroup.get(damagedHeroIndex).getHealth());
            attackRegularGroup.remove(damagedHeroIndex);
        } else {
            logger.debug("Здоровье " + attackRegularGroup.get(damagedHeroIndex).getName()
                    + " уменьшилось и равно " + attackRegularGroup.get(damagedHeroIndex).getHealth());
            logger.debug(attackRegularGroup.get(damagedHeroIndex).toString());
        }
        logger.debug
                ("______________________________________________________________________________________________________________________________________________");
    }

    /**
     * Нанесение удара стороне GOOD или Evil
     */
    public void makeDamage(GameEngine gameEngine,
                           ArrayList<Person> attackRegularGroup, ArrayList<Person> attackPrivilegedGroup, String attackMessage) {
        //Если существует 2 подгруппы: Привилегированные и Обычные
        if (attackPrivilegedGroup.size() != 0 && attackRegularGroup.size() != 0) {
            //случайный выбор атакуемой подгруппы
            switch (gameEngine.figuresRandomGenerator()) {
                //Атака Привилегированной группы
                case 0:
                    attackPrivilegedGroup(gameEngine, attackPrivilegedGroup, attackMessage);
                    break;
                //Атака Обычной группы
                case 1:
                    attackRegularGroup(gameEngine, attackRegularGroup, attackMessage);
                    break;
            }
        }
        //Если существует только Привилегированная подгруппа
        else if (attackPrivilegedGroup.size() != 0 && attackRegularGroup.size() == 0) {
            //Атака Привилегированной группы
            attackPrivilegedGroup(gameEngine, attackPrivilegedGroup, attackMessage);
        }
        //Если существует только Обычная группа
        else if (attackPrivilegedGroup.size() == 0 && attackRegularGroup.size() != 0) {
            //Атака Обычной группы
            attackRegularGroup(gameEngine, attackRegularGroup, attackMessage);
        }
    }

    /**
     * Магия Улучшение юнита Привилегированной или Обычной группы
     */
    void upgradeUnit(GameEngine gameEngine, ArrayList<Person> ownPrivilegedGroup, String magicMessage) {
        int upgradingHeroIndex;
        upgradingHeroIndex = gameEngine.figuresRangeRandomGenerator(0, ownPrivilegedGroup);
        if (ownPrivilegedGroup.get(upgradingHeroIndex).getUpgraded() == 1) {
            ownPrivilegedGroup.get(upgradingHeroIndex).setUpgraded(gameEngine.up);
            logger.debug(this.getName()
                    + ((char) 27 + "[36m"
                    + " достает волшебную палочку ---(~ и тычет в "
                    +(char) 27 + "[0m")
                    + ownPrivilegedGroup.get(upgradingHeroIndex).getName()
                    + ((char) 27 + "[36m"
                    + " увеличивая его силу на 50%, теперь наносимый им ущерб умножается на "
                    + ownPrivilegedGroup.get(upgradingHeroIndex).getUpgraded()
                    + (char) 27 + "[0m"));
        } else if (ownPrivilegedGroup.get(upgradingHeroIndex).getUpgraded() == 0.5) {
            ownPrivilegedGroup.get(upgradingHeroIndex).setUpgraded(1);
            logger.debug(this.getName()
                    + ((char) 27 + "[36m"
                    + " достает волшебную палочку ---(~ и тычет в "
                    +(char) 27 + "[0m")
                    + ownPrivilegedGroup.get(upgradingHeroIndex).getName()
                    + ((char) 27 + "[36m"
                    + " увеличивая его силу на 50%, теперь наносимый им ущерб умножается на "
                    + ownPrivilegedGroup.get(upgradingHeroIndex).getUpgraded()
                    + (char) 27 + "[0m"));
        } else if (ownPrivilegedGroup.get(upgradingHeroIndex).getUpgraded() == gameEngine.up) {
            logger.debug(this.getName()
                    + ((char) 27 + "[36m"
                    + " Хлопает по плечу "
                    +(char) 27 + "[0m")
                    + ownPrivilegedGroup.get(upgradingHeroIndex).getName()
                    + ((char) 27 + "[36m"
                    + " увеличивая его самооценку. Т.к его сила атаки максимальна и наносимый им ущерб уже умножается на "
                    + ownPrivilegedGroup.get(upgradingHeroIndex).getUpgraded()
                    + (char) 27 + "[0m"));
        }
        logger.debug
                ("______________________________________________________________________________________________________________________________________________");
    }

    /**
     * Использование магии для UpGrade
     */
    public void useUpgradeMagic(GameEngine gameEngine,
                                ArrayList<Person> ownRegularGroup, ArrayList<Person> ownPrivilegedGroup, String magicMessage) {
        int upgradingHeroIndex;
        //Если существует 2 подгруппы: Привилегированные и Обычные
        if (ownPrivilegedGroup.size() != 0 && ownRegularGroup.size() != 0) {
            //случайный выбор подгруппы для улучшения
            switch (gameEngine.figuresRandomGenerator()) {
                //Улучшение юнита Привилегированной группы
                case 0:
                    upgradeUnit(gameEngine, ownPrivilegedGroup, magicMessage);
                    break;
                //Улучшение юнита Обычной группы
                case 1:
                    upgradeUnit(gameEngine, ownRegularGroup, magicMessage);
                    break;
            }
        }
        //Если существует только Привилегированная подгруппа
        else if (ownPrivilegedGroup.size() != 0 && ownRegularGroup.size() == 0) {
            //Улучшение юнита Привилегированной группы
            upgradeUnit(gameEngine, ownPrivilegedGroup, magicMessage);
        }
        //Если существует только Обычная группа
        else if (ownPrivilegedGroup.size() == 0 && ownRegularGroup.size() != 0) {
            //Улучшение юнита Обычной группы
            upgradeUnit(gameEngine, ownRegularGroup, magicMessage);
        }
    }

    /**
     * Ухудшение атаки врага из любой группы
     * @param gameEngine
     * @param enemyGroup
     * @param downgradeMessage
     */
    void downgradeUnit(GameEngine gameEngine, ArrayList<Person> enemyGroup, String downgradeMessage) {
        int downgradingHeroIndex;
        downgradingHeroIndex = gameEngine.figuresRangeRandomGenerator(0, enemyGroup);
        if (enemyGroup.get(downgradingHeroIndex).getUpgraded() == 1) {
            enemyGroup.get(downgradingHeroIndex).setUpgraded(0.5);
            logger.debug(this.getName()
                    + ((char) 27 + "[35m"
                    + " наложил проклятье на "
                    + enemyGroup.get(downgradingHeroIndex).getName()
                    + " которое уменьшает его силу на 50%, теперь наносимый им ущерб умножается на "
                    + enemyGroup.get(downgradingHeroIndex).getUpgraded()
                    + (char) 27 + "[0m"));
        } else if (enemyGroup.get(downgradingHeroIndex).getUpgraded() == 1.5) {
            enemyGroup.get(downgradingHeroIndex).setUpgraded(1);
            logger.debug(this.getName()
                    + ((char) 27 + "[35m"
                    + " наложил проклятье на "
                    + enemyGroup.get(downgradingHeroIndex).getName()
                    + " которое уменьшает его силу на 50%, теперь наносимый им ущерб умножается на "
                    + enemyGroup.get(downgradingHeroIndex).getUpgraded()
                    + (char) 27 + "[0m"));
        } else if (enemyGroup.get(downgradingHeroIndex).getUpgraded() == 0.5) {
            logger.debug(enemyGroup.get(downgradingHeroIndex).getName()
                    + ((char) 27 + "[35m"
                    + " смеется над "
                    + this.getName()
                    + " нельзя просто так ухудшить то, что и так не работает "
                    + enemyGroup.get(downgradingHeroIndex).getUpgraded()
                    + (char) 27 + "[0m"));
        }
        logger.debug
                ("______________________________________________________________________________________________________________________________________________");
    }

    /**
     * Использование магии для DownGrade против врага
     */
    public void useDowngradeMagic(GameEngine gameEngine,
                                  ArrayList<Person> enemyRegularGroup, ArrayList<Person> enemyPrivilegedGroup, String downgradeMessage) {
        //Если существует 2 подгруппы: Привилегированные и Обычные
        if (enemyPrivilegedGroup.size() != 0 && enemyRegularGroup.size() != 0) {
            //случайный выбор подгруппы для улучшения
            switch (gameEngine.figuresRandomGenerator()) {
                //ухудшение юнита Привилегированной группы
                case 0:
                    downgradeUnit(gameEngine, enemyPrivilegedGroup, downgradeMessage);
                    break;
                //ухудшение юнита Обычной группы
                case 1:
                    downgradeUnit(gameEngine, enemyRegularGroup, downgradeMessage);
                    break;
            }
        }
        //Если существует только Привилегированная подгруппа
        else if (enemyPrivilegedGroup.size() != 0 && enemyRegularGroup.size() == 0) {
            //ухудшение юнита Привилегированной группы
            downgradeUnit(gameEngine, enemyPrivilegedGroup, downgradeMessage);
        }
        //Если существует только Обычная группа
        else if (enemyPrivilegedGroup.size() == 0 && enemyRegularGroup.size() != 0) {
            //ухудшение юнита Обычной группы
            downgradeUnit(gameEngine, enemyRegularGroup, downgradeMessage);
        }
    }

    public abstract void attack(GameEngine gameEngine);

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getDamage() {
        return damage * getUpgraded();
    }

    public void setSide(ERaceType eRaceType) {
        if (eRaceType == ERaceType.ELF || eRaceType == ERaceType.HUMAN) {
            this.side = "GOOD";
        } else {
            this.side = "EVIL";
        }
    }

    @Override
    public String toString() {
        return " {" +
                "health=" + health +
                ", readyToFight" + readyToFight +
                ", damage=" + damage +
                ", upgraded=" + upgraded +
                ", name='" + name + '\'' +
                ", race=" + race +
                ", side='" + side + '\'' +
                '}';
    }

    public void setReadyToFight(boolean readyToFight) {
        this.readyToFight = readyToFight;
    }

    public boolean isReadyToFight() {
        return readyToFight;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }


    public double getUpgraded() {
        return upgraded;
    }

    public void setUpgraded(double upgraded) {
        this.upgraded = upgraded;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ERaceType getRace() {
        return race;
    }

    public String getSide() {
        return side;
    }

    public void setRace(ERaceType race) {
        this.race = race;
    }

    public String getAttackFirstTypeMessage() {
        return attackFirstTypeMessage;
    }

    public void setAttackFirstTypeMessage(String attackFirstTypeMessage) {
        this.attackFirstTypeMessage = attackFirstTypeMessage;
    }

    protected String getAttackSecondTypeMessage() {
        return attackSecondTypeMessage;
    }

    public void setAttackSecondTypeMessage(String attackSecondTypeMessage) {
        this.attackSecondTypeMessage = attackSecondTypeMessage;
    }
}