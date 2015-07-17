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
package l1j.server.server.serverpackets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.TimeZone;

import javolution.util.FastTable;
import l1j.server.Config;
import l1j.server.L1DatabaseFactory;
import l1j.server.server.Account;
import l1j.server.server.Opcodes;
import l1j.server.server.model.L1Clan;
import l1j.server.server.model.L1Clan.ClanMember;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1ItemInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.utils.SQLUtil;

/**
 * ��ų �������̳� ���� ����Ʈ�� ǥ�� �� ������ �뵵�� ���Ǵ� ��Ŷ�� Ŭ����
 */
public class S_PacketBox extends ServerBasePacket {
	private static final String S_PACKETBOX = "[S] S_PacketBox";

	private byte[] _byte = null;

	// *** S_107 sub code list ***
	public static final int UPDATE_OLD_PART_MEMBER = 104;

	/** 3.3C */
	public static final int PATRY_UPDATE_MEMBER = 105;

	/** 3.3C  */
	public static final int PATRY_SET_MASTER = 106;

	/** 3.3C  */
	public static final int PATRY_MEMBERS = 110;
	
	public static final int UNLIMITED_ICON = 147;
	
	/** ���� ��� */
	 public static final int  PLAYSOUND= 73; 

	/** ���͸���Ʈ */
	//public static final int CLAN_LIST = 119;
    public static final int ER_UpDate = 132;
	
	public static final int BOOKMARK_SIZE_PLUS_10 = 141;// ��� Ȯ��
	
	/** �����ð�ǥ��*/
	public static final int MAP_TIMER = 153;  //�����ð�ǥ��
	public static final int MAP_TIMER_OUT = 159;
	
	/** ���ȹ��� */
	public static final int ICON_SECURITY_SERVICES = 125;
	
	/** ���� â������Ʈ */
	public static final int CLAN_WAREHOUSE_LIST = 117;// 

	/** pc�� ������*/
	public static final int ICON_PC_BUFF = 127; 

	// 1:Kent 2:Orc 3:WW 4:Giran 5:Heine 6:Dwarf 7:Aden 8:Diad 9:���� 9 ...
	/** C(id) H(?): %s�� �������� ���۵Ǿ����ϴ�. */
	public static final int MSG_WAR_BEGIN = 0;

	/** C(id) H(?): %s�� �������� �����߽��ϴ�. */
	public static final int MSG_WAR_END = 1;

	/** C(id) H(?): %s�� �������� �������Դϴ�. */
	public static final int MSG_WAR_GOING = 2;

	/** -: ���� �ֵ����� ��ҽ��ϴ�. (������ �ٲ��) */
	public static final int MSG_WAR_INITIATIVE = 3;

	/** -: ���� �����߽��ϴ�. */
	public static final int MSG_WAR_OCCUPY = 4;

	/** ?: ������ �������ϴ�. (������ �ٲ��) */
	public static final int MSG_DUEL = 5;

	/** C(count): SMS�� �۽ſ� �����߽��ϴ�. / ����%d�Ǽ۽ŵǾ����ϴ�. */
	public static final int MSG_SMS_SENT = 6;

	/** -: �ູ��, 2���� �κημ� ����Ǿ����ϴ�. (������ �ٲ��) */
	public static final int MSG_MARRIED = 9;

	/** C(weight): �߷�(30 �ܰ�) */
	public static final int WEIGHT = 10;

	/** C(food): ������(30 �ܰ�) */
	public static final int FOOD = 11;

	/** C(0) C(level): �� ��������%d���� ���ϸ� ����� �� �ֽ��ϴ�. (0~49�ܴ̿� ǥ�õ��� �ʴ´�) */
	public static final int MSG_LEVEL_OVER = 12;

	/** UB���� HTML */
	public static final int HTML_UB = 14;

	/**
	 * C(id)<br>
	 * 1:���� ����� �ִ� ������ ���� ����ȿ� ��� ���� ���� �������ϴ�.<br>
	 * 2:���� ���������� ȭ�� ���ɷ��� ������ �ɴϴ�.<br>
	 * 3:���� ���������� ���� ���ɷ��� ������ �ɴϴ�.<br>
	 * 4:���� ���������� �ٶ��� ���ɷ��� ������ �ɴϴ�.<br>
	 * 5:���� ���������� ���� ���ɷ��� ������ �ɴϴ�.<br>
	 */
	public static final int MSG_ELF = 15;

