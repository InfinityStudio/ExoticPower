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
package cn.liutils.util.mc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.base.Predicate;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Some commonly used entity selectors.
 * @author WeAthFolD
 */
public class EntitySelectors {

	public static Predicate living = new SelectorOfType(EntityLivingBase.class);
	
	public static Predicate player = new SelectorOfType(EntityPlayer.class);
	
	public static Predicate survivalPlayer = new Predicate<Entity>() {

		@Override
		public boolean apply(Entity e) {
			return (e instanceof EntityPlayer && !((EntityPlayer)e).capabilities.isCreativeMode);
		}
		
	};
	
	public static Predicate nothing = new Predicate<Entity>() {

		@Override
		public boolean apply(Entity p_82704_1_) {
			return false;
		}
		
	};
	
	public static Predicate everything = new Predicate<Entity>() {

		@Override
		public boolean apply(Entity p_82704_1_) {
			return true;
		}
		
	};
	
	public static class SelectorOfType implements Predicate<Entity> {
		
		final Class<? extends Entity> klass;
		
		public SelectorOfType(Class<? extends Entity> _klass) {
			klass = _klass;
		}

		@Override
		public boolean apply(Entity input) {
			return klass.isInstance(input);
		}
		
	}
	
	public static class ExcludeType implements Predicate<Entity> {
		
		final Class<? extends Entity> klass;

		public ExcludeType(Class<? extends Entity> _klass) {
			klass = _klass;
		}
		
		@Override
		public boolean apply(Entity entity) {
			return !klass.isInstance(entity);
		}
		
	}
	
	public static class RestrictRange implements Predicate<Entity> {
		
		final double x, y, z;
		final double rangeSq;
		
		public RestrictRange(Entity e, double range) {
			this(e.posX, e.posY, e.posZ, range);
		}
		
		public RestrictRange(double _x, double _y, double _z, double _range) {
			x = _x;
			y = _y;
			z = _z;
			rangeSq = _range * _range;
		}

		@Override
		public boolean apply(Entity entity) {
			Entity e = (Entity) entity;
			double dx = e.getPosition().getX() - x,
					dy = e.getPosition().getY() - y,
					dz = e.getPosition().getZ() - z;
			return dx * dx + dy * dy + dz * dz <= rangeSq;
		}
		
	}
	
	public static class Exclusion implements Predicate<Entity> {
		
		final Set<Entity> exclusions = new HashSet();
		
		public Exclusion(Entity ...excls) {
			for(Entity e : excls)
				exclusions.add(e);
		}
		
		public Exclusion add(Entity e) {
			exclusions.add(e);
			return this;
		}

		@Override
		public boolean apply(Entity entity) {
			return !exclusions.contains(entity);
		}
		
	}
	
	public static class SelectorList implements Predicate<Entity> {
		
		List<Predicate> list = new ArrayList();
		
		public SelectorList(Predicate ...sels) {
			for(Predicate i : sels)
				list.add(i);
		}
		
		public SelectorList append(Predicate selector) {
			list.add(selector);
			return this;
		}

		@Override
		public boolean apply(Entity entity) {
			
			for(Predicate i : list)
				if(!i.apply(entity))
					return false;
			return true;
		}
		
	}
	
	/**
	 * Combine a set of entitySelectors (logical AND) to create a new EntitySelector.
	 */
	public static Predicate combine(Predicate ...sels) {
		return new SelectorList(sels);
	}
	
	/**
	 * Create an EntitySelector that excludes the passed in entities.
	 */
	public static Predicate excludeOf(Entity ...ents) {
		return new Exclusion(ents);
	}
	
	public static Predicate excludeType(Class<? extends Entity> klass) {
		return new ExcludeType(klass);
	}
	
}