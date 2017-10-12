package io.blk.jaxlondon;

import java.math.BigInteger;

import io.blk.contracts.generated.JaxToken;

import org.web3j.abi.datatypes.Address;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;


public class JaxTokenExample {

    public static void main(String[] args) throws Exception {
        Web3j web3 = Web3j.build(new HttpService(
                "https://rinkeby.infura.io/<your-token>"));

        Credentials credentials =
                WalletUtils.loadCredentials(
                        "<password>",
                        "<walletfile>");

        JaxToken jaxToken = JaxToken.deploy(
                web3, credentials, ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT, BigInteger.ZERO
        ).get();

        System.out.println(
                jaxToken.balanceOf(new Address(credentials.getAddress())).get().getValue());

    }
}