	/** C(count) S(name)...: ���� ����Ʈ ���� �߰� */
	public static final int ADD_EXCLUDE2 = 17;

	/** S(name): ���� ����Ʈ �߰� */
	public static final int ADD_EXCLUDE = 18;

	/** S(name): ���� ���� */
	public static final int REM_EXCLUDE = 19;

	/** ��ų ������ */
	public static final int ICONS1 = 20;

	/** ��ų ������ */
	public static final int ICONS2 = 21;

	/** �ƿ����� ��ų ������ �� �̷�������� ������ ���� */
	public static final int ICON_AURA = 22;

	/** S(name): Ÿ�� ��������%s�� ���õǾ����ϴ�. */
	public static final int MSG_TOWN_LEADER = 23;

	/**
	 * D(���Ϳ���) (S(�����̸�) C(�������)) ���Ϳ� ������ �� ���¿����� /����.
	 */
	public static final int PLEDGE_TWO = 24;

	/**
	 * D(���Ϳ��̸�) C(��ũ) ���Ϳ� �߰��� �ο��� ������ �����ִ� ��Ŷ
	 */
	public static final int PLEDGE_REFRESH_PLUS = 25;

	/**
	 * D(���Ϳ��̸�) C(��ũ) ���Ϳ� ������ �ο��� ������ �����ִ� ��Ŷ
	 */
	public static final int PLEDGE_REFRESH_MINUS = 26;

	/**
	 * C(id): ����� ��ũ��%s�� ����Ǿ����ϴ�. (1-�߽� 2-�Ϲ� 3-��ȣ���)
	 */
	public static final int MSG_RANK_CHANGED = 27;

	/**
	 * D(���Ϳ���) (S(�����̸�) C(�������)) ���Ϳ� ������ �ȵ� ���¿����� /����.
	 */
	public static final int PLEDGE_ONE = 119;

	/** D(?) S(name) S(clanname): %s������%s�� ��Ÿ�ٵ屺�� ġ�����ϴ�. */
	public static final int MSG_WIN_LASTAVARD = 30;

	/** -: \f1����� ���������ϴ�. */
	public static final int MSG_FEEL_GOOD = 31;

	/** �Ҹ�.C_30 ��Ŷ�� ���� */
	public static final int SOMETHING1 = 33;

	/** H(time): ���� �Ϻ��� �������� ǥ�õȴ�. */
	public static final int ICON_BLUEPOTION = 34;

	/** H(time): ������ �������� ǥ�õȴ�. */
	public static final int ICON_POLYMORPH = 35;

	/** H(time): ä�� ������ �������� ǥ�õȴ�. */
	public static final int ICON_CHATBAN = 36;

	/** �� ������ ���� ��Ŷ */
	public static final int PET_ITEM = 37;

	/** ���� ������ HTML�� ǥ�õȴ� */
	public static final int HTML_CLAN1 = 38;

	/** H(time): �̹��� �������� ǥ�õȴ� */
	public static final int ICON_I2H = 40;

	/** ĳ������ ���� �ɼ�, ��Ʈ �� �������� ������ */
	public static final int CHARACTER_CONFIG = 41;

	/** ĳ���� ���� ȭ������ ���ư��� */
	public static final int LOGOUT = 42;

	/** �����߿� ��� ������ �� �����ϴ�. */
	public static final int MSG_CANT_LOGOUT = 43;

	/**
	 * C(count) D(time) S(name) S(info):<br>
	 * [CALL] ��ư�� ���� �����찡 ǥ�õȴ�. �̸��� ���� Ŭ�� �ϸ�(��) C_RequestWho�� ����, Ŭ���̾�Ʈ�� ������
	 * bot_list.txt�� �����ȴ�.�̸��� ������+Ű�� ������(��) ���ο� �����찡 ������.
	 */
	public static final int CALL_SOMETHING = 45;

