"""
Created on Sun Jan 10 09:21:05 2021
@author: oislen
"""

# import relevant libraries
import tkinter as tk
import cons as cons
#from scripts.gui.widgets.label_widget import label_widget
#from scripts.gui.widgets.radiobutton_widget import radiobutton_widget
from scripts.gui.noun_topic import noun_topic
#from scripts.gui.NewWindow import openNewWindow

def tkinter_interface():
    
    """
    
    """

    # create root object to contain interface
    root = tk.Tk()
    
    # configure root object
    # disable window maximise button
    # name root object
    root.title("CzechTutor")
    root.resizable(0,0)
    # set background colour to black
    root.configure(background='black')
    
    #-- Canvas --#
    
    # create canvas  object
    canvas = tk.Canvas(root, bg = 'black')
    canvas.pack(side = tk.LEFT)
    
    # create a verticle scrollbar
    scrollbar = tk.Scrollbar(root, orient="vertical", command=canvas.yview)
    scrollbar.pack(fill = "y", side = tk.LEFT)
    
    # configure the vertical scroll bar with the canvas object
    canvas.configure(yscrollcommand = scrollbar.set)
    def on_configure(event): canvas.configure(scrollregion=canvas.bbox('all'))
    canvas.bind('<Configure>', on_configure)
    
    #-- Frame --#
    
    # create a frame object to hold the user widgets
    frame = tk.Frame(canvas, bg = 'black')
    canvas.create_window((0,0), window=frame, anchor='nw')
    
    # extract user inputs
    topic, new_frame = noun_topic(frame = frame)


    # a button widget which will
    # open a new window on button click
    #btn = tk.Button(root, text ="Click to open a new window")
    
    # Following line will bind click event
    # On any click left / right button
    # of mouse a new window will be opened
    #btn.bind("<Button>", lambda e: NewWindow(root))
    #btn.pack(pady = 10)

    # run gui within infinite loop
    frame.mainloop()
    
    return topic

if __name__ == '__main__':
    topic = tkinter_interface()
    print('topic:',topic)