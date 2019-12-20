package unit.undead;

import engine.GameEngine;
import person.Person;
import races.ERaceType;

import java.util.ArrayList;

/**
 * зомби:
 *             удар копьем (нанесение урона 18 HP)
 */
public class WarriorUndead extends Person {
    public WarriorUndead() {
        super();
        setRace(ERaceType.UNDEAD);
        setName("Warrior"+getRace());
        setDamage(0);
        setSide(getRace());
        setAttackFirstTypeMessage(" удар копьем (нанесение урона 18 HP)");
    }

    @Override
    public void attack(GameEngine gameEngine) {
        setDamage(18);
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