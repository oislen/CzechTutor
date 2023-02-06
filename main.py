import pandas as pd
# load in scripts
import cons
from scripts.select_topic import select_topic
from scripts.question_pipeline import question_pipeline

def main(run = True, n_questions = 10, n_options = 4):
    """
    """
    while run:
        # select vocabulary topic
        topic = select_topic()
        # if topic is not exit
        if topic != 'Exit':
            # create topic_dict
            topic_dict = {}
            # set total to zero, and feedback string to ''
            topic_dict['total'] = 0
            # read in Czech vocabulary data
            data = pd.read_excel(cons.vocab_fpath, sheet_name=topic)
            # loop over each question
            for idx in range(n_questions):
                # create a dictionary to contain question data
                quest_dict = {'n_options':n_options, 'data':data}
                # run question pipeline
                quest_dict, topic_dict = question_pipeline(quest_dict, topic_dict)
                # assign question dictionary to topic dictionary
                topic_dict[f'quest_dict_{idx}'] = quest_dict
            # TODO: add function to generate topic feedback here
            # print results
            print(f'{topic_dict["total"]} out of {n_questions} questions correct')
        # otherwise
        else:
            # set run to False, and exit programme
            run = False
    return 0


