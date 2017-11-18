package discordrpc;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public class DiscordEventHandlers extends Structure {
	public DiscordEventHandlers.ready_callback ready;
	public DiscordEventHandlers.disconnected_callback disconnected;
	public DiscordEventHandlers.errored_callback errored;
	public DiscordEventHandlers.joinGame_callback joinGame;
	public DiscordEventHandlers.spectateGame_callback spectateGame;
	public DiscordEventHandlers.joinRequest_callback joinRequest;
	public interface ready_callback extends Callback {
		void apply();
	};
	public interface disconnected_callback extends Callback {
		void apply(int errorCode, String message);
	};
	public interface errored_callback extends Callback {
		void apply(int errorCode, String message);
	};
	public interface joinGame_callback extends Callback {
		void apply(String joinSecret);
	};
	public interface spectateGame_callback extends Callback {
		void apply(String spectateSecret);
	};
	public interface joinRequest_callback extends Callback {
		void apply(DiscordJoinRequest request);
	};
	public DiscordEventHandlers() {
		super();
	}
	protected List<String> getFieldOrder() {
		return Arrays.asList("ready", "disconnected", "errored", "joinGame", "spectateGame", "joinRequest");
	}
	public DiscordEventHandlers(DiscordEventHandlers.ready_callback ready, DiscordEventHandlers.disconnected_callback disconnected, DiscordEventHandlers.errored_callback errored, DiscordEventHandlers.joinGame_callback joinGame, DiscordEventHandlers.spectateGame_callback spectateGame, DiscordEventHandlers.joinRequest_callback joinRequest) {
		super();
		this.ready = ready;
		this.disconnected = disconnected;
		this.errored = errored;
		this.joinGame = joinGame;
		this.spectateGame = spectateGame;
		this.joinRequest = joinRequest;
	}
	public DiscordEventHandlers(Pointer peer) {
		super(peer);
	}
	public static class ByReference extends DiscordEventHandlers implements Structure.ByReference {
		
	};
	public static class ByValue extends DiscordEventHandlers implements Structure.ByValue {
		
	};
}

