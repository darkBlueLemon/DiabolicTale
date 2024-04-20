pragma solidity >=0.4.22 <0.9.0;

contract etherWallet{
    address payable public owner;
    constructor(){
        owner = payable(msg.sender);
    }
    receive() external payable{
        
    }
    function withdrawEther(uint amount) external{
        require (msg.sender == owner , "Only the owner can call this method");
        payable(msg.sender).transfer(amount);
    }
    function getBalance() external view returns(uint){
        return address(this).balance;
    }
}