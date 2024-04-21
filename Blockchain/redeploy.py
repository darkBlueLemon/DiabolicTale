import os
import subprocess

# solcjs --abi --bin -o .\abiBinOut\ .\contracts\smeReg.sol

# Directory containing the files
directory = 'Blockchain\contracts'

# List all visible files in the directory
files = [f for f in os.listdir(directory) if os.path.isfile(os.path.join(directory, f)) and not f.startswith('.')]

# Command to run for each file
  # Replace 'your_command' with your actual command

# Iterate over each file and run the command
for file in files:
    # Run the command for each file

    command = "solcjs --abi --bin -o .\abiBinOut\ {}"

    command = "solcjs --abi --bin -o .\abiBinOut\ {}"

    subprocess.run(command.format(os.path.join(directory, file)), shell=True)
