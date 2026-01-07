package com.github.cPajor.pcore.jungle;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Bisected.Half;
import org.bukkit.block.data.type.Campfire;
import org.bukkit.block.data.type.Candle;
import org.bukkit.block.data.type.SculkShrieker;
import org.bukkit.block.data.type.TrapDoor;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Warden;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import com.github.cPajor.pcore.data.util.Colors;

public class JungleGen extends ChunkGenerator implements Listener {
	
	@EventHandler
	public void popul(ChunkLoadEvent e) {
		if (e.isNewChunk() && e.getChunk().getWorld().getName().equalsIgnoreCase("jungle")) {
			for (int x = 0; x < 16; x++) {
				for (int y = 48; y < 80; y++) {
					for (int z = 0; z < 16; z++) {
						Block b = e.getChunk().getBlock(x, y, z);
						if (b.getType() == Material.SCULK_SHRIEKER) {
							SculkShrieker sr = (SculkShrieker) b.getBlockData();
							sr.setCanSummon(true);
							b.setBlockData(sr);
						}
						if (b.getType() == Material.BARRIER) {
							b.setType(Material.CANDLE);
							Candle c = (Candle) b.getBlockData();
							c.setCandles(4);
							c.setLit(true);
							b.setBlockData(c);
						}
						if (b.getType() == Material.COMMAND_BLOCK) {
							b.setType(Material.AIR);
							genStruct2(b.getLocation());
						}
					}	
				}	
			}
		}
	}
	
