package friendlyGetaways.dao;

import java.util.ArrayList;

import friendlyGetaways.dto.Property;
import friendlyGetaways.dto.UserLogin;
import friendlyGetaways.dto.UserRegisteration;

public interface Imethods {
public boolean validateUser(UserLogin u);
public boolean insertNewUser(UserRegisteration u);
ArrayList<Property> searchProperty(Property p);
}
