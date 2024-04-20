import com.invincible.contracts.*;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import java.io.IOException;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        Web3j web3 = Web3j.build(new HttpService("http://127.0.0.1:7475"));

        try {
            BigInteger blockNumber = web3.ethBlockNumber().send().getBlockNumber();
            System.out.println("Latest Ethereum block number: " + blockNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
