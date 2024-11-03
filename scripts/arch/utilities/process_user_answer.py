from scripts.utilities.check_user_input import check_user_input

def process_user_answer(quest_dict):
    """"""
    valid_options = quest_dict['valid_options']
    valid_answer = 0
    while valid_answer == 0:
        # ask for user input
        answer = input('Answer: ')
        # remove leading and trailling white space from answer
        answer = answer.strip()
        # verify user input
        user_input, return_code = check_user_input(answer, valid_options)
        if return_code == 1:
            print('Invalid answer selected')
        else:
            valid_answer = 1
    quest_dict['answer'] = answer
    return quest_dict