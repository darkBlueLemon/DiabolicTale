const etherWallet = artifacts.require("etherWallet")

module.exports = function (deployer) {
    deployer.deploy(etherWallet);
};