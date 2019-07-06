package com.muheda.rpc.thrift.client;

import com.muheda.domain.Contans;
import com.muheda.domain.ThriftClient;
import com.muheda.rpc.thrift.produce.ProduceCls;
import com.muheda.rpc.thrift.produce.RectDouble;
import com.muheda.rpc.thrift.produce.RoadDouble;
import com.muheda.utils.ReadProperty;
import org.apache.kafka.common.errors.InterruptException;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.util.List;

public class Client {


    private  static ThreadLocal<ThriftClient> threadLocal = new ThreadLocal();

    /**
     * thrift 服务所在的ip
     */
    public  static String  thriftServiceAddress = ReadProperty.getConfigData(Contans.RPC_THRIFT_ADDRESS);

    /**
     * thrift 服务所在的端口
     */
    public  static  Integer thriftServicePort = Integer.parseInt(ReadProperty.getConfigData(Contans.RPC_THRIFT_PORT));

    /**
     * thrift 超时时间
     * @return
     */
    public  static Integer  thriftServiceTimeOut =  Integer.parseInt(ReadProperty.getConfigData(Contans.RPC_THRIFT_TIMEOUT));


    /**
     * @desc 获取一个新的thrift客户端连接
     * @return 一个封装好的thrift客户端
     */
    public static ThriftClient getTHriftClient(){

        TFramedTransport transport = new TFramedTransport(new TSocket(Client.thriftServiceAddress, Client.thriftServicePort, Client.thriftServiceTimeOut));
        TBinaryProtocol protocol = new TBinaryProtocol(transport);
        ProduceCls.Client client = new ProduceCls.Client(protocol);

        if(transport == null || protocol == null || client == null){

            throw  new InterruptException("创建thrift客户端出现异常");
        }

        ThriftClient thriftClient = new ThriftClient();

        thriftClient.setTransport(transport);
        thriftClient.settBinaryProtocol(protocol);
        thriftClient.setClient(client);

        return thriftClient;

    }

    public  static List<RoadDouble>  getAssociatedRoads(RectDouble rectDouble) {

        ThriftClient thriftClient = threadLocal.get();

        if (thriftClient == null) {
            thriftClient = getTHriftClient();
            threadLocal.set(thriftClient);
        }

        TTransport transport = thriftClient.getTransport();
        ProduceCls.Client client = thriftClient.getClient();

        List<RoadDouble> roadByRectByType = null;

        try {
            transport.open();
        } catch (TTransportException e) {
            e.printStackTrace();
        }

        if (transport.isOpen()) {
            try {
                roadByRectByType = client.getRoadByRectByType(rectDouble, 2);
                System.out.println(roadByRectByType.size());

            } catch (TException e) {
                e.printStackTrace();
            }

        }

        return roadByRectByType;

    }



}
