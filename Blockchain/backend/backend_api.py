from flask import Flask, request
from web3 import Web3, HTTPProvider

app = Flask(__name__)

w3 = Web3(HTTPProvider('http://127.0.0.1:7545'))

caller = "0xd268C2504B16a520cb5A87468CcA42B46a81CAb1"

smeCA = "0x7B0FEbAf56641BEAedF761A12EfFF1eC924cedeF"
smeABI = '[{"inputs":[{"internalType":"string","name":"_bid","type":"string"},{"internalType":"string","name":"_content","type":"string"},{"internalType":"string","name":"_contact","type":"string"}],"name":"createPost","outputs":[],"stateMutability":"nonpayable","type":"function"},{"inputs":[{"internalType":"uint256","name":"_postID","type":"uint256"}],"name":"readPost","outputs":[{"internalType":"uint256","name":"","type":"uint256"},{"internalType":"string","name":"","type":"string"},{"internalType":"string","name":"","type":"string"},{"internalType":"string","name":"","type":"string"}],"stateMutability":"view","type":"function"},{"inputs":[],"name":"smePostCount","outputs":[{"internalType":"uint256","name":"","type":"uint256"}],"stateMutability":"view","type":"function"},{"inputs":[{"internalType":"uint256","name":"","type":"uint256"}],"name":"smePosts","outputs":[{"internalType":"uint256","name":"postID","type":"uint256"},{"internalType":"string","name":"bID","type":"string"},{"internalType":"string","name":"postContent","type":"string"},{"internalType":"string","name":"contact","type":"string"}],"stateMutability":"view","type":"function"}]'

smeContract = w3.eth.contract(address=smeCA, abi=smeABI)

finCA = "0x2F5bF803D9681842D08d39Cc029f9d1D7B25A431"
finABI = '[{"inputs":[{"internalType":"string","name":"_bid","type":"string"},{"internalType":"string","name":"_content","type":"string"},{"internalType":"string","name":"_contact","type":"string"}],"name":"createPost","outputs":[],"stateMutability":"nonpayable","type":"function"},{"inputs":[],"name":"financierPostCount","outputs":[{"internalType":"uint256","name":"","type":"uint256"}],"stateMutability":"view","type":"function"},{"inputs":[{"internalType":"uint256","name":"","type":"uint256"}],"name":"financierPosts","outputs":[{"internalType":"uint256","name":"postID","type":"uint256"},{"internalType":"string","name":"bID","type":"string"},{"internalType":"string","name":"postContent","type":"string"},{"internalType":"string","name":"contact","type":"string"}],"stateMutability":"view","type":"function"},{"inputs":[{"internalType":"uint256","name":"_postID","type":"uint256"}],"name":"readPost","outputs":[{"internalType":"uint256","name":"","type":"uint256"},{"internalType":"string","name":"","type":"string"},{"internalType":"string","name":"","type":"string"},{"internalType":"string","name":"","type":"string"}],"stateMutability":"view","type":"function"}]'

finContract = w3.eth.contract(address=finCA, abi=finABI)

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
    return post_data

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
    print(post_data)
    return {
        "postID": post_data[0],
        "bID": post_data[1],
        "content": post_data[2],
        "contact": post_data[3],
    }

if __name__ == '__main__':
    app.run(debug=True)
