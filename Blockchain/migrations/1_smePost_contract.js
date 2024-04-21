const smePost = artifacts.require("smePost")

module.exports = function (deployer) {
    deployer.deploy(smePost);
};