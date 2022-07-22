package dev.norbiros.denoriabot.events;

import dev.norbiros.denoriabot.DenoriaBot;
import dev.norbiros.denoriabot.configuration.Config;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.event.EventBus;
import net.luckperms.api.event.node.NodeRemoveEvent;
import net.luckperms.api.event.node.NodeAddEvent;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.NodeType;
import net.luckperms.api.node.types.InheritanceNode;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import java.util.logging.Level;


import java.util.Set;

public class LuckyPermsHandler {
    private final DenoriaBot plugin;
    private final LuckPerms luckPerms;

    public LuckyPermsHandler(DenoriaBot plugin, LuckPerms luckPerms) {
        this.plugin = plugin;
        this.luckPerms = luckPerms;
    }

    public void register() {
        EventBus eventBus = this.luckPerms.getEventBus();
        eventBus.subscribe(this.plugin, NodeRemoveEvent.class, this::onNodeRemove);
        eventBus.subscribe(this.plugin, NodeAddEvent.class, this::onNodeAdd);
    }

    private void onNodeRemove(NodeRemoveEvent e) {

        Node node = e.getNode();
        if (!e.isUser()) { return; }
        if (node.getType() != NodeType.INHERITANCE) { return; }

        InheritanceNode inheritanceNode = ((InheritanceNode) node);
        User user = (User) e.getTarget();
        Config config = new Config();

        String groupName = inheritanceNode.getGroupName();
        String userID = getDiscordId(config, user.getUsername());
        if (userID == null) return;

        if (DenoriaBot.getInstance().getConfig().getStringList("ranks").contains(groupName)) {
            Guild guild = plugin.jda.getGuildById(DenoriaBot.getInstance().getConfig().getLong("guild-id"));
            Role role = guild.getRolesByName(groupName, true).get(0);
            if (role == null) {
                plugin.getLogger().log(Level.SEVERE, "Role \"" + groupName + "\" doesn't exists on your discord server!");
                return;
            }
            guild.retrieveMemberById(userID).queue(discordUser -> {
                guild.removeRoleFromMember(discordUser, role).queue();
            });
        }
    }

    private void onNodeAdd(NodeAddEvent e) {

        Node node = e.getNode();
        if (!e.isUser()) { return; }
        if (node.getType() != NodeType.INHERITANCE) { return; }

        InheritanceNode inheritanceNode = ((InheritanceNode) node);
        User user = (User) e.getTarget();
        Config config = new Config();

        String groupName = inheritanceNode.getGroupName();
        String userID = getDiscordId(config, user.getUsername());
        if (userID == null) return;

        if (DenoriaBot.getInstance().getConfig().getString("ranks." + groupName) != null) {
            Guild guild = plugin.jda.getGuildById(DenoriaBot.getInstance().getConfig().getLong("guild-id"));
            Role role = guild.getRoleById(DenoriaBot.getInstance().getConfig().getString("ranks." + groupName));
            if (role == null) {
                plugin.getLogger().log(Level.SEVERE, "Role \"" + groupName + "\" doesn't exists on your discord server!");
                return;
            }
            guild.retrieveMemberById(userID).queue(discordUser -> {
                guild.addRoleToMember(discordUser, role).queue();
            });
        }
    }

    public String getDiscordId(Config config, String playerName) {
        Set<String> playerList = config.getCustomConfig().getConfigurationSection("player").getKeys(false);
        for (String key : playerList) {
            if (config.getCustomConfig().get("player." + key + ".name").toString().equalsIgnoreCase( playerName )) {
                return key;
            }
        }
        return "";
    }

}