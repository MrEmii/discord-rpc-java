package exemple;

import java.util.Scanner;

import javax.swing.JFrame;

import discordrpc.DiscordEventHandlers;
import discordrpc.DiscordJoinRequest;
import discordrpc.DiscordRPCLibrary;
import discordrpc.DiscordRichPresence;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    
    private static int FrustrationLevel = 0;

	private static long StartTime;

	public static void main(String[] args) {
	    JFrame jf = new JFrame();
	    jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    jf.setVisible(true);
		discordInit();
		gameLoop();
	    DiscordRPCLibrary.shutdown();
	    sc.close();
	    jf.dispose();
	}

	private static void discordInit() {
		DiscordEventHandlers handlers = new DiscordEventHandlers();
	    handlers.ready = new DiscordEventHandlers.ready_callback() {
			
			@Override
			public void apply() {
				 System.out.printf("\nDiscord: ready\n");
			}
		};
	    handlers.disconnected = new DiscordEventHandlers.disconnected_callback() {

			@Override
			public void apply(int errcode, String message) {
				System.out.printf("\nDiscord: disconnected (%d: %s)\n", errcode, message);
			}
		};
	    handlers.errored = new DiscordEventHandlers.errored_callback() {
			
			@Override
			public void apply(int errcode, String message) {
				System.out.printf("\nDiscord: error (%d: %s)\n", errcode, message);
			}
		};
	    handlers.joinGame = new DiscordEventHandlers.joinGame_callback() {
			
			@Override
			public void apply(String secret) {
				System.out.printf("\nDiscord: join (%s)\n", secret);
			}
		};
	    handlers.spectateGame = new DiscordEventHandlers.spectateGame_callback() {
			
			@Override
			public void apply(String secret) {
				System.out.printf("\nDiscord: spectate (%s)\n", secret);
			}
		};
	    handlers.joinRequest = new DiscordEventHandlers.joinRequest_callback() {
			
			@Override
			public void apply(DiscordJoinRequest request) {
				int response = -1;
			    System.out.printf("\nDiscord: join request from %s - %s - %s\n",
			           request.username,
			           request.avatar,
			           request.userId);
			    do {
			    	System.out.printf("Accept? (y/n)");
			    	String nextLine = sc.nextLine();
			    	if(nextLine == null) {
			    		break;
			    	}
			    	
			        if (nextLine.equals("y")) {
			            response = DiscordRPCLibrary.DISCORD_REPLY_YES;
			            break;
			        }
			        
			        if (nextLine.equals("n")) {
			            response = DiscordRPCLibrary.DISCORD_REPLY_NO;
			            break;
			        }
			    } while (true);
			    if (response != -1) {
			        DiscordRPCLibrary.respond(request.userId, response);
			    }
			}
		};
	    DiscordRPCLibrary.initialize("345229890980937739", handlers, 1, null);
	}
	
	private static void gameLoop() {
		
		StartTime = System.currentTimeMillis()/1000l;
		
		System.out.printf("You are standing in an open field west of a white house.\n");
		String nextLine;
		while((nextLine = sc.nextLine()) != null) {
			if(nextLine.equals("q")) {
				break;
			}
			
			if(nextLine.equals("t")) {
				System.out.printf("Shutting off Discord.\n");
				DiscordRPCLibrary.shutdown();
				continue;
			}
			
			if(nextLine.equals("y")) {
				System.out.printf("Reinit Discord.\n");
				discordInit();
				continue;
			}
			
			if ((System.currentTimeMillis()/1000l & 1l) == 1) {
				System.out.printf("I don't understand that.\n");
            }
            else {
            	System.out.printf("I don't know the word \"%s\".\n", nextLine.split(" ")[0]);
            }
			
			++FrustrationLevel;
			
			updateDiscordPresence();
		}
	}
	
	private static void updateDiscordPresence(){
	    DiscordRichPresence discordPresence = new DiscordRichPresence();
	    discordPresence.state = "West of House";
	    discordPresence.details = "Frustration level: "+FrustrationLevel;
	    discordPresence.startTimestamp = StartTime;
	    discordPresence.endTimestamp = System.currentTimeMillis()/1000l + 5 * 60;
	    discordPresence.largeImageKey = "canary-large";
	    discordPresence.smallImageKey = "ptb-small";
	    discordPresence.partyId = "party1234";
	    discordPresence.partySize = 1;
	    discordPresence.partyMax = 6;
	    discordPresence.matchSecret = "xyzzy";
	    discordPresence.joinSecret = "join";
	    discordPresence.spectateSecret = "look";
	    discordPresence.instance = 0;
	    DiscordRPCLibrary.updatePresence(discordPresence);
	}

}
