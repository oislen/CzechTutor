
# import relevant libraries
import tkinter as tk


def bool_widget(frame,
                default, 
                loc,
                pad = (10, 10), 
                sticky = tk.W
                ):
    
    """
    
    Boolean Widget Documentation
    
    Overview
    
    This function creates a boolean widget within a specified frame.
    
    Parameters
    
    frame - tk.Frame, the tkinter frame to assign the label widget to
    default - Boolean, the default option for the boolean widget
    loc - tuple, the location to place the boolean widget in the specified tkinter frame
    pad - tuple, the amount of padding to apply around the boolean widget, default is (10, 10)
    sticky - tk, how to align the boolean widget within the specified tkinter frame, default is tk.W
    
    Returns
    
    bool_var - String, the user specified boolean choice
    
    Example
    
    bool_wid(frame = frame, 
             default = True,
             loc = (1, 0), 
             pad = (10, 10), 
             sticky = tk.W
             )
           
    """
    
    # extract out location and padding settings
    row, column = loc[0], loc[1]
    padx, pady = pad[0], pad[1]
    
    # create options variable with defaults
    bool_var = tk.BooleanVar()
    bool_var.set(default)
    
    # create widget for user feedback
    bool_wid = tk.Checkbutton(frame, text = '', var = bool_var)
    bool_wid.grid(row = row, column = column, padx = padx, pady = pady, sticky = sticky)
    
    return bool_var