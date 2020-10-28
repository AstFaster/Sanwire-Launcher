package fr.sanwire.launcher.api.auth;

import fr.sanwire.launcher.api.auth.exception.AuthException;
import fr.sanwire.launcher.api.auth.request.*;
import fr.sanwire.launcher.api.auth.response.AuthResponse;
import fr.sanwire.launcher.api.auth.response.RefreshResponse;
import fr.sanwire.launcher.api.auth.util.HttpRequester;
import fr.sanwire.launcher.core.utils.logger.LogType;
import fr.sanwire.launcher.core.utils.logger.Logger;

public class Auth {

    private final Logger logger;

    public Auth(Logger logger) {
        this.logger = logger;
    }

    public Object sendRequestWithResponse(Object request) throws AuthException {
        HttpRequester requester = new HttpRequester(logger);
        if (request instanceof AuthRequest){
            return requester.gsonRequest(request, AuthResponse.class, "authenticate");
        } else if (request instanceof RefreshRequest){
            return requester.gsonRequest(request, RefreshResponse.class, "refresh");
        }
        if (this.logger != null) logger.log("Failed to authenticate to Mojang Service.", LogType.ERROR);
        return null;
    }

    public void sendRequestWithoutResponse(Object request) throws AuthException {
        HttpRequester requester = new HttpRequester(logger);
        if (request instanceof ValidateRequest){
            requester.gsonRequest(request, null, "validate");
        } else if (request instanceof SignoutRequest){
            requester.gsonRequest(request, null, "signout");
        } else if (request instanceof InvalidateRequest){
            requester.gsonRequest(request, null, "invalidate");
        }
        if (this.logger != null) logger.log("Failed to authenticate to Mojang Service.", LogType.ERROR);
    }

}
