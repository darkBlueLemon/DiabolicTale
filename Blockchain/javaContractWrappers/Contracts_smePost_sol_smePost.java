package com.invincible.contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.5.3.
 */
@SuppressWarnings("rawtypes")
public class Contracts_smePost_sol_smePost extends Contract {
    public static final String BINARY = "60806040525f80553480156011575f80fd5b506109b18061001f5f395ff3fe608060405234801561000f575f80fd5b506004361061003f575f3560e01c80634d6a608214610043578063e5a2136c14610076578063e8a4a1eb14610094575b5f80fd5b61005d60048036038101906100589190610353565b6100b0565b60405161006d94939291906103fd565b60405180910390f35b61007e61026e565b60405161008b9190610455565b60405180910390f35b6100ae60048036038101906100a9919061059a565b610273565b005b6001602052805f5260405f205f91509050805f0154908060010180546100d59061066b565b80601f01602080910402602001604051908101604052809291908181526020018280546101019061066b565b801561014c5780601f106101235761010080835404028352916020019161014c565b820191905f5260205f20905b81548152906001019060200180831161012f57829003601f168201915b5050505050908060020180546101619061066b565b80601f016020809104026020016040519081016040528092919081815260200182805461018d9061066b565b80156101d85780601f106101af576101008083540402835291602001916101d8565b820191905f5260205f20905b8154815290600101906020018083116101bb57829003601f168201915b5050505050908060030180546101ed9061066b565b80601f01602080910402602001604051908101604052809291908181526020018280546102199061066b565b80156102645780601f1061023b57610100808354040283529160200191610264565b820191905f5260205f20905b81548152906001019060200180831161024757829003601f168201915b5050505050905084565b5f5481565b5f80815480929190610284906106c8565b919050555060405180608001604052805f5481526020018481526020018381526020018281525060015f805481526020019081526020015f205f820151815f015560208201518160010190816102da91906108ac565b5060408201518160020190816102f091906108ac565b50606082015181600301908161030691906108ac565b50905050505050565b5f604051905090565b5f80fd5b5f80fd5b5f819050919050565b61033281610320565b811461033c575f80fd5b50565b5f8135905061034d81610329565b92915050565b5f6020828403121561036857610367610318565b5b5f6103758482850161033f565b91505092915050565b61038781610320565b82525050565b5f81519050919050565b5f82825260208201905092915050565b8281835e5f83830152505050565b5f601f19601f8301169050919050565b5f6103cf8261038d565b6103d98185610397565b93506103e98185602086016103a7565b6103f2816103b5565b840191505092915050565b5f6080820190506104105f83018761037e565b818103602083015261042281866103c5565b9050818103604083015261043681856103c5565b9050818103606083015261044a81846103c5565b905095945050505050565b5f6020820190506104685f83018461037e565b92915050565b5f80fd5b5f80fd5b7f4e487b71000000000000000000000000000000000000000000000000000000005f52604160045260245ffd5b6104ac826103b5565b810181811067ffffffffffffffff821117156104cb576104ca610476565b5b80604052505050565b5f6104dd61030f565b90506104e982826104a3565b919050565b5f67ffffffffffffffff82111561050857610507610476565b5b610511826103b5565b9050602081019050919050565b828183375f83830152505050565b5f61053e610539846104ee565b6104d4565b90508281526020810184848401111561055a57610559610472565b5b61056584828561051e565b509392505050565b5f82601f8301126105815761058061046e565b5b813561059184826020860161052c565b91505092915050565b5f805f606084860312156105b1576105b0610318565b5b5f84013567ffffffffffffffff8111156105ce576105cd61031c565b5b6105da8682870161056d565b935050602084013567ffffffffffffffff8111156105fb576105fa61031c565b5b6106078682870161056d565b925050604084013567ffffffffffffffff8111156106285761062761031c565b5b6106348682870161056d565b9150509250925092565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52602260045260245ffd5b5f600282049050600182168061068257607f821691505b6020821081036106955761069461063e565b5b50919050565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52601160045260245ffd5b5f6106d282610320565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff82036107045761070361069b565b5b600182019050919050565b5f819050815f5260205f209050919050565b5f6020601f8301049050919050565b5f82821b905092915050565b5f6008830261076b7fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff82610730565b6107758683610730565b95508019841693508086168417925050509392505050565b5f819050919050565b5f6107b06107ab6107a684610320565b61078d565b610320565b9050919050565b5f819050919050565b6107c983610796565b6107dd6107d5826107b7565b84845461073c565b825550505050565b5f90565b6107f16107e5565b6107fc8184846107c0565b505050565b5b8181101561081f576108145f826107e9565b600181019050610802565b5050565b601f821115610864576108358161070f565b61083e84610721565b8101602085101561084d578190505b61086161085985610721565b830182610801565b50505b505050565b5f82821c905092915050565b5f6108845f1984600802610869565b1980831691505092915050565b5f61089c8383610875565b9150826002028217905092915050565b6108b58261038d565b67ffffffffffffffff8111156108ce576108cd610476565b5b6108d8825461066b565b6108e3828285610823565b5f60209050601f831160018114610914575f8415610902578287015190505b61090c8582610891565b865550610973565b601f1984166109228661070f565b5f5b8281101561094957848901518255600182019150602085019450602081019050610924565b868310156109665784890151610962601f891682610875565b8355505b6001600288020188555050505b50505050505056fea264697066735822122027acb1df9e4efaaed8781309df6d1656a62637bafe5e0526385fa20e040cc27364736f6c63430008190033";

