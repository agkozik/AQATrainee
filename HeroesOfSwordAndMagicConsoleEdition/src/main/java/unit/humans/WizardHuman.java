package unit.humans;

import engine.GameEngine;
import person.Person;
import races.ERaceType;
import unit.elfs.WizardElf;

import java.util.ArrayList;

/**
 * наложение улучшения на персонажа своего отряда.
 *             атаковать магией (нанесение урона 4 HP)
 */
public class WizardHuman extends Person {

    public WizardHuman() {
        super();
        setRace(ERaceType.HUMAN);
        setName("Wizard"+getRace());
        setDamage(0);
        setSide(getRace());
        setAttackFirstTypeMessage(" использует наложение улучшения на персонажа своего отряда ");
        setAttackSecondTypeMessage(" магией (нанесение урона 4 HP) ");
    }

    @Override
    public void attack(GameEngine gameEngine) {
        int a = gameEngine.figuresRandomGenerator();
        switch (a) {
            case 0:
                useUpgradeMagic(gameEngine, gameEngine.getGoodRegularGroup(), gameEngine.getGoodPrivilegedGroup(),getAttackFirstTypeMessage());
                break;
            case 1:
                setDamage(4);
                makeDamage(gameEngine, gameEngine.getEvilRegularGroup(),gameEngine.getEvilPrivilegedGroup(),getAttackSecondTypeMessage());
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