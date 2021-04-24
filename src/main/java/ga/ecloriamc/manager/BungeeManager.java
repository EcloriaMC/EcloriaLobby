package ga.ecloriamc.manager;
import java.net.InetSocketAddress;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

import ga.ecloriamc.EcloriaLobby;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.collect.Iterables;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class BungeeManager {



    private final PluginMessageListener messageListener;
    private final Plugin plugin;
    private final Map<String, Queue<CompletableFuture<?>>> callbackMap;

    private Map<String, ForwardConsumer> forwardListeners;
    private ForwardConsumer globalForwardListener;


    public BungeeManager(EcloriaLobby plugin) {
        this.plugin = Objects.requireNonNull(plugin, "plugin cannot be null");
        this.callbackMap = new HashMap<>();

        this.messageListener = this::onPluginMessageReceived;

        Messenger messenger = Bukkit.getServer().getMessenger();
        messenger.registerOutgoingPluginChannel(plugin, "BungeeCord");
        messenger.registerIncomingPluginChannel(plugin, "BungeeCord", messageListener);
    }

    public void registerForwardListener(ForwardConsumer globalListener) {
        this.globalForwardListener = globalListener;
    }

    public void registerForwardListener(String channelName, ForwardConsumer listener) {
        if (forwardListeners == null) {
            forwardListeners = new HashMap<>();
        }
        synchronized (forwardListeners) {
            forwardListeners.put(channelName, listener);
        }
    }

    public CompletableFuture<Integer> getPlayerCount(Player p,String serverName) {

        CompletableFuture<Integer> future = new CompletableFuture<>();

        synchronized (callbackMap) {
            callbackMap.compute("PlayerCount-" + serverName, this.computeQueueValue(future));
        }

        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("PlayerCount");
        output.writeUTF(serverName);
        p.sendPluginMessage(this.plugin, "BungeeCord", output.toByteArray());
        return future;
    }

    public CompletableFuture<List<String>> getPlayerList(String serverName) {
        Player player = getFirstPlayer();
        CompletableFuture<List<String>> future = new CompletableFuture<>();

        synchronized (callbackMap) {
            callbackMap.compute("PlayerList-" + serverName, this.computeQueueValue(future));
        }

        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("PlayerList");
        output.writeUTF(serverName);
        player.sendPluginMessage(this.plugin, "BungeeCord", output.toByteArray());
        return future;
    }

    public CompletableFuture<List<String>> getServers() {
        Player player = getFirstPlayer();
        CompletableFuture<List<String>> future = new CompletableFuture<>();

        synchronized (callbackMap) {
            callbackMap.compute("GetServers", this.computeQueueValue(future));
        }

        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("GetServers");
        player.sendPluginMessage(this.plugin, "BungeeCord", output.toByteArray());
        return future;
    }

    public void connect(Player player, String serverName) {
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("Connect");
        output.writeUTF(serverName);
        player.sendPluginMessage(this.plugin, "BungeeCord", output.toByteArray());
    }


    public void connectOther(String playerName, String server) {
        Player player = getFirstPlayer();
        ByteArrayDataOutput output = ByteStreams.newDataOutput();

        output.writeUTF("ConnectOther");
        output.writeUTF(playerName);
        output.writeUTF(server);

        player.sendPluginMessage(this.plugin, "BungeeCord", output.toByteArray());
    }

    public CompletableFuture<InetSocketAddress> getIp(Player player) {
        CompletableFuture<InetSocketAddress> future = new CompletableFuture<>();

        synchronized (callbackMap) {
            callbackMap.compute("IP", this.computeQueueValue(future));
        }

        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("IP");
        player.sendPluginMessage(this.plugin, "BungeeCord", output.toByteArray());
        return future;
    }

    public void sendMessage(String playerName, String message) {
        Player player = getFirstPlayer();
        ByteArrayDataOutput output = ByteStreams.newDataOutput();

        output.writeUTF("Message");
        output.writeUTF(playerName);
        output.writeUTF(message);
        player.sendPluginMessage(this.plugin, "BungeeCord", output.toByteArray());
    }

    public CompletableFuture<String> getServer() {
        Player player = getFirstPlayer();
        CompletableFuture<String> future = new CompletableFuture<>();

        synchronized (callbackMap) {
            callbackMap.compute("GetServer", this.computeQueueValue(future));
        }

        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("GetServer");
        player.sendPluginMessage(this.plugin, "BungeeCord", output.toByteArray());
        return future;
    }

    public CompletableFuture<String> getUUID(Player player) {
        CompletableFuture<String> future = new CompletableFuture<>();

        synchronized (callbackMap) {
            callbackMap.compute("UUID", this.computeQueueValue(future));
        }

        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("UUID");
        player.sendPluginMessage(this.plugin, "BungeeCord", output.toByteArray());
        return future;
    }

    public CompletableFuture<String> getUUID(String playerName) {
        Player player = getFirstPlayer();
        CompletableFuture<String> future = new CompletableFuture<>();

        synchronized (callbackMap) {
            callbackMap.compute("UUIDOther-" + playerName, this.computeQueueValue(future));
        }

        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("UUIDOther");
        output.writeUTF(playerName);
        player.sendPluginMessage(this.plugin, "BungeeCord", output.toByteArray());
        return future;
    }


    public CompletableFuture<InetSocketAddress> getServerIp(Player p ,String serverName) {

        CompletableFuture<InetSocketAddress> future = new CompletableFuture<>();

        synchronized (callbackMap) {
            callbackMap.compute("ServerIP-" + serverName, this.computeQueueValue(future));
        }

        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("ServerIP");
        output.writeUTF(serverName);
        p.sendPluginMessage(this.plugin, "BungeeCord", output.toByteArray());
        return future;
    }


    public void kickPlayer(String playerName, String kickMessage) {
        Player player = getFirstPlayer();
        CompletableFuture<InetSocketAddress> future = new CompletableFuture<>();

        synchronized (callbackMap) {
            callbackMap.compute("KickPlayer", this.computeQueueValue(future));
        }

        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("KickPlayer");
        output.writeUTF(playerName);
        output.writeUTF(kickMessage);
        player.sendPluginMessage(this.plugin, "BungeeCord", output.toByteArray());
    }


    public void forward(String server, String channelName, byte[] data) {
        Player player = getFirstPlayer();

        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("Forward");
        output.writeUTF(server);
        output.writeUTF(channelName);
        output.writeShort(data.length);
        output.write(data);
        player.sendPluginMessage(this.plugin, "BungeeCord", output.toByteArray());
    }


    public void forwardToPlayer(String playerName, String channelName, byte[] data) {
        Player player = getFirstPlayer();

        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("ForwardToPlayer");
        output.writeUTF(playerName);
        output.writeUTF(channelName);
        output.writeShort(data.length);
        output.write(data);
        player.sendPluginMessage(this.plugin, "BungeeCord", output.toByteArray());
    }

    @SuppressWarnings("unchecked")
    private void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equalsIgnoreCase("BungeeCord")) return;

        ByteArrayDataInput input = ByteStreams.newDataInput(message);
        String subchannel = input.readUTF();

        synchronized (callbackMap) {
            Queue<CompletableFuture<?>> callbacks;

            if (subchannel.equals("PlayerCount") || subchannel.equals("PlayerList") ||
                    subchannel.equals("UUIDOther") || subchannel.equals("ServerIP")) {

                String identifier = input.readUTF(); // Server/player name
                callbacks = callbackMap.get(subchannel + "-" + identifier);

                if (callbacks == null || callbacks.isEmpty())  { return; }

                CompletableFuture<?> callback = callbacks.poll();

                try {
                    switch (subchannel) {
                        case "PlayerCount":
                            ((CompletableFuture<Integer>) callback).complete(input.readInt());
                            break;

                        case "PlayerList":
                            ((CompletableFuture<List<String>>) callback).complete(Arrays.asList(input.readUTF().split(", ")));
                            break;

                        case "UUIDOther":
                            ((CompletableFuture<String>) callback).complete(input.readUTF());
                            break;

                        case "ServerIP": {
                            String ip = input.readUTF();
                            int port = input.readUnsignedShort();
                            ((CompletableFuture<InetSocketAddress>) callback).complete(new InetSocketAddress(ip, port));
                            break;
                        }
                    }
                } catch(Exception ex) {
                    callback.completeExceptionally(ex);
                }

                return;
            }

            callbacks = callbackMap.get(subchannel);

            if (callbacks == null) {
                short dataLength = input.readShort();
                byte[] data = new byte[dataLength];
                input.readFully(data);

                if (globalForwardListener != null) {
                    globalForwardListener.accept(subchannel, player, data);
                }

                if (forwardListeners != null) {
                    synchronized (forwardListeners) {
                        ForwardConsumer listener = forwardListeners.get(subchannel);
                        if (listener != null) {
                            listener.accept(subchannel, player, data);
                        }
                    }
                }

                return;
            }

            if (callbacks.isEmpty()) {
                return;
            }

            final CompletableFuture<?> callback = callbacks.poll();

            try {
                switch (subchannel) {
                    case "GetServers":
                        ((CompletableFuture<List<String>>) callback).complete(Arrays.asList(input.readUTF().split(", ")));
                        break;

                    case "GetServer":
                    case "UUID":
                        ((CompletableFuture<String>) callback).complete(input.readUTF());
                        break;

                    case "IP": {
                        String ip = input.readUTF();
                        int port = input.readInt();
                        ((CompletableFuture<InetSocketAddress>) callback).complete(new InetSocketAddress(ip, port));
                        break;
                    }

                    default:
                        break;
                }
            } catch(Exception ex) {
                callback.completeExceptionally(ex);
            }
        }
    }

    public void unregister() {
        Messenger messenger = Bukkit.getServer().getMessenger();
        messenger.unregisterIncomingPluginChannel(plugin, "BungeeCord", messageListener);
        messenger.unregisterOutgoingPluginChannel(plugin);
        callbackMap.clear();
    }

    private BiFunction<String, Queue<CompletableFuture<?>>, Queue<CompletableFuture<?>>> computeQueueValue(CompletableFuture<?> queueValue) {
        return (key, value) -> {
            if (value == null) value = new ArrayDeque<CompletableFuture<?>>();
            value.add(queueValue);
            return value;
        };
    }

    private Player getFirstPlayer() {

        Player firstPlayer = getFirstPlayer0(Bukkit.getOnlinePlayers());

        if (firstPlayer == null) {
            throw new IllegalArgumentException("Bungee Messaging Api requires at least one player to be online.");
        }

        return firstPlayer;
    }

    @SuppressWarnings("unused")
    private Player getFirstPlayer0(Player[] playerArray) {
        return playerArray.length > 0 ? playerArray[0] : null;
    }

    private Player getFirstPlayer0(Collection<? extends Player> playerCollection) {
        return Iterables.getFirst(playerCollection, null);
    }

    @FunctionalInterface
    public interface ForwardConsumer {
        void accept(String channel, Player player, byte[] data);
    }
}