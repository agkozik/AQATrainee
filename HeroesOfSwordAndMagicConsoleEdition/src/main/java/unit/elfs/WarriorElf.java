package unit.elfs;

import engine.GameEngine;
import person.Person;
import races.ERaceType;

import java.util.ArrayList;

/**
 * воин:
 * атаковать мечом (нанесение урона 15 HP)
 */
public class WarriorElf extends Person {

    public WarriorElf() {
        super();
        setRace(ERaceType.ELF);
        setName("Warrior" + getRace());
        setDamage(0);
        setSide(getRace());
        setAttackFirstTypeMessage(" атаковать мечом (нанесение урона 15 HP) ");
    }

    @Override
    public void attack(GameEngine gameEngine) {
        setDamage(15);
        makeDamage(gameEngine, gameEngine.getEvilRegularGroup(),gameEngine.getEvilPrivilegedGroup(),getAttackFirstTypeMessage());
    }

    @Override
    public void setDamage(double damage) {
        super.setDamage(damage);
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