	/**
	 * C(id): ��Ʋ �ݷԼ���, ī���� �����̡�<br>
	 * id - 1:�����մϴ� 2:�����Ǿ��� 3:�����մϴ�
	 */
	public static final int MSG_COLOSSEUM = 49;

	/** ���� ������ HTML */
	public static final int HTML_CLAN2 = 51;

	/** �丮 �����츦 ���� */
	public static final int COOK_WINDOW = 52;

	/** C(type) H(time): �丮 �������� ǥ�õȴ� */
	public static final int ICON_COOKING = 53;

	/** �������� ��鸲���� */
	public static final int FISHING = 55;

	/** ������ ���� */
	public static final int DEL_ICON = 59;

	public static final int EXP_POTION3 = 9278;
	
	public static final int EXP_POTION2 = 9279;
	/** ���� �ð� type:������� time:�ð� */
	public static final int ACCOUNT_TIME = 61;

	/** ���� ��� */
	public static final int PLEDGE_UNION = 62;

	/** �̴ϰ��� : 5,4,3,2,1 ī��Ʈ */
	public static final int MINIGAME_START_COUNT = 64;

	/** �̴ϰ��� : Ÿ��(0:00����) */
	public static final int MINIGAME_START_TIME = 65;

	/** �̴ϰ��� : ������ ����Ʈ */
	public static final int MINIGAME_LIST = 66;

	/** �̴ϰ��� : ��� �� ������ �̵��˴ϴ�(10�� ��) * */
	public static final int MINIGAME_10SECOND_COUNT = 69;

	/** �̴ϰ��� : ���� */
	public static final int MINIGAME_END = 70;

	/** �̴ϰ��� : Ÿ�� */
	public static final int MINIGAME_TIME = 71;

	/** �̴ϰ��� : Ÿ�ӻ��� */
	public static final int MINIGAME_TIME_CLEAR = 72;

	/** ���� ���� ���� �ɷ��� �϶��մϴ�. */
	public static final int DAMAGE_DOWN = 74;
	/** ���� : ���� ���� */
	public static final int SPOT = 75;

	/** �����ϻ�� ���� */
	public static final int AINHASAD = 82;

	public static final int HADIN_DISPLAY = 83;//�δ�����Ʈ
	/**
	 * ��ȣ�� UI ǥ�� + ����� ���� - �׸��� ����
	 */
	/** ��ȣ��ǥ���߰� */
	public static final int KARMA = 87; // �̴ϸ���Ƽ����

	/**
	 * ��ȣ�� ������ ���� �Һи� ��Ŷ 2�� ��
	 */
	public static final int INIT_DG = 88;// UI DGǥ��

	/** DG */
	public static final int UPDATE_DG = 101;// UI DGǥ��

	/** ���� ��Ŷ */
	public static final int DRAGONBLOOD = 100;
	/** �巡�� ���̵� ��Ż ��Ŷ  */
	public static final int DRAGONMENU = 102;
	
	public static final int BAPO = 114; // �������

	public static final int DRAGONPERL = 60; // �巡������

	/** �δ� ��� �޼��� */
	public static final int GREEN_MESSAGE = 84;

	public static final int YELLOW_MESSAGE = 00000; // �δ� é��2 ���

	/** �δ� ��� �޼��� */
	public static final int EMERALD_EVA = 86;//�巡�￡�޶���

	public S_PacketBox(int subCode) {
		writeC(Opcodes.S_OPCODE_PACKETBOX);
		writeC(subCode);

		switch (subCode) {
		case MSG_WAR_INITIATIVE:
		case MSG_WAR_OCCUPY:
		case MSG_MARRIED:
		case MSG_FEEL_GOOD:
		case MSG_CANT_LOGOUT:
		case ICON_PC_BUFF:
		case LOGOUT:
			break;

		case FISHING:
		case MINIGAME_START_TIME:
		case MINIGAME_TIME_CLEAR:
			break;
		case CALL_SOMETHING:
			callSomething();
			break;
		case DEL_ICON:
			writeH(0);
			break;
		case ICON_AURA:
			writeC(0x98);
			writeC(0);
			writeC(0);
			writeC(0);
			writeC(0);
			writeC(0);
			break;
		case MINIGAME_10SECOND_COUNT:
			writeC(10);
			writeC(109);
			writeC(85);
			writeC(208);
			writeC(2);
			writeC(220);
			break;
		case MINIGAME_END:
			writeC(147);
			writeC(92);
			writeC(151);
			writeC(220);
			writeC(42);
			writeC(74);
			break;
		case MINIGAME_START_COUNT:
			writeC(5);
			writeC(129);
			writeC(252);
			writeC(125);
			writeC(110);
			writeC(17);
			break;
		default:
			break;
		}
	}

