package com.muheda.domain;

import com.muheda.rpc.thrift.produce.ProduceCls;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class ThriftClient {


    private  TTransport transport;

    private TBinaryProtocol  tBinaryProtocol;

    private ProduceCls.Client client;


    public TTransport getTransport() {
        return transport;
    }

    public void setTransport(TTransport transport) {
        this.transport = transport;
    }

    public TBinaryProtocol gettBinaryProtocol() {
        return tBinaryProtocol;
    }

    public void settBinaryProtocol(TBinaryProtocol tBinaryProtocol) {
        this.tBinaryProtocol = tBinaryProtocol;
    }

    public ProduceCls.Client getClient() {
        return client;
    }

    public void setClient(ProduceCls.Client client) {
        this.client = client;
    }



}
