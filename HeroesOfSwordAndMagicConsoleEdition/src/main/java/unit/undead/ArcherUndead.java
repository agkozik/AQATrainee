package unit.undead;

import engine.GameEngine;
import person.Person;
import races.ERaceType;

/**
 * охотник:
 * стрелять из лука (нанесение урона 4 HP)
 * атаковать (нанесение урона 2 HP)
 */
public class ArcherUndead extends Person {
    public ArcherUndead() {
        super();
        setRace(ERaceType.UNDEAD);
        setName("Archer" + getRace());
        setDamage(0);
        setSide(getRace());
        setAttackFirstTypeMessage(" стреляя из лука (нанесение урона 4 HP) ");
        setAttackSecondTypeMessage(" атака (нанесение урона 2 HP) ");
    }

    @Override
    public void attack(GameEngine gameEngine) {
        int a = gameEngine.figuresRandomGenerator();
        switch (a) {
            case 0:
                setDamage(4);
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