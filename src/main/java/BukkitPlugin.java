import org.bukkit.plugin.java.JavaPlugin;

public class BukkitPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        super.onEnable();

        LogCommandExecutor logCommandExecutor = new LogCommandExecutor(this);
        getCommand("log").setExecutor(logCommandExecutor);
        getCommand("log-anything").setExecutor(logCommandExecutor);
    }

 /*   @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (command.getName().toLowerCase()) {
            case "log":
                if (args.length < 1) {
                    sender.sendMessage("No message given");
                    return false;
                }
                getLogger().info(String.join(" ", args));
                return true;
            case "log-anything":
                getLogger().info("\"log-anything\" command was triggered. So here's your log message");
                return true;
            default:
                return false;
        }
    }*/
}
