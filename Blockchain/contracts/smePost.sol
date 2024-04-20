pragma solidity >=0.5.0 < 0.9.0;

contract smePost {
    uint public smePostCount = 0;

    struct Post {
        uint postID;
        string bID;
        string postContent;
        string contact;
    }

    mapping(uint => Post) public smePosts;

    function createPost(string memory _bid, string memory _content, string memory _contact) public {
        smePostCount ++;
        smePosts[smePostCount] = Post(smePostCount, _bid, _content, _contact);
    }

    function readPost(uint _postID) external view returns (uint, string memory, string memory, string memory) {
        _postID = 127;
        Post memory post = smePosts[_postID];
        return (_postID, post.bID, post.postContent, post.contact);
    }
}