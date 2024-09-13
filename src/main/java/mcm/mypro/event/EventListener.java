package mcm.mypro.event;

import mcm.mypro.Mypro;
import mcm.mypro.arrow.CustomArrow;
import mcm.mypro.arrow.CustomArrowImpl.TrackArrow;
import mcm.mypro.arrow.CustomArrowImpl.TrackArrowFire;
import mcm.mypro.arrow.CustomArrowImpl.TrackArrowLightning;
import mcm.mypro.consts.Consts;
import mcm.mypro.consts.Eum;
import mcm.mypro.global.ArrowData;
import mcm.mypro.utils.NameSpace;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.RayTraceResult;

import java.util.Objects;

public class EventListener implements Listener {


    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        if (!Objects.equals(event.getAction(), Action.LEFT_CLICK_AIR)) {
            return;
        }
        Player player = event.getPlayer();

        Eum.CustomArrowType customArrowType = getArrowType(player.getInventory().getItemInMainHand());
        if (customArrowType == null) {
            return;
        }

        switch (customArrowType) {
            case TRACE_LIGHTNING:
            case TRACE_FIRE:
            case TRACE: {
                Player targetPlayer = getRayTracePlayer(player);
                if (targetPlayer != null) {
                    // 获取主手物品
                    ItemStack mainHandItem = player.getInventory().getItemInMainHand();
                    if (mainHandItem.hasItemMeta()) {
                        ItemMeta itemMeta = mainHandItem.getItemMeta();
                        assert itemMeta != null;
                        itemMeta.getPersistentDataContainer().set(NameSpace.traceTarget, PersistentDataType.STRING, targetPlayer.getName());
                        mainHandItem.setItemMeta(itemMeta);
                        player.sendMessage("已将目标设定为 " + targetPlayer.getName());
                    }
                }
            }
        }

    }

    // 获取视线上的玩家
    public static Player getRayTracePlayer(Player player) {
        Location eyeLocation = player.getEyeLocation();
        RayTraceResult result = player.getWorld().rayTraceEntities(eyeLocation.add(eyeLocation.getDirection()), eyeLocation.getDirection(), 200);
        if (result != null && result.getHitEntity() != null && result.getHitEntity() instanceof Player) {
            return (Player) result.getHitEntity();
        }
        return null;
    }

    @EventHandler
    public void onEntityShootBowEvent(EntityShootBowEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getEntity();
        Eum.CustomArrowType customArrowType = Eum.CustomArrowType.valueOf(player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(NameSpace.customBow, PersistentDataType.STRING));
        switch (customArrowType) {
            case TRACE:
            case TRACE_LIGHTNING:
            case TRACE_FIRE: {
                String targetName;
                try {
                    targetName = Objects.requireNonNull(Objects.requireNonNull(player.getPlayer()).getInventory().getItemInMainHand().getItemMeta()).getPersistentDataContainer().get(NameSpace.traceTarget, PersistentDataType.STRING);
                    if (targetName == null || targetName.isEmpty()) {
                        return;
                    }
                } catch (NullPointerException e) {
                    return;
                }
                Entity projectile = event.getProjectile();

                if (projectile instanceof Arrow) {
                    Arrow arrow = (Arrow) projectile;
                    arrow.setGravity(false);
                    switch (customArrowType) {
                        case TRACE:
                            ArrowData.tagArrowList.add(new TrackArrow(arrow, Bukkit.getPlayer(targetName)));
                            break;
                        case TRACE_FIRE:
                            ArrowData.tagArrowList.add(new TrackArrowFire(arrow, Bukkit.getPlayer(targetName)));
                            break;
                        case TRACE_LIGHTNING:
                            ArrowData.tagArrowList.add(new TrackArrowLightning(arrow, Bukkit.getPlayer(targetName)));
                            break;
                    }
                }
            }
        }


    }

    @EventHandler
    public void on(PlayerJoinEvent event) {
        ItemStack specialBow = new ItemStack(Material.BOW);
        ItemMeta meta = specialBow.getItemMeta();

        if (meta != null) {
            meta.setDisplayName("§6特殊弓"); // 设置为黄色的“特殊弓”
            PersistentDataContainer dataContainer = meta.getPersistentDataContainer();
            dataContainer.set(NameSpace.customBow, PersistentDataType.STRING, Eum.CustomArrowType.TRACE_LIGHTNING.value());
            specialBow.setItemMeta(meta);
        }
        event.getPlayer().getInventory().addItem(specialBow);
    }

    private Eum.CustomArrowType getArrowType(ItemStack itemStack) {
        String content = Objects.requireNonNull(itemStack.getItemMeta()).getPersistentDataContainer().get(NameSpace.customBow, PersistentDataType.STRING);
        return Eum.CustomArrowType.valueOf(content);
    }
}