import cons
from scripts.gui.NewWindow import openNewWindow
from scripts.gui.widgets.label_widget import label_widget
from scripts.gui.widgets.radiobutton_widget import radiobutton_widget
from scripts.gui.widgets.button_widget import button_widget


def noun_topic(frame):

    """
    """

    # start point label
    label_widget(frame = frame, 
                 text = "Select Topic:", 
                 loc = (2, 0)
                 )
    
    # start point menu
    topic = radiobutton_widget(options = cons.topics, 
                               default_idx = 0,
                               frame = frame, 
                               loc = (3, 0)
                               )
    
    # extract user inputs
    topic = topic.get()

    new_frame = button_widget(frame = frame, text = 'Go', command = lambda frame: openNewWindow(input_frame = frame), loc = (15, 0))

    return topic, new_frame