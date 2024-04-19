pragma solidity ^0.8.13;

contract smePost {
    uint public postCount = 0;

    struct Post {
        uint postID;
        string bID;
        int creditScore;
        string postContent;
        string typePost;
    }

    mapping(uint => Post) public posts;

    constructor() public {
        createPost("ABCDE10000", 100, "Hello Post1", "Type1");
        createPost("ABCDE20000", 200, "Hi Post2", "Type2");
        createPost("ABCDE30000", 300, "Hello Post3", "Type1");
        createPost("ABCDE40000", 400, "Hola Post4", "Type3");
    }

    function createPost(string memory _bid, int _creditscore, string memory _content, string memory _type) public {
        postCount ++;
        posts[postCount] = Post(postCount, _bid, _creditscore, _content, _type);
    }
}