/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 *
 * http://www.gnu.org/copyleft/gpl.html
 */

package l1j.server.server.clientpackets;

import javolution.util.FastTable;
import l1j.server.server.ActionCodes;
import l1j.server.server.BugKick;
import l1j.server.server.Opcodes;
import l1j.server.server.model.Broadcaster;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1DollInstance;
import l1j.server.server.model.Instance.L1ItemInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.model.Instance.L1PetInstance;
import l1j.server.server.model.skill.L1SkillId;
import l1j.server.server.serverpackets.S_ChatPacket;
import l1j.server.server.serverpackets.S_Disconnect;
import l1j.server.server.serverpackets.S_DoActionGFX;
import l1j.server.server.serverpackets.S_DoActionShop;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.serverpackets.S_SystemMessage;
import l1j.server.server.templates.L1PrivateShopBuyList;
import l1j.server.server.templates.L1PrivateShopSellList;
import server.LineageClient;
import server.message.ServerMessage;
import server.system.autoshop.AutoShopManager;

// Referenced classes of package l1j.server.server.clientpackets:
// ClientBasePacket

public class C_Shop extends ClientBasePacket {

	private static final String C_SHOP = "[C] C_Shop";

	public C_Shop(byte abyte0[], LineageClient clientthread) {
		super(abyte0);

		L1PcInstance pc = clientthread.getActiveChar();
		if (pc == null) return;
		if (pc.isGhost()) return;
		if (pc.isTeleport() || pc.isDead())  return;
		
		if (pc.isInvisble()) {
			pc.sendPackets(new S_ServerMessage(755));
			return;
		}

		if (pc.getMapId() != 800) {
			pc.sendPackets(new S_SystemMessage("?????????? ???? ????????"));
			return;
		}
		
		if (pc.getGfxId().getTempCharGfx() != pc.getClassId()
				&& pc.getSkillEffectTimerSet().getSkillEffectTimeSec(
						L1SkillId.SHAPE_CHANGE) <= 0) {
			pc.sendPackets(new S_SystemMessage("???? ???????? ???? ?? ?????? ????????."));
			return;
		}
		if (pc.getLevel() <= 5) {// ????
			pc.sendPackets(new S_SystemMessage("?????????? ???? 5?????? ???? ????????."));
			return;
		}
		// /////////////////////////////////////////////////
		for (L1PcInstance target : L1World.getInstance().getAllPlayers()) {
			if (target.getId() != pc.getId()
					&& target.getAccountName().toLowerCase().equals(
							pc.getAccountName().toLowerCase())
					&& target.isPrivateShop()) {
				pc.sendPackets(new S_SystemMessage("?????????? ?????? ???????? ??????????."));
				return;
			}
		}

		FastTable<L1PcInstance> PcList = null;
		PcList = L1World.getInstance().getVisiblePlayer(pc, 1);
		if (PcList.size() >= 1) {
			pc.sendPackets(new S_SystemMessage(
					"?????? ?????? ?????????? 1?? ???????? ???? ??????????."));
			return;
		}
		// /////////////////////////////////////////////////
		FastTable<L1PrivateShopSellList> sellList = pc.getSellList();
		FastTable<L1PrivateShopBuyList> buyList = pc.getBuyList();
		L1ItemInstance checkItem;
		boolean tradable = true;

		int type = readC();
		if (type == 0) { // ????
			int sellTotalCount = readH();
			int sellObjectId;
			int sellPrice;
			int sellCount;
			Object[] petlist = null;
			for (int i = 0; i < sellTotalCount; i++) {
				sellObjectId = readD();
				sellPrice = readD();
				sellCount = readD();

				/** ???????? ???? ???? */
				if (sellTotalCount > 7) {
					pc.sendPackets(new S_SystemMessage("?????????? 7???????? ??????????."));
					return;
				}

				// ???? ?????? ?????????? ????
				checkItem = pc.getInventory().getItem(sellObjectId);
				if (sellObjectId != checkItem.getId() || !checkItem.isStackable() && sellCount != 1) {
					pc.sendPackets(new S_Disconnect());
					return;
				}
				if (sellCount <= 0 || checkItem.getCount() <= 0) {
					pc.sendPackets(new S_Disconnect());
					return;
				}
				if (sellCount > checkItem.getCount()) {
					sellCount = checkItem.getCount();
				}
				if (checkItem.getCount() < sellCount
						|| checkItem.getCount() <= 0 || sellCount <= 0) {
					sellList.clear();
					buyList.clear();
					BugKick.getInstance().KickPlayer(pc);
					return;
				}
				if (checkItem.getItem().getItemId() == 423012
						|| checkItem.getItem().getItemId() == 423013) { // 10??????
					pc.sendPackets(new S_ServerMessage(210, checkItem.getItem()
							.getName())); // \f1%0??
					// ????????
					// ????
					// ????????
					// ??????
					// ?? ??
					// ????????.
					return;
				}
				if (checkItem.getBless() >= 128) {
					pc.sendPackets(new S_ServerMessage(
							ServerMessage.CANNOT_DROP_OR_TRADE, checkItem
									.getItem().getName()));
					return;
				}
				if (!checkItem.getItem().isTradable()) {
					tradable = false;
					pc.sendPackets(new S_ServerMessage(166, checkItem.getItem()
							.getName(), "???? ????????????. "));
				}
				/*if (checkItem.getItem().getType2() == 1 || checkItem.getItem().getType2() == 2) { //??????
					if (pc.getMapId() == 350) { //???? ??????????
						sellList.clear();
						buyList.clear();
						pc.setPrivateShop(false);
						pc.sendPackets(new S_DoActionGFX(pc.getId(), ActionCodes.ACTION_Idle));
						Broadcaster.broadcastPacket(pc, new S_DoActionGFX(pc.getId(), ActionCodes.ACTION_Idle));
						pc.sendPackets(new S_SystemMessage("?????? ?????? ?? ???? ??????????????.\n?????? ?????? ??????????."));
						return;
					}
				}
				if (checkItem.getItem().getType2() == 0) { //??????
					if (pc.getMapId() == 340) { //???? ??????????
						sellList.clear();
						buyList.clear();
						pc.setPrivateShop(false);
						pc.sendPackets(new S_DoActionGFX(pc.getId(), ActionCodes.ACTION_Idle));
						Broadcaster.broadcastPacket(pc, new S_DoActionGFX(pc.getId(), ActionCodes.ACTION_Idle));
						pc.sendPackets(new S_SystemMessage("?????? ???? ?? ?????? ??????????????.\n?????? ?? ?????? ?????? ??????????."));
						return;
					}
				}*/
				L1DollInstance doll = null;
				Object[] dollList = pc.getDollList().values().toArray();
				for (Object dollObject : dollList) {
					doll = (L1DollInstance) dollObject;
					if (checkItem.getId() == doll.getItemObjId()) {
						pc.sendPackets(new S_ServerMessage(1181)); // 
						return;
					}
				}
				petlist = pc.getPetList().values().toArray();
				for (Object petObject : petlist) {
					if (petObject instanceof L1PetInstance) {
						L1PetInstance pet = (L1PetInstance) petObject;
						if (checkItem.getId() == pet.getItemObjId()) {
							tradable = false;
							pc.sendPackets(new S_ServerMessage(166, checkItem
									.getItem().getName(), "???? ????????????. "));
							break;
						}
					}
				}
				L1PrivateShopSellList pssl = new L1PrivateShopSellList();
				pssl.setItemObjectId(sellObjectId);
				pssl.setSellPrice(sellPrice);
				pssl.setSellTotalCount(sellCount);
				sellList.add(pssl);
			}

			int buyTotalCount = readH();
			int buyObjectId;
			int buyPrice;
			int buyCount;
			for (int i = 0; i < buyTotalCount; i++) {
				buyObjectId = readD();
				buyPrice = readD();
				buyCount = readD();

				if (sellTotalCount > 7) {
					pc.sendPackets(new S_SystemMessage("?????????? 7???????? ??????????."));
					return;
				}

				checkItem = pc.getInventory().getItem(buyObjectId);

				if (buyObjectId != checkItem.getId()) {
					pc.sendPackets(new S_Disconnect());
					return;
				}
				if (!checkItem.isStackable() && buyCount != 1) {
					pc.sendPackets(new S_Disconnect());
					return;
				}
				if (buyPrice <= 0) {
					sellList.clear();  
					buyList.clear();
					return;
				}
				if (buyCount <= 0 || checkItem.getCount() <= 0) {

					pc.sendPackets(new S_Disconnect());
					return;
				}
				if (buyCount > checkItem.getCount()) {
					buyCount = checkItem.getCount();
				}

				if (!checkItem.getItem().isTradable()) {
					tradable = false;
					pc.sendPackets(new S_ServerMessage(166, checkItem.getItem()
							.getName(), "???? ????????????. "));
				}
				petlist = pc.getPetList().values().toArray();
				for (Object petObject : petlist) {
					if (petObject instanceof L1PetInstance) {
						L1PetInstance pet = (L1PetInstance) petObject;
						if (checkItem.getId() == pet.getItemObjId()) {
							tradable = false;
							pc.sendPackets(new S_ServerMessage(166, checkItem
									.getItem().getName(), "???? ????????????. "));
							break;
						}
					}
				}
				L1PrivateShopBuyList psbl = new L1PrivateShopBuyList();
				psbl.setItemObjectId(buyObjectId);
				psbl.setBuyPrice(buyPrice);
				psbl.setBuyTotalCount(buyCount);
				buyList.add(psbl);
			}
			if (sellTotalCount == 0 && buyTotalCount == 0) {
				pc.sendPackets(new S_ServerMessage(908));
				pc.setPrivateShop(false);
				pc.sendPackets(new S_DoActionGFX(pc.getId(),
						ActionCodes.ACTION_Idle));
				Broadcaster.broadcastPacket(pc, new S_DoActionGFX(pc.getId(),
						ActionCodes.ACTION_Idle));
				return;
			}
			if (!tradable) { // ???? ???????? ???????? ???????? ???? ????, ???? ???? ????
				sellList.clear();
				buyList.clear();
				pc.setPrivateShop(false);
				pc.sendPackets(new S_DoActionGFX(pc.getId(),
						ActionCodes.ACTION_Idle));
				Broadcaster.broadcastPacket(pc, new S_DoActionGFX(pc.getId(),
						ActionCodes.ACTION_Idle));
				return;
			}
			byte[] chat = readByte();
			if (pc.getAccessLevel() > 0) {
				sellList.clear();
				buyList.clear();
			} else {
				pc.setShopChat(chat);
				pc.sendPackets(new S_DoActionShop(pc.getId(),ActionCodes.ACTION_Shop, chat));
				Broadcaster.broadcastPacket(pc, new S_DoActionShop(pc.getId(),ActionCodes.ACTION_Shop, chat));
			}

			pc.setPrivateShop(true);
			String text = "?????? ?????? ???????? ?????? ???????? ???????? ??????????.";
			S_ChatPacket msg = new S_ChatPacket(pc, text, Opcodes.S_OPCODE_NORMALCHAT, 0);
			pc.sendPackets(msg);
		} else if (type == 1) { // ????
			if (isTwoLogin(pc)) {
				pc.sendPackets(new S_Disconnect());
			}
			sellList.clear();
			buyList.clear();
			pc.setPrivateShop(false);
			pc.sendPackets(new S_DoActionGFX(pc.getId(),
					ActionCodes.ACTION_Idle));
			Broadcaster.broadcastPacket(pc, new S_DoActionGFX(pc.getId(),
					ActionCodes.ACTION_Idle));
		}
	}

	private boolean isTwoLogin(L1PcInstance c) {
		for(L1PcInstance target : L1World.getInstance().getAllPlayersToArray()){
			if(target.noPlayerCK) continue;
			int count = 0;
			if(c.getId() == target.getId()){
				count++;
				if(count > 1){
					c.getNetConnection().kick();
					c.getNetConnection().close();
					target.getNetConnection().kick();
					target.getNetConnection().close();
					return true;
				}
			}
			else if(c.getId() != target.getId()){
				if(c.getAccountName().equalsIgnoreCase(target.getAccountName())) {
					if(!AutoShopManager.getInstance().isAutoShop(target.getId())){
						c.getNetConnection().kick();
						c.getNetConnection().close();
						target.getNetConnection().kick();
						target.getNetConnection().close();
						return true;
					}				
				}
			}
		}
		return false;
	}		

	@Override
	public String getType() {
		return C_SHOP;
	}
}
