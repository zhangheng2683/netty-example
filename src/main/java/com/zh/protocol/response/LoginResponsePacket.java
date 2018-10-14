package com.zh.protocol.response;

import com.zh.protocol.Packet;
import lombok.Data;

import static com.zh.protocol.command.Command.LOGIN_RESPONSE;

/**
 * @Author zh2683
 */
@Data
public class LoginResponsePacket extends Packet {

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
