package Model;



public class User {
	private String user;
	private String password;
	private String email;
	private String address;
	private String country;
	private String urlimage;
	
	
	

	

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public String getCountry() {
		return country;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	public String getUser() {
		return user;
	}
//
	public User(String user, String password, String address, String country, String email, String urlimage) {
        super();
        this.user = user;
        this.password = password;
        this.email = email;
        this.address = address;
        this.country = country;
        this.urlimage = urlimage;
        
    }
	public String getUrlimage() {
		return urlimage;
	}

	public void setUrlimage(String urlimage) {
		this.urlimage = urlimage;
	}

	public User(String user, String password) {
		super();
		this.user = user;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
