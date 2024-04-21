pragma solidity >=0.5.0 <0.9.0;

import "./SMERegistration.sol";
import "./FinanceRegistration.sol";

contract BuySellTransaction {
    SMERegistration public smeRegistration;
    FinanceRegistration public financeRegistration; // Renamed from FinanceRegistration to financeRegistration

    // Struct to represent a buy-sell transaction
    struct Transaction {
        uint transactionKey;
        uint buyerBid;
        uint sellerBid;
        uint timestamp;
        string termsAndConditions;
    }

    // Mapping to store transactions
    mapping(uint => Transaction) public transactions;

    // Counter for transactions
    uint public transactionCount;

    // Event to log transaction creation
    event TransactionCreated(uint transactionKey, uint buyerBid, uint sellerBid, uint timestamp, string termsAndConditions);

    constructor (SMERegistration _smeRegistration, FinanceRegistration _financeRegistration) public {
        smeRegistration = _smeRegistration;
        financeRegistration = _financeRegistration; // Changed from FinanceRegistration to financeRegistration
    }

    // Function to create a buy-sell transaction
    function createTransaction(uint _buyerBid, uint _sellerBid, string memory _termsAndConditions) public {
        // Increment transaction count to generate unique transaction key
        transactionCount++;

        // Get current timestamp
        uint timestamp = block.timestamp;

        // Store transaction details
        transactions[transactionCount] = Transaction(transactionCount, _buyerBid, _sellerBid, timestamp, _termsAndConditions);

        // Emit event
        emit TransactionCreated(transactionCount, _buyerBid, _sellerBid, timestamp, _termsAndConditions);
    }
}
