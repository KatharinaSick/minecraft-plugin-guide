import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitPlugin extends JavaPlugin {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("communicate")) {
            // Chat message
            sender.sendMessage("This is a chat message");

            // In game text
            Player player = ((Player) sender);
            player.sendTitle("This is a title", "And a subtitle", 10, 70, 20);

            // Sound
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
            return true;
        }

        return super.onCommand(sender, command, label, args);
    }
}
