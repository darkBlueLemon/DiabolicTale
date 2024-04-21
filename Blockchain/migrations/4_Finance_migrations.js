const FinanceRegistration = artifacts.require("./FinanceRegistration.sol");

module.exports = function(deployer) {
  deployer.deploy(FinanceRegistration);
};
