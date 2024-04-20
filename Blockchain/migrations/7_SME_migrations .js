const SMERegistration = artifacts.require("./SMERegistration.sol");

module.exports = function(deployer) {
  deployer.deploy(SMERegistration);
};
