/*
 * @author Zhenli Min
 * @version 20190213
 */
import java.util.*;
import java.io.*;
public class MusicList {
	public static void main(String[]args) throws IOException {
		Scanner in = new Scanner(System.in);
		System.out.println("What kind of playlist you would like to use [Q]ueue or [S]tack?");
		System.out.print(">");
		String type = in.nextLine();
		while(!type.equals("Q")&&!type.equals("q")
				&&!type.equals("S")&&!type.equals("s")) {
			System.out.println("Invalid input");
			System.out.println("type = in.nextLine()");
		}
		SimplePlayList list = new SimplePlayList(type);
		readFile(list,in);
		displayList(list,in);
		displayRemaining(list);
	}
	
	public static void readFile(SimplePlayList list,Scanner in) throws IOException{
		System.out.print("Enter database filename: ");
		String fileName = in.nextLine();
		System.out.println();
		try {
			File f = new File(fileName);
			Scanner inFile = new Scanner(f);
			PlayListTrack track = new SimpleMusicTrack();
			while(track.getNextTrack(inFile)) {
				list.addTrack(track);
				track = new SimpleMusicTrack();
			}
		}
		catch(IOException e) {
			System.out.println("There was a problem reading from " + fileName);
		}
	}
	
	public static void displayList(SimplePlayList list, Scanner in) {
		String input=" ";
		String currentPlay = " ";
		int count = -1;
		while(!list.isEmpty()&&!input.equals("q")){
			if(count<0 && !input.equals("a")) {
				System.out.println("Currently playing: No Song Playing");
				System.out.println("Next track to play: "+getNextTrack(list));
				System.out.println("[P]lay next track");
				System.out.println("[A]dd a new track");
				System.out.println("[Q]uit");
				System.out.print(">");
				input=in.nextLine();
				System.out.println();
				count++;
			}
			else if(count>=0 && !input.equals("a")) {
				currentPlay=list.getNextTrack().toString();
				System.out.println("Currently playing: "+currentPlay);
				System.out.println("Next track to play: "+getNextTrack(list));
				System.out.println("[P]lay next track");
				System.out.println("[A]dd a new track");
				System.out.println("[Q]uit");
				System.out.print(">");
				input=in.nextLine();
				System.out.println();
				count++;
			}
			else {
				SimpleMusicTrack a = new SimpleMusicTrack();
				System.out.print("Track name: ");
				a.setName(in.nextLine());
				System.out.print("Artist name: ");
				a.setArtist(in.nextLine());
				System.out.print("Album name: ");
				a.setAlbum(in.nextLine());
				System.out.println();
				System.out.println("New track: "+a.getName());
				System.out.println("Artist: "+a.getArtist());
				System.out.println("Album: "+a.getAlbum());
				System.out.print("Are you sure you want to add this track [y/n]? ");
				String confirm = in.nextLine();
				if (confirm.equals("y")) {
					list.addTrack(a);
				}
				System.out.println();
				System.out.println("Currently playing: "+currentPlay);
				System.out.println("Next track to play: "+getNextTrack(list));
				System.out.println("[P]lay next track");
				System.out.println("[A]dd a new track");
				System.out.println("[Q]uit");
				System.out.print(">");
				input=in.nextLine();
				System.out.println();
				count++;
			}
			
		}	
	}
	
	public static String getNextTrack(SimplePlayList list) {
		String result="";
		if(list.isEmpty()) {
			result="Play list is empty.";
		}
		PlayListTrack a = list.peekAtNextTrack();
		result=a.toString();
		return result;
	}
	
	public static void displayRemaining(SimplePlayList list){
		System.out.println("Tracks remaining in play list");
		System.out.println("------------------------------------------------------------");
		int count = 1;
		if(list.isEmpty()) {
			System.out.println("No track remains in the play list");
		}
		else {
			while(!list.isEmpty()) {
				SimpleMusicTrack a = (SimpleMusicTrack) list.getNextTrack();
				System.out.println(count+" - "+a.getName()+" / "+a.getArtist()+" / "+a.getAlbum());
			}
		}
	}
}
