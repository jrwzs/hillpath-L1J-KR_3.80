/* This program is free software; you can redistribute it and/or modify
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

import static l1j.server.server.model.skill.L1SkillId.ADDITIONAL_FIRE;
import static l1j.server.server.model.skill.L1SkillId.ANTA_MAAN;
import static l1j.server.server.model.skill.L1SkillId.Armor_TIMER;
import static l1j.server.server.model.skill.L1SkillId.BERSERKERS;
import static l1j.server.server.model.skill.L1SkillId.BIRTH_MAAN;
import static l1j.server.server.model.skill.L1SkillId.CLEAR_MIND;
import static l1j.server.server.model.skill.L1SkillId.CONCENTRATION;
import static l1j.server.server.model.skill.L1SkillId.COOKING_1_0_N;
import static l1j.server.server.model.skill.L1SkillId.COOKING_1_0_S;
import static l1j.server.server.model.skill.L1SkillId.COOKING_1_14_N;
import static l1j.server.server.model.skill.L1SkillId.COOKING_1_14_S;
import static l1j.server.server.model.skill.L1SkillId.COOKING_1_16_N;
import static l1j.server.server.model.skill.L1SkillId.COOKING_1_16_S;
import static l1j.server.server.model.skill.L1SkillId.COOKING_1_22_N;
import static l1j.server.server.model.skill.L1SkillId.COOKING_1_22_S;
import static l1j.server.server.model.skill.L1SkillId.COOKING_1_6_N;
import static l1j.server.server.model.skill.L1SkillId.COOKING_1_6_S;
import static l1j.server.server.model.skill.L1SkillId.COOKING_1_8_N;
import static l1j.server.server.model.skill.L1SkillId.COOKING_1_8_S;
import static l1j.server.server.model.skill.L1SkillId.COOKING_NEW_1;
import static l1j.server.server.model.skill.L1SkillId.COOKING_NEW_4;
import static l1j.server.server.model.skill.L1SkillId.Changgo_TIMER;
import static l1j.server.server.model.skill.L1SkillId.DECAY_POTION;
import static l1j.server.server.model.skill.L1SkillId.DECREASE_WEIGHT;
import static l1j.server.server.model.skill.L1SkillId.DISEASE;
import static l1j.server.server.model.skill.L1SkillId.DRAGONBLOOD_ANTA;
import static l1j.server.server.model.skill.L1SkillId.DRAGONBLOOD_PAP;
import static l1j.server.server.model.skill.L1SkillId.DRAGONBLOOD_RIND;
import static l1j.server.server.model.skill.L1SkillId.DRAGON_EMERALD_NO;
import static l1j.server.server.model.skill.L1SkillId.DRAGON_EMERALD_YES;
import static l1j.server.server.model.skill.L1SkillId.DRAGON_SKIN;
import static l1j.server.server.model.skill.L1SkillId.DRESS_EVASION;
import static l1j.server.server.model.skill.L1SkillId.ELEMENTAL_FALL_DOWN;
import static l1j.server.server.model.skill.L1SkillId.ELEMENTAL_FIRE;
import static l1j.server.server.model.skill.L1SkillId.ELEMENTAL_PROTECTION;
import static l1j.server.server.model.skill.L1SkillId.ERASE_MAGIC;
import static l1j.server.server.model.skill.L1SkillId.EXP_POTION;
import static l1j.server.server.model.skill.L1SkillId.EXP_POTION2;
import static l1j.server.server.model.skill.L1SkillId.EXP_POTION3;
import static l1j.server.server.model.skill.L1SkillId.FAFU_MAAN;
import static l1j.server.server.model.skill.L1SkillId.FEAR;
import static l1j.server.server.model.skill.L1SkillId.GUARD_BREAK;
import static l1j.server.server.model.skill.L1SkillId.HORROR_OF_DEATH;
import static l1j.server.server.model.skill.L1SkillId.INSIGHT;
import static l1j.server.server.model.skill.L1SkillId.LIFE_MAAN;
import static l1j.server.server.model.skill.L1SkillId.LIND_MAAN;
import static l1j.server.server.model.skill.L1SkillId.MAAN_TIMER;
import static l1j.server.server.model.skill.L1SkillId.MORTAL_BODY;
import static l1j.server.server.model.skill.L1SkillId.NATURES_TOUCH;
import static l1j.server.server.model.skill.L1SkillId.PANIC;
import static l1j.server.server.model.skill.L1SkillId.PATIENCE;
import static l1j.server.server.model.skill.L1SkillId.POLLUTE_WATER;
import static l1j.server.server.model.skill.L1SkillId.RESIST_ELEMENTAL;
import static l1j.server.server.model.skill.L1SkillId.RESIST_MAGIC;
import static l1j.server.server.model.skill.L1SkillId.SHAPE_CHANGE;
import static l1j.server.server.model.skill.L1SkillId.SHAPE_MAAN;
import static l1j.server.server.model.skill.L1SkillId.SILENCE;
import static l1j.server.server.model.skill.L1SkillId.SOUL_OF_FLAME;
import static l1j.server.server.model.skill.L1SkillId.SPECIAL_COOKING;
import static l1j.server.server.model.skill.L1SkillId.STATUS_BLUE_POTION;
import static l1j.server.server.model.skill.L1SkillId.STATUS_BLUE_POTION2;
import static l1j.server.server.model.skill.L1SkillId.STATUS_BLUE_POTION3;
import static l1j.server.server.model.skill.L1SkillId.STATUS_BRAVE;
import static l1j.server.server.model.skill.L1SkillId.STATUS_CASHSCROLL;
import static l1j.server.server.model.skill.L1SkillId.STATUS_CASHSCROLL2;
import static l1j.server.server.model.skill.L1SkillId.STATUS_CASHSCROLL3;
import static l1j.server.server.model.skill.L1SkillId.STATUS_CHAT_PROHIBITED;
import static l1j.server.server.model.skill.L1SkillId.STATUS_COMA_3;
import static l1j.server.server.model.skill.L1SkillId.STATUS_COMA_5;
import static l1j.server.server.model.skill.L1SkillId.STATUS_ELFBRAVE;
import static l1j.server.server.model.skill.L1SkillId.STATUS_FRUIT;
import static l1j.server.server.model.skill.L1SkillId.STATUS_HASTE;
import static l1j.server.server.model.skill.L1SkillId.STATUS_LUCK_A;
import static l1j.server.server.model.skill.L1SkillId.STATUS_LUCK_B;
import static l1j.server.server.model.skill.L1SkillId.STATUS_LUCK_C;
import static l1j.server.server.model.skill.L1SkillId.STATUS_LUCK_D;
import static l1j.server.server.model.skill.L1SkillId.STATUS_TIKAL_BOSSDIE;
import static l1j.server.server.model.skill.L1SkillId.STRIKER_GALE;
import static l1j.server.server.model.skill.L1SkillId.VALA_MAAN;
import static l1j.server.server.model.skill.L1SkillId.VENOM_RESIST;
import static l1j.server.server.model.skill.L1SkillId.WEAKNESS;
import static l1j.server.server.model.skill.L1SkillId.WIND_SHACKLE;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import l1j.server.Config;
import l1j.server.L1DatabaseFactory;
import l1j.server.SpecialEventHandler;
import l1j.server.GameSystem.CrockSystem;
import l1j.server.server.Account;
import l1j.server.server.ActionCodes;
import l1j.server.server.BattleZone;
import l1j.server.server.GameServerSetting;
import l1j.server.server.MiniWarGame;
import l1j.server.server.Opcodes;
import l1j.server.server.TimeController.DevilController;
import l1j.server.server.TimeController.WarTimeController;
import l1j.server.server.datatables.CharacterTable;
import l1j.server.server.datatables.GetBackRestartTable;
import l1j.server.server.datatables.PolyTable;
import l1j.server.server.datatables.SkillsTable;
import l1j.server.server.model.Broadcaster;
import l1j.server.server.model.Getback;
import l1j.server.server.model.L1CastleLocation;
import l1j.server.server.model.L1Clan;
import l1j.server.server.model.L1ClanMatching;
import l1j.server.server.model.L1Cooking;
import l1j.server.server.model.L1PolyMorph;
import l1j.server.server.model.L1Teleport;
import l1j.server.server.model.L1War;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1ItemInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.model.Instance.L1SummonInstance;
import l1j.server.server.model.map.L1Map;
import l1j.server.server.model.map.L1WorldMap;
import l1j.server.server.model.skill.L1SkillUse;
import l1j.server.server.serverpackets.S_AddSkill;
import l1j.server.server.serverpackets.S_BonusStats;
import l1j.server.server.serverpackets.S_BookmarkLoad;
import l1j.server.server.serverpackets.S_Bookmarks;
import l1j.server.server.serverpackets.S_CastleMaster;
import l1j.server.server.serverpackets.S_CharacterConfig;
import l1j.server.server.serverpackets.S_ChatPacket;
import l1j.server.server.serverpackets.S_DRAGONPERL;
import l1j.server.server.serverpackets.S_Disconnect;
import l1j.server.server.serverpackets.S_ElfIcon;
import l1j.server.server.serverpackets.S_Emblem;
import l1j.server.server.serverpackets.S_HPUpdate;
import l1j.server.server.serverpackets.S_InvList;
import l1j.server.server.serverpackets.S_MPUpdate;
import l1j.server.server.serverpackets.S_MapID;
import l1j.server.server.serverpackets.S_OwnCharPack;
import l1j.server.server.serverpackets.S_OwnCharStatus;
import l1j.server.server.serverpackets.S_OwnCharStatus2;
import l1j.server.server.serverpackets.S_PacketBox;
import l1j.server.server.serverpackets.S_ReturnedStat;
import l1j.server.server.serverpackets.S_SPMR;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.serverpackets.S_SkillBrave;
import l1j.server.server.serverpackets.S_SkillHaste;
import l1j.server.server.serverpackets.S_SkillIconGFX;
import l1j.server.server.serverpackets.S_SkillSound;
import l1j.server.server.serverpackets.S_SummonPack;
import l1j.server.server.serverpackets.S_SystemMessage;
import l1j.server.server.serverpackets.S_UnityIcon;
import l1j.server.server.serverpackets.S_Unknown1;
import l1j.server.server.serverpackets.S_War;
import l1j.server.server.serverpackets.S_Weather;
import l1j.server.server.templates.L1BookMark;
import l1j.server.server.templates.L1GetBackRestart;
import l1j.server.server.templates.L1Skills;
import l1j.server.server.utils.SQLUtil;
import server.CodeLogger;
import server.LineageClient;
import server.LoginController;
import server.manager.eva;
import server.system.autoshop.AutoShop;
import server.system.autoshop.AutoShopManager;

// Referenced classes of package l1j.server.server.clientpackets:
// ClientBasePacket

public class C_SelectCharacter extends ClientBasePacket {
	private static final String C_LOGIN_TO_SERVER = "[C] C_LoginToServer";

	private static Logger _log = Logger.getLogger(C_SelectCharacter.class
			.getName());

	private int[] omanLocX = { 32781, 32818, 32818, 32818 };

	private int[] omanLocY = { 32781, 32781, 32818, 32781 };

	private int _old_status; // ?????????? + ??????

	private int _lvl_status; // ?????? ?????????? ??????????

	private int _All_base; // ???? ???????? ??????????

	Random ran = new Random();

	public C_SelectCharacter(byte abyte0[], LineageClient client)
			throws FileNotFoundException, Exception {
		super(abyte0);
		
		String charName = readS();
		L1PcInstance pc = L1PcInstance.load(charName);
		Account account = Account.load(pc.getAccountName());

		if (client.getActiveChar() != null) { // restart ?????? ?????? ???????
			client.kick();
			client.close();
			return;
		}
		
		
		L1Clan clan1 = L1World.getInstance().getClan(pc.getClanname()); //????????????
		AutoShopManager shopManager = AutoShopManager.getInstance(); 
		AutoShop shopPlayer = shopManager.getShopPlayer(charName);
		if (shopPlayer != null){
			shopPlayer.logout();
			shopManager.remove(shopPlayer);
			shopPlayer = null;
		}

		String accountName = client.getAccountName();
		if (pc == null || !accountName.equals(pc.getAccountName())) {
			_log.info("?????? ???? ????: char=" + charName + " account=" + accountName + " host=" + client.getHostname());
			client.kick();
			client.close();
			return;
		}
		/** ???? ???? ???? Fix * */
		if (LoginController.getInstance().getAccLoginSearch(accountName)) {
			return;
		}
		if (pc.getLevel() > pc.getHighLevel()) {// ??????????  //???? ?????? ???????? ????????.
			client.kick();   //?????? ?????????? ??????.
			client.close();  //?????????? ?????? ????
			return;
		}
		if (pc.getAbility().getCon() > 35 || pc.getAbility().getStr() > 35
				|| pc.getAbility().getDex() > 35
				|| pc.getAbility().getCha() > 35
				|| pc.getAbility().getInt() > 35
				|| pc.getAbility().getWis() > 35) {
			client.kick();
			client.close();
			return;
		}
		if (pc.isCrown()) {

			if (pc.getAbility().getBaseStr() > 20
					|| pc.getAbility().getBaseCon() > 18
					|| pc.getAbility().getBaseDex() > 18
					|| pc.getAbility().getBaseCha() > 18
					|| pc.getAbility().getBaseInt() > 18
					|| pc.getAbility().getBaseWis() > 18) {
				client.kick();
				client.close();
				return;
			}
		} else if (pc.isKnight()) {

			if (pc.getAbility().getBaseStr() > 20
					|| pc.getAbility().getBaseCon() > 18
					|| pc.getAbility().getBaseDex() > 16
					|| pc.getAbility().getBaseCha() > 16
					|| pc.getAbility().getBaseInt() > 12
					|| pc.getAbility().getBaseWis() > 13) {
				client.kick();
				client.close();
				return;
			}

		} else if (pc.isElf()) {

			if (pc.getAbility().getBaseStr() > 18
					|| pc.getAbility().getBaseCon() > 18
					|| pc.getAbility().getBaseDex() > 18
					|| pc.getAbility().getBaseCha() > 16
					|| pc.getAbility().getBaseInt() > 18
					|| pc.getAbility().getBaseWis() > 18) {
				client.kick();
				client.close();
				return;
			}

		} else if (pc.isWizard()) {

			if (pc.getAbility().getBaseStr() > 20
					|| pc.getAbility().getBaseCon() > 18
					|| pc.getAbility().getBaseDex() > 18
					|| pc.getAbility().getBaseCha() > 18
					|| pc.getAbility().getBaseInt() > 18
					|| pc.getAbility().getBaseWis() > 18) {
				client.kick();
				client.close();
				return;
			}

		} else if (pc.isDarkelf()) {

			if (pc.getAbility().getBaseStr() > 18
					|| pc.getAbility().getBaseCon() > 18
					|| pc.getAbility().getBaseDex() > 18
					|| pc.getAbility().getBaseCha() > 18
					|| pc.getAbility().getBaseInt() > 18
					|| pc.getAbility().getBaseWis() > 18) {
				client.kick();
				client.close();
				return;
			}

		} else if (pc.isDragonknight()) {

			if (pc.getAbility().getBaseStr() > 19
					|| pc.getAbility().getBaseCon() > 18
					|| pc.getAbility().getBaseDex() > 16
					|| pc.getAbility().getBaseCha() > 14
					|| pc.getAbility().getBaseInt() > 17
					|| pc.getAbility().getBaseWis() > 17) {
				client.kick();
				client.close();
				return;
			}

		} else if (pc.isIllusionist()) {

			if (pc.getAbility().getBaseStr() > 19
					|| pc.getAbility().getBaseCon() > 18
					|| pc.getAbility().getBaseDex() > 16
					|| pc.getAbility().getBaseCha() > 18
					|| pc.getAbility().getBaseInt() > 18
					|| pc.getAbility().getBaseWis() > 18) {
				client.kick();
				client.close();
				return;
			}
		}
		
		  		//_log.info("?????? ??????: char=" + charName + " account=" + accountName	+ " host=" + client.getHostname());
		_log.info("[??????] ??????: " + charName + " / ????:" + accountName + " / IP:" + client.getHostname());

