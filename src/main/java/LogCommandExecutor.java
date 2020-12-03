import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class LogCommandExecutor implements CommandExecutor {

    private Plugin plugin;

    public LogCommandExecutor(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (command.getName().toLowerCase()) {
            case "log":
                if (args.length < 1) {
                    sender.sendMessage("No message given");
                    return false;
                }
                plugin.getLogger().info(String.join(" ", args));
                return true;
            case "log-anything":
                plugin.getLogger().info("\"log-anything\" command was triggered. So here's your log message");
                return true;
            default:
                return false;
        }
    }
}
