package unit.undead;

import engine.GameEngine;
import person.Person;
import races.ERaceType;

/**
 * некромант:
 * наслать недуг (уменьшение силы урона персонажа противника на 50% на один ход)
 * атака (нанесение урона 5 HP)
 */
public class WizardUndead extends Person {
    public WizardUndead() {
        super();
        setRace(ERaceType.UNDEAD);
        setName("Wizard" + getRace());
        setDamage(0);
        setSide(getRace());
        setAttackFirstTypeMessage(" наслать недуг (уменьшение силы урона персонажа противника на 50% на один ход)");
        setAttackSecondTypeMessage(" атака (нанесение урона 5 HP)");
    }

    @Override
    public void attack(GameEngine gameEngine) {
        int a = gameEngine.figuresRandomGenerator();
        switch (a) {
            case 0:
                useDowngradeMagic(gameEngine,gameEngine.getGoodRegularGroup(),gameEngine.getGoodPrivilegedGroup(),getAttackFirstTypeMessage());
                break;
            case 1:
                setDamage(5);
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