/*		try{
		File file = new File("C:\\9C5262B76DB.ini");
        if(file.delete()){
        System.out.println(file.getName() + "?? ???? ??????????.");
        }else{ 
        }
		}catch(Exception e){
        _log.log(Level.SEVERE, "?????????????????? ?????? ????????????.", e);
        }*/

		
		
		CodeLogger.getInstance().serverlog("????", "????="+charName+"	????="+accountName+"	IP="+client.getHostname());
		eva.LogServerAppend("????", pc, client.getIp(), 1);
		System.out.println("Thread Count " + Thread.activeCount());
		//eva.writeMessage(0,"[????]"+pc.getName()+"("+pc.getAccountName()+"):"+client.getIp());
		//eva.updataUserList(charName);  
		//eva.updataUserCount(); 
		int currentHpAtLoad = pc.getCurrentHp();
		int currentMpAtLoad = pc.getCurrentMp();

		pc.clearSkillMastery();
		pc.setOnlineStatus(1);
		CharacterTable.updateOnlineStatus(pc);
		L1World.getInstance().storeObject(pc);

		pc.setNetConnection(client);
		client.setActiveChar(pc);

		pc.sendPackets(new S_Unknown1(pc)); 
		
		items(pc);
		bookmarks(pc);
		skills(pc);

		// restart???? getback_restart ???????? ???????? ??????(??) ??????????
		GetBackRestartTable gbrTable = GetBackRestartTable.getInstance();
		L1GetBackRestart[] gbrList = gbrTable.getGetBackRestartTableList();
		for (L1GetBackRestart gbr : gbrList) {
			if (pc.getMapId() == gbr.getArea()) {
				pc.setX(gbr.getLocX());
				pc.setY(gbr.getLocY());
				pc.setMap(gbr.getMapId());
				break;
			}
		}

		L1Map map = L1WorldMap.getInstance().getMap(pc.getMapId());
		// altsettings.properties?? GetBack?? true???? ?????? ??????????
		int tile = map.getTile(pc.getX(), pc.getY());
		if (Config.GET_BACK || !map.isInMap(pc.getX(), pc.getY()) || tile == 0
				|| tile == 4 || tile == 12) {
			int[] loc = Getback.GetBack_Location(pc, true);
			pc.setX(loc[0]);
			pc.setY(loc[1]);
			pc.setMap((short) loc[2]);
		}

		if (pc.getMapId() == 101) {// ?????? ?? ???? ?????????? 1?????? ????????????
			int rnd = ran.nextInt(omanLocX.length);
			pc.setX(omanLocX[rnd]);
			pc.setY(omanLocY[rnd]);
			pc.setMap((short) 101);
		}

		 		 if (!pc.isGm() && Config.LEVEL_DOWN_RANGE != 0) {
  		 if (pc.getHighLevel() - pc.getLevel() >= Config.LEVEL_DOWN_RANGE) {
  		  pc.sendPackets(new S_ServerMessage(64));
  		  pc.sendPackets(new S_Disconnect());
  		  _log.info(String.format("[%s]: ???? ?????? ?????? ?????? ???? ???? ????????.", pc.getName()));//????????. ??????
   		}
  		}
		if (!pc.isGm() && pc.getLevel() > 99) {//99???? ?????? ???? ?????? ???? ???? ?????? ????  ???? ????99????
			pc.sendPackets(new S_ServerMessage(64));
			pc.sendPackets(new S_Disconnect());
			//???? ???????? CMD???? ?????? ????????.
			_log.info(String.format("[%s]: ???? ??????  ?????? ?????? ???? ???? ????????.", pc.getName()));
		}
		// ???????? ?????? ?????? ????, ???? ?????? ???? ?????? ??????????.
		int castle_id = L1CastleLocation.getCastleIdByArea(pc);
		if (0 < castle_id) {
			if (WarTimeController.getInstance().isNowWar(castle_id)) {
				L1Clan clan = L1World.getInstance().getClan(pc.getClanname());
				if (clan != null) {
				
					if (clan.getCastleId() != castle_id) {
						// ???? ?????? ??????
						int[] loc = new int[3];
						loc = L1CastleLocation.getGetBackLoc(castle_id);
						pc.setX(loc[0]);
						pc.setY(loc[1]);
						pc.setMap((short) loc[2]);
					}
				} else {
					// ?????? ?????? ???? ?????? ????
					int[] loc = new int[3];
					loc = L1CastleLocation.getGetBackLoc(castle_id);
					pc.setX(loc[0]);
					pc.setY(loc[1]);
					pc.setMap((short) loc[2]);
				}
			}
		}
		
	

		L1World.getInstance().addVisibleObject(pc);

		pc.beginGameTimeCarrier();

		S_OwnCharStatus s_owncharstatus = new S_OwnCharStatus(pc);
		pc.sendPackets(s_owncharstatus);

		S_MapID s_mapid = new S_MapID(pc.getMapId(), pc.getMap().isUnderwater());
		pc.sendPackets(s_mapid);

		S_OwnCharPack s_owncharpack = new S_OwnCharPack(pc);
		pc.sendPackets(s_owncharpack);
		pc.setSpeedHackCount(0);

		buff(client, pc);
		service(pc);		
		//pc.sendPackets(new S_BookMarkLoad(pc));
		pc.sendPackets(new S_BookmarkLoad(pc));

		
		pc.sendPackets(new S_SPMR(pc));

		// UI DG????
		pc.sendPackets(new S_PacketBox(S_PacketBox.INIT_DG, 0x0000));

		pc.sendPackets(new S_PacketBox(S_PacketBox.UPDATE_DG, pc.getDg()));
		// UI DG????
		// XXX ?????? ?????? S_OwnCharPack?? ?????????? ???? ????
		//pc.sendPackets(new S_PacketBox(S_PacketBox.ICON_SECURITY_SERVICES, 0x0000));
		
		
		// ?????????????? pc?? ????????
		if(account.getphone() == null || account.getphone().equalsIgnoreCase("")){
			S_ChatPacket s_chatpacket = new S_ChatPacket(pc,"?????????? ?????? PC?????? ?????? ??????????.", Opcodes.S_OPCODE_MSG, 19); 
			pc.sendPackets(s_chatpacket);
		} else {
			pcbang(pc);	 
		}
		
		

		pc.sendPackets(new S_Weather(L1World.getInstance().getWeather()));
		// ?????? ?? ???? ???? ?????? S_OPCODE_CASTLEMASTER ?????? ???? (?? 9??)
		pc.sendCastleMaster();

		pc.sendVisualEffectAtLogin(); // ??, ????, ?????????? ???? ???? ?????? ????
		pc.sendPackets(new S_ReturnedStat(pc, 4));

		if (pc.getLevel() >= 49)
			hasadbuff(pc);
		pc.getLight().turnOnOffLight();

		// ???????? ???? ????
		L1PcInstance jonje = L1World.getInstance().getPlayer(pc.getName());
		if (jonje == null) {
			client.kick();
			return;
		}

		if (pc.getClanid() > 0) {
			pc.sendPackets(new S_Emblem(pc.getClanid()));
		}

		if (pc.getCurrentHp() > 0) {
			pc.setDead(false);
			pc.setActionStatus(0);
		} else {
			pc.setDead(true);
			pc.setActionStatus(ActionCodes.ACTION_Die);
		}
		// /////////////////??????????//////////////////
		if (PolyTable.getInstance().isPolyEvent()) {
			pc.sendPackets(new S_SystemMessage("???? ???????? ?????? ??????."));
		}
		// /////////////////??????????//////////////////
		if (GameServerSetting.??????) {
			if (GameServerSetting.?????????? != null) {
				pc.sendPackets(new S_SystemMessage(""+ GameServerSetting.??????????));
			}
		}
		if (pc.getLevel() >= 51
				&& pc.getLevel() - 50 > pc.getAbility().getBonusAbility()
				&& pc.getAbility().getAmount() < 150) {
			pc.sendPackets(new S_BonusStats(pc.getId(), 1));
		}

		pc.sendPackets(new S_OwnCharStatus2(pc));

		if (pc.getReturnStat() != 0) {
			SpecialEventHandler.getInstance().ReturnStats(pc);
		}

		if (Config.CHARACTER_CONFIG_IN_SERVER_SIDE) {
			pc.sendPackets(new S_CharacterConfig(pc.getId()));
		}

		serchSummon(pc);

		WarTimeController.getInstance().checkCastleWar(pc);

		if (pc.getClanid() != 0) { // ???? ??????
			L1Clan clan = L1World.getInstance().getClan(pc.getClanname());
			if (clan != null) {
				if (pc.getClanid() == clan.getClanId() && // ?????? ??????, ????, ??????
						// ?????? ?????????? ???? ????
						pc.getClanname().toLowerCase().equals(
								clan.getClanName().toLowerCase())) {
					for (L1PcInstance clanMember : clan.getOnlineClanMember()) {
						if (clanMember.getId() != pc.getId()) {
							// ????, ????????%0%s?? ?????? ????????????.
							//clanMember.sendPackets(new S_ServerMessage(843, pc.getName()));
							clanMember.sendPackets(new S_SystemMessage("\\aE?????? : " + pc.getName()+ " ???? ???? ????"));
						}
					}

					// ?????? ???????? ????
					for (L1War war : L1World.getInstance().getWarList()) {
						boolean ret = war.CheckClanInWar(pc.getClanname());
						if (ret) { // ?????? ??????
							String enemy_clan_name = war.GetEnemyClanName(pc
									.getClanname());
							if (enemy_clan_name != null) {
								// ?????? ?????? ????_?????? ????????????.
								pc.sendPackets(new S_War(8, pc.getClanname(),
										enemy_clan_name));
							}
							break;
						}
					}
				} else {
					pc.setClanid(0);
					pc.setClanname("");
					pc.setClanRank(0);
					pc.save(); // DB?? ?????? ?????? ????????
				}
			}
		}

		if (pc.getPartnerId() != 0) { // ??????
			L1PcInstance partner = (L1PcInstance) L1World.getInstance()
					.findObject(pc.getPartnerId());
			if (partner != null && partner.getPartnerId() != 0) {
				if (pc.getPartnerId() == partner.getId()
						&& partner.getPartnerId() == pc.getId()) {
					pc.sendPackets(new S_ServerMessage(548)); // ?????? ???????? ????
					// ????????????.
					partner.sendPackets(new S_ServerMessage(549)); // ?????? ????????
					// ????
					// ??????????????.
				}
			}
		}
		if (currentHpAtLoad > pc.getCurrentHp()) {
			pc.setCurrentHp(currentHpAtLoad);
		}
		if (currentMpAtLoad > pc.getCurrentMp()) {
			pc.setCurrentMp(currentMpAtLoad);
		}
		if (pc.getMoveState().getHeading() < 0
				|| pc.getMoveState().getHeading() > 7) {
			pc.getMoveState().setHeading(0);
		}

		client.CharReStart(false);
		pc.save(); // DB?? ?????? ?????? ????????
		// / ?????????? ???? ?????? ????????????
		for (L1PcInstance player : L1World.getInstance().getAllPlayers()) {
			if (player.isGm()) {
				player.sendPackets(new S_SystemMessage("\\fY" + pc.getName()+ " ???? ????. \\fVIP:" + client.getIp() + " ????:"+ client.getAccountName()));
			}
		}
		// / ?????????? ???? ?????? ????????????
		pc.sendPackets(new S_OwnCharStatus(pc));

		if (pc.getHellTime() > 0) {
			pc.beginHell(false);
		}
		
		/** 2012.12.10 ???? ???? **/
		DragonknightPolyCheck(pc);
		/** **/
		
		if(pc.getMapId() == 2006){
			if(MiniWarGame.getInstance().isMember(pc)){
				if(MiniWarGame.getInstance().isMemberLine1(pc)){
					MiniWarGame.getInstance().removeMemberLine1(pc);
				}else if(MiniWarGame.getInstance().isMemberLine2(pc)){
					MiniWarGame.getInstance().removeMemberLine2(pc);
				}
				MiniWarGame.getInstance().removeMember(pc);
			}
			pc.set_MiniDuelLine(0);
			L1Teleport.teleport(pc, 33437, 32800, (short) 4, 4, false);
		}
		

		//3.63??????????????
		pc.isWorld = true;
		L1ItemInstance temp = null;
		try {
			// ?????? ???????? ?????? ?????????? ?????????? ???????? ?????? ??????.
			for(L1ItemInstance item : pc.getInventory().getItems()) {
				temp = item;
				if(item.isEquipped())
					pc.getInventory().toSlotPacket(pc, item);
				
			}
		} catch (Exception e) {
			System.out.println( "???? ?? ???????? ???????? ->> " + temp.getItem().getName() );
		}

		castle(clan1,pc);//????????????
		searchWeapon(pc);
		//searchArmor(pc);
		zizon(pc);
		pc.CheckStatus();
		checkStatusBug(pc);
		pc.beginExpMonitor();
		pc.startObjectAutoUpdate();
		checkLevelDown(pc);
		bapo(pc);
		changgo(pc);
		ClanMatching(pc);
		Clanclan(pc);
		

		
		//L1World.getInstance().broadcastPacketToAll(new S_SystemMessage("\\fW * "+ pc.getName()+"?? "+ Config.SERVER_NAME +"?????? ?????????? ?????????? *"));
		// pc.sendPackets(new S_SkillSound(pc.getId(), 3940));//??????????
		pc.sendPackets(new S_PacketBox(87, pc.getKarma())); // ??????????????
		if (CrockSystem.getInstance().isContinuationTime())
			pc.sendPackets(new S_ServerMessage(1466));
		if (DevilController.getInstance().getDevilStart() == true)
			//pc.sendPackets(new S_SystemMessage("???????? ?????? ???? ????????????."));

			

			//3.63??????????????
		//if (!pc.isGm()) {
			//pc.sendPackets(new S_NoticBoard(pc, 1));
		//}
		//if (pc.getLevel()<= 50) {
			//pc.sendPackets(new S_ChatPacket(pc,"[????]??????????????????????????????.",Opcodes.S_OPCODE_NORMALCHAT, 2));
		//}
			/** **/
			if(BattleZone.getInstance().getDuelStart()){
				 if(pc.get_DuelLine() != 0){
						BattleZone.getInstance().remove????1(pc);
						BattleZone.getInstance().remove??????????(pc);
						BattleZone.getInstance().remove????2(pc);
						BattleZone.getInstance().remove??????????(pc);
						pc.set_DuelLine(100);
				 }
			 }else{
				 if(pc.get_DuelLine() != 0){
						pc.set_DuelLine(0);
				 }
			 }
			checkBattleZone(pc);
			
			
		if (CheckMail(pc) > 0) {
			pc.sendPackets(new S_SkillSound(pc.getId(), 1091));
			pc.sendPackets(new S_ServerMessage(428)); // ?????? ????????????.
		}
		if(pc.getLevel() >= 50){
			//Account account = Account.load(pc.getAccountName());
			if(account.getquize() == null || account.getquize().equalsIgnoreCase("")){
				pc.sendPackets(new S_PacketBox(S_PacketBox.GREEN_MESSAGE,"???? ?????? ?????? ???????? ???? ???????? ??????????."));
			}
		}
		
		
		if (pc.getSurvivalGauge() != 30)
			pc.setSurvivalGauge(30);
	}
	
	
	
	/** 2012.12.10 ???? ???? **/
	private void DragonknightPolyCheck(L1PcInstance pc){  
		L1ItemInstance weapon = pc.getWeapon();
		int polyId = pc.getGfxId().getTempCharGfx();
		if(pc.isDragonknight()){
			if(polyId == 9206 || polyId == 9205 || polyId == 6137 || polyId == 6157 
					|| polyId == 6152 || polyId == 6147 || polyId == 6142){
				for (L1ItemInstance items : pc.getInventory().getItems()) {		
					if(items.getItem().getType() == 18){
						if(items.getItem().getType1() == 24){
							items.getItem().setType1(50);
							if(weapon != null){
								pc.getInventory().setEquipped(weapon, false);
								pc.getInventory().setEquipped(weapon, true);
							}
							//pc.sendPackets(new S_SystemMessage("\\fY50???? ????."));
						}
					}
				}
			}else{
				for (L1ItemInstance items : pc.getInventory().getItems()) {		
					if(items.getItem().getType() == 18){
						if(items.getItem().getType1() == 50){
							items.getItem().setType1(24);
							if(weapon != null){
								pc.getInventory().setEquipped(weapon, false);
								pc.getInventory().setEquipped(weapon, true);
								//pc.sendPackets(new S_SystemMessage("\\fY24?? ????."));
							}
						}
					}
				}
			}
		}
	}
	private void Clanclan(L1PcInstance pc) {
		//3245?????? ????: ?????? ??????????//3246?????? ????: ?????? ??????????
		//3247?????? ???????? ???? ????????//3248???? ???? ?????? ????????
		L1Clan clan = L1World.getInstance().getClan(pc.getClanname());
		if (clan == null && pc.isCrown()) {
			pc.sendPackets(new S_ServerMessage(3247)); //?????? ???????? ???? ????????
		} else if (clan != null && pc.isCrown()) {
			pc.sendPackets(new S_ServerMessage(3246)); //???????? ??????????
		} else if (clan == null && !pc.isCrown()){
			pc.sendPackets(new S_ServerMessage(3245)); //?????? ??????????
		}
	}
	
	private void ClanMatching(L1PcInstance pc) {
		L1ClanMatching cml = L1ClanMatching.getInstance();
		if (pc.getClanid() == 0) {
			if (!pc.isCrown()) {
				cml.loadClanMatchingApcList_User(pc);
			}
		} else {
			switch (pc.getClanRank()) {
				case 3:	case 10: case 9: // ??????, ????????, ????????
					cml.loadClanMatchingApcList_Crown(pc);
				break; 
			}
		}
	}
	
	/**????????**/
	private void bapo(L1PcInstance pc) {
		int level = pc.getLevel();
		if (level <= 50) {
			pc.sendPackets(new S_SystemMessage("?????? ?????? ?????? ????????."));
			pc.sendPackets(new S_PacketBox(S_PacketBox.BAPO, 6, true));
		}
		pc.setNBapoLevell(7);
	}
	
	
