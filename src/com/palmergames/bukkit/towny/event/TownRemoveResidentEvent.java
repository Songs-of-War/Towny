package com.palmergames.bukkit.towny.event;

import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.TownObject;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Author: Chris H (Zren / Shade)
 * Date: 5/23/12
 *
 * Fired after a resident has been removed from a town.
 */
public class TownRemoveResidentEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    
    private Resident resident;
    private TownObject town;

    @Override
    public HandlerList getHandlers() {
    	
        return handlers;
    }
    
    public static HandlerList getHandlerList() {

		return handlers;
	}

    public TownRemoveResidentEvent(Resident resident, TownObject town) {
        super(!Bukkit.getServer().isPrimaryThread());
        this.resident = resident;
        this.town = town;
    }

    /**
     *
     * @return the resident who has been removed from a town.
     */
    public Resident getResident() {
        return resident;
    }

    /**
     *
     * @return the town the resident was previously in.
     */
    public TownObject getTown() {
        return town;
    }

}
