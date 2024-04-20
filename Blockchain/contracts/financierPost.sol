pragma solidity >=0.5.0 < 0.9.0;

contract financierPost {
    uint public financierPostCount = 0;

    struct Post {
        uint postID;
        string bID;
        string postContent;
        string contact;
        string filter;
        bool isDeleted;
    }

    mapping(uint => Post) public financierPosts;

    function createPost(string memory _bid, string memory _content, string memory _contact, string memory _filter) public {
        financierPostCount ++;
        financierPosts[financierPostCount] = Post(financierPostCount, _bid, _content, _contact, _filter, false);
    }

    function updatePost(uint _postid, string memory _newcontent, string memory _newcontact) public {
        Post memory currPost = financierPosts[_postid];
        currPost.postContent = _newcontent;
        currPost.contact = _newcontact;
        financierPosts[_postid].isDeleted = true;
        financierPostCount ++;
        financierPosts[financierPostCount] = currPost;
    }

    function deletePost(uint _postid) public {
        financierPosts[_postid].isDeleted = true;
    }
}