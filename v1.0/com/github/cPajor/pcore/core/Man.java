package com.github.cPajor.pcore.core;

import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;

import com.github.cPajor.pcore.CReg;
import com.github.cPajor.pcore.commands.admin.ACommand;
import com.github.cPajor.pcore.commands.admin.BackCommand;
import com.github.cPajor.pcore.commands.admin.BcCommand;
import com.github.cPajor.pcore.commands.admin.ChatCommand;
import com.github.cPajor.pcore.commands.admin.ClearCommand;
import com.github.cPajor.pcore.commands.admin.EcoCommand;
import com.github.cPajor.pcore.commands.admin.EventCommand;
import com.github.cPajor.pcore.commands.admin.FlyCommand;
import com.github.cPajor.pcore.commands.admin.GatewayCommand;
import com.github.cPajor.pcore.commands.admin.GmCommand;
import com.github.cPajor.pcore.commands.admin.GodCommand;
import com.github.cPajor.pcore.commands.admin.InvSeeCommand;
import com.github.cPajor.pcore.commands.admin.ItemCommand;
import com.github.cPajor.pcore.commands.admin.ItemnameCommand;
import com.github.cPajor.pcore.commands.admin.OTPCommand;
import com.github.cPajor.pcore.commands.admin.SkullCommand;
import com.github.cPajor.pcore.commands.admin.SlavSpyCommand;
import com.github.cPajor.pcore.commands.admin.TpCommand;
import com.github.cPajor.pcore.commands.admin.VanishCommand;
import com.github.cPajor.pcore.commands.gracz.MsgCommand;
import com.github.cPajor.pcore.commands.gracz.RynekCommand;
import com.github.cPajor.pcore.commands.gracz.SpawnCommand;
import com.github.cPajor.pcore.commands.gracz.TpaCommand;
import com.github.cPajor.pcore.commands.gracz.TpacceptCommand;
import com.github.cPajor.pcore.commands.gracz.WCommand;
import com.github.cPajor.pcore.data.bazary.CRynekListeners;
import com.github.cPajor.pcore.data.gateway.GatewayListeners;
import com.github.cPajor.pcore.data.task.CombatTask;
import com.github.cPajor.pcore.data.task.TagTask;
import com.github.cPajor.pcore.data.task.TitleTask;
import com.github.cPajor.pcore.listeners.magic.MagicListeners;
import com.github.cPajor.pcore.listeners.player.AntyNogiListener;
import com.github.cPajor.pcore.listeners.player.ChatListeners;
import com.github.cPajor.pcore.listeners.player.CombatListener;
import com.github.cPajor.pcore.listeners.player.CommandListener;
import com.github.cPajor.pcore.listeners.player.EcoListeners;
import com.github.cPajor.pcore.listeners.player.EnchantListener;
import com.github.cPajor.pcore.listeners.player.JoinListener;
import com.github.cPajor.pcore.listeners.player.MagicWeaponListeners;
import com.github.cPajor.pcore.listeners.player.MeteorytListener;
import com.github.cPajor.pcore.listeners.player.RandomTPListener;

public class Man {
	
	public static void init() {
		registerCommands();
		registerListener();
		setupTasks();
		setupSettings();
		//GuildManager.load();
		GatewayListeners.init();
		/*if (Bukkit.getPluginManager().isPluginEnabled("Citizens")) {
			CitizensHook.HHook();
		}*/
		//if (Bukkit.getPluginManager().isPluginEnabled("FancyNpcs")) {
		//	FancyNPCsHook.Hhook(); 
		//}
	}
	
