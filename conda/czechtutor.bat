:: create and activate new environment
call conda env remove --name czechtutor
call conda create --name czechtutor python --yes
call conda activate czechtutor

:: update conda version
call conda update -n base conda --yes

:: install required webscraping packages
call conda install -c conda-forge requests --yes
call conda install -c conda-forge beautifulsoup4 --yes

:: install required data processing packages
call conda install -c conda-forge numpy --yes
call conda install -c conda-forge pandas --yes
call conda install -c conda-forge openpyxl --yes

:: install required visualisation packages
call conda install -c conda-forge matplotlib --yes

:: install tkinter for gui
call conda install -c conda-forge tk --yes

:: install jupyterlab
call conda install -c conda-forge jupyterlab --yes

:: deactivate new environment
call conda deactivate