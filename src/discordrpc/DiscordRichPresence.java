package discordrpc;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public class DiscordRichPresence extends Structure {
	/** max 128 bytes */
	public String state;
	/** max 128 bytes */
	public String details;
	public long startTimestamp;
	public long endTimestamp;
	/** max 32 bytes */
	public String largeImageKey;
	/** max 128 bytes */
	public String largeImageText;
	/** max 32 bytes */
	public String smallImageKey;
	/** max 128 bytes */
	public String smallImageText;
	/** max 128 bytes */
	public String partyId;
	public int partySize;
	public int partyMax;
	/** max 128 bytes */
	public String matchSecret;
	/** max 128 bytes */
	public String joinSecret;
	/** max 128 bytes */
	public String spectateSecret;
	public byte instance;
	
	public DiscordRichPresence() {
		super();
	}
	protected List<String> getFieldOrder() {
		return Arrays.asList("state", "details", "startTimestamp", "endTimestamp", "largeImageKey", "largeImageText", "smallImageKey", "smallImageText", "partyId", "partySize", "partyMax", "matchSecret", "joinSecret", "spectateSecret", "instance");
	}
	public DiscordRichPresence(Pointer peer) {
		super(peer);
	}
	public static class ByReference extends DiscordRichPresence implements Structure.ByReference {
		
	};
	public static class ByValue extends DiscordRichPresence implements Structure.ByValue {
		
	};
}


