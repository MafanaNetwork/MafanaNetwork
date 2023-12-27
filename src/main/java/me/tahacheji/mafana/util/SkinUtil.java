package me.tahacheji.mafana.packets.fakePlayer;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.tahacheji.mafana.packets.fakePlayer.SkinType;
import org.bukkit.OfflinePlayer;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SkinUtil {

    private final static Map<UUID, String[]> cache = new HashMap<>();

    /**
     * Get the skin data by a player's unique identifier
     *
     * @param uuid the unique identifier to get the skin data by
     * @return the skin data
     */
    public static String[] getSkinData(UUID uuid) throws IOException {
        if (cache.containsKey(uuid)) {
            return cache.get(uuid);
        }

        final URL url = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid.toString().replace("-", "") + "?unsigned=false");
        final JsonObject json = new JsonParser().parse(new InputStreamReader(url.openStream())).getAsJsonObject().get("properties").getAsJsonArray().get(0).getAsJsonObject();

        return cache.put(uuid, new String[]{
                json.get("value").getAsString(),
                json.get("signature").getAsString()
        });
    }

    public static String[] getSkinDataThrown(UUID uuid) {
        try {
            return getSkinData(uuid);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return null;
    }

    public static SkinType getPlayerSkin(OfflinePlayer player) {
        String[] x = getSkinDataThrown(player.getUniqueId());
        if (x != null) {
            String texture = x[0];
            String signature = x[1];

            System.out.println("Texture: " + texture);
            System.out.println("Signature: " + signature);

            // Add a null check before creating SkinType
            if (texture != null && signature != null) {
                return new SkinType(signature, texture);
            } else {
                System.out.println("Texture or signature is null!");
            }
        }
        return null;
    }

}
