package friendlyGetaways.dto;

public class UserLogin {  
private String name,password;  
  
public UserLogin(String name, String password) {
	super();
	this.name = name;
	this.password = password;
}
public String getName() {  
    return name;  
}  
public void setName(String name) {  
    this.name = name;  
}  
public String getPassword() {  
    return password;  
}  
public void setPassword(String password) {  
    this.password = password;  
}  
//public boolean validate(){  
//    if(password.equals("admin")){  
//        return true;  
//    }  
//    else{  
//        return false;  
//    }  
//}  
}  
