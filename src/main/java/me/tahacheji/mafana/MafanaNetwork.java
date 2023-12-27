package me.tahacheji.mafana;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;

import me.tahacheji.mafana.listener.*;
import me.tahacheji.mafana.packets.TablistHandler;
import me.tahacheji.mafana.loaders.ConfigLoader;
import me.tahacheji.mafana.util.VersionUtil;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public final class MafanaHub extends JavaPlugin {

    private static final String configFileName = "tablistConfig.yml";

    private BukkitListener listener;
    private TablistHandler tablistHandler;
    private ConfigLoader configLoader;
    private static MafanaHub instance;

    private File configFile;


    @Override
    public void onEnable() {
        instance = this;
        this.tablistHandler = new TablistHandler(this);
        generateConfigFile();
        this.configLoader = new ConfigLoader(this.configFile);
        this.listener = new BukkitListener(this.configLoader);
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        if(VersionUtil.isNewTablist()) {
            manager.addPacketListener(new PlayerRemoveListener(this,ListenerPriority.NORMAL, PacketType.Play.Server.PLAYER_INFO_REMOVE));
        }
        manager.addPacketListener(new PlayerInfoListener(this, ListenerPriority.NORMAL, PacketType.Play.Server.PLAYER_INFO));
        manager.addPacketListener(new NamedEntityListener(this, ListenerPriority.NORMAL, PacketType.Play.Server.NAMED_ENTITY_SPAWN));
        getServer().getPluginManager().registerEvents(this.listener, this);

        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);


    }

    private void generateConfigFile() {
        File dataFolder = new File(this.getDataFolder().getParentFile(), "TablistManagerAPI").getAbsoluteFile();
        if(!dataFolder.exists()){
            this.getLogger().info("Config folder 'TablistManagerAPI' doesn't exists. Creating one 4 ya.");
            if(dataFolder.mkdirs()) {
                this.getLogger().info("Config folder created");
            } else {
                this.getLogger().severe("Config folder couldn't be created (Do you have permissions?)") ;
            }
        }
        File file = new File(dataFolder, configFileName);
        this.configFile = file;
        if(!file.exists()) {
            getLogger().info("Attempting to save default config for TablistManagerAPI at " + file.getPath());
            try {
                InputStream fileContent = getResource(configFileName);
                file.createNewFile();
                FileOutputStream fs = new FileOutputStream(file.getAbsolutePath());
                fs.write(fileContent.readAllBytes());
                fs.close();
            } catch (IOException e) {
                getLogger().severe("Default config file for TablistManagerAPI couldn't be created") ;
            }
        }
    }

    public void reload() {
        this.generateConfigFile();
        this.configLoader.reloadFields();
        this.listener.reloadChanges();
    }

    public static MafanaHub getInstance() {
        return instance;
    }

    public JavaPlugin getPlugin() {
        return this;
    }

    public ConfigLoader getConfigLoader() {
        return configLoader;
    }

    /**
     * @return An instance of the TablistHandler attached to this TablistManager instance
     */
    public TablistHandler getTablistHandler() {
        return tablistHandler;
    }

}
