package unit.orks;

import engine.GameEngine;
import person.Person;
import races.ERaceType;

import java.util.ArrayList;

/**
 * шаман:
 * наложение улучшения на персонажа своего отряда.
 * наложение проклятия (снятие улучшения с персонажа противника для следующего хода)
 */
public class WizardOrk extends Person {
    public WizardOrk() {
        super();
        setRace(ERaceType.ORK);
        setName("Wizard" + getRace());
        setDamage(0);
        setSide(getRace());
        setAttackFirstTypeMessage(" наложение улучшения на персонажа своего отряда.");
        setAttackSecondTypeMessage(" наложение проклятия (снятие улучшения с персонажа противника для следующего хода)");
    }

    @Override
    public void attack(GameEngine gameEngine) {
        int a = gameEngine.figuresRandomGenerator();
        switch (a) {
            case 0:
                useUpgradeMagic(gameEngine,gameEngine.getEvilRegularGroup(), gameEngine.getEvilPrivilegedGroup(),getAttackFirstTypeMessage());
                break;
            case 1:
               //-> наложение проклятия (снятие улучшения с персонажа противника для следующего хода
                useDowngradeMagic(gameEngine,gameEngine.getGoodRegularGroup(),gameEngine.getGoodPrivilegedGroup(),getAttackSecondTypeMessage());
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