	private static void setupSettings() { 
		/*World wf = Bukkit.getWorld("nicasil");
		if (wf == null) {
			WorldCreator wc = new WorldCreator("nicasil");
			wc.generator(new NicasilGen());
			wc.environment(Environment.NETHER);
			wc.generateStructures(true);
			wc.biomeProvider(new NicasilGen.BB());
			wf = Bukkit.createWorld(wc);
		}
		wf.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
		wf.getWorldBorder().setCenter(0, 0);
		wf.getWorldBorder().setSize(10000);
		wf.setFullTime(20000);
		
		World jg = Bukkit.getWorld("jungle");
		if (jg == null) {
			WorldCreator wc = new WorldCreator("jungle");
			wc.generator(new JungleGen());
			wc.generateStructures(false);
			wc.biomeProvider(JungleGen.bp);
			jg = Bukkit.createWorld(wc);
		}
		jg.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
		jg.setGameRule(GameRule.DO_WARDEN_SPAWNING,	true);
		jg.getWorldBorder().setCenter(0, 0);
		jg.getWorldBorder().setSize(10000);
		jg.setFullTime(20000);*/

		//WorldCreator wcc = new WorldCreator("farlands");
		//wcc.type(WorldType.AMPLIFIED);
		//wcc.generateStructures(true);
		//if (Bukkit.getWorld("farlands") == null) Bukkit.createWorld(wcc);
		
		for (World s : Bukkit.getWorlds()) {
			s.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
		} 
		
		//npcs.add(new Cnpc(new Location(Bukkit.getWorld("spawnc"), -12.5, 113, -3.5, -45, 0), Colors.fix("&4&lYour mama 1")));
		//npcs.add(new Cnpc(new Location(Bukkit.getWorld("spawnc"), -8.5, 113, -7.5, -45, 0), Colors.fix("&4&lYour mama 2")));
		//npcs.add(new Cnpc(new Location(Bukkit.getWorld("spawnc"), -12.5, 113, 14.5, 225, 0), Colors.fix("&4&lYour mama 3")));
		//npcs.add(new Cnpc(new Location(Bukkit.getWorld("spawnc"), -8.5, 113, 18.5, 225, 0), Colors.fix("&4&lYour mama 4")));
		 
		
	}
	
	private static void setupTasks() {
		new TagTask().runTaskTimer(CReg.getPlugin(), 10L, 10L);
		//new RynekRefreshTask().runTaskTimer(CReg.getPlugin(), 400L, 400L);
		new CombatTask().runTaskTimer(CReg.getPlugin(), 1L, 0L);
		new TitleTask().runTaskTimer(CReg.getPlugin(), 15L, 15L);
		//new klanSys().runTaskTimer(CReg.getPlugin(), 10, 10);
	}
	
