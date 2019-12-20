package unit.elfs;

import engine.GameEngine;
import person.Person;
import races.ERaceType;

import java.util.ArrayList;

/**
 * лучник:
 * стрелять из лука (нанесение урона 7 HP)
 * атаковать противника (нанесение урона 3 HP)
 */
public class ArcherElf extends Person {

    public ArcherElf() {
        super();
        setRace(ERaceType.ELF);
        setName("Archer" + getRace());
        setDamage(0);
        setSide(getRace());
        setAttackFirstTypeMessage(" стреляя из лука (нанесение урона 7 HP)");
        setAttackSecondTypeMessage(" атака противника (нанесение урона 3 HP)");
    }

    @Override
    public void attack(GameEngine gameEngine) {
        int a = gameEngine.figuresRandomGenerator();
        switch (a) {
            case 0:
                setDamage(7);
                makeDamage(gameEngine,gameEngine.getEvilRegularGroup(),gameEngine.getEvilPrivilegedGroup(), getAttackFirstTypeMessage());
                break;
            case 1:
                setDamage(3);
                makeDamage(gameEngine,gameEngine.getEvilRegularGroup(),gameEngine.getEvilPrivilegedGroup(),getAttackSecondTypeMessage());
                break;
        }
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
    public void setAttackSecondTypeMessage(String attackSecondTypeMessage) {
        super.setAttackSecondTypeMessage(attackSecondTypeMessage);
    }

    @Override
    public String toString() {
        return "Info: " + getName() + super.toString();
    }
}