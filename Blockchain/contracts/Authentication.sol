pragma solidity >=0.4.22 <0.9.0;

contract Authentication {
    // Struct to represent a user
    struct User {
        string username;
        string email;
        bytes32 hashedPassword;
        address walletAddress;
    }

    // Mapping to store users
    mapping(address => User) public users;

    // Event to log user registration
    event UserRegistered(address indexed userAddress, string username, string email);

    // Function to register a new user
    function signup(string memory _username, string memory _email, string memory _password) public {
        require(bytes(_username).length > 0, "Username must not be empty");
        require(bytes(_email).length > 0, "Email must not be empty");
        require(bytes(_password).length > 0, "Password must not be empty");
        require(users[msg.sender].walletAddress == address(0), "User already registered");

        bytes32 hashedPassword = hashPassword(_password);

        // Store user details
        users[msg.sender] = User(_username, _email, hashedPassword, msg.sender);

        // Emit event
        emit UserRegistered(msg.sender, _username, _email);
    }

    // Function to sign in a user
    function signin(string memory _email, string memory _password) public view returns (bool) {
        bytes32 hashedPassword = hashPassword(_password);
        User storage user = users[msg.sender];

        // Check if user exists and password matches
        if (keccak256(abi.encodePacked(user.email)) == keccak256(abi.encodePacked(_email)) && user.hashedPassword == hashedPassword) {
            return true;
        } else {
            return false;
        }
    }

    // Function to hash a password using keccak256 (SHA-3)
    function hashPassword(string memory _password) public pure returns (bytes32) {
        return keccak256(abi.encodePacked(_password));
    }

    // Function to assign a wallet address to a user
    function assignWalletAddress(address _userAddress, address _walletAddress) public {
        require(users[_userAddress].walletAddress == address(0), "Wallet already assigned");
        users[_userAddress].walletAddress = _walletAddress;
    }
}
