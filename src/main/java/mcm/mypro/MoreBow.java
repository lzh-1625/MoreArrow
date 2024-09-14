package mcm.mypro;

import mcm.mypro.cmd.BowCommand;
import mcm.mypro.event.EventListener;
import mcm.mypro.arrow.ArrowActionHandler;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;


public final class MoreBow extends JavaPlugin implements Listener {


    @Override
    public void onEnable() {
        eventRegister(); // 注册事件监听器
        cmdRegister(); //


        new ArrowActionHandler().runTaskTimer(this, 0L, 1L);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

    }

    public void eventRegister() {
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);

    }

    public void cmdRegister() {
        Objects.requireNonNull(this.getCommand("morebow")).setExecutor(new BowCommand());
    }

    public static Plugin plugin() {
        return MoreBow.getPlugin(MoreBow.class);
    }

}
