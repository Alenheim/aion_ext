/*
 * This file is part of aion-unique <aion-unique.com>.
 *
 *  aion-unique is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  aion-unique is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with aion-unique.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openaion.gameserver.skill.action;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.openaion.gameserver.model.gameobjects.Creature;
import org.openaion.gameserver.skill.model.Skill;



/**
 * @author ATracer
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MpUseAction")
public class MpUseAction extends Action
{
	@Override
	public void act(Skill skill)
	{
		Creature effector = skill.getEffector();
		float valueWithDelta = 0;
		if (percent)
		{
			valueWithDelta = effector.getLifeStats().getMaxMp() * value / 100;
		}
		else
		{	
			valueWithDelta = value + delta * skill.getSkillLevel();
			int changeMpPercent = skill.getChangeMpConsumption();
			if (changeMpPercent != 0)
				valueWithDelta = valueWithDelta + (valueWithDelta*((float)changeMpPercent/100f) );
		}
		effector.getLifeStats().reduceMp((int)valueWithDelta);
	}

}
