import os
import sys

# set directory names
git_dir = 'E:\\GitHub'
root_dir = os.path.join(git_dir, 'CzechTutor')
scripts_dir = os.path.join(root_dir, 'scripts')
utilities_dir = os.path.join(root_dir, 'utilities')
data_dir = os.path.join(root_dir, 'data')
images_dir = os.path.join(data_dir, 'images')
# set file names
vocab_fpath = os.path.join(data_dir, 'vocab.xlsx')
# set available topics
topics = ['Animals', 'City', 'Countryside', 'Drinks', 'Food']
# set free-images url
url = 'https://free-images.com'
# combine all directories into one list
dir_list = [git_dir, root_dir, scripts_dir, utilities_dir, data_dir, images_dir]
for dir in dir_list:
    sys.path.append(dir)