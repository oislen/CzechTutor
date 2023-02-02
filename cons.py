import os

# set directory names
git_dir = 'E:\\GitHub'
root_dir = os.path.join(git_dir, 'CzechTutor')
scripts_dir = os.path.join(root_dir, 'scripts')
data_dir = os.path.join(root_dir, 'data')
images_dir = os.path.join(data_dir, 'images')
# set file names
vocab_fpath = os.path.join(data_dir, 'vocab.xlsx')
# set available topics
#topics = ['Animals', 'Countryside', 'City', 'Drinks', 'Food', 'House', 'Hobbies', 'People', 'School']
topics = ['Animals']
# set free-images url
url = 'https://free-images.com'