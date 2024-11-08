import os
import sys

# set directory names
git_dir = 'E:\\GitHub'
root_dir = os.path.join(git_dir, 'CzechTutor')
scripts_dir = os.path.join(root_dir, 'scripts')
utilities_dir = os.path.join(scripts_dir, 'utilities')
gui_dir = os.path.join(scripts_dir, 'gui')
gui_widgets_dir = os.path.join(gui_dir, 'widgets')
data_dir = os.path.join(root_dir, 'data')
images_dir = os.path.join(data_dir, 'images')
# set file names
vocab_fpath = os.path.join(data_dir, 'vocab.xlsx')
# set available topics
topics = ['Animals', 'Clothes', 'City', 'Countryside', 'Drinks', 'Food', 'House', 'Hobbies', 'People', 'Numbers', 'School']
# set free-images url
url = 'https://free-images.com'
# combine all directories into one list
dir_list = [git_dir, root_dir, scripts_dir, utilities_dir, data_dir, images_dir, gui_dir, gui_widgets_dir]
for dir in dir_list:
    sys.path.append(dir)