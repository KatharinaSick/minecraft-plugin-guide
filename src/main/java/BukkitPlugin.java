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

        // primary logger of the server
        // Logger logger = Bukkit.getLogger();

        // logger associated with the plugin
        Logger logger = getLogger();

        // set the log level
        logger.setLevel(Level.ALL);

        // log messages to all levels
        // this logs are also logged to the file, as they are using the plugins logger
        logger.severe("Severe log message");
        logger.warning("Warning log message");
        logger.info("Info log message");
        logger.config("Config log message");
        logger.fine("Fine log message");
        logger.finer("Finer log message");
        logger.finest("Finest log message");

        // another way of logging messages to all log levels
        logger.log(Level.SEVERE, "Another Severe log message");
        logger.log(Level.WARNING, "Another Warning log message");
        logger.log(Level.INFO, "Another Info log message");
        logger.log(Level.CONFIG, "Another Config log message");
        logger.log(Level.FINE, "Another Fine log message");
        logger.log(Level.FINER, "Another Finer log message");
        logger.log(Level.FINEST, "Another Finest log message");
    }

    @Override
    public void onDisable() {
        super.onDisable();
        Arrays.stream(getLogger().getHandlers()).forEach(Handler::close);
    }
}
