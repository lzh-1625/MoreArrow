package mcm.mypro.event;

import mcm.mypro.bow.CustomBow;
import mcm.mypro.bow.CustomBowHandler;
import mcm.mypro.consts.Eum;
import mcm.mypro.utils.NameSpace;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class EventListener implements Listener {


    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        if (!Objects.equals(event.getAction(), Action.LEFT_CLICK_AIR)) {
            return;
        }
        Player player = event.getPlayer();

        CustomBow customBow = CustomBowHandler.getBow(Objects.requireNonNull(player.getInventory().getItemInMainHand().getItemMeta()).getPersistentDataContainer().get(NameSpace.customBow, PersistentDataType.STRING));
        if (customBow == null) {
            return;
        }

        customBow.leftClick(player);
    }


    @EventHandler
    public void onEntityShootBowEvent(EntityShootBowEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getEntity();
        Entity projectile = event.getProjectile();

        if (!(projectile instanceof Arrow)) {
            return;
        }
        Arrow arrow = (Arrow) projectile;

        CustomBow customBow = CustomBowHandler.getBow(Objects.requireNonNull(player.getInventory().getItemInMainHand().getItemMeta()).getPersistentDataContainer().get(NameSpace.customBow, PersistentDataType.STRING));
        if (customBow == null) {
            return;
        }
        customBow.shootArrow(arrow,player);

    }

    // 阻止特殊弓被修复
    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent event) {
        ItemStack firstItem = event.getInventory().getItem(0);
        if (firstItem != null) {
            if (getArrowType(firstItem) != null) {
                event.setResult(null);
                event.getInventory().setRepairCost(0);
            }
        }
    }

    private Eum.CustomArrowType getArrowType(ItemStack itemStack) {
        String content = Objects.requireNonNull(itemStack.getItemMeta()).getPersistentDataContainer().get(NameSpace.customBow, PersistentDataType.STRING);
        return Eum.CustomArrowType.valueOf(content);
    }
}