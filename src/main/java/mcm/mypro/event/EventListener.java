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
//        Eum.CustomArrowType customArrowType = getArrowType(player.getInventory().getItemInMainHand());
//        if (customArrowType == null) {
//            return;
//        }


//        switch (customArrowType) {
//            case TRACE_LIGHTNING:
//            case TRACE_FIRE:
//            case TRACE_TNT:
//            case TRACE: {
//                Player targetPlayer = getRayTracePlayer(player);
//                if (targetPlayer != null) {
//                    // 获取主手物品
//                    ItemStack mainHandItem = player.getInventory().getItemInMainHand();
//                    if (mainHandItem.hasItemMeta()) {
//                        ItemMeta itemMeta = mainHandItem.getItemMeta();
//                        assert itemMeta != null;
//                        itemMeta.getPersistentDataContainer().set(NameSpace.traceTarget, PersistentDataType.STRING, targetPlayer.getName());
//                        mainHandItem.setItemMeta(itemMeta);
//                        player.sendMessage("已将目标设定为 " + targetPlayer.getName());
//                    }
//                }
//                break;
//            }
//        }

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

//        Eum.CustomArrowType customArrowType = Eum.CustomArrowType.valueOf(Objects.requireNonNull(player.getInventory().getItemInMainHand().getItemMeta()).getPersistentDataContainer().get(NameSpace.customBow, PersistentDataType.STRING));

//        switch (customArrowType) {
//            case TRACE:
//            case TRACE_LIGHTNING:
//            case TRACE_TNT:
//            case TRACE_FIRE: {
//                String targetName;
//                try {
//                    targetName = Objects.requireNonNull(Objects.requireNonNull(player.getPlayer()).getInventory().getItemInMainHand().getItemMeta()).getPersistentDataContainer().get(NameSpace.traceTarget, PersistentDataType.STRING);
//                    if (targetName == null || targetName.isEmpty()) {
//                        return;
//                    }
//                } catch (NullPointerException e) {
//                    return;
//                }
//
//                arrow.setGravity(false);
//                switch (customArrowType) {
//                    case TRACE:
//                        ArrowData.tagArrowList.add(new TrackArrow(arrow, Bukkit.getPlayer(targetName)));
//                        break;
//                    case TRACE_FIRE:
//                        ArrowData.tagArrowList.add(new TrackArrowFire(arrow, Bukkit.getPlayer(targetName)));
//                        break;
//                    case TRACE_LIGHTNING:
//                        ArrowData.tagArrowList.add(new TrackArrowLightning(arrow, Bukkit.getPlayer(targetName)));
//                        break;
//                    case TRACE_TNT:
//                        ArrowData.tagArrowList.add(new TraceArrowTnt(arrow, Bukkit.getPlayer(targetName)));
//                        break;
//                }
//                break;
//            }
//            case TNT:
//                ArrowData.tagArrowList.add(new TntArrow(arrow, player));
//                break;
//            case LIGHTNING:
//                ArrowData.tagArrowList.add(new LightningArrow(arrow));
//                break;
//            case ENDER:
//                ArrowData.tagArrowList.add(new EnderArrow(arrow, player));
//                break;
//        }


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