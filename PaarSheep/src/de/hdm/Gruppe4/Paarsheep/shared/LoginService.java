package de.hdm.Gruppe4.Paarsheep.shared;


import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.Gruppe4.Paarsheep.client.LoginInfo;

@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {
  public LoginInfo login(String requestUri);
}
