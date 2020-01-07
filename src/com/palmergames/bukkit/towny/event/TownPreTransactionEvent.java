package com.palmergames.bukkit.towny.event;

import com.palmergames.bukkit.towny.exceptions.EconomyException;
import com.palmergames.bukkit.towny.object.TownObject;
import com.palmergames.bukkit.towny.object.Transaction;
import org.bukkit.Bukkit;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TownPreTransactionEvent extends Event implements Cancellable {
	private TownObject town;
	private static final HandlerList handlers = new HandlerList();
	private Transaction transaction;
	private String cancelMessage = "Sorry this event was cancelled.";
	private boolean isCancelled = false;

	public TownPreTransactionEvent(TownObject town, Transaction transaction) {
		super(!Bukkit.getServer().isPrimaryThread());
		this.town = town;
		this.transaction = transaction;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {

		return handlers;
	}

	public TownObject getTown() {
		return town;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	@Override
	public boolean isCancelled() {
		return isCancelled;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		this.isCancelled = cancelled;
	}

	public String getCancelMessage() {
		return cancelMessage;
	}

	public void setCancelMessage(String cancelMessage) {
		this.cancelMessage = cancelMessage;
	}

	public int getNewBalance() {
		try {
			switch (transaction.getType()) {
				case DEPOSIT:
					return (int) (town.getHoldingBalance() + transaction.getAmount());
				case WITHDRAW:
					return (int) (town.getHoldingBalance() - transaction.getAmount());
				default:
					break;
			}
		} catch (EconomyException e) {
			Bukkit.getLogger().warning(e.getMessage());
		}

		return 0;
	}
}
