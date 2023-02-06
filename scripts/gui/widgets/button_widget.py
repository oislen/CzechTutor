
# import relevant libraries
import tkinter as tk

   
def button_widget(frame, 
                  text,
                  command,
                  loc, 
                  pad = (10, 10), 
                  sticky = tk.W
                  ):
    
    """
    
    """
    
    # extract out location and padding settings
    row, column = loc[0], loc[1]
    padx, pady = pad[0], pad[1]
    
    # create widget for user feedback
    button_wid = tk.Button (frame, text = text, command = command)
    button_wid.grid(row = row, column = column, padx = padx, pady = pady, sticky = sticky)
    