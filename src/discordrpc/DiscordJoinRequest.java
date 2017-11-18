package discordrpc;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public class DiscordJoinRequest extends Structure {
	public String userId;
	public String username;
	public String avatar;
	public DiscordJoinRequest() {
		super();
	}
	protected List<String> getFieldOrder() {
		return Arrays.asList("userId", "username", "avatar");
	}
	public DiscordJoinRequest(String userId, String username, String avatar) {
		super();
		this.userId = userId;
		this.username = username;
		this.avatar = avatar;
	}
	public DiscordJoinRequest(Pointer peer) {
		super(peer);
	}
	public static class ByReference extends DiscordJoinRequest implements Structure.ByReference {
		
	};
	public static class ByValue extends DiscordJoinRequest implements Structure.ByValue {
		
	};
}

