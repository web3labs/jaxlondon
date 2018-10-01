package io.blk.jaxlondon;

import io.blk.contracts.generated.Greeter;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

public class HelloBlockchainWorld {

    public static void main(String[] args) throws Exception {
        Web3j web3 = Web3j.build(new HttpService(
                "https://rinkeby.infura.io/<infura token>"));

        Credentials credentials =
                WalletUtils.loadCredentials(
                        "<password>",
                        "<walletfile>.json");

        Greeter contract = Greeter.deploy(
                web3, credentials,
                DefaultGasProvider.GAS_PRICE,
                DefaultGasProvider.GAS_LIMIT,
                "Hello blockchain world!").send();

        String greeting = contract.greet().send();
        System.out.println(greeting);

        TransactionReceipt transactionReceipt =
                contract.newGreeting("Hello new blockchain world!").send();
        System.out.println(transactionReceipt.getTransactionHash());

        String newGreeting = contract.greet().send();
        System.out.println(newGreeting);
    }
}
