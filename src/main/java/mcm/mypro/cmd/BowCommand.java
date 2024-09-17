package mcm.mypro.cmd;

import mcm.mypro.bow.CustomBow;
import mcm.mypro.bow.CustomBowHandler;
import mcm.mypro.consts.Eum;
import mcm.mypro.utils.NameSpace;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class BowCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.isOp()) {
                return false;
            }
            switch (args[0]) {
                case "give":
                    giveBow(player, args[1]);
            }
        } else {
            sender.sendMessage("该命令只能由玩家执行!");
        }
        return true;
    }


    private void giveBow(Player player, String bowType) {
        ItemStack specialBow = new ItemStack(Material.BOW);
        ItemMeta meta = specialBow.getItemMeta();
        assert meta != null;
        specialBow.setItemMeta(meta);
        PersistentDataContainer dataContainer = meta.getPersistentDataContainer();
        CustomBow customBow = CustomBowHandler.getBow(bowType);
        if (customBow == null) {
            return;
        }
        meta.setDisplayName(customBow.getDisplayName());
        dataContainer.set(NameSpace.customBow, PersistentDataType.STRING, bowType);
        specialBow.setItemMeta(meta);
        player.getInventory().addItem(specialBow);
        player.sendMessage("已获得【" + customBow.getDisplayName() + "】");
    }
}