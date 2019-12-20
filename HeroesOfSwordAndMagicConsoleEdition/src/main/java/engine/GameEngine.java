package engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import person.Person;
import unit.elfs.ArcherElf;
import unit.elfs.WarriorElf;
import unit.elfs.WizardElf;
import unit.humans.ArcherHuman;
import unit.humans.WarriorHuman;
import unit.humans.WizardHuman;
import unit.orks.ArcherOrk;
import unit.orks.WarriorOrk;
import unit.orks.WizardOrk;
import unit.undead.ArcherUndead;
import unit.undead.WarriorUndead;
import unit.undead.WizardUndead;

import java.util.ArrayList;
import java.util.Random;

public class GameEngine {
    private static final Logger logger = LoggerFactory.getLogger(GameEngine.class);
    private ArrayList<Person> groupGood = new ArrayList<>();
    private ArrayList<Person> groupEvil = new ArrayList<>();
    private ArrayList<Person> goodRegularGroup = new ArrayList<>();
    private ArrayList<Person> goodPrivilegedGroup = new ArrayList<>();
    private ArrayList<Person> evilPrivilegedGroup = new ArrayList<>();
    private ArrayList<Person> evilRegularGroup = new ArrayList<>();
    private String groupGoodName;
    private String groupEvilName;
    public final double up = 1.5;

    public ArrayList<Person> getGroupGood() {
        return groupGood;
    }

    public void setGroupGood(ArrayList<Person> groupGood) {
        this.groupGood = groupGood;
    }

    public ArrayList<Person> getGroupEvil() {
        return groupEvil;
    }

    public void setGroupEvil(ArrayList<Person> groupEvil) {
        this.groupEvil = groupEvil;
    }

    public ArrayList<Person> getGoodPrivilegedGroup() {
        return goodPrivilegedGroup;
    }

    public void setGoodPrivilegedGroup(ArrayList<Person> goodPrivilegedGroup) {
        this.goodPrivilegedGroup = goodPrivilegedGroup;
    }

    public ArrayList<Person> getGoodRegularGroup() {
        return goodRegularGroup;
    }

    public void setGoodRegularGroup(ArrayList<Person> goodRegularGroup) {
        this.goodRegularGroup = goodRegularGroup;
    }

    public ArrayList<Person> getEvilPrivilegedGroup() {
        return evilPrivilegedGroup;
    }

    public void setEvilPrivilegedGroup(ArrayList<Person> evilPrivilegedGroup) {
        this.evilPrivilegedGroup = evilPrivilegedGroup;
    }

    public ArrayList<Person> getEvilRegularGroup() {
        return evilRegularGroup;
    }

    public void setEvilRegularGroup(ArrayList<Person> evilRegularGroup) {
        this.evilRegularGroup = evilRegularGroup;
    }

    private String getGroupGoodName() {
        return groupGoodName;
    }

    public void setGroupGoodName(String groupGoodName) {
        this.groupGoodName = groupGoodName;
    }

    private String getGroupEvilName() {
        return groupEvilName;
    }

    public void setGroupEvilName(String groupEvilName) {
        this.groupEvilName = groupEvilName;
    }

    /**
     * Генерация случайного целого числа из интервала [0-1]
     */
    public int figuresRandomGenerator() {
        return (int) (Math.random() * 2);
    }

    /**
     * Генерация случайного целого числа из интервала [min-max)
     */
    public int figuresRangeRandomGenerator(int min, ArrayList<Person> group) {
        int index;
        int diff = group.size() - min;
        Random random = new Random();
        index = random.nextInt(diff);
        index += min;
        return index;
    }

