const SMERegistration = artifacts.require("SMERegistration");
const FinanceRegistration = artifacts.require("FinanceRegistration");
const BuySellTransaction = artifacts.require("BuySellTransaction");

module.exports = function(deployer) {
  deployer.deploy(BuySellTransaction, SMERegistration.address, FinanceRegistration.address);
};
