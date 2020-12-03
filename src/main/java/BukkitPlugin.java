import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.*;

public class BukkitPlugin extends JavaPlugin {

    @Override
    public void onLoad() {
        super.onLoad();

        // Create a directory for the plugin in the logs directory of the server
        String logDirectory = String.format("./logs/%s", getName());
        new File(logDirectory).mkdirs();

        // Get the logger
        Logger logger = getLogger();
        try {
            // Create a new file handler
            FileHandler fileHandler = new FileHandler(String.format(
                    "%s/%s.log",
                    logDirectory,
                    new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-SSS").format(new Date())
            ));
            // Set the format of the log messages
            // fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setFormatter(new Formatter() {
                @Override
                public String format(LogRecord record) {
                    return String.format(
                            "[%s][%s]: %s\n",
                            new SimpleDateFormat("HH:mm:ss.SSS").format(new Date(record.getMillis())),
                            record.getLevel(),
                            record.getMessage()
                    );
                }
            });
            // Set the log level of the file handler
            fileHandler.setLevel(Level.ALL);
            // Finally add the handler to the logger
            logger.addHandler(fileHandler);
            logger.info("New File Handler added");
        } catch (IOException e) {
            // Log a warning, if adding the handler somehow failed
            logger.warning("Couldn't add File Handler. Read stack trace below for more details");
            e.printStackTrace();
        }
    }

    @Override
    public void onEnable() {
        super.onEnable();

        LogCommandExecutor logCommandExecutor = new LogCommandExecutor(this);
        getCommand("log").setExecutor(logCommandExecutor);
        getCommand("log-anything").setExecutor(logCommandExecutor);
    }

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

    @Override
    public void onDisable() {
        super.onDisable();
        Arrays.stream(getLogger().getHandlers()).forEach(Handler::close);
    }
}
