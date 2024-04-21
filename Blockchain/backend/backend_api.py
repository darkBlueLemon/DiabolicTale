from flask import Flask, request, jsonify
import web3
from web3 import Web3, HTTPProvider

app = Flask(__name__)

w3 = Web3(HTTPProvider('http://127.0.0.1:7545'))

caller = "0xd268C2504B16a520cb5A87468CcA42B46a81CAb1"

smeCA = "0x6de746E43E6669B13178598bdd9fcB1aa59804A1"
smeABI = '[{"inputs":[{"internalType":"string","name":"_bid","type":"string"},{"internalType":"string","name":"_content","type":"string"},{"internalType":"string","name":"_contact","type":"string"}],"name":"createPost","outputs":[],"stateMutability":"nonpayable","type":"function"},{"inputs":[{"internalType":"uint256","name":"_postID","type":"uint256"}],"name":"readPost","outputs":[{"internalType":"uint256","name":"","type":"uint256"},{"internalType":"string","name":"","type":"string"},{"internalType":"string","name":"","type":"string"},{"internalType":"string","name":"","type":"string"}],"stateMutability":"view","type":"function"},{"inputs":[],"name":"smePostCount","outputs":[{"internalType":"uint256","name":"","type":"uint256"}],"stateMutability":"view","type":"function"},{"inputs":[{"internalType":"uint256","name":"","type":"uint256"}],"name":"smePosts","outputs":[{"internalType":"uint256","name":"postID","type":"uint256"},{"internalType":"string","name":"bID","type":"string"},{"internalType":"string","name":"postContent","type":"string"},{"internalType":"string","name":"contact","type":"string"}],"stateMutability":"view","type":"function"}]'

smeContract = w3.eth.contract(address=smeCA, abi=smeABI)

finCA = "0x2bC4d89d6e6eBa95d6d64015Be2fD8B238aAF09C"
finABI = '[{"inputs":[{"internalType":"string","name":"_bid","type":"string"},{"internalType":"string","name":"_content","type":"string"},{"internalType":"string","name":"_contact","type":"string"}],"name":"createPost","outputs":[],"stateMutability":"nonpayable","type":"function"},{"inputs":[],"name":"financierPostCount","outputs":[{"internalType":"uint256","name":"","type":"uint256"}],"stateMutability":"view","type":"function"},{"inputs":[{"internalType":"uint256","name":"","type":"uint256"}],"name":"financierPosts","outputs":[{"internalType":"uint256","name":"postID","type":"uint256"},{"internalType":"string","name":"bID","type":"string"},{"internalType":"string","name":"postContent","type":"string"},{"internalType":"string","name":"contact","type":"string"}],"stateMutability":"view","type":"function"},{"inputs":[{"internalType":"uint256","name":"_postID","type":"uint256"}],"name":"readPost","outputs":[{"internalType":"uint256","name":"","type":"uint256"},{"internalType":"string","name":"","type":"string"},{"internalType":"string","name":"","type":"string"},{"internalType":"string","name":"","type":"string"}],"stateMutability":"view","type":"function"}]'

