package ie.gmit.sw;

public class GetDoc {
	private String path;

	public GetDoc() {
		this.path = new String();
	}

	public String getPath() {
		return "./"+path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
