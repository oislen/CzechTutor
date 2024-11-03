import cons
from scripts.utilities.check_user_input import check_user_input

def select_topic():
    # set parameter for topic selected
    topic_selected = 0
    # while no topic has been selected
    while topic_selected == 0:
        # format topics into bullet points
        topics_str = ' '.join([f'* {topic} \n' for topic in cons.topics])
        # ask for user input
        topic = input(f'Select a Topic: \n\n {topics_str} \n Or Exit \n \n').strip()
        # verify user input
        user_input, return_code = check_user_input(topic, cons.topics + ['Exit'])
        # if a valid topic has been selected
        if return_code == 0:
            # update topic selected parameter
            topic_selected = 1
            # print feedback
            print(f'Topic Selected: {topic}')
    return topic