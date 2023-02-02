import cons
import pandas as pd

from scripts.utilities.plot_english import plot_english

from scripts.pull_data import pull_data
from scripts.select_topic import select_topic
from scripts.select_form import select_form
from scripts.formulate_question import formulate_question
from scripts.process_user_answer import process_user_answer
from scripts.grade_answer import grade_answer

def main(run = True, questions = 10):
    """
    """
    while run:
        # select vocabulary topic
        topic = select_topic()
        # set total to zero
        total = 0
        # read in Czech vocabulary data
        data = pd.read_excel(cons.vocab_fpath, sheet_name=topic)
        # loop over each question
        for i in range(questions):
            # pull data for the questions
            target_sample, outside_samples, options = pull_data(data)
            # extract english translation of sample
            english = target_sample['English'].iloc[0]
            # determine form of question
            form = select_form()
            # plot image of english word
            plot_english(target_sample['Image'].iloc[0])
            # pose question
            valid_options = formulate_question(english, form, options)
            # process user's answer
            answer = process_user_answer(valid_options)
            # grade answer
            total = grade_answer(answer, target_sample, form, options, english, total)  
        # print results
        print(f'{total} out of {questions} questions correct')
    return 0


