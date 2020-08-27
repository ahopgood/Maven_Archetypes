package com.alexander.maven.archetype.ws.cxf.server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSPasswordCallback;

import com.alexander.maven.archetype.ws.cxf.UserPasswords;
import com.alexander.maven.archetype.ws.cxf.UserTokens;

public class PasswordCallback implements CallbackHandler {

	private static final Map<String, String> passwordMap = new HashMap<String, String>();
	
	static {
		passwordMap.put(UserTokens.CLIENT1, UserPasswords.PASSWORD1);
		passwordMap.put(UserTokens.CLIENT2, UserPasswords.PASSWORD2);
	}
	
	@Override
	public void handle(Callback[] callbacks) throws IOException,
			UnsupportedCallbackException {
		//makes a call to the database to check credentials
		
		WSPasswordCallback pc = (WSPasswordCallback)callbacks[0];
		
		String password = passwordMap.get(pc.getIdentifier());
		pc.setPassword(password);
	}

}
