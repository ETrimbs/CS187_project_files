package guessme;

public class LLIntegerNode {
	
	public int info;
	public LLIntegerNode link;
	
	public LLIntegerNode() {
		info = 0;
		link = null;
	}

	public LLIntegerNode(int info, LLIntegerNode link) {
		this.info = info;
		this.link = link;
	}
	
	public LLIntegerNode(int info) {
		this.info = info;
		this.link = null;
	}
	
	public LLIntegerNode(LLIntegerNode node) {
		info = node.getInfo();
		link = null;
	}

	public void setInfo(int info) {
		this.info = info;
	}

	public void setLink(LLIntegerNode link) {
		this.link = link;
	}

	public int getInfo() {
		return info;
	}

	public LLIntegerNode getLink() {
		return link;
	}
}

