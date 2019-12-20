package unit.orks;

import engine.GameEngine;
import person.Person;
import races.ERaceType;

import java.util.ArrayList;

/**
 *  лучник:
 *             стрелять из лука (нанесение урона 3 HP)
 *             удар клинком (нанесение урона 2 HP)
 */
public class ArcherOrk extends Person {
    public ArcherOrk() {
        super();
        setRace(ERaceType.ORK);
        setName("Archer"+getRace());
        setDamage(0);
        setSide(getRace());
        setAttackFirstTypeMessage(" стреляя из лука (нанесение урона 3 HP) ");
        setAttackSecondTypeMessage(" удар клинком (нанесение урона 2 HP) ");
    }

    @Override
    public void attack(GameEngine gameEngine) {
        int a = gameEngine.figuresRandomGenerator();
        switch (a) {
            case 0:
                setDamage(3);
                makeDamage(gameEngine,gameEngine.getGoodRegularGroup(),gameEngine.getGoodPrivilegedGroup(),getAttackFirstTypeMessage());
                break;
            case 1:
                setDamage(2);
                makeDamage(gameEngine,gameEngine.getGoodRegularGroup(),gameEngine.getGoodPrivilegedGroup(),getAttackSecondTypeMessage());
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