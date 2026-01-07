package com.github.cPajor.pcore.data.util;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.entity.Entity;


public class EntityData {
	
	public static Entity getLivingEntity(UUID uniqueId){
	    for (World world : Bukkit.getWorlds()) {
	        for (Chunk chunk : world.getLoadedChunks()) {
	            for (Entity entity : chunk.getEntities()) {
	                if (entity.getUniqueId().equals(uniqueId))
	                    return entity;
	            }
	        }
	    }

	    return null;
	}

}
