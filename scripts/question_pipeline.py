# load utility functions
from scripts.utilities.select_form import select_form
from scripts.utilities.pull_data import pull_data
from scripts.utilities.plot_target import plot_target
from scripts.utilities.formulate_question import formulate_question
from scripts.utilities.process_user_answer import process_user_answer
from scripts.utilities.grade_answer import grade_answer

def question_pipeline(quest_dict, total):
    """
    """
    # pull data for the questions
    quest_dict = pull_data(quest_dict)
    # determine form of question
    quest_dict = select_form(quest_dict)
    # plot image of english word
    plot_target(quest_dict)
    # pose question
    quest_dict = formulate_question(quest_dict)
    # process user's answer
    quest_dict = process_user_answer(quest_dict)
    # grade answer
    quest_dict, total = grade_answer(quest_dict, total)  
    return quest_dict, total