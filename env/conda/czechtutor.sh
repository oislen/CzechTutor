# create and activate new environment
conda env remove --name czechtutor
conda create --name czechtutor python --yes
conda activate czechtutor

# update conda version
conda update -n base conda --yes

# install required webscraping packages
conda install -c conda-forge requests --yes
conda install -c conda-forge beautifulsoup4 --yes

# install required data processing packages
conda install -c conda-forge numpy --yes
conda install -c conda-forge pandas --yes
conda install -c conda-forge openpyxl --yes

# install required visualisation packages
conda install -c conda-forge matplotlib --yes

# install tkinter for gui
conda install -c conda-forge tk --yes

# install jupyterlab
conda install -c conda-forge jupyterlab --yes

# deactivate new environment
conda deactivate