/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.   See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 *
 * http://www.gnu.org/copyleft/gpl.html
 */
package l1j.server.server.command.executor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import l1j.server.L1DatabaseFactory;
import l1j.server.server.datatables.CharacterTable;
import l1j.server.server.datatables.ItemTable;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1ItemInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_SystemMessage;
import l1j.server.server.utils.SQLUtil;

public class L1CheckCharacter implements L1CommandExecutor {
	private static Logger _log = Logger.getLogger(L1CheckCharacter.class.getName());
	private L1CheckCharacter() {}
	public static L1CommandExecutor getInstance() {	return new L1CheckCharacter();}

	@Override
	public void execute(L1PcInstance pc, String cmdName, String arg) {
		Connection c = null;
		PreparedStatement p = null;
		PreparedStatement p1 = null;
		ResultSet r = null;
		ResultSet r1 = null;
		try {				
			StringTokenizer st = new StringTokenizer(arg);
			String charname = st.nextToken();
			String type = st.nextToken();

			c = L1DatabaseFactory.getInstance().getConnection();
			
			String itemname;
			int searchCount = 0;
			if (type.equalsIgnoreCase("????")){	
				try {
					// ???? ???????? ID ???? 1=objid 2=charname
					p = c.prepareStatement("SELECT objid, char_name FROM characters WHERE char_name = '" + charname + "'");
					r = p.executeQuery();
					while(r.next()){
						pc.sendPackets(new S_SystemMessage("\\fW** ????: "+type+" ????: " + charname + " **"));
						L1PcInstance target = L1World.getInstance().getPlayer(charname);			
						if (target != null) target.saveInventory();						
						// ???? ?????? ???? 1-itemid 2-???? 3-???? 4-???? 5-???? 6-???? 7-????
						p1 = c.prepareStatement("SELECT item_id,enchantlvl,is_equipped,count,item_name,bless,attr_enchantlvl " +
								"FROM character_items WHERE char_id = '" + r.getInt(1) + "' ORDER BY 3 DESC,2 DESC, 1 ASC");
						r1 = p1.executeQuery();				
						while(r1.next()){
							itemname = getInvenItemMsg(r1.getInt(1),r1.getInt(2),r1.getInt(3),r1.getInt(4),r1.getString(5),r1.getInt(6),r1.getInt(7));
							pc.sendPackets(new S_SystemMessage("\\fU"+ ++searchCount +". " + itemname));
							itemname = "";
						}
						pc.sendPackets(new S_SystemMessage("\\fW** ?? "+searchCount+"???? ???????? ???? ?????????? **"));
					}					
				} catch (Exception e) {
					pc.sendPackets(new S_SystemMessage("\\fW** [" + charname + "] ???? ???? ???? **"));
				}
			} else if (type.equalsIgnoreCase("????")){
				try {
					p = c.prepareStatement("SELECT account_name, char_name FROM characters WHERE char_name = '" + charname + "'");
					r = p.executeQuery();
					while (r.next()){
						pc.sendPackets(new S_SystemMessage("\\fW** ????: "+type+" ????: " + charname + "(" + r.getString(1) + ") **"));
						//???? ???? ???? 1-itemid 2-???? 3-???? 4-???? 5-???? 6-????
						p1 = c.prepareStatement("SELECT item_id,enchantlvl,count,item_name,bless,attr_enchantlvl FROM character_warehouse " +
								"WHERE account_name = '" + r.getString(1) + "' ORDER BY 2 DESC, 1 ASC");
						r1 = p1.executeQuery();
						while (r1.next()){
							itemname = getInvenItemMsg(r1.getInt(1),r1.getInt(2),0,r1.getInt(3),r1.getString(4),r1.getInt(5),r1.getInt(6));
							pc.sendPackets(new S_SystemMessage("\\fU"+ ++searchCount +". " + itemname));
							itemname = "";
						}
						pc.sendPackets(new S_SystemMessage("\\fW** ?? "+searchCount+"???? ???????? ???? ?????????? **"));
					}
				} catch (Exception e) {
					pc.sendPackets(new S_SystemMessage("\\fW** [" + charname + "] ???? ???? ???? **"));
				}
			} else if (type.equalsIgnoreCase("????????")){				
				try {
					p = c.prepareStatement("SELECT account_name, char_name FROM characters WHERE char_name = '" + charname + "'");
					r = p.executeQuery();
					while (r.next()){
						pc.sendPackets(new S_SystemMessage("\\fW** ????: "+type+" ????: " + charname + "(" + r.getString(1) + ") **"));
						//???? ???????? ???? 1-itemid 2-???? 3-???? 4-???? 5-???? 6-????
						p1 = c.prepareStatement("SELECT item_id,enchantlvl,count,item_name,bless,attr_enchantlvl FROM character_warehouse_elf " +
								"WHERE account_name = '" + r.getString(1) + "' ORDER BY 2 DESC, 1 ASC");
						r1 = p1.executeQuery();
						while (r1.next()){							
							itemname = getInvenItemMsg(r1.getInt(1),r1.getInt(2),0,r1.getInt(3),r1.getString(4),r1.getInt(5),r1.getInt(6));
							pc.sendPackets(new S_SystemMessage("\\fU"+ ++searchCount +". " + itemname));
							itemname = "";
						}
						pc.sendPackets(new S_SystemMessage("\\fW** ?? "+searchCount+"???? ???????? ???? ?????????? **"));
					}
				} catch (Exception e) {
					pc.sendPackets(new S_SystemMessage("\\fW** [" + charname + "] ???? ???? ???? **"));
				}
			} else if (type.equalsIgnoreCase("????")){	
				try {
					// ???? ???????? ID ???? 1=objid 2=charname
					p = c.prepareStatement("SELECT objid, char_name FROM characters WHERE char_name = '" + charname + "'");
					r = p.executeQuery();
					while(r.next()){
						pc.sendPackets(new S_SystemMessage("\\fW** ????: "+type+" ????: " + charname + " **"));
						L1PcInstance target = L1World.getInstance().getPlayer(charname);			
						if (target != null) target.saveInventory();						
						// ???? ?????? ???? 1-itemid 2-???? 3-???? 4-???? 5-???? 6-???? 7-????
						p1 = c.prepareStatement("SELECT item_id,enchantlvl,is_equipped,count,item_name,bless,attr_enchantlvl " +
								"FROM character_items WHERE char_id = '" + r.getInt(1) + "' ORDER BY 3 DESC,2 DESC, 1 ASC");
						r1 = p1.executeQuery();				
						while(r1.next()){
							L1ItemInstance item = ItemTable.getInstance().createItem(r1.getInt(1));							
							if (item.getItem().getType2() == 1 || item.getItem().getType2() == 2) { // ???? ???????? ????
								itemname = getInvenItem(r1.getInt(1),r1.getInt(2),r1.getInt(3),r1.getInt(4),r1.getString(5),r1.getInt(6),r1.getInt(7));
								pc.sendPackets(new S_SystemMessage("\\fU"+ ++searchCount +". " + itemname));
								itemname = "";
							}
						}
						pc.sendPackets(new S_SystemMessage("\\fW** ?? "+searchCount+"???? ???????? ???? ?????????? **"));
					}					
				} catch (Exception e) {
					pc.sendPackets(new S_SystemMessage("\\fW** [" + charname + "] ???? ???? ???? **"));
				}
			} else if (type.equalsIgnoreCase("????")) {
				try {
//					L1PcInstance player = L1World.getInstance().getPlayer(charname);
//					
//					if (player == null) {
//						pc.sendPackets(new S_SystemMessage("?????? ?????? ???????? ?????????? ???????? ????????."));
//						return;
//					}
					
					CharacterTable.getInstance().CharacterAccountCheck(pc, charname);
				} catch (Exception e) {
					pc.sendPackets(new S_SystemMessage("\\fW** [" + charname + "] ???? ???? ???? **"));
				}
			}		
		} catch (Exception e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			pc.sendPackets(new S_SystemMessage(".???? [??????] [????,????,????????,????,????]"));
		} finally {
			SQLUtil.close(r1);SQLUtil.close(p1);
			SQLUtil.close(r);SQLUtil.close(p);
			SQLUtil.close(c);
		}
	}
	private String getInvenItemMsg(int itemid, int enchant, int equip, int count, String itemname, int bless, int attr){
		StringBuilder name = new StringBuilder();
		// +9 ???????? ?????? ?????? (????)		
		// ????
		if (enchant > 0) {
			name.append("+" + enchant + " ");
		} else if (enchant == 0) {
			name.append("");
		} else if (enchant < 0) {
			name.append(String.valueOf(enchant) + " ");
		}
		// ????
		switch (bless) {
		case 0:name.append("???????? ");break;
		case 1:name.append("");break;		
		case 2:name.append("???????? ");break;
		default: break;
		}
		// ????
		switch(attr){
		case 1: name.append("$6115 "); break;
		case 2: name.append("$6116 "); break;
		case 3: name.append("$6117 "); break;
		case 4: name.append("$6118 "); break;
		case 5: name.append("$6119 "); break;
		case 6: name.append("$6120 "); break;
		case 7: name.append("$6121 "); break;
		case 8: name.append("$6122 "); break;
		case 9: name.append("$6123 "); break;
		case 10: name.append("$6124 "); break;
		case 11: name.append("$6125 "); break;
		case 12: name.append("$6126 "); break;
		default: break;
		}
		// ????
		name.append(itemname + " ");
		// ????????
		if (equip == 1){
			name.append("(????)");
		}
		// ??????
		if (count > 1){
			name.append("(" + count + ")");
		}
		return name.toString();
	}
	
