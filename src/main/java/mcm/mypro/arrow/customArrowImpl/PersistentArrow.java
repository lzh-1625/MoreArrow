package mcm.mypro.arrow.customArrowImpl;

import mcm.mypro.arrow.CustomArrow;
import org.bukkit.Particle;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;

public class PersistentArrow extends CustomArrowBase implements CustomArrow {
    public PersistentArrow(Arrow arrow, Player belongPlayer) {
        this.arrow = arrow;
        this.belongPlayer = belongPlayer;
    }

    @Override
    public Boolean handle() {
        if (arrow == null || arrow.isDead()) {
            return true;
        }
//        if (arrow.getTicksLived() % 5 != 0) {
//            return false;
//        }
        double radius = 5.0;
        for (int i = 0; i < 360; i += 30) {
            double radians = Math.toRadians(i);
            double x = radius * Math.cos(radians);
            double z = radius * Math.sin(radians);
            // 生成粒子
            belongPlayer.getWorld().spawnParticle(Particle.FLASH, arrow.getLocation().add(x + 0.5, 1.2, z + 0.5), 5);
            belongPlayer.getWorld().spawnParticle(Particle.FLASH, arrow.getLocation().add(x + 0.5, 0, z + 0.5), 5);
        }
        return false;
    }
}
