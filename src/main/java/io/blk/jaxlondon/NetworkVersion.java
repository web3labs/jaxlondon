package io.blk.jaxlondon;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.NetVersion;
import org.web3j.protocol.http.HttpService;

public class NetworkVersion {

    public static void main(String[] args) throws Exception {
        Web3j web3j = Web3j.build(
                new HttpService(
                        "https://rinkeby.infura.io/<infura token>"));
        NetVersion netVersion =
                web3j.netVersion().send();
        System.out.println(netVersion.getNetVersion());
    }
}
