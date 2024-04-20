# import web3
# from web3 import Web3, HTTPProvider
# from web3.contract import Contract
#
# w3 = Web3(HTTPProvider('http://127.0.0.1:7545'))
#
# caller = "0xd268C2504B16a520cb5A87468CcA42B46a81CAb1"
#
# # etherCA = "0x170032f3BF5A3073393baB8aF9C0796619ffF851"
# # etherABI = '[{"inputs":[],"stateMutability":"nonpayable","type":"constructor"},{"inputs":[],"name":"getBalance","outputs":[{"internalType":"uint256","name":"","type":"uint256"}],"stateMutability":"view","type":"function"},{"inputs":[],"name":"owner","outputs":[{"internalType":"address payable","name":"","type":"address"}],"stateMutability":"view","type":"function"},{"inputs":[{"internalType":"uint256","name":"amount","type":"uint256"}],"name":"withdrawEther","outputs":[],"stateMutability":"nonpayable","type":"function"},{"stateMutability":"payable","type":"receive"}]'
# #
# # financierCA = "0x507E91f1fB799c02D35aaC59f5C1594e1271fa9e"
# # financierABI = '[{"inputs":[{"internalType":"string","name":"_bid","type":"string"},{"internalType":"string","name":"_content","type":"string"},{"internalType":"string","name":"_contact","type":"string"},{"internalType":"string","name":"_filter","type":"string"}],"name":"createPost","outputs":[],"stateMutability":"nonpayable","type":"function"},{"inputs":[{"internalType":"uint256","name":"_postid","type":"uint256"}],"name":"deletePost","outputs":[],"stateMutability":"nonpayable","type":"function"},{"inputs":[],"name":"financierPostCount","outputs":[{"internalType":"uint256","name":"","type":"uint256"}],"stateMutability":"view","type":"function"},{"inputs":[{"internalType":"uint256","name":"","type":"uint256"}],"name":"financierPosts","outputs":[{"internalType":"uint256","name":"postID","type":"uint256"},{"internalType":"string","name":"bID","type":"string"},{"internalType":"string","name":"postContent","type":"string"},{"internalType":"string","name":"contact","type":"string"},{"internalType":"string","name":"filter","type":"string"},{"internalType":"bool","name":"isDeleted","type":"bool"}],"stateMutability":"view","type":"function"},{"inputs":[{"internalType":"uint256","name":"_postid","type":"uint256"},{"internalType":"string","name":"_newcontent","type":"string"},{"internalType":"string","name":"_newcontact","type":"string"}],"name":"updatePost","outputs":[],"stateMutability":"nonpayable","type":"function"}]'
#
# smeCA = "0x7B0FEbAf56641BEAedF761A12EfFF1eC924cedeF"
# smeABI = '[{"inputs":[{"internalType":"string","name":"_bid","type":"string"},{"internalType":"string","name":"_content","type":"string"},{"internalType":"string","name":"_contact","type":"string"}],"name":"createPost","outputs":[],"stateMutability":"nonpayable","type":"function"},{"inputs":[{"internalType":"uint256","name":"_postID","type":"uint256"}],"name":"readPost","outputs":[{"internalType":"uint256","name":"","type":"uint256"},{"internalType":"string","name":"","type":"string"},{"internalType":"string","name":"","type":"string"},{"internalType":"string","name":"","type":"string"}],"stateMutability":"view","type":"function"},{"inputs":[],"name":"smePostCount","outputs":[{"internalType":"uint256","name":"","type":"uint256"}],"stateMutability":"view","type":"function"},{"inputs":[{"internalType":"uint256","name":"","type":"uint256"}],"name":"smePosts","outputs":[{"internalType":"uint256","name":"postID","type":"uint256"},{"internalType":"string","name":"bID","type":"string"},{"internalType":"string","name":"postContent","type":"string"},{"internalType":"string","name":"contact","type":"string"}],"stateMutability":"view","type":"function"}]'
# # smeRegABI = '[{"anonymous":false,"inputs":[{"indexed":false,"internalType":"string","name":"businessID","type":"string"},{"indexed":false,"internalType":"address","name":"smeAddress","type":"address"}],"name":"SMERegistered","type":"event"},{"inputs":[{"internalType":"string","name":"_businessID","type":"string"}],"name":"getSMEData","outputs":[{"internalType":"string","name":"","type":"string"},{"internalType":"uint256","name":"","type":"uint256"},{"internalType":"string","name":"","type":"string"},{"internalType":"string","name":"","type":"string"},{"internalType":"address","name":"","type":"address"}],"stateMutability":"view","type":"function"},{"inputs":[{"internalType":"string","name":"_businessID","type":"string"}],"name":"getSMEPrivateKey","outputs":[{"internalType":"bytes","name":"","type":"bytes"}],"stateMutability":"view","type":"function"},{"inputs":[{"internalType":"string","name":"_businessID","type":"string"},{"internalType":"uint256","name":"_creditScore","type":"uint256"},{"internalType":"string","name":"_companyName","type":"string"},{"internalType":"string","name":"_contactDetails","type":"string"}],"name":"registerSME","outputs":[],"stateMutability":"nonpayable","type":"function"},{"inputs":[{"internalType":"string","name":"","type":"string"}],"name":"smeRegistry","outputs":[{"internalType":"string","name":"businessID","type":"string"},{"internalType":"uint256","name":"creditScore","type":"uint256"},{"internalType":"string","name":"companyName","type":"string"},{"internalType":"string","name":"contactDetails","type":"string"},{"internalType":"address","name":"smeAddress","type":"address"}],"stateMutability":"view","type":"function"}]'
#
# # CA_list = [etherCA, financierCA, smeCA, smeRegCA]
# # ABI_list = [etherABI, financierABI, smeABI, smeRegABI]
#
# # for i in range(4):
# #     print(CA_list[i])
# #     currCA = CA_list[i]
# #     currABI = ABI_list[i]
# #
# #     currContract = w3.eth.contract(address=currCA, abi=currABI)
# #
# #     for fn in currContract.functions:
# #         print(fn)
#
# smeContract = w3.eth.contract(address=smeCA, abi=smeABI)
#
# smeContract.functions.createPost("ABCDE", "CONTENT", "CONTACT").transact({'from': caller})
#
# # Get the most recent block number
# latest_block_number = w3.eth.block_number
#
# print(latest_block_number)
#
# out = smeContract.functions.readPost(latest_block_number).call()
#
# print(out)