	public S_PacketBox(int subCode, int value) {
		writeC(Opcodes.S_OPCODE_PACKETBOX);
		writeC(subCode);

		switch (subCode) {
		case ICON_BLUEPOTION:
		case ICON_CHATBAN:
		case ICON_I2H:
		case ICON_POLYMORPH:
		case MINIGAME_TIME:
		case BOOKMARK_SIZE_PLUS_10:
			   writeC(value);
			   break;
			
		case INIT_DG:// UI DGǥ��
			writeH(value); // time
			break;
		 case MAP_TIMER:
			   writeH(value); // time
			   break;
		case MSG_WAR_BEGIN:
		case MSG_WAR_END:
		case MSG_WAR_GOING:
			writeC(value); // castle id
			writeH(0); // ?
			break;
		case MSG_SMS_SENT:
		case WEIGHT:
		case FOOD:
		case UPDATE_DG: // UI DGǥ��
			writeC(value);
			break;
		case KARMA: // ��ȣ��ǥ���߰�
			writeD(value);
			break;
		case MSG_ELF:
		case MSG_RANK_CHANGED:
		case MSG_COLOSSEUM:
		case SPOT:
		case ER_UpDate:
			writeC(value); // msg id
			break;
		case MSG_LEVEL_OVER:
			writeC(0); // ?
			writeC(value); // 0-49�ܴ̿� ǥ�õ��� �ʴ´�
			break;
		case COOK_WINDOW:
			writeC(0xdb); // ?
			writeC(0x31);
			writeC(0xdf);
			writeC(0x02);
			writeC(0x01);
			writeC(value); // level
			break;
		case AINHASAD:
			value /= 10000;
			writeD(value);// % ��ġ 1~200
		case HADIN_DISPLAY://�δ�����Ʈ
			writeC(value);
			break;
		default:
			break;
		}
	}

	public S_PacketBox(int subCode, int type, boolean show){

		writeC(Opcodes.S_OPCODE_PACKETBOX);
		writeC(subCode);

		switch (subCode) {
		case UNLIMITED_ICON:
			writeC(show ? 0x01 : 0x00); // On Off
			writeC(type); // 
		   break;
		
		case BAPO:
			writeD(type); // 1~7 ���
			writeD(show ? 0x01 : 0x00);
		default:
			break;
		}
	}

	public S_PacketBox(int subCode, int type, int time) {
		writeC(Opcodes.S_OPCODE_PACKETBOX);
		writeC(subCode);

		switch (subCode) {
		case DRAGONPERL:// �巡������
			writeC(time);
			writeC(type);
			break;
		case EXP_POTION2:
			   writeC(time);
			   writeC(type);
			   break;
		case EXP_POTION3:
			   writeC(time);
			   writeC(type);
			   break;
		case ACCOUNT_TIME:
			writeD(time);
			writeC(type);
		case ICON_COOKING:
			if (type != 7) {
				writeC(0x0c);
				writeC(0x0c);
				writeC(0x0c);
				writeC(0x12);
				writeC(0x0c);
				writeC(0x09);
				writeC(0x00);
				writeC(0x00);
				writeC(type);
				writeC(0x24);
				writeH(time);
				writeH(0x00);
			} else {
				writeC(0x0c);
				writeC(0x0c);
				writeC(0x0c);
				writeC(0x12);
				writeC(0x0c);
				writeC(0x09);
				writeC(0xc8);
				writeC(0x00);
				writeC(type);
				writeC(0x26);
				writeH(time);
				writeC(0x3e);
				writeC(0x87);
			}
			break;
		case ICON_AURA:
			writeC(0xdd);
			writeH(time);
			writeC(type);
			break;
		case DRAGONBLOOD:
			writeC(type);
			writeD(time);
			break;
		case MSG_DUEL:
			writeD(type);
			writeD(time);
			break;
		case EMERALD_EVA://�巡�￡�޶���
			writeC(0x70);
			writeC(1);
			writeC(type);// 2: 2������� �ƴ϶��:���Ұ�
			writeH(time);// �ʷ� ����
			break;
		default:
			break;
		}
	}

