def grade_answer(quest_dict, topic_dict):
    """
    """
    # extract records from question dictionary
    options = quest_dict['options']
    form = quest_dict['form']
    answer = quest_dict['answer']
    english = quest_dict['english']
    total = topic_dict['total']
    # extract english translation of sample
    czech = options.loc[options['target'] == 1, form].iloc[0]
    # retrieve the english translation of the answer
    answer_english = options.loc[options[form] == answer, 'English'].iloc[0]
    # retrieve answer wikitionary link
    answer_wiktionary = options.loc[options[form] == answer, 'Wiktionary'].iloc[0]
    # determine if the correct answer was chosen
    result = 1 if answer_english == english else 0
    # update running total of game
    topic_dict['total'] = total + result
    # generate feedback response
    response = 'Correct' if result == 1 else f"Incorrect, the correct answer was {czech}"
    # assign to dictionary
    quest_dict['result'] = result
    quest_dict['response'] = response
    quest_dict['total'] = total
    print(response)
    return quest_dict, topic_dict