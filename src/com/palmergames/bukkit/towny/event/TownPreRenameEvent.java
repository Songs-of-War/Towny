package com.palmergames.bukkit.towny.event;

import org.bukkit.Bukkit;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.palmergames.bukkit.towny.object.TownObject;

public class TownPreRenameEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();
	private String oldName, newName;
	private TownObject town;
	private boolean isCancelled = false;

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	public TownPreRenameEvent(TownObject town, String newName) {
		super(!Bukkit.getServer().isPrimaryThread());
		this.oldName = town.getName();
		this.town = town;
		this.newName = newName;
	}

	/**
	 *
	 * @return the old town name.
	 */
	public String getOldName() {
		return oldName;
	}
	/**
	 * 
	 * @return the new town name.
	 */
	public String getNewName() {
		return newName;
	}

	/**
	 *
	 * @return the town with it's changed name
	 */
	public TownObject getTown() {
		return this.town;
	}

	@Override
	public boolean isCancelled() {
		return isCancelled;
	}

	@Override
	public void setCancelled(boolean cancel) {
		this.isCancelled = cancel;
	}
}
