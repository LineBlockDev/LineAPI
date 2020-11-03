package net.lineblock.socket.other;

import java.util.ArrayList;
import java.util.List;

import net.lineblock.json.JSONObject;

public class EmbedMessage {

	private List<EmbedSection> sections = new ArrayList<>();
	private String sender;
	private String sender_image_url;
	private String image_url;
	
	public EmbedMessage(String sender, String sender_image_url, String image_url, EmbedSection... secs) {
		for(EmbedSection s : secs)
			sections.add(s);
		this.sender = sender;
		this.sender_image_url = sender_image_url;
		this.image_url = image_url;
	}
	
	@Override
	public String toString() {
		String sectionS = "";
		for (int i = 0; i < sections.size(); i++) {
			sectionS += sections.get(i).toString();
			if(i != sections.size()-1)
				sectionS += ",";
		}
		return new JSONObject(
				"{\"sender\":\""+sender+"\",\"sender_image_url\":\""+sender_image_url+"\",\"image_url\":\""+image_url+"\",\"sections\""
						+ "["
						+ sectionS
						+ "]"
				+ "}"
		).toString();
	}
	
	public String getSender() {return sender;}
	public String getSenderImageUrl() {return sender_image_url;}
	public String getImageUrl() {return image_url;}
	
	public class EmbedSection {
		private String title;
		private String message;
		private boolean inline;
		public EmbedSection(String _title, String _message, boolean _inline) {
			title = _title;
			message = _message;
			inline = _inline;
		}
		@Override
		public String toString() {
			return new JSONObject("{\"title\":\""+title+"\",\"message\":\""+message+"\",\"inline\":"+Boolean.toString(inline)+"}").toString();
		}
		public String getTitle() {return title;}
		public String getMessage() {return message;}
		public boolean isInline() {return inline;}
	}

}
