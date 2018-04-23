/**
 *Made by: Stormblackbird
 *Version: 1.0.1
 *By order of: Hyperion Parks
 */
package com.hex;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;

import com.bergerkiller.bukkit.common.entity.CommonEntity;

public class Main extends JavaPlugin{

	@Override
	public void onEnable() {
		getLogger().info("Plugin enabled.");
	}
	
	@Override
	public void onDisable() {
		getLogger().info("Plugin disabled.");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.isOp()) {
			if(cmd.getName().equalsIgnoreCase("Hex")){
				if(args[0].equalsIgnoreCase("start")) {
					
					final ArmorStand armorstand = (ArmorStand) Bukkit.getWorld("atr").spawn(new Location(Bukkit.getWorld("atr"), 864.5, 7, 9646.5, -90f, -27f), ArmorStand.class);
				    final Location loc = armorstand.getLocation();
				    
					armorstand.setGravity(false);
					armorstand.setVisible(false);
					
	            	List<Entity> entitylist = armorstand.getNearbyEntities(1, 1, 1);
	            	for(int t = 0; t<entitylist.size();t++) {
	            		if(entitylist.get(t).getType()==EntityType.PLAYER){
	            			
	            	final Player player = (Player) entitylist.get(t);
	            	player.teleport(new Location(player.getWorld(), 864.5, 7, 9646.5, -90f, -27f));
	            			
	            	
					player.setGameMode(GameMode.SPECTATOR);
					player.setSpectatorTarget(armorstand);
					
					//Cancel player exit
					new BukkitRunnable() {
						@Override
						public void run() {
							if(!(player.getSpectatorTarget() == armorstand) && !(armorstand.isDead())) {
								player.setSpectatorTarget(armorstand);
							}
							
							if(armorstand.isDead()) {
								this.cancel();
							}
						}
					}.runTaskTimer(this, 0, 1);
					
					//End
					new BukkitRunnable() {
						
						@Override
						public void run() {
							armorstand.remove();
							player.teleport(new Location(player.getWorld(), 904, 37, 9650, -63f, 0f));
							player.setGameMode(GameMode.ADVENTURE);
							this.cancel();
						}
					}.runTaskTimer(this, (161*20), 1);
	            	
	            		
					armorstand.setCustomName("hexrideas" + t);
	            		}
	            	}
	            		
					//Some looking around
					new BukkitRunnable() {
						
						@Override
						public void run() {
							loc.setYaw((float) (loc.getYaw() - 1));
							loc.setPitch((float) (loc.getPitch() - 0.5));
							armorstand.teleport(loc);
							if(loc.getYaw() <= -134) {
								this.cancel();
							}
						}
					}.runTaskTimer(this, 40, 1);
					
					new BukkitRunnable() {
						
						@Override
						public void run() {
							loc.setYaw((float) (loc.getYaw() + 1));
							loc.setPitch((float) (loc.getPitch() + 0.5));
							armorstand.teleport(loc);
							if(loc.getYaw() >= -90) {
								this.cancel();
							}
						}
					}.runTaskTimer(this, 88, 1);
					
					new BukkitRunnable() {
						
						@Override
						public void run() {
							loc.setYaw((float) (loc.getYaw() + 1));
							loc.setPitch((float) (loc.getPitch() - 0.5));
							armorstand.teleport(loc);
							if(loc.getYaw() >= -46) {
								this.cancel();
							}
						}
					}.runTaskTimer(this, 132, 1);
					
					new BukkitRunnable() {
						
						@Override
						public void run() {
							loc.setPitch((float) (loc.getPitch() + 0.5));
							armorstand.teleport(loc);
							if(loc.getPitch() >= -30) {
								loc.setPitch(-30f);
								armorstand.teleport(loc);
								this.cancel();
							}
						}
					}.runTaskTimer(this, 176, 1);
					
					//Flashing effects on the side
					
					//Return to default perspective
					new BukkitRunnable() {
						
						@Override
						public void run() {
							loc.setYaw((float) (loc.getYaw() - 1));
							loc.setPitch((float) (loc.getPitch() - 0.1));
							armorstand.teleport(loc);
							if(loc.getYaw() <= -90) {
								loc.setPitch(-45f);
								armorstand.teleport(loc);
								this.cancel();
							}
						}
					}.runTaskTimer(this, 400, 1);
					
					//Effects with branch (Smoke and light)
					
					//First rotation
					new BukkitRunnable() {
						
						@Override
						public void run() {
							
							int pitch = (int) loc.getPitch();
							loc.setPitch((float) ((loc.getPitch() - 0.3)  ));
							
							loc.setY(7 + 4*Math.sin(((loc.getPitch()+45)/360) * 2*Math.PI));
							loc.setX(868.5 - 4*Math.cos(((loc.getPitch()+45)/360) * 2*Math.PI) );
							
							CommonEntity<?> cm = CommonEntity.get(armorstand);
							cm.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
						
							if(pitch <= -80 ) {
								this.cancel();
							}
						}
					}.runTaskTimer(this, (26*20), 1);
					
					//Second rotation
					new BukkitRunnable() {
						
						@Override
						public void run() {
							
							int pitch = (int) loc.getPitch();
							loc.setPitch((float) ((loc.getPitch() + 0.5)  ));
							
							loc.setY(7 + 4*Math.sin(((loc.getPitch()+45)/360) * 2*Math.PI));
							loc.setX(868.5 - 4*Math.cos(((loc.getPitch()+45)/360) * 2*Math.PI) );
							
							CommonEntity<?> cm = CommonEntity.get(armorstand);
							cm.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
						
							if(pitch >= 40 ) {
								this.cancel();
							}
						}
					}.runTaskTimer(this, (35*20), 1);
					
					//Third rotation
					new BukkitRunnable() {
						
						@Override
						public void run() {
							
							int pitch = (int) loc.getPitch();
							loc.setPitch((float) ((loc.getPitch() - 0.7)  ));
							
							loc.setY(7 + 4*Math.sin(((loc.getPitch()+45)/360) * 2*Math.PI));
							loc.setX(868.5 - 4*Math.cos(((loc.getPitch()+45)/360) * 2*Math.PI) );
							
							CommonEntity<?> cm = CommonEntity.get(armorstand);
							cm.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
						
							if(pitch <= -100 ) {
								this.cancel();
							}
						}
					}.runTaskTimer(this, (48*20), 1);
					
					//Fourth rotation
					new BukkitRunnable() {
						
						@Override
						public void run() {
							
							int pitch = (int) loc.getPitch();
							loc.setPitch((float) ((loc.getPitch() + 1)  ));
							
							loc.setY(7 + 4*Math.sin(((loc.getPitch()+45)/360) * 2*Math.PI));
							loc.setX(868.5 - 4*Math.cos(((loc.getPitch()+45)/360) * 2*Math.PI) );
							
							CommonEntity<?> cm = CommonEntity.get(armorstand);
							cm.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
						
							if(pitch >= 90 ) {
								this.cancel();
							}
						}
					}.runTaskTimer(this, (60*20), 1);
					
					//Fifth rotation
					new BukkitRunnable() {
						
						@Override
						public void run() {
							
							int pitch = (int) loc.getPitch();
							loc.setPitch((float) ((loc.getPitch() - 1)  ));
							
							loc.setY(7 + 4*Math.sin(((loc.getPitch()+45)/360) * 2*Math.PI));
							loc.setX(868.5 - 4*Math.cos(((loc.getPitch()+45)/360) * 2*Math.PI) );
							
							CommonEntity<?> cm = CommonEntity.get(armorstand);
							cm.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
						
							if(pitch <= -405 ) {
								this.cancel();
							}
						}
					}.runTaskTimer(this, (71*20), 1);
					
					
					//Sixth rotation
					new BukkitRunnable() {
						
						@Override
						public void run() {
							
							int pitch = (int) loc.getPitch();
							loc.setPitch((float) ((loc.getPitch() + 1.5)  ));
							
							loc.setY(7 + 4*Math.sin(((loc.getPitch()+45)/360) * 2*Math.PI));
							loc.setX(868.5 - 4*Math.cos(((loc.getPitch()+45)/360) * 2*Math.PI) );
							
							CommonEntity<?> cm = CommonEntity.get(armorstand);
							cm.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
						
							if(pitch >= 100 ) {
								this.cancel();
							}
						}
					}.runTaskTimer(this, (97*20), 1);
					
					//Seventh rotation
					new BukkitRunnable() {
						
						@Override
						public void run() {
							
							int pitch = (int) loc.getPitch();
							loc.setPitch((float) ((loc.getPitch() -1)  ));
							
							loc.setY(7 + 4*Math.sin(((loc.getPitch()+45)/360) * 2*Math.PI));
							loc.setX(868.5 - 4*Math.cos(((loc.getPitch()+45)/360) * 2*Math.PI) );
							
							CommonEntity<?> cm = CommonEntity.get(armorstand);
							cm.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
						
							if(pitch <= -45 ) {
								this.cancel();
							}
						}
					}.runTaskTimer(this, (144*20), 1);
					
	            	return true;
				}
				else return false;
			}
			else return false;
		}
		else {
			sender.sendMessage("You don't have the permission to execute this command!");
			return true;
		}
	}
}