package mcm.mypro.bow;

import mcm.mypro.bow.CustomBow;
import mcm.mypro.bow.customBowImpl.*;
import mcm.mypro.consts.Eum;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.RayTraceResult;

public class CustomBowHandler {
    public static CustomBow getBow(String bowName) {
        bowName = bowName.toUpperCase();
        try {
            switch (Eum.CustomArrowType.valueOf(bowName)) {
                case TRACE:
                    return new TraceBow();
                case TRACE_FIRE:
                    return new TraceFireBow();
                case TRACE_LIGHTNING:
                    return new TraceLightningBow();
                case TRACE_TNT:
                    return new TraceTntBow();
                case TNT:
                    return new TntBow();
                case LIGHTNING:
                    return new LightningBow();
                case ENDER:
                    return new EnderBow();
                case ICE:
                    return new IceBow();
                case TRACE_ICE:
                    return new TraceIceBow();
                case FIXED_POINT:
                    return new FixedPointBow();
                case FIXED_POINT_TNT:
                    return new FixedPointTntBow();
                case SMOKE:
                    return new SmokeBow();
            }
        } catch (Exception e) {
            return null;
        }
        return null;
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

    public static Block getRayTraceBlock(Player player) {
        Location eyeLocation = player.getEyeLocation();
        RayTraceResult result = player.getWorld().rayTraceBlocks(eyeLocation, eyeLocation.getDirection(), 2000);
        if (result != null && result.getHitBlock() != null) {
            return result.getHitBlock();
        }
        return null;
    }
}