    private static String librariesLinkedBinary;

    public static final String FUNC_CREATEPOST = "createPost";

    public static final String FUNC_SMEPOSTCOUNT = "smePostCount";

    public static final String FUNC_SMEPOSTS = "smePosts";

    @Deprecated
    protected Contracts_smePost_sol_smePost(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Contracts_smePost_sol_smePost(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Contracts_smePost_sol_smePost(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Contracts_smePost_sol_smePost(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> createPost(String _bid, String _content, String _contact) {
        final Function function = new Function(
                FUNC_CREATEPOST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_bid), 
                new org.web3j.abi.datatypes.Utf8String(_content), 
                new org.web3j.abi.datatypes.Utf8String(_contact)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> smePostCount() {
        final Function function = new Function(FUNC_SMEPOSTCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Tuple4<BigInteger, String, String, String>> smePosts(BigInteger param0) {
        final Function function = new Function(FUNC_SMEPOSTS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteFunctionCall<Tuple4<BigInteger, String, String, String>>(function,
                new Callable<Tuple4<BigInteger, String, String, String>>() {
                    @Override
                    public Tuple4<BigInteger, String, String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<BigInteger, String, String, String>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (String) results.get(3).getValue());
                    }
                });
    }

    @Deprecated
    public static Contracts_smePost_sol_smePost load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Contracts_smePost_sol_smePost(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Contracts_smePost_sol_smePost load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Contracts_smePost_sol_smePost(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Contracts_smePost_sol_smePost load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Contracts_smePost_sol_smePost(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Contracts_smePost_sol_smePost load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Contracts_smePost_sol_smePost(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Contracts_smePost_sol_smePost> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Contracts_smePost_sol_smePost.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<Contracts_smePost_sol_smePost> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Contracts_smePost_sol_smePost.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<Contracts_smePost_sol_smePost> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Contracts_smePost_sol_smePost.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<Contracts_smePost_sol_smePost> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Contracts_smePost_sol_smePost.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static void linkLibraries(List<Contract.LinkReference> references) {
        librariesLinkedBinary = linkBinaryWithReferences(BINARY, references);
    }

    public static String getDeploymentBinary() {
        if (librariesLinkedBinary != null) {
            return librariesLinkedBinary;
        } else {
            return BINARY;
        }
    }
}
