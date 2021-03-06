import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class NettyServer implements Runnable {

    public static void main(String[] args) {
        new Thread(new NettyServer()).start();
    }

    @Override
    public void run() {
        EventLoopGroup auth = new NioEventLoopGroup(1);
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(auth, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(
                                    new ObjectEncoder(),
                                    new ObjectDecoder(ClassResolvers.cacheDisabled(null)),
                                    new ClientHandlerReader());
                        }
                    });
            ChannelFuture future = bootstrap.bind("192.168.0.101", 8189).sync();
            System.out.println("server started");
            future.channel().closeFuture().sync(); // block
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            auth.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}