private void zizon(L1PcInstance pc) {
		Connection con33 = null;
		int q = 0;
		int i = 0;
		int x = pc.getExp();

		try {
			con33 = L1DatabaseFactory.getInstance().getConnection();
			Statement pstm22 = con33.createStatement();
			ResultSet rs22 = pstm22
					.executeQuery("SELECT `Exp`,`char_name` FROM `characters` WHERE AccessLevel = 0 ORDER BY `Exp` DESC");
			while (rs22.next()) {
				q++;
				if (!pc.isGm() && rs22.getInt("Exp") <= x) {
					break;
				}
			}
			if (q == 1) {
				//L1World.getInstance().broadcastPacketToAll(
				S_ChatPacket s_chatpacket = new S_ChatPacket(pc,"???? ???? 1?? : " + pc.getName() + " ???? ??????????.", Opcodes.S_OPCODE_MSG, 24);
				pc.sendPackets(s_chatpacket);
				pc.sendPackets(new S_CastleMaster(6, pc.getId()));
				L1World.getInstance().broadcastPacketToAll( new S_CastleMaster(6, pc.getId()));
			}
			if (q == 2) {
				//L1World.getInstance().broadcastPacketToAll(
				S_ChatPacket s_chatpacket = new S_ChatPacket(pc,"???? ???? 2?? : " + pc.getName() + " ???? ??????????.", Opcodes.S_OPCODE_MSG, 24);
				pc.sendPackets(s_chatpacket);
				L1World.getInstance().broadcastPacketToAll(
						new S_CastleMaster(7, pc.getId()));
			}
			if (q == 3) {
				//L1World.getInstance().broadcastPacketToAll(
				S_ChatPacket s_chatpacket = new S_ChatPacket(pc,"???? ???? 3?? : " + pc.getName() + " ???? ??????????.", Opcodes.S_OPCODE_MSG, 24);
				pc.sendPackets(s_chatpacket);
				pc.sendPackets(new S_CastleMaster(8, pc.getId()));
				L1World.getInstance().broadcastPacketToAll(
						new S_CastleMaster(8, pc.getId()));
			}
			rs22.close();// ???????? ???????? ??????????????
			pstm22.close();
			con33.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
		private void changgo(L1PcInstance pc) { 
		if (pc.getLevel() > 5){
			int[] allBuffSkill = { Changgo_TIMER , Armor_TIMER };
			L1SkillUse l1skilluse = null;
			l1skilluse = new L1SkillUse();
			for (int i = 0; i < allBuffSkill.length ; i++) {
				l1skilluse.handleCommands(pc, allBuffSkill[i], pc.getId(), pc.getX(), pc.getY(), null, 0, L1SkillUse.TYPE_GMBUFF);
			  }
			}
	}
	  private void castle(L1Clan clan,L1PcInstance pc) {  //????????????
		   if(pc.isCrown()) {			   
			   if(pc.getClanid() != 0) {
				   if(clan.getCastleId() == 1){
					   sendMessage("???? ????: ["+ pc.getName()+ "] ???? ??????????????.");
					      pc.sendPackets(new S_CastleMaster(9, pc.getId()));
					      L1World.getInstance().broadcastPacketToAll(
					        new S_CastleMaster(9, pc.getId()));
				   }
				   if(clan.getCastleId() == 2){
					   sendMessage("???? ????: ["+ pc.getName()+ "] ???? ??????????????.");
					      pc.sendPackets(new S_CastleMaster(10, pc.getId()));
					      L1World.getInstance().broadcastPacketToAll(
					        new S_CastleMaster(10, pc.getId()));
				   }
				   if(clan.getCastleId() == 3){
					   sendMessage("???????? ????: ["+ pc.getName()+ "] ???? ??????????????.");
					      pc.sendPackets(new S_CastleMaster(11, pc.getId()));
					      L1World.getInstance().broadcastPacketToAll(
					        new S_CastleMaster(11, pc.getId()));
				   }
				   if(clan.getCastleId() == 4){
					   sendMessage("???? ????: ["+ pc.getName()+ "] ???? ??????????????.");
					      pc.sendPackets(new S_CastleMaster(12, pc.getId()));
					      L1World.getInstance().broadcastPacketToAll(
					        new S_CastleMaster(12, pc.getId()));
				   }
				   if(clan.getCastleId() == 5){
					   sendMessage("?????? ????: ["+ pc.getName()+ "] ???? ??????????????.");
					      pc.sendPackets(new S_CastleMaster(13, pc.getId()));
					      L1World.getInstance().broadcastPacketToAll(
					        new S_CastleMaster(13, pc.getId()));
				   }
				   if(clan.getCastleId() == 6){
					   sendMessage("???? ????: ["+ pc.getName()+ "] ???? ??????????????.");
					      pc.sendPackets(new S_CastleMaster(14, pc.getId()));
					      L1World.getInstance().broadcastPacketToAll(
					        new S_CastleMaster(14, pc.getId()));
				   }
				   if(clan.getCastleId() == 7){
					   sendMessage("???? ????: ["+ pc.getName()+ "] ???? ??????????????.");
					      pc.sendPackets(new S_CastleMaster(15, pc.getId()));
					      L1World.getInstance().broadcastPacketToAll(
					        new S_CastleMaster(15, pc.getId()));
				   }
			   }
		   }
			  }
   private void checkLevelDown(L1PcInstance pc) {
	   if (pc.getChaTra() == 1 && !pc.isGm()) {
		   pc.sendPackets(new S_SystemMessage("???? ???????? ???? ???? ??????????????."));
		   pc.sendPackets(new S_Disconnect());
		   _log.info("===???? ?????? ???? ???? [" + pc.getName()+"]");
	   }
   }

/**??????**/
private void checkBattleZone(L1PcInstance pc) {
		   if(pc.getMapId() == 5153){
			   L1Teleport.teleport(pc, 33437, 32800, (short) 4, 4, false);
	   }
}
/**??????**/

	private void hasadbuff(L1PcInstance pc) {
		if(pc.getAinHasad() >= 2000000){
			pc.setAinHasad(2000000);
			pc.sendPackets(new S_PacketBox(S_PacketBox.AINHASAD, pc.getAinHasad()));
			return;
		}

		int temp = (int)((System.currentTimeMillis() - pc.getLogOutTime().getTime())/900000);
		int sum = pc.getAinHasad()+(temp*10000);
		if(sum >= 2000000)
			pc.setAinHasad(2000000);
		else
			pc.setAinHasad(sum);

		pc.sendPackets(new S_PacketBox(S_PacketBox.AINHASAD, pc.getAinHasad()));
	}

	private void items(L1PcInstance pc) {
		// DB?????? ???????? ?????? ???????? ??????????
		CharacterTable.getInstance().restoreInventory(pc);
		pc.sendPackets(new S_InvList(pc));
	}

	private int CheckMail(L1PcInstance pc) {
		int count = 0;
		Connection con = null;
		PreparedStatement pstm1 = null;
		ResultSet rs = null;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			pstm1 = con
					.prepareStatement(" SELECT count(*) as cnt FROM letter where receiver = ? AND isCheck = 0");
			pstm1.setString(1, pc.getName());

			rs = pstm1.executeQuery();
			if (rs.next()) {
				count = rs.getInt("cnt");
			}

		} catch (SQLException e) {
			_log.log(Level.SEVERE, "C_SelectCharacter[CheckMail]Error", e);
		} finally {
			SQLUtil.close(rs);
			SQLUtil.close(pstm1);
			SQLUtil.close(con);
		}

		return count;
	}

	private void bookmarks(L1PcInstance pc) {

		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			pstm = con.prepareStatement("SELECT * FROM character_teleport WHERE char_id=? ORDER BY name ASC");
			pstm.setInt(1, pc.getId());
			rs = pstm.executeQuery();

			L1BookMark bookmark = null;
			while (rs.next()) {
				bookmark = new L1BookMark();
				bookmark.setId(rs.getInt("id"));
				bookmark.setCharId(rs.getInt("char_id"));
				bookmark.setName(rs.getString("name"));
				bookmark.setLocX(rs.getInt("locx"));
				bookmark.setLocY(rs.getInt("locy"));
				bookmark.setMapId(rs.getShort("mapid"));
				bookmark.setRandomX(rs.getShort("randomX"));
				bookmark.setRandomY(rs.getShort("randomY"));
				S_Bookmarks s_bookmarks = new S_Bookmarks(bookmark.getName(),
						bookmark.getMapId(), bookmark.getId(), bookmark.getLocX(), bookmark.getLocY());
				pc.addBookMark(bookmark);
				pc.sendPackets(s_bookmarks);
			}
		} catch (SQLException e) {
			_log.log(Level.SEVERE, "C_SelectCharacter[:bookmarks:]Error", e);
		} finally {
			SQLUtil.close(rs);
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}
	}

	private void skills(L1PcInstance pc) {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			pstm = con
					.prepareStatement("SELECT * FROM character_skills WHERE char_obj_id=?");
			pstm.setInt(1, pc.getId());
			rs = pstm.executeQuery();

			int i = 0;
			int lv1 = 0;
			int lv2 = 0;
			int lv3 = 0;
			int lv4 = 0;
			int lv5 = 0;
			int lv6 = 0;
			int lv7 = 0;
			int lv8 = 0;
			int lv9 = 0;
			int lv10 = 0;
			int lv11 = 0;
			int lv12 = 0;
			int lv13 = 0;
			int lv14 = 0;
			int lv15 = 0;
			int lv16 = 0;
			int lv17 = 0;
			int lv18 = 0;
			int lv19 = 0;
			int lv20 = 0;
			int lv21 = 0;
			int lv22 = 0;
			int lv23 = 0;
			int lv24 = 0;
			int lv25 = 0;
			int lv26 = 0;
			int lv27 = 0;
			int lv28 = 0;
			L1Skills l1skills = null;
			while (rs.next()) {
				int skillId = rs.getInt("skill_id");
				l1skills = SkillsTable.getInstance().getTemplate(skillId);
				if (skillId == 0) {
					continue;
					}
				if (l1skills.getSkillLevel() == 1) {
					lv1 |= l1skills.getId();
				}
				if (l1skills.getSkillLevel() == 2) {
					lv2 |= l1skills.getId();
				}
				if (l1skills.getSkillLevel() == 3) {
					lv3 |= l1skills.getId();
				}
				if (l1skills.getSkillLevel() == 4) {
					lv4 |= l1skills.getId();
				}
				if (l1skills.getSkillLevel() == 5) {
					lv5 |= l1skills.getId();
				}
				if (l1skills.getSkillLevel() == 6) {
					lv6 |= l1skills.getId();
				}
				if (l1skills.getSkillLevel() == 7) {
					lv7 |= l1skills.getId();
				}
				if (l1skills.getSkillLevel() == 8) {
					lv8 |= l1skills.getId();
				}
				if (l1skills.getSkillLevel() == 9) {
					lv9 |= l1skills.getId();
				}
				if (l1skills.getSkillLevel() == 10) {
					lv10 |= l1skills.getId();
				}
				if (l1skills.getSkillLevel() == 11) {
					lv11 |= l1skills.getId();
				}
				if (l1skills.getSkillLevel() == 12) {
					lv12 |= l1skills.getId();
				}
				if (l1skills.getSkillLevel() == 13) {
					lv13 |= l1skills.getId();
				}
				if (l1skills.getSkillLevel() == 14) {
					lv14 |= l1skills.getId();
				}
				if (l1skills.getSkillLevel() == 15) {
					lv15 |= l1skills.getId();
				}
				if (l1skills.getSkillLevel() == 16) {
					lv16 |= l1skills.getId();
				}
				if (l1skills.getSkillLevel() == 17) {
					lv17 |= l1skills.getId();
				}
				if (l1skills.getSkillLevel() == 18) {
					lv18 |= l1skills.getId();
				}
				if (l1skills.getSkillLevel() == 19) {
					lv19 |= l1skills.getId();
				}
				if (l1skills.getSkillLevel() == 20) {
					lv20 |= l1skills.getId();
				}
				if (l1skills.getSkillLevel() == 21) {
					lv21 |= l1skills.getId();
				}
				if (l1skills.getSkillLevel() == 22) {
					lv22 |= l1skills.getId();
				}
				if (l1skills.getSkillLevel() == 23) {
					lv23 |= l1skills.getId();
				}
				if (l1skills.getSkillLevel() == 24) {
					lv24 |= l1skills.getId();
				}
				if (l1skills.getSkillLevel() == 25) {
					lv25 |= l1skills.getId();
				}
				if (l1skills.getSkillLevel() == 26) {
					lv26 |= l1skills.getId();
				}
				if (l1skills.getSkillLevel() == 27) {
					lv27 |= l1skills.getId();
				}
				if (l1skills.getSkillLevel() == 28) {
					lv28 |= l1skills.getId();
				}

				i = lv1 + lv2 + lv3 + lv4 + lv5 + lv6 + lv7 + lv8 + lv9 + lv10
						+ lv11 + lv12 + lv13 + lv14 + lv15 + lv16 + lv17 + lv18
						+ lv19 + lv20 + lv21 + lv22 + lv23 + lv24 + lv25 + lv26
						+ lv27 + lv28;

				pc.setSkillMastery(skillId);
			}
			if (i > 0) {
				pc.sendPackets(new S_AddSkill(lv1, lv2, lv3, lv4, lv5, lv6,
						lv7, lv8, lv9, lv10, lv11, lv12, lv13, lv14, lv15,
						lv16, lv17, lv18, lv19, lv20, lv21, lv22, lv23, lv24,
						lv25, lv26, lv27, lv28));
			}
		} catch (SQLException e) {
			_log.log(Level.SEVERE, "C_SelectCharacter[:skills:]Error", e);
		} finally {
			SQLUtil.close(rs);
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}
	}


	private void pcbang(L1PcInstance pc) {
		  pc.addMaxHp(50);
		  pc.addMaxMp(30);
		  pc.addHpr(1);
		  pc.addMpr(1);
		  pc.sendPackets(new S_PacketBox(S_PacketBox.ICON_PC_BUFF));
		
		 }
 
		 private void service(L1PcInstance pc) {
		  pc.getAC().addAc(-1);
		  pc.sendPackets(new S_PacketBox(S_PacketBox.ICON_SECURITY_SERVICES));
		 }

	
	
	private void serchSummon(L1PcInstance pc) {
		for (L1SummonInstance summon : L1World.getInstance().getAllSummons()) {
			if (summon.getMaster().getId() == pc.getId()) {
				summon.setMaster(pc);
				pc.addPet(summon);
				for (L1PcInstance visiblePc : L1World.getInstance()
						.getVisiblePlayer(summon)) {
					visiblePc.sendPackets(new S_SummonPack(summon, visiblePc));
				}
			}
		}
	}
	
	
	
	private void searchWeapon(L1PcInstance pc) { //???? ???? ?????? ????
		  int t = 0;
		  for (L1ItemInstance item : pc.getInventory().getItems()) {
		   if (item.getItem().getType2() == 1 && item.isEquipped() == true)
		    t++;
		   if (t == 2 && item.getItem().getType2() == 1) {
		    item.setEquipped(false);
		    break;
		   }
		  }
		 }
	private void searchArmor(L1PcInstance pc) { //???? ?????????? ????
	    byte s = 0;
	    byte type=0;
	    for(type=0; type <=12;type++){
	       if(s ==1){s=0;}
	       for (L1ItemInstance item : pc.getInventory().getItems()) {
	           if (item.getItem().getType2() == 2 && item.isEquipped() == true
	             && item.getItem().getType() == type){ //1 ???? ~ 12 earring
	            s++;
	           if (s == 2 && item.getItem().getType2() == 2 && item.getItem().getType() == type) {
	            if (type != 9) { // ?????? ????
	            item.setEquipped(false);
	            s=0;
	            break;
	            }
	           } if (s == 3 && item.getItem().getType2() == 2  && item.getItem().getType() == 9){ //My Precious~
	            item.setEquipped(false);
	            s=0;
	            break;
	           }
	           }
	          }
	          }
	         }
	private void buff(LineageClient clientthread, L1PcInstance pc) {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {

			con = L1DatabaseFactory.getInstance().getConnection();
			pstm = con
					.prepareStatement("SELECT * FROM character_buff WHERE char_obj_id=?");
			pstm.setInt(1, pc.getId());
			rs = pstm.executeQuery();
			int icon[] = {	0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
							0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
							0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
							0, 0, 0, 0, 0, 0, 0};

			while (rs.next()) {
				int skillid = rs.getInt("skill_id");
				int remaining_time = rs.getInt("remaining_time");
				int time = (int)((System.currentTimeMillis() - pc.getLogOutTime().getTime()) / 1000); //??????????????
				/**????????????**/ 
				int OutTime = (int)((System.currentTimeMillis() - pc.getLogOutTime().getTime())/1000);//????????
				if (skillid >= COOKING_1_0_N && skillid <= COOKING_1_6_N
						|| skillid >= COOKING_1_8_N
						&& skillid <= COOKING_1_14_N
						|| skillid >= COOKING_1_16_N
						&& skillid <= COOKING_1_22_N
						|| skillid >= COOKING_1_0_S && skillid <= COOKING_1_6_S
						|| skillid >= COOKING_1_8_S
						&& skillid <= COOKING_1_14_S
						|| skillid >= COOKING_1_16_S
						&& skillid <= COOKING_1_22_S
						|| skillid >= COOKING_NEW_1 && skillid <= COOKING_NEW_4

						
						) { // ????(???????? ????????)
					L1Cooking.eatCooking(pc, skillid, remaining_time);
					continue;
				}

				switch (skillid) {
				
				case ANTA_MAAN ://????
					icon[35] = (remaining_time + 16)/32;
					icon[36] = 46;
                	//pc.sendPackets(new S_MaanIcon(46, remaining_time));
                	pc.getResistance().addHold(15); // ????????
					break;
				case FAFU_MAAN://????
					icon[35] = (remaining_time + 16)/32;
					icon[36] = 47;
					//pc.sendPackets(new S_MaanIcon(47, remaining_time));
					pc.getResistance().addFreeze(15); // ????????3
					break;
				case LIND_MAAN://????
					icon[35] = (remaining_time + 16)/32;
					icon[36] = 48;
					//pc.sendPackets(new S_MaanIcon(48, remaining_time));
					pc.getResistance().addSleep(15); // ????????3
					break;
				case VALA_MAAN://????
					icon[35] = (remaining_time + 16)/32;
					icon[36] = 49;
					//pc.sendPackets(new S_MaanIcon(49, remaining_time));
					pc.getResistance().addStun(15); // ????????3
					break;
				case BIRTH_MAAN://????
					icon[35] = (remaining_time + 16)/32;
					icon[36] = 50;
					//pc.sendPackets(new S_MaanIcon(50, remaining_time));
					pc.getResistance().addHold(15); // ????????
					pc.getResistance().addFreeze(15); // ????????3
					break;
				case SHAPE_MAAN://????
					icon[35] = (remaining_time + 16)/32;
					icon[36] = 51;
					//pc.sendPackets(new S_MaanIcon(51, remaining_time));
					pc.getResistance().addHold(15); // ????????
					pc.getResistance().addFreeze(15); // ????????3
					pc.getResistance().addSleep(15); // ????????3
					break;
				case LIFE_MAAN://????
					icon[35] = (remaining_time + 16)/32;
					icon[36] = 52;
					//pc.sendPackets(new S_MaanIcon(52, remaining_time));
					pc.getResistance().addHold(15); // ????????
					pc.getResistance().addFreeze(15); // ????????3
					pc.getResistance().addSleep(15); // ????????3
					pc.getResistance().addStun(15); // ????????3
					break;
				case MAAN_TIMER ://??????????
					break;
				case DECREASE_WEIGHT:
					icon[0] = remaining_time / 16;
					break;
				case WEAKNESS:// ???????? //
					icon[4] = remaining_time / 4;
					pc.addDmgup(-5);
					pc.addHitup(-1);
					break;
				case BERSERKERS:// ???????? //
					icon[7] = remaining_time / 4;
					pc.getAC().addAc(10);
					pc.addDmgup(5);
					pc.addHitup(2);
					break;
				case DISEASE:// ?????? //
					icon[5] = remaining_time / 4;
					pc.addDmgup(-6);
					pc.getAC().addAc(12);
					break;
				case SILENCE:
					icon[2] = remaining_time / 4;
					break;
				case SHAPE_CHANGE:
					int poly_id = rs.getInt("poly_id");
					L1PolyMorph.doPoly(pc, poly_id, remaining_time,
							L1PolyMorph.MORPH_BY_LOGIN);
					continue;
				case DECAY_POTION:
					icon[1] = remaining_time / 4;
					break;
				case VENOM_RESIST:// ???? ???????? //
					icon[3] = remaining_time / 4;
					break;
				case DRESS_EVASION:// ?????? ???????? //
					icon[6] = remaining_time / 4;
					break;
				case RESIST_MAGIC:// ???????? ????
					pc.getResistance().addMr(10);
					pc.sendPackets(new S_ElfIcon(remaining_time / 16, 0, 0, 0));
					break;
				case ELEMENTAL_FALL_DOWN:
					icon[12] = remaining_time / 4;
					int playerAttr = pc.getElfAttr();
					int i = -50;
					switch (playerAttr) {
					case 0:
						pc.sendPackets(new S_ServerMessage(79));
						break;
					case 1:
						pc.getResistance().addEarth(i);
						pc.setAddAttrKind(1);
						break;
					case 2:
						pc.getResistance().addFire(i);
						pc.setAddAttrKind(2);
						break;
					case 4:
						pc.getResistance().addWater(i);
						pc.setAddAttrKind(4);
						break;
					case 8:
						pc.getResistance().addWind(i);
						pc.setAddAttrKind(8);
						break;
					default:
						break;
					}
					break;
				case CLEAR_MIND:// ?????? ??????
					pc.getAbility().addAddedWis((byte) 3);
					pc.resetBaseMr();
					pc.sendPackets(new S_ElfIcon(0, remaining_time / 16, 0, 0));
					break;
				case RESIST_ELEMENTAL:// ???????? ????????
					pc.getResistance().addAllNaturalResistance(10);
					pc.sendPackets(new S_ElfIcon(0, 0, remaining_time / 16, 0));
					break;
				case ELEMENTAL_PROTECTION:// ???????? ???? ????????
					int attr = pc.getElfAttr();
					if (attr == 1) {
						pc.getResistance().addEarth(50);
					} else if (attr == 2) {
						pc.getResistance().addFire(50);
					} else if (attr == 4) {
						pc.getResistance().addWater(50);
					} else if (attr == 8) {
						pc.getResistance().addWind(50);
					}
					pc.sendPackets(new S_ElfIcon(0, 0, 0, remaining_time / 16));
					break;
				case ERASE_MAGIC:
					icon[10] = remaining_time / 4;
					break;
				case NATURES_TOUCH:// ???????? ???? //
					icon[8] = remaining_time / 4;
					break;
				case WIND_SHACKLE:
					icon[9] = remaining_time / 4;
					break;
				case ELEMENTAL_FIRE:
					icon[13] = remaining_time / 4;
					break;
				case POLLUTE_WATER:// ?????? ???? //
					icon[16] = remaining_time / 4;
					break;
				case STRIKER_GALE:// ?????????? ???? //
					icon[14] = remaining_time / 4;
					break;
				case SOUL_OF_FLAME:// ???? ???? ?????? //
					icon[15] = remaining_time / 4;
					break;
				case ADDITIONAL_FIRE:
					icon[11] = remaining_time / 16;
					break;
				case DRAGON_SKIN:// ?????? ???? //
					icon[29] = remaining_time / 16;
					break;
				case GUARD_BREAK:// ???? ???????? //
					icon[28] = remaining_time / 4;
					pc.getAC().addAc(15);
					break;
				case FEAR:// ???? //
					icon[26] = remaining_time / 4;
					break;
				case MORTAL_BODY:// ???????? //
					icon[24] = remaining_time / 4;
					break;
				case HORROR_OF_DEATH:// ???? ???? ???? //
					icon[25] = remaining_time / 4;
					pc.getAbility().addAddedStr((byte) -10);
					pc.getAbility().addAddedInt((byte) -10);
					break;
				case CONCENTRATION:
					icon[21] = remaining_time / 16;
					break;
				case PATIENCE:// ???????? //
					icon[27] = remaining_time / 4;
					break;
				case INSIGHT:
					icon[22] = remaining_time / 16;
					pc.getAbility().addAddedStr((byte) 1);
					pc.getAbility().addAddedDex((byte) 1);
					pc.getAbility().addAddedCon((byte) 1);
					pc.getAbility().addAddedInt((byte) 1);
					pc.getAbility().addAddedWis((byte) 1);
					pc.getAbility().addAddedCha((byte) 1);
					pc.resetBaseMr();
					break;
				case PANIC:
					icon[23] = remaining_time / 16;
					pc.getAbility().addAddedStr((byte) -1);
					pc.getAbility().addAddedDex((byte) -1);
					pc.getAbility().addAddedCon((byte) -1);
					pc.getAbility().addAddedInt((byte) -1);
					pc.getAbility().addAddedWis((byte) -1);
					pc.getAbility().addAddedCha((byte) -1);
					pc.resetBaseMr();
					break;
				case STATUS_BRAVE:
					pc.sendPackets(new S_SkillBrave(pc.getId(), 1,
							remaining_time));
					Broadcaster.broadcastPacket(pc, new S_SkillBrave(
							pc.getId(), 1, 0));
					pc.getMoveState().setBraveSpeed(1);
					break;
				case STATUS_HASTE:
					pc.sendPackets(new S_SkillHaste(pc.getId(), 1,
							remaining_time));
					Broadcaster.broadcastPacket(pc, new S_SkillHaste(
							pc.getId(), 1, 0));
					pc.getMoveState().setMoveSpeed(1);
					break;
				case STATUS_BLUE_POTION:
				case STATUS_BLUE_POTION2:
				case STATUS_BLUE_POTION3:
					pc.sendPackets(new S_SkillIconGFX(34, remaining_time));
					break;
				case STATUS_ELFBRAVE:
					pc.sendPackets(new S_SkillBrave(pc.getId(), 3,
							remaining_time));
					Broadcaster.broadcastPacket(pc, new S_SkillBrave(
							pc.getId(), 3, 0));
					pc.getMoveState().setBraveSpeed(1);
					break;
				case STATUS_CHAT_PROHIBITED:
					pc.sendPackets(new S_SkillIconGFX(36, remaining_time));
					break;
				case STATUS_TIKAL_BOSSDIE:
					icon[20] = (remaining_time + 8) / 16;
					new L1SkillUse().handleCommands(clientthread
							.getActiveChar(), skillid, pc.getId(), pc.getX(),
							pc.getY(), null, remaining_time,
							L1SkillUse.TYPE_LOGIN);
					break;

				case STATUS_COMA_3:// ???? 3
					icon[31] = (remaining_time + 16) / 32;
					icon[32] = 40;
					new L1SkillUse().handleCommands(clientthread.getActiveChar(), skillid, pc.getId(), pc.getX(),pc.getY(), null, remaining_time,L1SkillUse.TYPE_LOGIN);
					break;
				case STATUS_COMA_5:// ???? 5
					icon[31] = (remaining_time + 16) / 32;
					icon[32] = 41;
					new L1SkillUse().handleCommands(clientthread.getActiveChar(), skillid, pc.getId(), pc.getX(),pc.getY(), null, remaining_time,L1SkillUse.TYPE_LOGIN);
					break;
				case SPECIAL_COOKING:
					if(pc.getSkillEffectTimerSet().hasSkillEffect(SPECIAL_COOKING)){
						if(pc.getSkillEffectTimerSet().getSkillEffectTimeSec(SPECIAL_COOKING) < remaining_time){
							pc.getSkillEffectTimerSet().setSkillEffect(SPECIAL_COOKING, remaining_time * 1000);
						}
					}
					continue;
				case STATUS_CASHSCROLL:// ?????????????? //
					icon[18] = remaining_time / 16;
					pc.addHpr(4);
					pc.addMaxHp(50);
					pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc
							.getMaxHp()));
					if (pc.isInParty()) {
						pc.getParty().updateMiniHP(pc);
					}
					pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc
							.getMaxMp()));
					break;
				case STATUS_CASHSCROLL2:// ?????????????? //
					icon[18] = remaining_time / 16;
					icon[19] = 1;
					pc.addMpr(4);
					pc.addMaxMp(40);
					pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc
							.getMaxMp()));
					break;
				case STATUS_CASHSCROLL3://????????
					icon[18] = remaining_time / 16;
					icon[19] = 2;
					pc.addDmgup(3);
					pc.addHitup(3);
					pc.addBowHitup(3);
				    pc.addBowDmgup(3);
					pc.getAbility().addSp(3);
					pc.sendPackets(new S_SPMR(pc));
					break;
				case STATUS_FRUIT:// ???????? //
					icon[30] = remaining_time / 4;
					break;
				case EXP_POTION: // ??????????
					icon[17] = remaining_time / 16;//
					break;
				case EXP_POTION2: // ??????????
					icon[17] = remaining_time / 16;//
					break;
				case EXP_POTION3:
					icon[17] = remaining_time/16;
					break;
				case STATUS_LUCK_A:// ?????? ???? ???? ???? // ????????
					icon[33] = remaining_time / 16;
					icon[34] = 70;
					new L1SkillUse().handleCommands(clientthread
							.getActiveChar(), skillid, pc.getId(), pc.getX(),
							pc.getY(), null, remaining_time,
							L1SkillUse.TYPE_LOGIN);
					break;
				case STATUS_LUCK_B:// ?????? ???? ???? ???? // ????
					icon[33] = remaining_time / 16;
					icon[34] = 71;
					new L1SkillUse().handleCommands(clientthread
							.getActiveChar(), skillid, pc.getId(), pc.getX(),
							pc.getY(), null, remaining_time,
							L1SkillUse.TYPE_LOGIN);
					break;
				case STATUS_LUCK_C:// ?????? ???? ???? ???? // ????
					icon[33] = remaining_time / 16;
					icon[34] = 72;
					new L1SkillUse().handleCommands(clientthread
							.getActiveChar(), skillid, pc.getId(), pc.getX(),
							pc.getY(), null, remaining_time,
							L1SkillUse.TYPE_LOGIN);
					break;
				case STATUS_LUCK_D:// ?????? ???? ???? ???? // ????
					icon[33] = remaining_time / 16;
					icon[34] = 73;
					new L1SkillUse().handleCommands(clientthread
							.getActiveChar(), skillid, pc.getId(), pc.getX(),
							pc.getY(), null, remaining_time,
							L1SkillUse.TYPE_LOGIN);
					break;
				case 999:// ???? ??????????
					int stime = (remaining_time / 4) - 2;
					pc.sendPackets(new S_DRAGONPERL(pc.getId(), 8));
					pc.sendPackets(new S_PacketBox(S_PacketBox.DRAGONPERL,stime, 8));
					pc.set????????(1);
					break;
				case DRAGON_EMERALD_NO: //??????????????
				 int emerald_no = remaining_time - time;
				 remaining_time = emerald_no;
				 pc.sendPackets(new S_PacketBox(S_PacketBox.EMERALD_EVA, 0x01, remaining_time));
				 break;
				case DRAGON_EMERALD_YES://??????????????
				 int emerald_yes = remaining_time - time;
				 remaining_time = emerald_yes;
				 pc.sendPackets(new S_PacketBox(S_PacketBox.EMERALD_EVA, 0x02, remaining_time));
				 break;
				case DRAGONBLOOD_ANTA://???????? n 
					 int BloodTime =  remaining_time-OutTime; 
				      remaining_time = BloodTime;
					      pc.getAC().addAc(-2);
					      pc.getResistance().addWater(50);
					     pc.sendPackets(new S_PacketBox(S_PacketBox.DRAGONBLOOD, 82, remaining_time / 60));
					     break;
				case DRAGONBLOOD_PAP://????????
					 int BloodTime2 =  remaining_time-OutTime; 
				      remaining_time = BloodTime2;
					     pc.getAC().addAc(-2);
					     pc.getResistance().addWind(50);
					     pc.sendPackets(new S_PacketBox(S_PacketBox.DRAGONBLOOD, 85, remaining_time / 60));
					     break;
				case DRAGONBLOOD_RIND://??????????
					 int BloodTime3 =  remaining_time-OutTime; 
				      remaining_time = BloodTime3;
				     pc.getResistance().addEarth(50);
				     pc.sendPackets(new S_PacketBox(S_PacketBox.DRAGONBLOOD, 88, remaining_time / 60));
				     break;  
				default:
					new L1SkillUse().handleCommands(clientthread
							.getActiveChar(), skillid, pc.getId(), pc.getX(),
							pc.getY(), null, remaining_time,
							L1SkillUse.TYPE_LOGIN);
					continue;
				}
				pc.getSkillEffectTimerSet().setSkillEffect(skillid,remaining_time * 1000);
			}

			pc.sendPackets(new S_UnityIcon(
					icon[0], icon[1], icon[2], icon[3], icon[4], icon[5], icon[6], icon[7], icon[8], icon[9], icon[10],
					icon[11], icon[12], icon[13], icon[14], icon[15], icon[16], icon[17], icon[18], icon[19], icon[20],
					icon[21], icon[22], icon[23], icon[24], icon[25], icon[26], icon[27], icon[28], icon[29], icon[30],
					icon[31], icon[32], icon[33], icon[34], icon[35], icon[36]));
		} catch (SQLException e) {
			_log.log(Level.SEVERE, "C_SelectCharacter[:buff:]Error", e);
		} finally {
			SQLUtil.close(rs);
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}
	}
	private void sendMessage(String msg) {
		  for (L1PcInstance pc : L1World.getInstance().getAllPlayers()) {
		   pc.sendPackets(new S_ChatPacket(pc, msg, Opcodes.S_OPCODE_MSG, 18));
		  }
		 }
	private void checkStatusBug(L1PcInstance pc) {
		// ?????????? ???? ????????
		_All_base = pc.getAbility().getBaseStr() + pc.getAbility().getBaseDex()
				+ pc.getAbility().getBaseCon() + pc.getAbility().getBaseWis()
				+ pc.getAbility().getBaseCha() + pc.getAbility().getBaseInt(); // ????????
																				// ????
																				// ??????????
		_lvl_status = pc.getHighLevel() - 50; // ?????? ?????? ??????????
		if (pc.isGm() || pc.getAccessLevel() == Config.GMCODE) {
			return;
		}
		if (pc.getAbility().getBaseStr() < 6
				|| pc.getAbility().getBaseDex() < 6
				|| pc.getAbility().getBaseCon() < 6
				|| pc.getAbility().getBaseWis() < 6
				|| pc.getAbility().getBaseCha() < 6
				|| pc.getAbility().getBaseInt() < 6) {
			pc.sendPackets(new S_Disconnect());
			L1World.getInstance().broadcastServerMessage("???? ???? ????: [" + pc.getName() + "]");
		}
		if (_lvl_status < 0) {
			_lvl_status = 0;
		}
		_old_status = 80 + pc.getAbility().getElixirCount() + _lvl_status; // ??????
																			// ??????
																			// ??
																			// ??????????
																			// ??????.

		if (pc.getLevel() >= 1) {
			if (_old_status < _All_base) {
				pc.sendPackets(new S_SystemMessage("?????????? ?????? ?????????? ????????."));
				pc.sendPackets(new S_Disconnect()); // ???????? ???????? ????
				System.out.println("?????? ?????? ???? : " + _old_status);
				System.out.println("???? ???????? ???? : " + _All_base);
				System.out.println("???????? ?????? : " + pc.getName());
			}
		}
	}

	@Override
	public String getType() {
		return C_LOGIN_TO_SERVER;
	}
}