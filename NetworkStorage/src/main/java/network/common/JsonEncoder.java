package network.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import network.message.Message;

import java.util.List;

public class JsonEncoder extends MessageToMessageEncoder<Message> {
    private  final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Message message, List<Object> list) throws Exception {
        byte[] bytes = OBJECT_MAPPER.writeValueAsBytes(message);
        list.add(bytes);
    }
}