    /**
     * Формирование участвующей в бою группы (2 стороны: Good and Evil)
     */
    public void startGame() throws InterruptedException {
        System.out.println("------------------------------------------------------------------------Игра началась---------------------------------------------------------");
        int a = figuresRandomGenerator();

        if (a == 0) {
            groupGoodName = "Elfs";
            groupGood.add(new WizardElf());
            groupGood.add(new ArcherElf());
            groupGood.add(new ArcherElf());
            groupGood.add(new ArcherElf());
            groupGood.add(new WarriorElf());
            groupGood.add(new WarriorElf());
            groupGood.add(new WarriorElf());
            groupGood.add(new WarriorElf());
        } else {
            groupGoodName = "Humans";
            groupGood.add(new WizardHuman());
            groupGood.add(new ArcherHuman());
            groupGood.add(new ArcherHuman());
            groupGood.add(new ArcherHuman());
            groupGood.add(new WarriorHuman());
            groupGood.add(new WarriorHuman());
            groupGood.add(new WarriorHuman());
            groupGood.add(new WarriorHuman());
        }
        a = figuresRandomGenerator();
        if (a == 0) {
            groupEvilName = "Orks";
            groupEvil.add(new WizardOrk());
            groupEvil.add(new ArcherOrk());
            groupEvil.add(new ArcherOrk());
            groupEvil.add(new ArcherOrk());
            groupEvil.add(new WarriorOrk());
            groupEvil.add(new WarriorOrk());
            groupEvil.add(new WarriorOrk());
            groupEvil.add(new WarriorOrk());
        } else {
            groupEvilName = "Undead";
            groupEvil.add(new WizardUndead());
            groupEvil.add(new ArcherUndead());
            groupEvil.add(new ArcherUndead());
            groupEvil.add(new ArcherUndead());
            groupEvil.add(new WarriorUndead());
            groupEvil.add(new WarriorUndead());
            groupEvil.add(new WarriorUndead());
            groupEvil.add(new WarriorUndead());
        }
        System.out.println("Сражаются расы: "
                + ((char) 27 + "[33m" + getGroupGoodName() + (char) 27 + "[0m")
                + " против "
                + ((char) 27 + "[31m" + getGroupEvilName() + (char) 27 + "[0m"));
        groupGood.get(0).setUpgraded(up);
        groupGood.get(1).setUpgraded(up);
        groupGood.get(2).setUpgraded(up);
        groupGood.get(3).setUpgraded(up);
        groupGood.get(4).setUpgraded(up);
        groupGood.get(5).setUpgraded(up);
        groupEvil.get(0).setUpgraded(up);
        groupEvil.get(1).setUpgraded(up);
        groupEvil.get(2).setUpgraded(up);
        groupEvil.get(3).setUpgraded(up);
        groupEvil.get(4).setUpgraded(up);
        groupEvil.get(5).setUpgraded(up);
        sortGroup();

        while (!((goodRegularGroup.size() == 0 && goodPrivilegedGroup.size() == 0) || (evilRegularGroup.size() == 0 && evilPrivilegedGroup.size() == 0))) {
            battleNextStep(firstGroupAttack());
            resetReadyToFight(goodRegularGroup, goodPrivilegedGroup, evilRegularGroup, evilPrivilegedGroup);

            if ((goodRegularGroup.size() == 0 && goodPrivilegedGroup.size() == 0)) {
                logger.debug(((char) 27 + "[31m" + "Победил отряд EVIL!" + (char) 27 + "[0m"));
                break;
            } else if ((evilRegularGroup.size() == 0 && evilPrivilegedGroup.size() == 0)) {
                logger.debug(((char) 27 + "[33m" + "Победил отряд GOOD!" + (char) 27 + "[0m"));
                break;
            } else {
                pressAnyKeyToContinue();
                logger.debug("-///////////////////////////////////////////////////////////////-Следующий ход-//////////////////////////////////////////////////////////////-");
            }
        }
    }