	@EventHandler
	public void ward2(EntityTargetEvent e) {
		if (e.getEntity().getType() == EntityType.WARDEN && e.getTarget() != null && e.getTarget().getType() == EntityType.BAT) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void ward1(CreatureSpawnEvent e) {
		if (e.getEntityType() == EntityType.WARDEN && e.getSpawnReason() != SpawnReason.COMMAND) {
			Bat b = e.getEntity().getWorld().spawn(e.getEntity().getLocation(), Bat.class);
			b.addPassenger(e.getEntity());
			b.setSilent(true);
			b.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1));
			Warden w = (Warden) e.getEntity();
			b.setCustomName(w.getUniqueId().toString());
			w.setHealth(100);
			w.setSilent(true);
			w.setMaxHealth(100);
			w.setCustomNameVisible(false);
			w.setCustomName(Colors.fix("&c*"));
		}
		if (e.getEntity().getWorld().getName().equalsIgnoreCase("jungle")) {
			if (e.getEntity().getType() == EntityType.GHAST || e.getEntity().getType() == EntityType.MAGMA_CUBE) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void wardded(EntityDeathEvent e) {
		if (e.getEntity().getCustomName() != null && e.getEntity().getType() == EntityType.BAT) {
			Entity ef =  Bukkit.getEntity(UUID.fromString(e.getEntity().getCustomName()));
			if (ef == null) return;
			if (ef.getCustomName() == null) {
				return;
			}
			Warden w = (Warden) ef;
			Bat b = e.getEntity().getWorld().spawn(e.getEntity().getLocation(), Bat.class);
			b.addPassenger(e.getEntity());
			b.setSilent(true);
			b.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1));
			b.setCustomName(w.getUniqueId().toString());
		}
	}
	
	/*
	@EventHandler
	public void snd1(BlockBreakEvent e) {
		if (e.getBlock().getWorld().getName().equalsIgnoreCase("jungle")) {
			if (e.getBlock().getType() == Material.SCULK_SENSOR) {
				e.getPlayer().playSound(e.getBlock().getLocation(), Sound.BLOCK_SCULK_SHRIEKER_SHRIEK, SoundCategory.MASTER, 100, 1);
			}
		}
	}
	
	@EventHandler
	public void popu2(ChunkPopulateEvent e) {
		if (e.getChunk().getWorld().getName().equalsIgnoreCase("jungle")) {
			Random r = new Random();
			int x = e.getChunk().getX() * 16 + 5;
			int z = e.getChunk().getZ() * 16 + 5;
			Location l = e.getChunk().getWorld().getBlockAt(x, e.getChunk().getWorld().getHighestBlockYAt(x, z), z).getLocation();
			//if (r.nextBoolean()) {
				if (r.nextBoolean()) {
					e.getChunk().getWorld().generateTree(l, TreeType.TALL_MANGROVE);
				} else {
					e.getChunk().getWorld().generateTree(l, TreeType.MANGROVE);
				}
			//}
		}
	}*/
	
	//@EventHandler
	//public void wie(WorldInitEvent e) {
		//if (e.getWorld().getName().equalsIgnoreCase("jungle"))
		//	e.getWorld().getPopulators().add(new BPop());
	//}
	
	@Override
	public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biom) {
		SimplexOctaveGenerator generator = new SimplexOctaveGenerator(new Random(world.getSeed()), 32);
		generator.setScale(0.005);
		ChunkData chunk = createChunkData(world);
		int tree = 0;
		int vtr = 0;
		int crr = 0;
		int crf = 0;
		int kx = 0;
		for (int x = 0; x < 16; x++) {
		    for (int z = 0; z < 16; z++) {
		    	int y = (int) ((generator.noise(chunkX * 16 + x, chunkZ * 16 + z, 0.5D, 0.5D, true) + 1) * 15D + 50D);
		    	
		    	if (y >= 70) {
		    		if (y == 70) { y += 2; } else 
		    		if (y == 71) {
		    			y += 3;
		    		} else {
		    			y = -y + 140 + (-y + 70);
		    		}
		    	}
		  
		        if (tree < 2 && random.nextInt() % 25 == 0) { 
		    		chunk.setBlock(x, y + 1, z, Material.SCULK_SENSOR);
		        	tree++;
		        }

		    	for (int i = y; i > -64; i--) {
		    		if (i > y - 10) {
		    			boolean v = random.nextBoolean();
		    			if (y == i) {
			    			chunk.setBlock(x, i, z, v ? Material.SCULK : Material.PODZOL);
		    			} else
		    			chunk.setBlock(x, i, z, v ? Material.SCULK : Material.ROOTED_DIRT);
		    		} else
		    		chunk.setBlock(x, i, z, random.nextInt(5) % 5 == 0 && y > 40 ? Material.SCULK : Material.STONE);	
		    	}
		    	
		    	if (random.nextBoolean() && random.nextBoolean() && random.nextBoolean()) {
	    			chunk.setBlock(x, y + 1, z, Material.SCULK_VEIN);
		    	}
		    	
		    	for (int i = 32; i > -32; i--) {
		    		if (random.nextInt(5) % 5 == 0) chunk.setBlock(x, i, z, Material.COPPER_ORE);	
		    	}
		    	
		    	if (vtr < 10 && random.nextInt() % 25 == 0) { 
		    		chunk.setBlock(x, y + 1, z, Material.MANGROVE_WOOD);
		    		chunk.setBlock(x, y, z, Material.MANGROVE_WOOD);
		    		chunk.setBlock(x, y - 1, z, Material.MUDDY_MANGROVE_ROOTS);
		    		vtr++;
		    	}
		    	
		    	if (z != 0 && z != 15 && x != 15)
		    	if (crr < 1 && random.nextInt() % 25 == 0) { 
		    		chunk.setBlock(x, y + 4, z, Material.MANGROVE_ROOTS);
		    		chunk.setBlock(x, y + 3, z, Material.MANGROVE_ROOTS);
		    		chunk.setBlock(x, y + 3, z + 1, Material.MANGROVE_ROOTS);
		    		chunk.setBlock(x, y + 3, z - 1, Material.MANGROVE_ROOTS);
		    		chunk.setBlock(x, y + 2, z, random.nextBoolean() ? Material.MANGROVE_ROOTS : Material.MUDDY_MANGROVE_ROOTS);
		    		chunk.setBlock(x, y + 1, z, random.nextBoolean() ? Material.MANGROVE_ROOTS : Material.MUDDY_MANGROVE_ROOTS);
		    		chunk.setBlock(x + 1, y + 1, z, Material.BARRIER);
		    		crr++;
		    	}
		    	
		    	if (z > 4 && z < 12 && x > 4 && x < 12 && random.nextInt() % 1000 == 0 && crf == 0) {
		    		for (int i = 0; i < 4; i++) {
		    			for (int j = 0; j < 4; j++) {
				    		chunk.setBlock(x + i, y + 1, z + j, Material.AIR);
			    		}	
		    		}
		    		chunk.setBlock(x, y, z, Material.HAY_BLOCK);
		    		chunk.setBlock(x, y + 1, z, Material.SOUL_CAMPFIRE);
		    		chunk.setBlock(x + 2, y + 1, z, Material.DEEPSLATE_TILE_SLAB);
		    		chunk.setBlock(x - 2, y + 1, z, Material.DEEPSLATE_TILE_SLAB);
		    		chunk.setBlock(x, y + 1, z + 2, Material.DEEPSLATE_TILE_SLAB);
		    		chunk.setBlock(x, y + 1, z - 2, Material.DEEPSLATE_TILE_SLAB);
		    		crf++;
		    	}
		    	
		    	if (kx == 0 && random.nextInt() % 50 == 0) {
		    		chunk.setBlock(x, y + 1, z, Material.SCULK_SHRIEKER);
		    		kx++;
		    	}
		    	
	    		chunk.setBlock(x, -64, z, Material.BEDROCK);
	    		chunk.setBlock(x, -63, z, Material.BEDROCK);
	    		chunk.setBlock(x, -62, z, Material.REINFORCED_DEEPSLATE);
		    }
		}
		
		return chunk;
	}
	
	@Override
	public boolean shouldGenerateCaves() {
		return true;
	}
	
	@Override
	public boolean shouldGenerateCaves(WorldInfo worldInfo, Random random, int chunkX, int chunkZ) {
		return true;
	}
	
	public static final BiomeProvider bp = new BiomeProvider() {
		
		@Override
		public Biome getBiome(WorldInfo worldInfo, int x, int y, int z) {
			return Biome.BASALT_DELTAS;
		}
		
		@Override
		public List<Biome> getBiomes(WorldInfo worldInfo) {
			return Arrays.asList(Biome.BASALT_DELTAS);
		}
		

	};

	public static void genStruct1(Location l) {
		for (int x = -3; x < 4; x++) {
			for (int z = -3; z < 4; z++) {
				l.clone().add(x, 0, z).getBlock().setType(Material.DEEPSLATE_TILES);
			}
		}
		l.clone().getBlock().setType(Material.CRYING_OBSIDIAN);
		//
		for (int x = -3; x < 4; x++) {
			for (int z = -3; z < 4; z++) {
				if ((3 == Math.abs(x) || 4 == Math.abs(z)) && (4 == Math.abs(x) || 3 == Math.abs(z)) && (4 != Math.abs(x) && 4 != Math.abs(z))) {
					l.clone().add(x, 1, z).getBlock().setType(Material.POLISHED_DEEPSLATE_SLAB);
				}
				if (2 == Math.abs(x) || 2 == Math.abs(z)) {
					l.clone().add(x, 1, z).getBlock().setType(Material.POLISHED_DEEPSLATE_SLAB);
				}
				if (1 == Math.abs(x) && 1 == Math.abs(z)) {
					l.clone().add(x, 5, z).getBlock().setType(Material.POLISHED_BLACKSTONE_BRICK_SLAB);
				}
				if ((2 == Math.abs(x) && 1 == Math.abs(z)) || (1 == Math.abs(x) && 2 == Math.abs(z))) {
					l.clone().add(x, 4, z).getBlock().setType(Material.POLISHED_DEEPSLATE);
				}
				if (2 == Math.abs(x) && 2 == Math.abs(z)) {
					for (int i = 1; i < 4; i++) {
						l.clone().add(x, i, z).getBlock().setType(Material.POLISHED_BASALT);
					}
					l.clone().add(x, 4, z).getBlock().setType(Material.POLISHED_BLACKSTONE_BRICK_SLAB);
				}
			}
		}
		l.clone().add(0, 5, 0).getBlock().setType(Material.SHROOMLIGHT);
	}

	public static void genStruct2(Location l) {
		l.add(0, 0, -2);
		for (int i = -1; i < 2; i++) {
			Block b = l.clone().add(i, 0, 0).getBlock();
			b.setType(Material.SPRUCE_TRAPDOOR);
			TrapDoor d = (TrapDoor) b.getBlockData();
			d.setOpen(true);
			d.setHalf(Half.BOTTOM);
			d.setFacing(BlockFace.NORTH);
			b.setBlockData(d);
		}
		l.add(0, 0, 1);
		for (int i = -1; i < 2; i++) {
			Block b = l.clone().add(i, 0, 0).getBlock();
			b.setType(Material.CAMPFIRE);
			Campfire d = (Campfire) b.getBlockData();
			d.setLit(false);
			d.setFacing(BlockFace.NORTH);
			b.setBlockData(d);
		}
		l.add(0, 0, 1);
		for (int i = -1; i < 2; i++) {
			Block b = l.clone().add(i, 0, 0).getBlock();
			b.setType(Material.SPRUCE_FENCE);
		}
		l.add(0, 0, 1);
		for (int i = -1; i < 2; i++) {
			Block b = l.clone().add(i, 0, 0).getBlock();
			b.setType(Material.CAMPFIRE);
			Campfire d = (Campfire) b.getBlockData();
			d.setLit(false);
			d.setFacing(BlockFace.NORTH);
			b.setBlockData(d);
		}
		l.add(0, 0, 1);
		for (int i = -1; i < 2; i++) {
			Block b = l.clone().add(i, 0, 0).getBlock();
			b.setType(Material.SPRUCE_TRAPDOOR);
			TrapDoor d = (TrapDoor) b.getBlockData();
			d.setOpen(true);
			d.setHalf(Half.BOTTOM);
			d.setFacing(BlockFace.SOUTH);
			b.setBlockData(d);
		}
		l.add(0, 1, -2);
		for (int i = -1; i < 2; i++) {
			Block b = l.clone().add(i, 0, 0).getBlock();
			b.setType(Material.SPRUCE_TRAPDOOR);
			TrapDoor d = (TrapDoor) b.getBlockData();
			d.setOpen(false);
			d.setHalf(Half.BOTTOM);
			d.setFacing(BlockFace.WEST);
			b.setBlockData(d);
		}
	}
}
