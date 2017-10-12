package io.blk.jaxlondon;

import java.math.BigInteger;

import io.blk.contracts.generated.Greeter;

import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;

public class HelloBlockchainWorld {

    public static void main(String[] args) throws Exception {
        Web3j web3 = Web3j.build(new HttpService(
                "https://rinkeby.infura.io/<infura token>"));

        Credentials credentials =
                WalletUtils.loadCredentials(
                        "<password>",
                        "<walletfile>.json");

        Greeter contract = Greeter.deploy(
                web3, credentials, ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT,
                BigInteger.ZERO, new Utf8String("Hello blockchain world!"))
                .get();

        Utf8String greeting = contract.greet().get();
        System.out.println(greeting.getValue());

        TransactionReceipt transactionReceipt =
                contract.newGreeting(new Utf8String("Hello new blockchain world!")).get();
        System.out.println(transactionReceipt.getTransactionHash());

        Utf8String newGreeting = contract.greet().get();
        System.out.println(newGreeting.getValue());
    }
}
