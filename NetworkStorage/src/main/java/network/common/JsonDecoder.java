package network.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import network.message.Message;

import java.util.List;

public class JsonDecoder extends MessageToMessageDecoder<byte[]> {
    private  final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, byte[] bytes, List<Object> list) throws Exception {
        Message message = OBJECT_MAPPER.readValue(bytes, Message.class);
        list.add(message);
    }
}
