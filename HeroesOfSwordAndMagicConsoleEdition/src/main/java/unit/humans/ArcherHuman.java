package unit.humans;

import engine.GameEngine;
import person.Person;
import races.ERaceType;

import java.util.ArrayList;

/**
 * арбалетчик:
 *             стрелять из арбалета (нанесение урона 5 HP)
 *             атаковать (нанесение урона 3 HP)
 */
public class ArcherHuman extends Person {
    public ArcherHuman() {
        super();
        setRace(ERaceType.HUMAN);
        setName("Archer"+getRace());
        setDamage(0);
        setSide(getRace());
        setAttackFirstTypeMessage(" стреляет из арбалета (нанесение урона 5 HP)");
        setAttackSecondTypeMessage(" атака (нанесение урона 3 HP)");
    }


    @Override
    public void attack(GameEngine gameEngine) {
        int a = gameEngine.figuresRandomGenerator();
        switch (a) {
            case 0:
                setDamage(5);
                makeDamage(gameEngine,gameEngine.getEvilRegularGroup(),gameEngine.getEvilPrivilegedGroup(),getAttackFirstTypeMessage());
                break;
            case 1:
                setDamage(3);
                makeDamage(gameEngine,gameEngine.getEvilRegularGroup(),gameEngine.getEvilPrivilegedGroup(),getAttackSecondTypeMessage());
                break;
        }

    }

    @Override
    public void setAttackFirstTypeMessage(String attackFirstTypeMessage) {
        super.setAttackFirstTypeMessage(attackFirstTypeMessage);
    }

    @Override
    public String toString() {
        return "Info: " + getName() + super.toString();
    }
}