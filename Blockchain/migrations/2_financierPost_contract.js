const financierPost = artifacts.require("financierPost")

module.exports = function (deployer) {
    deployer.deploy(financierPost);
};