pragma solidity >=0.4.22 <0.9.0;

contract SMERegistration {
    // Struct to represent an SME
    struct SME {
        string businessID;
        uint creditScore;
        string companyName;
        string contactDetails;
        address smeAddress;
    }

    // Mapping to store SMEs
    mapping(bytes32 => SME) public smeRegistry;

    // Event to log SME registration
    event SMERegistered(string businessID, address smeAddress);

    // Function to register a new SME
    function registerSME(string memory _businessID, uint _creditScore, string memory _companyName, string memory _contactDetails) public {
        require(bytes(_businessID).length > 0, "Business ID must not be empty");
        require(smeRegistry[keccak256(bytes(_businessID))].smeAddress == address(0), "SME already registered");

        // Generate a new account for the SME
        address smeAddress = address(uint160(uint(keccak256(abi.encodePacked(block.timestamp, msg.sender))))); 
        
        // Create a new SME
        SME storage newSME = smeRegistry[keccak256(bytes(_businessID))];
        newSME.businessID = _businessID;
        newSME.creditScore = _creditScore;
        newSME.companyName = _companyName;
        newSME.contactDetails = _contactDetails;
        newSME.smeAddress = smeAddress;

        // Emit event
        emit SMERegistered(_businessID, smeAddress);
    }

    // Function to retrieve SME data using BusinessID
    function getSMEData(string memory _businessID) public view returns (string memory, uint, string memory, string memory, address) {
        SME memory sme = smeRegistry[keccak256(bytes(_businessID))];
        require(bytes(sme.businessID).length > 0, "SME not found");
        return (sme.businessID, sme.creditScore, sme.companyName, sme.contactDetails, sme.smeAddress);
    }

    // Function to get the private key of an SME
    function getSMEPrivateKey(string memory _businessID) public view returns (bytes memory) {
        return abi.encodePacked(block.timestamp, smeRegistry[keccak256(bytes(_businessID))].smeAddress);
    }
}