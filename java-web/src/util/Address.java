package util;

public class Address {

	// @DbColumnMapping("")
	private String id;
	private String addr;

	/*
	 * @DbColumnMapping(value="ADDr") private String adssdr;
	 */
	/*
	 * public String getAdssdr() { return adssdr; } public void setAdssdr(String
	 * adssdr) { this.adssdr = adssdr; }
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public Address() {
	}
}