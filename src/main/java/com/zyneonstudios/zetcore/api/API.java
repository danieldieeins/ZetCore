package com.zyneonstudios.zetcore.api;

import org.bukkit.util.StringUtil;

public class API {

    private static CommunicationAPI communicator;
    private static ServerAPI server;
    private static UserAPI userHandler;
    private static StringAPI stringUtil;

    public static CommunicationAPI getCommunicator() {
        if(communicator==null) {
            communicator = new CommunicationAPI();
        }
        return communicator;
    }

    public static ServerAPI getServer() {
        if(server==null) {
            server = new ServerAPI();
        }
        return server;
    }

    public static UserAPI getUserHandler() {
        if(userHandler==null) {
            userHandler = new UserAPI();
        }
        return userHandler;
    }

    public static StringAPI getStringUtil() {
        if(stringUtil==null) {
            stringUtil = new StringAPI();
        }
        return stringUtil;
    }
}