finContract = w3.eth.contract(address=finCA, abi=finABI)

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
smeregCA='0x038881c6E6242F5E3705272C4af7Fd1ED7adBA51'
smeregAbi='[{"anonymous":false,"inputs":[{"indexed":false,"internalType":"string","name":"businessID","type":"string"},{"indexed":false,"internalType":"address","name":"smeAddress","type":"address"}],"name":"SMERegistered","type":"event"},{"inputs":[{"internalType":"string","name":"_businessID","type":"string"}],"name":"getSMEData","outputs":[{"internalType":"string","name":"","type":"string"},{"internalType":"uint256","name":"","type":"uint256"},{"internalType":"string","name":"","type":"string"},{"internalType":"string","name":"","type":"string"},{"internalType":"address","name":"","type":"address"}],"stateMutability":"view","type":"function"},{"inputs":[{"internalType":"string","name":"_businessID","type":"string"}],"name":"getSMEPrivateKey","outputs":[{"internalType":"bytes","name":"","type":"bytes"}],"stateMutability":"view","type":"function"},{"inputs":[{"internalType":"string","name":"_businessID","type":"string"},{"internalType":"uint256","name":"_creditScore","type":"uint256"},{"internalType":"string","name":"_companyName","type":"string"},{"internalType":"string","name":"_contactDetails","type":"string"}],"name":"registerSME","outputs":[],"stateMutability":"nonpayable","type":"function"},{"inputs":[{"internalType":"string","name":"","type":"string"}],"name":"smeRegistry","outputs":[{"internalType":"string","name":"businessID","type":"string"},{"internalType":"uint256","name":"creditScore","type":"uint256"},{"internalType":"string","name":"companyName","type":"string"},{"internalType":"string","name":"contactDetails","type":"string"},{"internalType":"address","name":"smeAddress","type":"address"}],"stateMutability":"view","type":"function"}]'
smeregContract=w3.eth.contract(address=smeregCA, abi=smeABI)
authCA = "0xA2cecA3D7cC7d21c0B14186d476fB43b65893B8e"
authAbi = (
    '[{"anonymous":false,"inputs":[{"indexed":true,"internalType":"address","name":"userAddress","type":"address"},{"indexed":false,"internalType":"string","name":"username","type":"string"},{"indexed":false,"internalType":"string","name":"email","type":"string"}],"name":"UserRegistered","type":"event"},{"inputs":[{"internalType":"address","name":"_userAddress","type":"address"},{"internalType":"address","name":"_walletAddress","type":"address"}],"name":"assignWalletAddress","outputs":[],"stateMutability":"nonpayable","type":"function"},{"inputs":[{"internalType":"string","name":"_password","type":"string"}],"name":"hashPassword","outputs":[{"internalType":"bytes32","name":"","type":"bytes32"}],"stateMutability":"pure","type":"function"},{"inputs":[{"internalType":"string","name":"_email","type":"string"},{"internalType":"string","name":"_password","type":"string"}],"name":"signin","outputs":[{"internalType":"bool","name":"","type":"bool"}],"stateMutability":"view","type":"function"},{"inputs":[{"internalType":"string","name":"_username","type":"string"},{"internalType":"string","name":"_email","type":"string"},{"internalType":"string","name":"_password","type":"string"}],"name":"signup","outputs":[],"stateMutability":"nonpayable","type":"function"},{"inputs":[{"internalType":"address","name":"","type":"address"}],"name":"users","outputs":[{"internalType":"string","name":"username","type":"string"},{"internalType":"string","name":"email","type":"string"},{"internalType":"bytes32","name":"hashedPassword","type":"bytes32"},{"internalType":"address","name":"walletAddress","type":"address"}],"stateMutability":"view","type":"function"}]')
AuthContract = w3.eth.contract(address=authCA, abi=authAbi)

buysellCA = '0xd5F2e3f660272aA9070973fD9f0D4F845ed0668E'
buysellAbi = '[{"inputs":[{"internalType":"contract SMERegistration","name":"_smeRegistration","type":"address"},{"internalType":"contract FinanceRegistration","name":"_financeRegistration","type":"address"}],"stateMutability":"nonpayable","type":"constructor"},{"anonymous":false,"inputs":[{"indexed":false,"internalType":"uint256","name":"transactionKey","type":"uint256"},{"indexed":false,"internalType":"uint256","name":"buyerBid","type":"uint256"},{"indexed":false,"internalType":"uint256","name":"sellerBid","type":"uint256"},{"indexed":false,"internalType":"uint256","name":"timestamp","type":"uint256"},{"indexed":false,"internalType":"string","name":"termsAndConditions","type":"string"}],"name":"TransactionCreated","type":"event"},{"inputs":[{"internalType":"uint256","name":"_buyerBid","type":"uint256"},{"internalType":"uint256","name":"_sellerBid","type":"uint256"},{"internalType":"string","name":"_termsAndConditions","type":"string"}],"name":"createTransaction","outputs":[],"stateMutability":"nonpayable","type":"function"},{"inputs":[],"name":"financeRegistration","outputs":[{"internalType":"contract FinanceRegistration","name":"","type":"address"}],"stateMutability":"view","type":"function"},{"inputs":[],"name":"smeRegistration","outputs":[{"internalType":"contract SMERegistration","name":"","type":"address"}],"stateMutability":"view","type":"function"},{"inputs":[],"name":"transactionCount","outputs":[{"internalType":"uint256","name":"","type":"uint256"}],"stateMutability":"view","type":"function"},{"inputs":[{"internalType":"uint256","name":"","type":"uint256"}],"name":"transactions","outputs":[{"internalType":"uint256","name":"transactionKey","type":"uint256"},{"internalType":"uint256","name":"buyerBid","type":"uint256"},{"internalType":"uint256","name":"sellerBid","type":"uint256"},{"internalType":"uint256","name":"timestamp","type":"uint256"},{"internalType":"string","name":"termsAndConditions","type":"string"}],"stateMutability":"view","type":"function"}]'
buysellContract = w3.eth.contract(address=buysellCA, abi=buysellAbi)

