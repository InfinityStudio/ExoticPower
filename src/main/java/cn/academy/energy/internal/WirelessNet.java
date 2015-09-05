/**
 * Copyright (c) Lambda Innovation, 2013-2015
 * 本作品版权由Lambda Innovation所有。
 * http://www.li-dev.cn/
 *
 * This project is open-source, and it is distributed under  
 * the terms of GNU General Public License. You can modify
 * and distribute freely as long as you follow the license.
 * 本项目是一个开源项目，且遵循GNU通用公共授权协议。
 * 在遵照该协议的情况下，您可以自由传播和修改。
 * http://www.gnu.org/licenses/gpl.html
 */
package cn.academy.energy.internal;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import cn.academy.energy.api.block.IWirelessMatrix;
import cn.academy.energy.api.block.IWirelessNode;
import cn.academy.energy.internal.VBlocks.VWMatrix;
import cn.academy.energy.internal.VBlocks.VWNode;

/**
 * @author WeAthFolD
 */
public class WirelessNet {
	
	static final int UPDATE_INTERVAL = 40;
	static final double BUFFER_MAX = 2000;

	final WiWorldData data;
	World world;
	
	List<VWNode> nodes = new LinkedList();
	
	VWMatrix matrix;
	
	String ssid;
	String password;
	
	double buffer;
	
	int aliveUpdateCounter = UPDATE_INTERVAL;
	
	boolean alive = false;
	boolean disposed = false;
	
	public WirelessNet(WiWorldData data, VWMatrix matrix, String ssid, String pass) {
		this.data = data;
		
		this.matrix = matrix;
		
		this.ssid = ssid;
		this.password = pass;
		
		System.out.println("Directly creating " + ssid);
	}
	
	public WirelessNet(WiWorldData data, NBTTagCompound tag) {
		this.data = data;
		
		//Load the matrix
		matrix = new VWMatrix(tag.getCompoundTag("matrix"));
		
		//Load the info
		ssid = tag.getString("ssid");
		password = tag.getString("password");
		buffer = tag.getDouble("buffer");
		
		//Load the node list
		NBTTagList list = (NBTTagList) tag.getTag("list");
		for(int i = 0; i < list.tagCount(); ++i) {
			doAddNode(new VWNode(list.getCompoundTagAt(i)));
		}
		
		debug("Loading " + ssid + " from NBT, " + list.tagCount() + " nodes.");
	}
	
	NBTTagCompound toNBT() { 
		NBTTagCompound tag = new NBTTagCompound();
		tag.setTag("matrix", matrix.toNBT());
		tag.setString("ssid", ssid);
		tag.setString("password", password);
		tag.setDouble("buffer", buffer);
		
		NBTTagList list = new NBTTagList();
		for(VWNode vn : nodes) {
			if(!vn.isLoaded(world) || vn.get(world) != null) {
				list.appendTag(vn.toNBT());
			}
		}
		tag.setTag("list", list);
		
		debug(ssid + " toNBT()");
		
		return tag; 
	}
	
	public String getSSID() {
		return ssid;
	}
	
	public String getPassword() {
		return password;
	}
	
	public boolean resetPassword(String p, String np) {
		if(!p.equals(password))
			return false;
		password = np;
		return true;
	}
	
	/**
	 * Get whether this matrix is alive (That is, there are >=1 node loaded and should be ticked normally).
	 */
	public boolean isAlive() {
		return alive;
	}
	
	public boolean isDisposed() {
		return disposed;
	}
	
	public int getLoad() {
		return nodes.size();
	}
	
	public int getCapacity() {
		World world = data.world;
		IWirelessMatrix imat = matrix.get(world);
		return imat == null ? 0 : imat.getCapacity();
	}
	
	/**
	 * Dispose (a.k.a. destroy) this network and unlink all its linked nodes.
	 */
	void dispose() {
		disposed = true;
	}
	
	boolean addNode(VWNode node, String password) {
		if(!password.equals(this.password))
			return false;
		if(getLoad() >= getCapacity())
			return false;
		
		IWirelessMatrix imat = matrix.get(world);
		if(imat == null) {
			return false;
		}
		
		double r = imat.getRange();
		if(node.distSq(matrix) > r * r)
			return false;
		
		WiWorldData data = getWorldData();
		
		//Check if this node is previously added
		WirelessNet other = data.getNetwork(node.get(world));
		if(other != null) {
			other.removeNode(node);
		}
		
		doAddNode(node);
		
		return true;
	}
	
