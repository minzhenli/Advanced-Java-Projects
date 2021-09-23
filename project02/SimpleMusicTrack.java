import java.util.Scanner;

/*
 * @author Zhenli Min
 * @version 20190211
 */

public class SimpleMusicTrack implements PlayListTrack {
	private String track;
	private String artist;
	private String album;
	
	public SimpleMusicTrack() {
		track="";
		artist="";
		album="";
	}
	
	public String getName() {
		return this.track;
	}
	
	public void setName(String name) {
		this.track=name;
	}
	
	public String getArtist() {
		return this.artist;
	}
	
	public void setArtist(String artist) {
		this.artist=artist;
	}
	
	public String getAlbum() {
		return this.album;
	}
	
	public void setAlbum(String album) {
		this.album=album;
	}
	
	public boolean getNextTrack(Scanner infile) {
		boolean a = false;
		if(infile.hasNext()) {
		int count =0;
		while(count<3&&infile.hasNext()) {
			if(count==0) {
				setName(infile.nextLine());
				count++;
			}
			else if(count==1) {
				setArtist(infile.nextLine());
				count++;
			}
			else{
				setAlbum(infile.nextLine());
				count++;
			}
		}
		if(count!=3) {
			a=false;
		}
		else {a=true;}
		}
		return a;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof SimpleMusicTrack) {
			SimpleMusicTrack a = (SimpleMusicTrack) obj;
			if(this.track.equals(a.getName())
			    &&this.artist.equals(a.getArtist())
			    &&this.album.equals(a.getAlbum())) {
				result=true;
			}
		}
		return result;
	}
	
	@Override
	public String toString() {
		String a = "";
		a=this.track+" / "+this.artist;
		return a;
	}
}