	private String getInvenItem(int itemid, int enchant, int equip, int count, String itemname, int bless, int attr){		
		StringBuilder name = new StringBuilder();
		// +9 ???????? ?????? ?????? (????)		
		// ????
		if (enchant > 0) {
			name.append("+" + enchant + " ");
		} else if (enchant == 0) {
			name.append("");
		} else if (enchant < 0) {
			name.append(String.valueOf(enchant) + " ");
		}
		// ????
		switch (bless) {
		case 0:name.append("???????? ");break;
		case 1:name.append("");break;		
		case 2:name.append("???????? ");break;
		default: break;
		}
		// ????
		switch(attr){
		case 1: name.append("$6115 "); break;
		case 2: name.append("$6116 "); break;
		case 3: name.append("$6117 "); break;
		case 4: name.append("$6118 "); break;
		case 5: name.append("$6119 "); break;
		case 6: name.append("$6120 "); break;
		case 7: name.append("$6121 "); break;
		case 8: name.append("$6122 "); break;
		case 9: name.append("$6123 "); break;
		case 10: name.append("$6124 "); break;
		case 11: name.append("$6125 "); break;
		case 12: name.append("$6126 "); break;
		default: break;
		}
		// ????
		name.append(itemname + " ");
		// ????????
		if (equip == 1){
			name.append("(????)");
		}
		switch (bless) {
		case 129:name.append("[????]");break;
		default: break;
		}
		// ??????
		if (count > 1){
			name.append("(" + count + ")");
		}
		return name.toString();
	}
}