import web3
from web3 import Web3, HTTPProvider

w3 = Web3(HTTPProvider('http://127.0.0.1:7545'))

caller = "0xd268C2504B16a520cb5A87468CcA42B46a81CAb1"


"""

FIN POST TEST

finCA = "0x2F5bF803D9681842D08d39Cc029f9d1D7B25A431"
finABI = '[{"inputs":[{"internalType":"string","name":"_bid","type":"string"},{"internalType":"string","name":"_content","type":"string"},{"internalType":"string","name":"_contact","type":"string"}],"name":"createPost","outputs":[],"stateMutability":"nonpayable","type":"function"},{"inputs":[],"name":"financierPostCount","outputs":[{"internalType":"uint256","name":"","type":"uint256"}],"stateMutability":"view","type":"function"},{"inputs":[{"internalType":"uint256","name":"","type":"uint256"}],"name":"financierPosts","outputs":[{"internalType":"uint256","name":"postID","type":"uint256"},{"internalType":"string","name":"bID","type":"string"},{"internalType":"string","name":"postContent","type":"string"},{"internalType":"string","name":"contact","type":"string"}],"stateMutability":"view","type":"function"},{"inputs":[{"internalType":"uint256","name":"_postID","type":"uint256"}],"name":"readPost","outputs":[{"internalType":"uint256","name":"","type":"uint256"},{"internalType":"string","name":"","type":"string"},{"internalType":"string","name":"","type":"string"},{"internalType":"string","name":"","type":"string"}],"stateMutability":"view","type":"function"}]'

finContract = w3.eth.contract(address=finCA, abi=finABI)

#Create a post
finContract.functions.createPost("ABCDE", "CONTENT", "CONTACT").transact({'from': caller})

# Get the most recent post ID
latest_post_id = finContract.functions.financierPostCount().call()

print("Latest post ID:", latest_post_id)

# Read the latest post
post_data = finContract.functions.readPost(latest_post_id).call()

print("Post Data:", post_data)
"""






"""
SME POST TEST

smeCA = "0x7B0FEbAf56641BEAedF761A12EfFF1eC924cedeF"
smeABI = '[{"inputs":[{"internalType":"string","name":"_bid","type":"string"},{"internalType":"string","name":"_content","type":"string"},{"internalType":"string","name":"_contact","type":"string"}],"name":"createPost","outputs":[],"stateMutability":"nonpayable","type":"function"},{"inputs":[{"internalType":"uint256","name":"_postID","type":"uint256"}],"name":"readPost","outputs":[{"internalType":"uint256","name":"","type":"uint256"},{"internalType":"string","name":"","type":"string"},{"internalType":"string","name":"","type":"string"},{"internalType":"string","name":"","type":"string"}],"stateMutability":"view","type":"function"},{"inputs":[],"name":"smePostCount","outputs":[{"internalType":"uint256","name":"","type":"uint256"}],"stateMutability":"view","type":"function"},{"inputs":[{"internalType":"uint256","name":"","type":"uint256"}],"name":"smePosts","outputs":[{"internalType":"uint256","name":"postID","type":"uint256"},{"internalType":"string","name":"bID","type":"string"},{"internalType":"string","name":"postContent","type":"string"},{"internalType":"string","name":"contact","type":"string"}],"stateMutability":"view","type":"function"}]'

smeContract = w3.eth.contract(address=smeCA, abi=smeABI)

# Create a post
smeContract.functions.createPost("ABCDE", "CONTENT", "CONTACT").transact({'from': caller})

# Get the most recent post ID
latest_post_id = smeContract.functions.smePostCount().call()

print("Latest post ID:", latest_post_id)

# Read the latest post
post_data = smeContract.functions.readPost(latest_post_id).call()

print("Post Data:", post_data)

"""