package Position;

import java.util.LinkedList;

public class GroupData {
	
	private static int occupy=1;
	private static int updatePosition=1;
	private static ChatNode [] chatText=new ChatNode[20];
	private static int now=0;
	private static LinkedList<PositionNode> position=new LinkedList(); 
	
	public static int getOccupy() {
		occupy--;
		return occupy+1;
	}
	public static int getUpdatePosition() {
		updatePosition--;
		return updatePosition+1;
	}
	
	public static void setOccupy(int occupy) {
		GroupData.occupy = occupy;
	}
	public static void setUpdatePosition(int updatePosition) {
		GroupData.updatePosition = updatePosition;
	}
	
	public GroupData(){
		
	}
	
	public int insert(String id,String text){
		if(chatText[now]==null){
			chatText[now]=new ChatNode(id,text);
			now=(now+1)%20;
		}
		else{
			chatText[now]=new ChatNode(id,text);
			now=(now+1)%20;
		}
		return now;
	}
	
	public LinkedList<ChatNode> getChat(int last){
		LinkedList<ChatNode> chat=new LinkedList<ChatNode>();
		int i=now;
		int j=last;
		while(j!=i){
			ChatNode temp=chatText[j];
			if(temp!=null)
				chat.add(temp);
			j=(j+1)%20;
		}
		return chat;
	}
	
	public void share(String id,String longitude,String latitude,String name ,boolean type){
		boolean have=false;
		int i=0;
		while(i<position.size()){
			if(position.get(i).id.equals(id)){
				if(type)
					position.get(i).setPosition(longitude, latitude);
				have=true;
				break;
			}
			i++;
		}
		if(!have){
			PositionNode temp=new PositionNode(id, longitude, latitude, name);
			position.add(temp);
		}	
	}
	public void deletePosition(String id){
		int i=0;
		while(i<position.size()){
			if(position.get(i).id.equals(id)){
				position.remove(i);
				break;
			}
			i++;
		}
	}
	public PositionNode getPosition(String id){
		int i=0;
		while(i<position.size()){
			if(position.get(i).id.equals(id)){
				return position.get(i);
			}
			i++;
		}
		return null;
	}
	public LinkedList<PositionNode> getPosition(){
		LinkedList<PositionNode> p=new LinkedList<PositionNode>();
		int i=0;
		while(i<position.size()){
			PositionNode temp=position.get(i);
			p.add(temp);
			i++;
		}
		return p;
	}

}
