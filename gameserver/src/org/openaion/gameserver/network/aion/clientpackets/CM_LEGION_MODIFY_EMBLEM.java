/*
 * This file is part of aion-unique <aion-unique.org>.
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
package org.openaion.gameserver.network.aion.clientpackets;

import org.openaion.gameserver.model.gameobjects.player.Player;
import org.openaion.gameserver.network.aion.AionClientPacket;
import org.openaion.gameserver.services.LegionService;

/**
 * @author Simple
 * 
 */
public class CM_LEGION_MODIFY_EMBLEM extends AionClientPacket
{

	/** Emblem related information **/
	private int				legionId;
	private int				emblemVer;
	private int				red;
	private int				green;
	private int				blue;

	/**
	 * @param opcode
	 */
	public CM_LEGION_MODIFY_EMBLEM(int opcode)
	{
		super(opcode);
	}

	@Override
	protected void readImpl()
	{
		legionId = readD();
		emblemVer = readH();
		readC(); // 0xFF is default; sets the transparency
		red = readC();
		green = readC();
		blue = readC();
	}

	@Override
	protected void runImpl()
	{
		Player activePlayer = getConnection().getActivePlayer();

		if(activePlayer.isLegionMember())
			LegionService.getInstance().storeLegionEmblem(activePlayer, legionId, emblemVer, red, green, blue, false);
	}
}
