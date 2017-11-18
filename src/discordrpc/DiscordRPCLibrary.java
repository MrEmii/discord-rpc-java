package discordrpc;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

public interface DiscordRPCLibrary extends Library {
	public static final String JNA_LIBRARY_NAME = "discordRPC";
	
	public static final int DISCORD_REPLY_NO = 0;
	public static final int DISCORD_REPLY_YES = 1;
	public static final int DISCORD_REPLY_IGNORE = 2;
	
	public static final NativeLibrary JNA_NATIVE_LIB = NativeLibrary.getInstance(DiscordRPCLibrary.JNA_LIBRARY_NAME);
	public static final DiscordRPCLibrary INSTANCE = (DiscordRPCLibrary)Native.loadLibrary(DiscordRPCLibrary.JNA_LIBRARY_NAME, DiscordRPCLibrary.class);
	
	
	void Discord_Initialize(String applicationId, DiscordEventHandlers handlers, int autoRegister, String optionalSteamId);
	void Discord_Shutdown();
	void Discord_RunCallbacks();
	void Discord_UpdatePresence(DiscordRichPresence presence);
	void Discord_Respond(String userid, int reply);
	
	/* ADDED TO AVOID AN ".INSTANCE" IN CALLS */
	
	public static void initialize(String applicationId, DiscordEventHandlers handlers, int autoRegister, String optionalSteamId) {
		INSTANCE.Discord_Initialize(applicationId, handlers, autoRegister, optionalSteamId);
	}
	
	public static void shutdown() {
		INSTANCE.Discord_Shutdown();
	}
	
	public static void runCallbacks() {
		INSTANCE.Discord_RunCallbacks();
	}
	public static void updatePresence(DiscordRichPresence presence) {
		INSTANCE.Discord_UpdatePresence(presence);
	}
	public static void respond(String userid, int reply) {
		INSTANCE.Discord_Respond(userid, reply);
	}
}