    private void pressAnyKeyToContinue() {
        logger.debug("\nЧтобы начать следующий ход - нажмите ENTER...");
        try {
            System.in.read();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private int firstGroupAttack() {
        int a = tossUp();
        switch (a) {
            case 0:
                stepAttack(goodRegularGroup, goodPrivilegedGroup);
                return a;
            case 1:
                stepAttack(evilRegularGroup, evilPrivilegedGroup);
                return a;
        }
        return a;
    }

    /**
     * Подсчет отыгравших юнитов, false если еще есть кому ходить и true если все юниты походили
     *
     * @return
     */
    public boolean lastStepCheck() {
        int counterGood = 0;
        int counterEvil = 0;
        for (Person personGR : goodRegularGroup) {
            if (personGR.isReadyToFight()) {
                counterGood++;
            }
        }
        for (Person personGP : goodPrivilegedGroup) {
            if (personGP.isReadyToFight()) {
                counterGood++;
            }
        }
        for (Person personEP : evilPrivilegedGroup) {
            if (personEP.isReadyToFight()) {
                counterEvil++;
            }
        }
        for (Person personER : evilRegularGroup) {
            if (personER.isReadyToFight()) {
                counterEvil++;
            }
        }

        if ((counterGood == 0 && counterEvil == 0)) {
            return true;
        } else {
            return false;
        }
    }

    public void battleNextStep(int a) {
        while (!lastStepCheck()) {
            if (a == 0) {
                stepAttack(evilRegularGroup, evilPrivilegedGroup);
                stepAttack(goodRegularGroup, goodPrivilegedGroup);
            } else {
                stepAttack(goodRegularGroup, goodPrivilegedGroup);
                stepAttack(evilRegularGroup, evilPrivilegedGroup);
            }
        }
        logger.debug("\n---Ход завершен---\n");
        logger.debug("\n---Текущая информация об отрядах---\n");
        showRegularAndPrivilegedSeparatedGroups();
    }

    /**
     * Сортировка отряда на подотряды: привилегированные и непривилегированные по признаку (IsUpgraded)
     */
    private void sortGroup() {
        logger.debug("Сортировка отряда GOOD!");
        for (Person i : groupGood) {
            if (i.getUpgraded() == up) {
                goodPrivilegedGroup.add(i);
            } else {
                goodRegularGroup.add(i);
            }
        }
        logger.debug("Сортировка отряда EVIL!");
        for (Person j : groupEvil) {
            if (j.getUpgraded() == up) {
                evilPrivilegedGroup.add(j);
            } else {
                evilRegularGroup.add(j);
            }
        }
    }

    /**
     * Первая Атака отряда
     */
    public void stepAttack(ArrayList<Person> regularGroup, ArrayList<Person> privilegedGroup) {
        Person person;
        int attackingHeroIndex;
        //За один ход каждый персонаж отряда может выполнить действие (действие определяется случайным образом):
        //сначала из привилегированной группы, а если она пуста, тогда из общего списка персонажей отряда рандомно.
        if (privilegedGroup.size() != 0 && checkReadyToFight(privilegedGroup)) {
            do {
                attackingHeroIndex = figuresRangeRandomGenerator(0, privilegedGroup);
            }
            while (!privilegedGroup.get(attackingHeroIndex).isReadyToFight());
            person = privilegedGroup.get(attackingHeroIndex);
            person.attack(this);
            person.setReadyToFight(false);
            regularGroup.add(person);//Персонаж из привилегированной группы после выполнения одного действия перемещается в общую группу.
            privilegedGroup.remove(attackingHeroIndex);//Персонаж из привилегированной группы после выполнения одного действия перемещается в общую группу.
        } else {
            if (regularGroup.size() != 0 && checkReadyToFight(regularGroup)) {
                do {
                    attackingHeroIndex = figuresRangeRandomGenerator(0, regularGroup);
                }
                while (!regularGroup.get(attackingHeroIndex).isReadyToFight());

                person = regularGroup.get(attackingHeroIndex);
                person.attack(this);
                person.setReadyToFight(false);
            }
        }
    }

    /**
     * Проверка походивших юнитов
     *
     * @param group
     * @return
     */
    public boolean checkReadyToFight(ArrayList<Person> group) {
        int counter = 0;
        for (Person person : group) {
            if (person.isReadyToFight()) {
                counter++;
            }
        }
        if (counter > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Сброс возможности совершить ход
     *
     * @param group1
     * @param group2
     * @param group3
     * @param group4
     */
    public void resetReadyToFight(ArrayList<Person> group1, ArrayList<Person> group2, ArrayList<Person> group3, ArrayList<Person> group4) {
        for (Person person1 : group1) {
            person1.setReadyToFight(true);
        }
        for (Person person2 : group2) {
            person2.setReadyToFight(true);
        }
        for (Person person3 : group3) {
            person3.setReadyToFight(true);
        }
        for (Person person4 : group4) {
            person4.setReadyToFight(true);
        }
    }

    /**
     * Определяет, какая сторона (GOOD or EVIL) нападает первой
     *
     * @return int [0-1]
     */
    public int tossUp() {
        int a = figuresRandomGenerator();
        if (a == 0) {
            logger.debug(
                    ((char) 27 + "[32m"
                            + "\nНападает сторона GOOD!\n")
                            + (char) 27 + "[0m");
            return 0;
        } else {
            logger.debug(
                    ((char) 27 + "[31m"
                            + "\nНападает сторона EVIL!\n")
                            + (char) 27 + "[0m");
            return 1;
        }
    }

    /**
     * отображает всю информации об обычной и привилегированной подгруппах
     */
    private void showRegularAndPrivilegedSeparatedGroups() {
        logger.debug(((char) 27 + "[33m" + "Отряд GOOD:" + (char) 27 + "[0m"));
        logger.debug("Обычная группа состоит из " + goodRegularGroup.size() + " юнитов:");
        for (Person regular : goodRegularGroup) {
            logger.debug("Юнит " + regular);
        }
        logger.debug("Привилегированная группа состоит из " + goodPrivilegedGroup.size() + " юнитов:");
        for (Person privileged : goodPrivilegedGroup) {
            logger.debug("Юнит " + privileged);
        }

        logger.debug(((char) 27 + "[31m" + "Отряд EVIL:" + (char) 27 + "[0m"));
        logger.debug("Обычная группа состоит из " + evilRegularGroup.size() + " юнитов:");
        for (Person regular : evilRegularGroup) {
            logger.debug("Юнит " + regular);
        }
        logger.debug("Привилегированная группа состоит из " + evilPrivilegedGroup.size() + " юнитов:");
        for (Person privileged : evilPrivilegedGroup) {
            logger.debug("Юнит " + privileged);
        }
    }
}