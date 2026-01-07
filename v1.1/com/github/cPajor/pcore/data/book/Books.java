package com.github.cPajor.pcore.data.book;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import com.github.cPajor.pcore.core.data.CItem;
import com.github.cPajor.pcore.data.util.Colors;
import com.github.cPajor.pcore.data.util.TextUtil;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;

public class Books {
	
	@SuppressWarnings("deprecation")
	public static void bookPomoc(Player p) {
		ItemStack book = new CItem(Material.WRITTEN_BOOK).toIs();
		BookMeta bm = (BookMeta) book.getItemMeta();
		bm.setAuthor("Pajor");
		bm.setTitle(Colors.fix(" "));

		bm.spigot().addPage(TextUtil.toBookPage(
				new TextUtil("&4/panel &7- panel gracza").setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/panel")).toTextComponent(),
				new TextUtil("&4/bazar").setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/bazar")).toTextComponent()
		));
		book.setItemMeta(bm);
		p.openBook(book);
	}

}
