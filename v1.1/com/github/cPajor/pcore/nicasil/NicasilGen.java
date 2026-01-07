package com.github.cPajor.pcore.nicasil;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.event.Listener;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;
import org.bukkit.util.noise.SimplexOctaveGenerator;

public class NicasilGen extends ChunkGenerator implements Listener {
	
	@Override
	public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biom) {
		SimplexOctaveGenerator generator = new SimplexOctaveGenerator(new Random(world.getSeed()), 128);
		generator.setScale(0.01);
		ChunkData chunk = createChunkData(world);
		for (int x = 0; x < 16; x++) {
		    for (int z = 0; z < 16; z++) {
		    	int y = (int) ((generator.noise(chunkX * 16 + x, chunkZ * 16 + z, 0.5D, 0.5D, true) + 1) * 15D + 50D);
		    	
		    	chunk.setBlock(x, 2, z, Material.LAVA);
		    	chunk.setBlock(x, 253, z, Material.LAVA);
		    	chunk.setBlock(x, 252, z, Material.LAVA);
		    	if (random.nextInt() % 40 != 0) chunk.setBlock(x, 251, z, Material.TERRACOTTA);
		    	//
		    	if (y > 127 && y < 192) {
		    		for (int i = y; i < 192; i++) {
		    			chunk.setBlock(x, i, z, random.nextInt() % 50 == 0 ? Material.SHROOMLIGHT : Material.DEEPSLATE);
		    		}
		    	} else
		    	if (y > 63) {
		    		for (int i = 1; i <= y; i++) {
		    			chunk.setBlock(x, i, z, random.nextInt() % 50 == 0 ? Material.SHROOMLIGHT : Material.DEEPSLATE);
		    		}
		    		int rs = random.nextInt() % 30;
			    	if (rs == 0) {
			    		for (int i = 50; i < y; i++) chunk.setBlock(x, i, z, Material.LAVA);
			    	} 
			    	if (rs == 1) {
			    		chunk.setBlock(x, y - 1, z, Material.DEEPSLATE_IRON_ORE);	
			    	}
	    			if (rs < 10 && rs > 1) {
			    		chunk.setBlock(x, y - 1, z, Material.GRASS_BLOCK);
			    		if (random.nextInt() % 6 == 0) chunk.setBlock(x, y, z, Material.TORCHFLOWER);
	    			}
	    			if (rs < 20 && rs > 10) {
			    		chunk.setBlock(x, y - 1, z, Material.TERRACOTTA);
	    			}
		    	} else {
		    		for (int i = y + 47; i < 255; i++) {
		    			chunk.setBlock(x, i, z, random.nextInt() % 50 == 0 ? Material.SHROOMLIGHT : Material.DEEPSLATE);
		    		}
		    	}
		    	
		    	chunk.setBlock(x, 1, z, Material.BEDROCK);
		    	chunk.setBlock(x, 0, z, Material.BEDROCK);
		    	chunk.setBlock(x, 255, z, Material.BEDROCK);
		    	chunk.setBlock(x, 254, z, Material.BEDROCK);
		    }
		}
		
		return chunk;
	}
	
	public static class BB extends BiomeProvider {

		@Override
		public Biome getBiome(WorldInfo worldInfo, int x, int y, int z) {
			return Biome.SOUL_SAND_VALLEY;
		}

		@Override
		public List<Biome> getBiomes(WorldInfo worldInfo) {
			return Arrays.asList(Biome.SOUL_SAND_VALLEY);
		}
		
	}
	
	@Override
	public boolean shouldGenerateCaves() {
		return true;
	}
	
	@Override
	public boolean shouldGenerateStructures() {
		return true;
	}
}