	public S_PacketBox(int subCode, int type, int petid, int ac) {
		writeC(Opcodes.S_OPCODE_PACKETBOX);
		writeC(subCode);

		switch (subCode) {
		case PET_ITEM:
			writeC(type);
			writeD(petid); // pet objid
			writeH(ac);
			break;
		default:
			break;
		}
	}

	public S_PacketBox(int subCode, String name) {
		writeC(Opcodes.S_OPCODE_PACKETBOX);
		writeC(subCode);

		switch (subCode) {
		case ADD_EXCLUDE:
		case REM_EXCLUDE:
		case MSG_TOWN_LEADER:
//		case PLEDGE_UNION:
			writeS(name);
			break;
		case PLAYSOUND:  //?
			   writeH(Integer.parseInt(name));
			   break;

		/** �δ� ��� �޼��� */
		case GREEN_MESSAGE: // �δ��߰�
			writeC(2);
			writeS(name);
			break;
		case YELLOW_MESSAGE:
			/** �δ� ��� �޼��� */
			break;
		default:
			break;
		}
	}

	public S_PacketBox(int subCode, Object[] names) {
		writeC(Opcodes.S_OPCODE_PACKETBOX);
		writeC(subCode);

		switch (subCode) {
		case ADD_EXCLUDE2:
			writeC(names.length);
			for (Object name : names) {
				writeS(name.toString());
			}
			break;
		default:
			break;
		}
	}
	
	

	
	

	public S_PacketBox(int subCode, int id, String name, String clanName) {
		writeC(Opcodes.S_OPCODE_PACKETBOX);
		writeC(subCode);

		switch (subCode) {
		case MSG_WIN_LASTAVARD:
			writeD(id); // ũ�� ID�ΰ� �����ΰ�?
			writeS(name);
			writeS(clanName);
			break;
		default:
			break;
		}
	}

