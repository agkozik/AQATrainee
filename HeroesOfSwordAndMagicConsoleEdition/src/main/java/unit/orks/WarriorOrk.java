package unit.orks;

import engine.GameEngine;
import person.Person;
import races.ERaceType;

import java.util.ArrayList;

/**
 * гоблин:
 *             атака дубиной (нанесение урона 20 HP)
 */
public class WarriorOrk extends Person {
    public WarriorOrk() {
        super();
        setRace(ERaceType.ORK);
        setName("Warrior"+getRace());
        setDamage(0);
        setSide(getRace());
        setAttackFirstTypeMessage(" атака дубиной (нанесение урона 20 HP)");
    }

    @Override
    public void attack(GameEngine gameEngine) {
                setDamage(20);
                makeDamage(gameEngine,gameEngine.getGoodRegularGroup(),gameEngine.getGoodPrivilegedGroup(),getAttackFirstTypeMessage());
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