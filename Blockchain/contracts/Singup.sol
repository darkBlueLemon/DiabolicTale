pragma solidity >=0.5.0 <0.9.0;

contract Signup {
    
    struct User {
        string username;
        string email;
        string password; 
        address userAddress;
        address walletAddress; 
    }

    // Mapping to store users
    mapping(bytes32 => User) public userRegistry;

    // Event to log user registration
    event UserRegistered(string username, address userAddress, address walletAddress);

    // Function to register a new user
    function registerUser(string memory _username, string memory _email, string memory _password, address _walletAddress) public {
        require(bytes(_username).length > 0, "Username must not be empty");
        require(bytes(_email).length > 0, "Email must not be empty");
        require(bytes(_password).length > 0, "Password must not be empty");
        require(_walletAddress != address(0), "Wallet address must be valid");
        require(userRegistry[keccak256(bytes(_email))].userAddress == address(0), "User already registered");

        // Generate a new address for the user
        address userAddress = address(uint160(uint(keccak256(abi.encodePacked(block.timestamp, msg.sender))))); // Pseudo-random generation of address

        // Create a new user
        User storage newUser = userRegistry[keccak256(bytes(_email))];
        newUser.username = _username;
        newUser.email = _email;
        newUser.password = _password;
        newUser.userAddress = userAddress;
        newUser.walletAddress = _walletAddress; // Assign the provided wallet address to the user

        // Emit event
        emit UserRegistered(_username, userAddress, _walletAddress);
    }

    // Function to retrieve user data using email
    function getUserData(string memory _email) public view returns (string memory, string memory, address, address) {
        User memory user = userRegistry[keccak256(bytes(_email))];
        require(bytes(user.username).length > 0, "User not found");
        return (user.username, user.email, user.userAddress, user.walletAddress);
    }
}
