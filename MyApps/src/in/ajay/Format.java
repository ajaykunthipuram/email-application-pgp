package in.ajay;

public class Format {
	private int id;
	private String from;
	private String to;
	private byte[] message;
	private byte[] enKey;
	private String md;
	private int ml;
	private int dl;
	private byte[] smsg;
	
	public byte[] getSmsg() {
		return smsg;
	}
	public void setSmsg(byte[] smsg) {
		this.smsg = smsg;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public byte[] getMessage() {
		return message;
	}
	public void setMessage(byte[] message) {
		this.message = message;
	}
	public byte[] getEnKey() {
		return enKey;
	}
	public void setEnKey(byte[] enKey) {
		this.enKey = enKey;
	}
	public String getMd() {
		return md;
	}
	public void setMd(String md) {
		this.md = md;
	}
	public int getMl() {
		return ml;
	}
	public void setMl(int ml) {
		this.ml = ml;
	}
	public int getDl() {
		return dl;
	}
	public void setDl(int dl) {
		this.dl = dl;
	}
	public int getKl() {
		return kl;
	}
	public void setKl(int kl) {
		this.kl = kl;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	private int kl;
	private String time;
	
	
}
