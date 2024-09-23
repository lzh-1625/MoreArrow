package mcm.mypro.bow.customBowImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import mcm.mypro.arrow.customArrowImpl.FixedPointArrow;
import mcm.mypro.arrow.customArrowImpl.TrackArrow;
import mcm.mypro.bow.CustomBow;
import mcm.mypro.bow.CustomBowHandler;
import mcm.mypro.global.ArrowData;
import mcm.mypro.utils.NameSpace;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FixedPointBow implements CustomBow {
    @Override
    public String getDisplayName() {
        return "§6弓(定点)";
    }

    @Override
    public void shootArrow(Arrow arrow, Player player) {
        try {
            String coordinateStr = Objects.requireNonNull(Objects.requireNonNull(player.getPlayer()).getInventory().getItemInMainHand().getItemMeta()).getPersistentDataContainer().get(NameSpace.fixedPoint, PersistentDataType.STRING);
            assert coordinateStr != null;
            String[] coordinateList = coordinateStr.split("\\|");
            arrow.setGravity(false);
            ArrowData.customArrowList.add(new FixedPointArrow(arrow, Integer.parseInt(coordinateList[0]), Integer.parseInt(coordinateList[1])));
        } catch (Exception ignored) {

        }

    }

    @Override
    public void leftClick(Player player) {
        Block block = CustomBowHandler.getRayTraceBlock(player);
        if (block == null) {
            return;
        }
        double radius = 3.0;
        // 计算圆上的点
        for (int i = 0; i < 360; i += 10) {
            double radians = Math.toRadians(i);
            double x = radius * Math.cos(radians);
            double z = radius * Math.sin(radians);
            // 生成粒子
            player.getWorld().spawnParticle(Particle.COMPOSTER, block.getLocation().add(x + 0.5, 1.2, z + 0.5), 1);
            player.getWorld().spawnParticle(Particle.COMPOSTER, block.getLocation().add(x + 0.5, 0, z + 0.5), 1);
        }
        ItemStack mainHandItem = player.getInventory().getItemInMainHand();
        if (mainHandItem.hasItemMeta()) {
            ItemMeta itemMeta = mainHandItem.getItemMeta();
            assert itemMeta != null;
            itemMeta.getPersistentDataContainer().set(NameSpace.fixedPoint, PersistentDataType.STRING, block.getX() + "|" + block.getZ());
            mainHandItem.setItemMeta(itemMeta);
            player.sendMessage("已将目标设定为 " + block.getX() + "," + block.getZ());
        }
    }
}