	public S_PacketBox(L1PcInstance pc, int subCode) {
		String clanName = pc.getClanname();
		L1Clan clan = L1World.getInstance().getClan(clanName);

		writeC(Opcodes.S_OPCODE_PACKETBOX);
		writeC(subCode);

		switch (subCode) {
		 case CLAN_WAREHOUSE_LIST:
			   int count = 0;
			   Connection con = null;
			   PreparedStatement pstm = null;
			   PreparedStatement pstm2 = null;
			   PreparedStatement pstm3 = null;
			   ResultSet rs = null;
			   ResultSet rs3 = null;
			   try {
			    con = L1DatabaseFactory.getInstance().getConnection();
			    pstm = con.prepareStatement("SELECT id, time FROM clan_warehouse_log WHERE clan_name='" + pc.getClanname() + "'");
			    rs = pstm.executeQuery();
			    while (rs.next()) {
			     if (System.currentTimeMillis() - rs.getTimestamp(2).getTime() > 4320000) {// 3��
			      pstm2 = con.prepareStatement("DELETE FROM clan_warehouse_log WHERE id='" + rs.getInt(1) + "'");
			      pstm2.execute();
			     } else
			      count++;
			    }
			    writeD(count);
			    pstm3 = con.prepareStatement("SELECT name, item_name, item_count, type, time FROM clan_warehouse_log WHERE clan_name='"
			      + pc.getClanname() + "'");
			    rs3 = pstm3.executeQuery();
			    while (rs3.next()) {
			     writeS(rs3.getString(1));
			     writeC(rs3.getInt(4));// 0:�ñ� 1:ã��
			     writeS(rs3.getString(2));
			     writeD(rs3.getInt(3));
			     writeD((int) (System.currentTimeMillis() - rs3.getTimestamp(5).getTime()) / 60000);// ����ð� ��
			    }
			   } catch (SQLException e) {
			   } finally {
			    SQLUtil.close(rs, pstm, con);
			    SQLUtil.close(pstm2);
			    SQLUtil.close(rs3);
			    SQLUtil.close(pstm3);
			   }
			   break;

				
		
		case PLEDGE_ONE:
			writeD(clan.getOnlineMemberCount());
			   for (L1PcInstance targetPc : clan.getOnlineClanMember()) { 
			    writeS(targetPc.getName());
			    writeC(targetPc.getClanRank());
			   }
			   if (clan.getClanBirthDay() != null){
				   String.valueOf(clan.getClanBirthDay());
				   
			   } else { //�ʵ��߰��� null���̹Ƿ�.. �ӽ�����
			    writeD((int)(System.currentTimeMillis() / 1000L));
			   }
			   
			   writeS(clan.getLeaderName());
				break;
			
			
		case PLEDGE_TWO:
			writeD(clan.getClanMemberList().size());

			ClanMember member;
			FastTable<ClanMember> clanMemberList = clan.getClanMemberList(); // ���
			// ���Ϳ���
			// �̸���
			// ���
			for (int i = 0; i < clanMemberList.size(); i++) {
				member = clanMemberList.get(i);
				writeS(member.name);
				writeC(member.rank);
			}

			writeD(clan.getOnlineMemberCount());
			for (L1PcInstance targetPc : clan.getOnlineClanMember()) { // �¶���
				writeS(targetPc.getName());
			}
			break;
		case PLEDGE_REFRESH_PLUS:
		case PLEDGE_REFRESH_MINUS:
			writeS(pc.getName());
			writeC(pc.getClanRank());
			writeH(0);
			break;
		case KARMA:
			writeD(pc.getKarma());
			writeH(0); // �ʿ��ұ�?
			break;
		default:
			break;
		}
	}
	public S_PacketBox(int subCode, L1ItemInstance Key){ //�巡�� Ű
		writeC(Opcodes.S_OPCODE_PACKETBOX);
		writeC(subCode);
		  switch (subCode) {
		  case DRAGONMENU :
			  writeD(Key.getId());
			  writeC(1);  // ��Ÿ
			  writeC(1);  // ��Ǫ
			  writeC(1);  // ���巹�̵�
			  writeC(0);  // �߶�
			  break; default: break; }
		  }
	public S_PacketBox(String name, int mapid, int x, int y, int Mid) { //�Ŵϸ���ġ����
          writeC(Opcodes.S_OPCODE_PACKETBOX);
		  writeC(0x6F);
		  writeS(name); 
		  writeH(mapid);
		  writeH(x);
		  writeH(y);
		  writeH(Mid);
		  writeH(0);
		 } 
	private void callSomething() {
		Iterator<L1PcInstance> itr = L1World.getInstance().getAllPlayers()
				.iterator();

		writeC(L1World.getInstance().getAllPlayers().size());
		L1PcInstance pc = null;
		Account acc = null;
		Calendar cal = null;
		while (itr.hasNext()) {
			pc = itr.next();
			acc = Account.load(pc.getAccountName());

			// �ð� ���� �켱 �α��� �ð��� �־� ����655

			if (acc == null) {
				writeD(0);
			} else {
				cal = Calendar.getInstance(TimeZone
						.getTimeZone(Config.TIME_ZONE));
				long lastactive = acc.getLastActive().getTime();
				cal.setTimeInMillis(lastactive);
				cal.set(Calendar.YEAR, 1970);
				int time = (int) (cal.getTimeInMillis() / 1000);
				writeD(time); // JST 1970 1/1 09:00 �� ����
			}

			// ĳ���� ����
			writeS(pc.getName()); // �ݰ� 12�ڱ���
			writeS(pc.getClanname()); // []���� ǥ�õǴ� ĳ���� ����.�ݰ� 12�ڱ���
		}
	}

	@Override
	public byte[] getContent() {
		if (_byte == null) {
			_byte = getBytes();
		}

		return _byte;
	}

	@Override
	public String getType() {
		return S_PACKETBOX;
	}
}