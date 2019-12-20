package unit.elfs;

import engine.GameEngine;
import person.Person;
import races.ERaceType;

import java.util.ArrayList;

/**
 * Раса эльфов маг:
 * наложение улучшения на персонажа своего отряда
 * нанесение урона персонажу противника магией на 10 HP
 */
public class WizardElf extends Person {

    public WizardElf() {
        super();
        setRace(ERaceType.ELF);
        setName("Wizard" + getRace());
        setDamage(0);
        setSide(getRace());
        setAttackFirstTypeMessage(" использует наложение улучшения на персонажа своего отряда ");
        setAttackSecondTypeMessage(" нанесение урона персонажу противника магией на 10 HP ");
    }

    @Override
    public void attack(GameEngine gameEngine) {
        int a = gameEngine.figuresRandomGenerator();
        switch (a) {
            case 0:
                useUpgradeMagic(gameEngine, gameEngine.getGoodRegularGroup(), gameEngine.getGoodPrivilegedGroup(),getAttackFirstTypeMessage());
                break;
            case 1:
                setDamage(10);
                makeDamage(gameEngine, gameEngine.getEvilRegularGroup(),gameEngine.getEvilPrivilegedGroup(),getAttackSecondTypeMessage());
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