	private static void registerListener() {
		//Bukkit.getPluginManager().registerEvents(new ModPanelCommand(), CReg.getPlugin());
		//Bukkit.getPluginManager().registerEvents(new PanelCommand(), CReg.getPlugin());
		Bukkit.getPluginManager().registerEvents(new ChatListeners(), CReg.getPlugin());
		Bukkit.getPluginManager().registerEvents(new CommandListener(),CReg.getPlugin());
		//Bukkit.getPluginManager().registerEvents(new ReachListnener(), CReg.getPlugin());
		Bukkit.getPluginManager().registerEvents(new CombatListener(), CReg.getPlugin());
		//Bukkit.getPluginManager().registerEvents(new CraftingListeners(), CReg.getPlugin());
		//Bukkit.getPluginManager().registerEvents(new DepozytCommand(), CReg.getPlugin());
		Bukkit.getPluginManager().registerEvents(new GodCommand(), CReg.getPlugin());
		Bukkit.getPluginManager().registerEvents(new JoinListener(), CReg.getPlugin());
		Bukkit.getPluginManager().registerEvents(new RandomTPListener(), CReg.getPlugin()); 
		Bukkit.getPluginManager().registerEvents(new MeteorytListener(), CReg.getPlugin());
		Bukkit.getPluginManager().registerEvents(new AntyNogiListener(), CReg.getPlugin());
		Bukkit.getPluginManager().registerEvents(new SpawnCommand(), CReg.getPlugin());
		Bukkit.getPluginManager().registerEvents(new EnchantListener(), CReg.getPlugin());
		//Bukkit.getPluginManager().registerEvents(new GearboxListener(), CReg.getPlugin());
		Bukkit.getPluginManager().registerEvents(new VanishCommand("vanish2"), CReg.getPlugin());
		Bukkit.getPluginManager().registerEvents(new ItemCommand(), CReg.getPlugin());
		Bukkit.getPluginManager().registerEvents(new CRynekListeners(), CReg.getPlugin());
		//Bukkit.getPluginManager().registerEvents(new InvSeeCommand(), CReg.getPlugin());
		Bukkit.getPluginManager().registerEvents(new MagicWeaponListeners(), CReg.getPlugin());
		Bukkit.getPluginManager().registerEvents(new GodCommand(), CReg.getPlugin());
		//Bukkit.getPluginManager().registerEvents(new Nicasil(), CReg.getPlugin());
		Bukkit.getPluginManager().registerEvents(new EcoListeners(), CReg.getPlugin());
		
		// TODO
		// TODO Bukkit.getPluginManager().registerEvents(new SpawnListeners(), CReg.getPlugin());

		//Bukkit.getPluginManager().registerEvents(new KlanListeners(), CReg.getPlugin());
		//Bukkit.getPluginManager().registerEvents(new CNPCListeners(), CReg.getPlugin());
		//Bukkit.getPluginManager().registerEvents(new WstormListener(), CReg.getPlugin());
		/*new BukkitRunnable() {
			@Override
			public void run() {
				if (WstormListener.pacynki.size() == 0) return;
				List<Suprise> torem = new ArrayList<Suprise>();
				for (Suprise sv : WstormListener.pacynki) {
					if (sv.time < System.currentTimeMillis()) {
						torem.add(sv);
						continue;
					}
					if (sv.time > 12000 + System.currentTimeMillis()) {
						continue;
					}
					Collection<Entity> slv = sv.loc.getWorld().getNearbyEntities(sv.loc, 3, 2, 3, t -> { return (t instanceof LivingEntity); });
					if (slv.size() > 0) {
						for (Entity e : slv) {
							LivingEntity lv = (LivingEntity) e;
							lv.playEffect(EntityEffect.GUARDIAN_TARGET);
							EvokerFangs ev = sv.loc.getWorld().spawn(lv.getLocation(), EvokerFangs.class);
							ev.setOwner(sv.owner);
							lv.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 30, 3, true));
							//ev.setAttackDelay(2);
						}
						torem.add(sv);
					}
				}
				for (Suprise sv : torem) {
					WstormListener.pacynki.remove(sv);
				}
			}
		}.runTaskTimer(CReg.getPlugin(), 5, 5);
		*/
		//
		//Bukkit.getPluginManager().registerEvents(new JungleGen(), CReg.getPlugin());
		//Bukkit.getPluginManager().registerEvents(new IfaceSklep(), CReg.getPlugin());
		//IfaceSklep.loadConfig();
		Bukkit.getPluginManager().registerEvents(new MagicListeners(), CReg.getPlugin());
	}
	
	private static void registerCommands() { 
		CmdM.register(new GatewayCommand());
		CmdM.register(new EventCommand());
		CmdM.register(new ACommand());
		CmdM.register(new GmCommand("gm"));
		CmdM.register(new GmCommand("gamemode"));
		CmdM.register(new TpCommand());
		CmdM.register(new InvSeeCommand());
		CmdM.register(new BcCommand());
		CmdM.register(new SkullCommand());
		CmdM.register(new ChatCommand());
		CmdM.register(new FlyCommand());
		CmdM.register(new GodCommand());
		CmdM.register(new SlavSpyCommand());	
		CmdM.register(new ItemnameCommand());
		CmdM.register(new ItemCommand());
		CmdM.register(new VanishCommand("v"));
		CmdM.register(new VanishCommand("vanish"));
		CmdM.register(new OTPCommand());
		CmdM.register(new ClearCommand());
		CmdM.register(new RynekCommand());
		CmdM.register(new EcoCommand());
		CmdM.register(new MsgCommand());
		CmdM.register(new TpaCommand());
		CmdM.register(new TpacceptCommand());
		CmdM.register(new SpawnCommand());
		CmdM.register(new BackCommand());
		CmdM.register(new WCommand());
		//CmdM.register(new KlanCommand("klan"));
		//CmdM.register(new KlanCommand("k"));
		//CmdM.register(new SklepCommand());
	}

 }
