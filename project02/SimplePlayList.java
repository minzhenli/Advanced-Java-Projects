/*
 * @author Zhenli Min
 * @version 20190213
 */

import java.util.*;
public class SimplePlayList implements PlayList{
	private Queue<PlayListTrack> listQ;
	private Stack<PlayListTrack> listS;
	
	public SimplePlayList() {
		listQ=new LinkedList<PlayListTrack>();
		listS=new Stack<PlayListTrack>();
	}
	
	public SimplePlayList(String in) {
		if(in.equals("Q")||in.equals("q")) {
			listQ=new LinkedList<PlayListTrack>();
			listS=null;
		}
		else {
			listS=new Stack<PlayListTrack>();
			listQ=null;
		}
		
	}
	
	public PlayListTrack getNextTrack() {
		if(listQ==null) {
			return listS.pop();
		}
		else {
			return listQ.remove();
		}
	}
		// Removes track from PlayList and returns it to the caller
		// Should return a null value if the PlayList is empty
	public PlayListTrack peekAtNextTrack() {
		if(listQ==null) {
			return listS.peek();
		}
		else {
			return listQ.peek();
		}
	}
		// Returns next entry to the caller, but leaves it in the list
	public void addTrack(PlayListTrack track) {
		if(listQ==null) {
			listS.push(track);
		}
		else {
			listQ.add(track);
		}
	}
		// Adds this track to the playlist in the appropriate order
	public boolean isEmpty() {
		if(listQ==null) {
			return listS.isEmpty();
		}
		else {
			return listQ.isEmpty();
		}
	}
		// Returns true if the playlist is empty
}
