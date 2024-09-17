package mcm.mypro.arrow;

import mcm.mypro.global.ArrowData;

import org.bukkit.scheduler.BukkitRunnable;

public class ArrowActionHandler extends BukkitRunnable {
    @Override
    public void run() {
        System.out.println(ArrowData.tagArrowList.size());
        if (ArrowData.tagArrowList.isEmpty()) {
            return;
        }
        ArrowData.tagArrowList.removeIf(e -> {
            try {
                return (e != null && e.handle());
            } catch (Exception exception) {
                return true;
            }
        });

    }

}
