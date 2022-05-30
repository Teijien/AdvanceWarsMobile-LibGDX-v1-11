package com.microwars.game;

public class BasicUnit implements Unit {
    private final int team;
    private final Stats stats;

    public BasicUnit(int team, Stats stats) {
        this.team = team;
        this.stats = stats;
    }

    public void attackUnit(Unit defender) {
        defender.takeDmg(stats.getAtk() * hp() / 100);
        if (defender.getStats().getHp() > 0) {
            takeDmg(defender.getStats().getAtk() * defender.hp() / 100);
        }
    }

    public void takeDmg(int atk) {
        int dmg = atk - stats.getDef();
        if (dmg < 10) {
            dmg = 10;
        }
        if (stats.getDef() <= atk) {
            stats.setHp(hp() - dmg);
        }
        System.out.println(team + " unit took " + (dmg) + " damage! HP = " + hp());
    }

    public Stats getStats() {
        return stats;
    }

    public int mov() {
        return getStats().getMov();
    }

    public int hp() {
        return getStats().getHp();
    }

    public int team() {
        return team;
    }
}
