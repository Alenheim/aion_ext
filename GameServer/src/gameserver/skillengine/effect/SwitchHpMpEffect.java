/**
 * This file is part of Aion X Emu <aionxemu.com>
 *
 *  This is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This software is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser Public License
 *  along with this software.  If not, see <http://www.gnu.org/licenses/>.
 */
package gameserver.skillengine.effect;

import gameserver.model.gameobjects.Creature;
import gameserver.model.gameobjects.stats.CreatureLifeStats;
import gameserver.network.aion.serverpackets.SM_ATTACK_STATUS.TYPE;
import gameserver.skillengine.model.Effect;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * @author ATracer
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SwitchHpMpEffect")
public class SwitchHpMpEffect extends EffectTemplate {

    @Override
    public void applyEffect(Effect effect) {
        CreatureLifeStats<? extends Creature> lifeStats = effect.getEffected().getLifeStats();
        int currentHp = lifeStats.getCurrentHp();
        int currentMp = lifeStats.getCurrentMp();

        lifeStats.increaseHp(TYPE.NATURAL_HP, currentMp - currentHp);
        lifeStats.increaseMp(TYPE.NATURAL_MP, currentHp - currentMp);
    }

    @Override
    public void calculate(Effect effect) {
        effect.addSucessEffect(this);
    }
}