	boolean isInRange(int x, int y, int z) {
		IWirelessMatrix imat = matrix.get(world);
		if(imat == null) {
			return false;
		}
		
		double r = imat.getRange();
		double vec1[] = new double[] {x, y, z};
		double vec2[] = new double[] {matrix.x, matrix.y, matrix.z};
		if(vec1.length != vec2.length) {
			throw new RuntimeException("Inconsistent length");
		}
		
		double ret = 0.0;
		for(int i = 0; i < vec1.length; ++i) {
			double d = vec2[i] - vec1[i];
			ret += d * d;
		}
		return ret <= r * r;
	}
	
	private void doAddNode(VWNode node) {
		//Really add
		WiWorldData data = getWorldData();
		nodes.add(node);
		data.netLookup.put(node, this);
	}
	
	void removeNode(VWNode node) {
		nodes.remove(node);
		
		WiWorldData data = getWorldData();
		data.netLookup.remove(node);
	}
	
	void onCreate(WiWorldData data) {
		data.netLookup.put(ssid, this);
		data.netLookup.put(matrix, this);
	}
	
	void onCleanup(WiWorldData data) {
		data.netLookup.remove(ssid);
		data.netLookup.remove(matrix);
		
		for(VWNode n : nodes) {
			data.netLookup.remove(n);
		}
	}
	
	private WiWorldData getWorldData() {
		return data;
	}
	
	/**
	 * This is a slightly costy function. You should buffer the result and query through isAlive().
	 * query it infrequently.
	 * @return same as isAlive
	 */
	private boolean checkIsAlive() {
		//IF: alive node count > 1
		for(VWNode node : nodes) {
			if(node.isLoaded(world) && node.get(world) != null) {
				alive = true;
				return true;
			}
		}
		alive = false;
		return false;
	}
	
	void tick() {
		
		// Filter the not-alive nets and update the state lazily
		if(!isAlive()) {
			--aliveUpdateCounter;
			if(aliveUpdateCounter == 0) {
				aliveUpdateCounter = UPDATE_INTERVAL;
				checkIsAlive();
			}
			
			return;
		}
		
		// Check whether the matrix is valid. The matrix is ALWAYS loaded.
		IWirelessMatrix imat = matrix.get(world);
		if(imat == null) {
			debug("WirelessNet with SSID " + ssid + " matrix destoryed, removing");
			dispose();
			return;
		}
		
		// Balance.
		// Shuffle in order to not balance one node all the time
		// Maybe a bit of slow?
		Collections.shuffle(nodes);
		
		double sum = 0, maxSum = 0;
		Iterator<VWNode> iter = nodes.iterator();
		while(iter.hasNext()) {
			VWNode vn = iter.next();
			if(vn.isLoaded(world)) {
				IWirelessNode node = vn.get(world);
				if(node == null) {
					debug("Removing " + vn + " from " + ssid);
					iter.remove();
				} else {
					sum += node.getEnergy();
					maxSum += node.getMaxEnergy();
				}
			}
		}
		
		double percent = sum / maxSum;
		double transferLeft = imat.getBandwidth();
		// Loop through and calc
		for(VWNode vn : nodes) {
			if(vn.isLoaded(world)) {
				IWirelessNode node = vn.get(world);
				
				double cur = node.getEnergy();
				double targ = node.getMaxEnergy() * percent;
				
				double delta = targ - cur;
				delta = Math.signum(delta) * Math.min(Math.abs(delta), Math.min(transferLeft, node.getBandwidth()));
				
				if(buffer + delta > BUFFER_MAX) {
					delta = BUFFER_MAX - buffer;
				} else if(buffer + delta < 0) {
					delta = -buffer;
				}
				
				transferLeft -= Math.abs(delta);
				buffer += delta;
				node.setEnergy(cur + delta);
				
				if(transferLeft == 0)
					break;
			}
		}
	}
	
	private void debug(Object msg) {
		//AcademyCraft.log.info("WN:" + msg);
	}
	
}
