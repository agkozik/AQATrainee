package unit.humans;

import engine.GameEngine;
import person.Person;
import races.ERaceType;

import java.util.ArrayList;

public class WarriorHuman extends Person {
    public WarriorHuman() {
        super();
        setRace(ERaceType.HUMAN);
        setName("Warrior"+getRace());
        setDamage(0);
        setSide(getRace());
        setAttackFirstTypeMessage(" мечом (нанесение урона 15 HP)");
    }

    @Override
    public void attack(GameEngine gameEngine) {
        setDamage(15);
        makeDamage(gameEngine, gameEngine.getEvilRegularGroup(),gameEngine.getEvilPrivilegedGroup(),getAttackFirstTypeMessage());
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