fregCA='0x573D9F228c9e87A105a740C4AA299c28aC8cd5AB'
fregAbi='[{"anonymous":false,"inputs":[{"indexed":false,"internalType":"string","name":"businessID","type":"string"},{"indexed":false,"internalType":"address","name":"smeAddress","type":"address"}],"name":"SMERegistered","type":"event"},{"inputs":[{"internalType":"string","name":"_businessID","type":"string"}],"name":"getSMEData","outputs":[{"internalType":"string","name":"","type":"string"},{"internalType":"string","name":"","type":"string"},{"internalType":"string","name":"","type":"string"},{"internalType":"address","name":"","type":"address"}],"stateMutability":"view","type":"function"},{"inputs":[{"internalType":"string","name":"_businessID","type":"string"}],"name":"getSMEPrivateKey","outputs":[{"internalType":"bytes","name":"","type":"bytes"}],"stateMutability":"view","type":"function"},{"inputs":[{"internalType":"string","name":"_businessID","type":"string"},{"internalType":"string","name":"_companyName","type":"string"},{"internalType":"string","name":"_contactDetails","type":"string"}],"name":"registerSME","outputs":[],"stateMutability":"nonpayable","type":"function"},{"inputs":[{"internalType":"bytes32","name":"","type":"bytes32"}],"name":"smeRegistry","outputs":[{"internalType":"string","name":"businessID","type":"string"},{"internalType":"string","name":"companyName","type":"string"},{"internalType":"string","name":"contactDetails","type":"string"},{"internalType":"address","name":"smeAddress","type":"address"}],"stateMutability":"view","type":"function"}]'
fregContract = w3.eth.contract(address=fregCA, abi=fregAbi)

@app.route('/freg', methods=['POST'])
def freg():
    bid=request.args.get('businessID')
    companyName=request.args.get('companyName')
    contactDetails=request.args.get('contactDetails')
    fregContract.functions.registerSME(bid,companyName,contactDetails).transact({'from': caller})

@app.route('/finGetDetails', methods=['GET'])
def finGetDetails():
    bid=request.args.get('businessID')
    details=fregContract.functions.getSMEData.call()
    return {  "PID": details[0],  "BID": details[1],  "contact": details[2],"finadd": details[3]}

@app.route('/smereg', methods=['POST'])
def smereg():
    bid=request.args.get('businessID')
    companyName=request.args.get('companyName')
    contactDetails=request.args.get('contactDetails')
    smeregContract.functions.registerSME(bid,companyName,contactDetails).transact({'from': caller})

@app.route('/smeGetDetails', methods=['GET'])
def smeGetDetails():
    bid=request.args.get('businessID')
    details=smeregContract.functions.getSMEData.call()
    return {  "PID": details[0],  "BID": details[1],  "contact": details[2],"finadd": details[3]}




@app.route('/lastid', methods=['GET'])
def id():
    fincount = int(finContract.functions.financierPostCount().call())
    semcount = int(smeContract.functions.smePostCount().call())
    return jsonify({'financier_post_count': fincount, 'sme_post_count': semcount})


#FINISHED
#

@app.route('/signup', methods=['POST'])
def signup():
    username = request.args.get('username')
    email = request.args.get('email')
    password = request.args.get('password')
    AuthContract.functions.signup(username, email, password).transact({'from': caller})
    # AuthContract.functions.assignWalletAddress("xyz", "mno").transact({'from': caller})
    return True


@app.route('/signin', methods=['POST'])
def signin():
    email = request.args.get('email')
    password = request.args.get('password')
    return AuthContract.functions.signin(email, password).transact({'from': caller})


@app.route('/createTransaction', methods=['POST'])
def createTransaction():
    buyerbid = int(request.args.get('buyerbid'))
    sellerbid = int(request.args.get('sellerbid'))
    termsandconditions = request.args.get('termsandconditions')
    buysellContract.functions.createTransaction(buyerbid, sellerbid, termsandconditions).transact({'from': caller})
    return "Transaction Created"

@app.route('/')
def hello():
    return "Hello, World!"


@app.route('/smePutPost', methods=['POST'])
def smeputpost():
    bid = request.args.get('bid')
    content = request.args.get('content')
    contact = request.args.get('contact')
    smeContract.functions.createPost(bid, content, contact).transact({'from': caller})
    return "SME Post created successfully"


@app.route("/smeGetPost", methods=['GET'])
def smegetpost():
    pid = int(request.args.get('pid'))
    post_data = smeContract.functions.readPost(pid).call()
    return {  "PID": post_data[0],  "BID": post_data[1],  "content": post_data[2],  "contact": post_data[3]}


@app.route('/finPutPost', methods=['POST'])
def finputpost():
    bid = request.args.get('bid')
    content = request.args.get('content')
    contact = request.args.get('contact')
    finContract.functions.createPost(bid, content, contact).transact({'from': caller})
    return "Financial Post created successfully"


@app.route("/finGetPost", methods=['GET'])
def fingetpost():
    pid = int(request.args.get('pid'))
    post_data = finContract.functions.readPost(pid).call()
    return {
        "postID": post_data[0],
        "bID": post_data[1],
        "content": post_data[2],
        "contact": post_data[3],
    }


if __name__ == '__main__':
    app.run(debug=True)
