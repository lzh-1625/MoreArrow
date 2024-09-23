package mcm.mypro.arrow;

import mcm.mypro.global.ArrowData;

import org.bukkit.scheduler.BukkitRunnable;

public class ArrowActionHandler extends BukkitRunnable {
    @Override
    public void run() {
        if (ArrowData.customArrowList.isEmpty()) {
            return;
        }
        ArrowData.customArrowList.removeIf(e -> {
            try {
                return (e != null && e.handle());
            } catch (Exception exception) {
                return true;
            }
        });

    }

}
